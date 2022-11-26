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
    description: string

    @Column({nullable: false})
    startDate: Date

    @Column({nullable: true})
    endDate: Date
}

export default EventModel
