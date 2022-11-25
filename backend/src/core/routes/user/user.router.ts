import { Router } from "express"
import UserController from "../../controllers/user.controller"
import { upload } from "../../../shared/multer"
const router = Router()
const userController = new UserController()

router.post('/upload', upload.single('avatar'), userController.uploadPhoto)

export default router
