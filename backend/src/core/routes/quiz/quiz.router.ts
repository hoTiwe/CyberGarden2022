import { Router } from "express";
import authorized from "../../../middlewares/authorized";
import QuizController from "../../controllers/quiz.controller";
const router = Router()

const controller = new QuizController()

router.get('/profession', authorized, controller.getQuiz)

export default router
