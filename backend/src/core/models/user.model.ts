import { columns } from "mssql";
import { 
    Entity,
    Column,
    PrimaryGeneratedColumn,
    OneToOne,
    JoinColumn,
 } from "typeorm";
import InfoModel from "./info.model"

@Entity()
class UserModel {

    @PrimaryGeneratedColumn()
    id: number

    @Column()
    name: string

    @Column()
    surname: string

    @Column()
    login: string

    @Column()
    password: string

    @OneToOne(() => InfoModel)
    @JoinColumn()
    user_info: InfoModel
}

export default UserModel