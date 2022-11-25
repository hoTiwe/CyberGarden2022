import { Request, Response, NextFunction } from "express";
import { StatusCodes } from "http-status-codes";
import ApiError from "../shared/api.error";

export default function (
    err: Error,
    req: Request,
    res: Response,
    next: NextFunction
) {
    if (err instanceof ApiError) {
        res.status(err.code).json(err.what());
    } else {
        res.status(StatusCodes.INTERNAL_SERVER_ERROR).json({
            message: err.message,
            name: err.name,
            stack: err.stack,
        });
    }
}
