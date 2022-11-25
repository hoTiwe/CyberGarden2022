import { 
    Entity,
    Column, 
    PrimaryGeneratedColumn,
    ManyToMany,
    JoinTable
 } from "typeorm";
import HobbieModel from "./hobbie.model"

@Entity()
class InfoModel {

    @PrimaryGeneratedColumn()
    id: number

    @Column()
    about: string

    @Column()
    profession: string
    
    @ManyToMany(() => HobbieModel)
    @JoinTable()
    hobbies: HobbieModel[]
}

export default InfoModel