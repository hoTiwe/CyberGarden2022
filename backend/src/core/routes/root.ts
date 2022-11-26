import { Router } from "express";
import authRouter from "./auth/auth.router"
import sharedRouter from "./shared/shared.router"
import userRouter from "./user/user.router"

const router = Router()

router.use('/auth', authRouter)
router.use('/shared', sharedRouter)
router.use('/users', userRouter)
router.get('/', (req, res) => {
    res.send('hello, world!')
})

export default router