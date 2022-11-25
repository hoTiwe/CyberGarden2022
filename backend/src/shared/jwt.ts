import jwt from "jsonwebtoken"

export function get(payload: object) {
    return jwt.sign(payload, "secretCat", { expiresIn: "24h" })
}

export function verify(token: string) {
    return jwt.verify(token, "secretCat")
}
