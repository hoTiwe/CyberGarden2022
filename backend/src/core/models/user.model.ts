import {
    Entity,
    Column,
    PrimaryGeneratedColumn,
    OneToOne,
    JoinColumn,
    ManyToMany,
    JoinTable,
    ManyToOne
} from "typeorm"
import HobbieModel from "./hobbie.model"
import LinkModel from "./link.model"
import ProfessionModel from "./profession.model"

@Entity()
class UserModel {
    @PrimaryGeneratedColumn()
    id: number

    @Column()
    name: string

    @Column()
    login: string

    @Column()
    password: string

    @Column()
    avatar: string

    @Column()
    about: string

    @ManyToOne(() => ProfessionModel, { cascade: true })
    @JoinColumn()
    profession: ProfessionModel

    @ManyToOne(() => LinkModel, { cascade: true })
    @JoinColumn()
    links: LinkModel

    @ManyToMany(() => HobbieModel, { cascade: true })
    @JoinTable()
    hobbies: HobbieModel[]
}

export default UserModel
