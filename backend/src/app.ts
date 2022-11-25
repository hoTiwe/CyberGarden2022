import express, { Router } from "express"
import "reflect-metadata"
import config from "./config/config"
import dataSource from "./db"
import error from "./middlewares/error"
import bodyParser from "body-parser"
import cors from "cors"

async function bootstrap(root: Router) {
    const app = express()
    await dataSource.initialize()

    app.use(cors())
    app.use(bodyParser.urlencoded({ extended: false }))
    app.use(bodyParser.json())

    app.use(root)
    app.use(error)

    app.listen(config.PORT)
}

export { bootstrap }