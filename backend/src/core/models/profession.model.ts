import { 
    Entity,
    Column,
    PrimaryGeneratedColumn,
    OneToMany,
    JoinColumn,
 } from "typeorm";

@Entity()
class ProfessionModel {

    @PrimaryGeneratedColumn()
    id: number

    @Column()
    profession: string
}

export default ProfessionModel