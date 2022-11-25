import { 
    Entity,
    Column,
    PrimaryGeneratedColumn,
    OneToMany,
    JoinColumn,
 } from "typeorm";

@Entity()
class HobbieModel {

    @PrimaryGeneratedColumn()
    id: number

    @Column()
    title: string
}

export default HobbieModel