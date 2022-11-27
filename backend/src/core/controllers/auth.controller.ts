import { NextFunction, Request, Response } from "express"
import { Repository, In } from "typeorm"
import dataSource from "../../db"
import ApiError, { StatusCodes } from "../../shared/api.error"
import UserModel from "../models/user.model"
import bcrypt from "bcrypt"
import * as jwt from "../../shared/jwt"
import LinkModel from "../models/link.model"
import HobbieModel from "../models/hobbie.model"
import ProfessionModel from "../models/profession.model"

class AuthController {
    private users: Repository<UserModel>
    private hobbies: Repository<HobbieModel>
    private professions: Repository<ProfessionModel>

    constructor() {
        this.users = dataSource.getRepository(UserModel)
        this.hobbies = dataSource.getRepository(HobbieModel)
        this.professions = dataSource.getRepository(ProfessionModel)
    }

    login = async (req: Request, res: Response, next: NextFunction) => {
        // this method is already guarded with authorized middleware
        res.sendStatus(StatusCodes.OK)
    }

    register = async (req: Request, res: Response, next: NextFunction) => {
        const {
            email,
            password,
            name,
            profession,
            about,
            link_inst,
            link_tg,
            link_vk,
            hobbies,
        } = req.body

        try {
            let user = new UserModel()

            let links = new LinkModel()
            links.inst_link = link_inst.replace(/"/g, '')
            links.tg_link = link_tg.replace(/"/g, '')
            links.vk_limk = link_vk.replace(/"/g, '')
            user.links = links

            user.profession = await this.professions.findOne({
                where: {
                    id: profession,
                },
            })

            user.hobbies = await this.hobbies.find({
                where: {
                    id: In(hobbies),
                },
            })

            user.login = email.replace(/"/g, '')
            user.name = name.replace(/"/g, '')
            user.about = about.replace(/"/g, '')
            user.password = await bcrypt.hash(password, 10)
            user.avatar = req.file.filename
            await this.users.save(user)

            res.json({
                token: jwt.get({
                    id: user.id,
                }),
            })
        } catch (err) {
            return next(err)
        }
    }

    user = (req: Request, res: Response, next: NextFunction) => {
        res.json(req.user)
    }
}

export default AuthController
