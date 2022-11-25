import { NextFunction, Request, Response } from "express"
import { Repository } from "typeorm"
import dataSource from "../../db"
import ApiError, { StatusCodes } from "../../shared/api.error"
import UserModel from "../models/user.model"
import bcrypt from "bcrypt"
import * as jwt from "../../shared/jwt"

class AuthController {
    private users: Repository<UserModel>

    constructor() {
        this.users = dataSource.getRepository(UserModel)
    }

    login = async (req: Request, res: Response, next: NextFunction) => {
        const { login, password } = req.body
        return next(
            new ApiError(StatusCodes.INTERNAL_SERVER_ERROR, "not implemented")
        )
    }

    register = async (req: Request, res: Response, next: NextFunction) => {
        const { login, password, name, surname } = req.body

        if (!login || !password || !name || !surname) {
            return next(new ApiError(StatusCodes.BAD_REQUEST, "invalid body"))
        }

        let user = new UserModel()
        user.login = login
        user.name = name
        user.surname = surname
        user.password = await bcrypt.hash(password, 10)
        await this.users.save(user)

        res.json({
            token: jwt.get({
                id: user.id
            }),
        })
    }

    user = (req: Request, res: Response, next: NextFunction) => {
        res.json(req.user)
    }
}

export default AuthController
