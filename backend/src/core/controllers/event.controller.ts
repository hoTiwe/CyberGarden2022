import { NextFunction, Request, Response } from "express"
import { Repository } from "typeorm"
import dataSource from "../../db"
import ApiError, { StatusCodes } from "../../shared/api.error"
import EventModel from "../models/event.model"
import ProfessionModel from "../models/profession.model"
import UserModel from "../models/user.model"

class EventController {
    private events: Repository<EventModel>

    constructor() {
        this.events = dataSource.getRepository(EventModel)
    }

    getAll: ControllerHandler = async (req, res, next) => {
        res.json(await this.events.find())
    }

    getById: ControllerHandler = async (req, res, next) => {
        const id = Number(req.query.id || req.body.id)
        if (!id || isNaN(id)) {
            return next(new ApiError(StatusCodes.BAD_REQUEST, "id is null"))
        }

        res.json(await this.events.findOne({ where: { id } }))
    }
}

export default EventController
