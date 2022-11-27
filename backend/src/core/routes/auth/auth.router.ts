import { Router } from "express"
import authorized from "../../../middlewares/authorized"
import { upload } from "../../../shared/multer"
import AuthController from "../../controllers/auth.controller"
const router = Router()

const authController = new AuthController()

router.post("/login", authorized, authController.login)
router.post("/register", upload.single("avatar"), authController.register)
router.post("/user", authorized, authController.user)

export default router
