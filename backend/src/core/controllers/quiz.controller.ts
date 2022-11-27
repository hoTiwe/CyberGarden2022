import { NextFunction, Request, Response } from "express"
import { Repository, In, Not } from "typeorm"
import dataSource from "../../db"
import ProfessionModel from "../models/profession.model"
import UserModel from "../models/user.model"

class QuizController {
    private users: Repository<UserModel>
    private professions: Repository<ProfessionModel>

    constructor() {
        this.users = dataSource.getRepository(UserModel)
        this.professions = dataSource.getRepository(ProfessionModel)
    }

    getQuiz: ControllerHandler = async (req, res, next) => {
        let excludeUser = req.user?.id ?? 0
        let userArray = await this.users.find({
            relations: {
                profession: true,
                links: true,
                hobbies: true
            },
            where: {
                id: Not(In([excludeUser]))
            }
        })
        let random = userArray[Math.ceil(Math.random() * 100) % userArray.length]
        let profs = <ProfessionModel[]>(await this.professions.query(`select top(3) * from profession_model where id <> ${random.profession.id} order by newid() `))
        profs.push(random.profession)

        res.json({
                question: "Как думаете, какую должность занимает человек на фото?",
                variants: profs.map((v) => ({ id: v.id, value: v.profession })),
                user: random,
            })
    }
}

export default QuizController
