import { Router } from "express";
import authRouter from "./auth/auth.router"
import hobbieRouter from "./hobbies/hobbe.router"
import userRouter from "./user/user.router"

const router = Router()

router.use('/auth', authRouter)
router.use('/hobbie', hobbieRouter)
router.use('/user', userRouter)
router.get('/', (req, res) => {
    res.send('hello, world!')
})

export default router