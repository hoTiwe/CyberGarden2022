import "reflect-metadata"
import { DataSource } from "typeorm"
import config from "./config/config"
import EventModel from "./core/models/event.model"
import HobbieModel from "./core/models/hobbie.model"
import LinkModel from "./core/models/link.model"
import ProfessionModel from "./core/models/profession.model"
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
    entities: [UserModel, HobbieModel, ProfessionModel, LinkModel, EventModel],
    synchronize: true
})

export default dataSource