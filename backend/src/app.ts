import express, { Router } from "express"

async function bootstrap(root: Router) {
    const app = express()

    app.use(root)

    app.listen(3000)
};

export { bootstrap }