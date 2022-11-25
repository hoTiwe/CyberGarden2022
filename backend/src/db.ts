import "reflect-metadata"
import { DataSource } from "typeorm"
import config from "./config/config"
import UserModel from "./core/models/user.model"
import InfoModel from "./core/models/info.model"
import HobbieModel from "./core/models/hobbie.model"

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
    entities: [UserModel, InfoModel, HobbieModel],
    synchronize: false
})

export default dataSource