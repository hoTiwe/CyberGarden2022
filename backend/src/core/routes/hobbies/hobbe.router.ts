import { Router } from "express";
import HobbieController from "../../controllers/hobbie.controller";
const router = Router()

const hobbieController = new HobbieController()

router.get('/ass', hobbieController.getAll)

export default router