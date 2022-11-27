import { Router } from "express"
import EventController from "../../controllers/event.controller"
const router = Router()

const controller = new EventController()

router.get('/', controller.getAll)
router.get('/id', controller.getById)

export default router