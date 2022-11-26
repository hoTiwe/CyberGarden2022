import { Router } from "express";
import HobbieController from "../../controllers/hobbie.controller";
import ProfessionController from "../../controllers/profession.controller";
const router = Router()

const hobbieController = new HobbieController()
const professionController = new ProfessionController()

router.get('/hobbies', hobbieController.getAll)
router.get('/professions', professionController.getAll)

export default router