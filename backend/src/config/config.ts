import dotenv from "dotenv"

dotenv.config()


const config = {
    PORT: Number(process.env.PORT),
    DB_NAME: String(process.env.DB_NAME),
    DB_USER: String(process.env.DB_USER),
    DB_PASS: String(process.env.DB_PASS),
    DB_HOST: String(process.env.DB_HOST),
    DB_PORT: Number(process.env.DB_PORT)
}

export default config