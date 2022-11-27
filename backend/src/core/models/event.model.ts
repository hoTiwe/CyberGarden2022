import {
    Entity,
    Column,
    PrimaryGeneratedColumn,
    OneToMany,
    OneToOne,
    JoinColumn,
    ManyToMany,
    JoinTable,
} from "typeorm"
import UserModel from "./user.model"

@Entity()
class EventModel {
    @PrimaryGeneratedColumn()
    id: number

    @OneToOne(() => UserModel, { cascade: true })
    @JoinColumn()
    creator: UserModel

    @ManyToMany(() => UserModel, { cascade: true })
    @JoinTable()
    subscribers: UserModel[]

    @Column()
    title: string

    @Column()
    description: string

    @Column()
    adress: string

    @Column({nullable: false})
    startDate: Date

    @Column({nullable: true})
    endDate: Date

    @Column({nullable: true})
    chat: string
}

export default EventModel
