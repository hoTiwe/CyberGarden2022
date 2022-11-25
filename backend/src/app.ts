import express, { Router } from "express"
import "reflect-metadata"
import UserModel from "./core/models/user.model"
import dataSource from "./db"


async function bootstrap(root: Router) {
    const app = express()
    await dataSource.initialize()

    app.use(root)

    app.listen(3000)
};

export { bootstrap }