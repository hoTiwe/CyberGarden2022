import { bootstrap } from "./src/app"
import root from "./src/core/routes/root"

bootstrap(root)
.then(() => console.log('app has started...'))
.catch(console.error)