export {}

type ReqUser = {
    id?: number
}

declare global {
  namespace Express {
    export interface Request {
      user?: ReqUser;
    }
  }
}