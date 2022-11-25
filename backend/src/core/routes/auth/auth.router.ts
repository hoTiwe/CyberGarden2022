import { Router } from "express";
import dataSource from "../../../db";
import authorized from "../../../middlewares/authorized";
import AuthController from "../../controllers/auth.controller";
import UserModel from "../../models/user.model";
const router = Router()

const controller = new AuthController()

router.post('/register', controller.register)
router.post('/user', authorized, controller.user)

export default router