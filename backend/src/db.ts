import "reflect-metadata"
import { DataSource } from "typeorm"
import config from "./config/config"
import UserModel from "./core/models/user.model"

const dataSource = new DataSource({
    type: 'mssql',
    username: config.DB_USER,
    password: config.DB_PASS,
    host: config.DB_HOST,
    port: config.DB_PORT,
    database: config.DB_NAME,
    options: {
        encrypt: false
    },
    entities: [UserModel]
})

export default dataSource