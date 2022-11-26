import { NextFunction, Request, Response } from "express"
import { Repository } from "typeorm"
import dataSource from "../../db"
import UserModel from "../models/user.model"

class UserController {
    private users: Repository<UserModel>

    constructor() {
        this.users = dataSource.getRepository(UserModel)
    }

    getAll: ControllerHandler = async (req, res, next) => {
        res.json(await this.users.find({
            relations: {
                hobbies: true,
                links: true, 
                profession: true
            },
        }))
    }
}

export default UserController
