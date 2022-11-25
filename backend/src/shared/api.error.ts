import { StatusCodes } from "http-status-codes"

export default class ApiError extends Error {
    constructor(public code: number, public reason: string) {
        super(reason)
    }

    what() {
        return {
            code: this.code,
            message: this.reason,
            stack: this.stack,
        }
    }
}

export { StatusCodes }
