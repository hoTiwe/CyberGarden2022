import multer from "multer"

var storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, "static/")
    },
    filename: function (req, file, cb) {
        let extArray = file.originalname.split(".")
        let extension = extArray[extArray.length - 1]
        let newName = "avatar-" + req.body.email + "." + extension
        cb(null, newName.replace(/"/g, ""))
    },
})

const upload = multer({ storage: storage })

export { upload }
