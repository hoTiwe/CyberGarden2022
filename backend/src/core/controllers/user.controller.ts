import { NextFunction, Request, Response } from "express"
import { Repository } from "typeorm"
import dataSource from "../../db"
import UserModel from "../models/user.model"

class UserController {
    private hobbies: Repository<UserModel>

    constructor() {
        this.hobbies = dataSource.getRepository(UserModel)
    }

    uploadPhoto: ControllerHandler = async (req, res, next) => {
        res.json({test: 1})
    }
}

export default UserController
