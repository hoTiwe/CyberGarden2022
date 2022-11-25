import { Entity, Column, PrimaryGeneratedColumn } from "typeorm";

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

}

export default UserModel