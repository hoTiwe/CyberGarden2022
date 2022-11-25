import { Router } from "express";
import authRouter from "./auth/auth.router"
const router = Router()

router.use('/auth', authRouter)
router.get('/', (req, res) => {
    res.send('hello, world!')
})

export default router