import { Request, Response, NextFunction } from "express";

export {} 

type ReqUser = {
    id?: number
}

declare global {
  type ControllerHandler = (req: Request, res: Response, next: NextFunction) => Promise<any>
  namespace Express {
    export interface Request {
      user?: ReqUser;
    }
  }
}