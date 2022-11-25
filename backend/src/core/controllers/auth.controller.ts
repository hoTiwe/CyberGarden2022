import { NextFunction, Request, Response } from "express";
import { DataSource, Repository } from "typeorm"
import config from "../../config/config";
import dataSource from "../../db";
import UserModel from "../models/user.model";

class AuthController {

    constructor() {
        
    }

    async login(req: Request, res: Response, next: NextFunction) {
        let repo = dataSource.getRepository(UserModel)
        res.json(await repo.find())
    }

}

export default new AuthController()