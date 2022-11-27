import { NextFunction, Request, Response } from "express"
import { Repository } from "typeorm"
import dataSource from "../../db"
import HobbieModel from "../models/hobbie.model"

class HobbieController {
    private hobbies: Repository<HobbieModel>

    constructor() {
        this.hobbies = dataSource.getRepository(HobbieModel)
    }

    getAll: ControllerHandler = async (req, res, next) => {
        const result = await this.hobbies.find()
        res.json(result)
    }
}

export default HobbieController
