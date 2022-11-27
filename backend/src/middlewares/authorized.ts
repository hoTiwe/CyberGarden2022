import { NextFunction, Request, Response } from "express"
import ApiError, { StatusCodes } from "../shared/api.error"
import * as jwt from "../shared/jwt"

export default function (req: Request, res: Response, next: NextFunction) {
    const token = req.header("Authorization")
    
    if (!token) {
        return next(new ApiError(StatusCodes.UNAUTHORIZED, "empty credentials"))
    }

    try {
        const user = <any>jwt.verify(token)
        console.log(user)
        req.user = {
            id: user.id,
        }
        return next()
    } catch (err) {
        return next(new ApiError(StatusCodes.UNAUTHORIZED, "token has expired"))
    }
}
