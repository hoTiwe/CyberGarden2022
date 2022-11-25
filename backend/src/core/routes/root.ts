import { Router } from "express";
import authRouter from "./auth/auth.router"
import hobbieRouter from "./hobbies/hobbe.router"

const router = Router()

router.use('/auth', authRouter)
router.use('/hobbie', hobbieRouter)
router.get('/', (req, res) => {
    res.send('hello, world!')
})

export default router