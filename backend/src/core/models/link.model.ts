
import { 
    Entity,
    Column, 
    PrimaryGeneratedColumn, 
    OneToOne,
    JoinColumn,
    ManyToMany,
    JoinTable
}
from "typeorm";

@Entity()
class LinkModel {

    @PrimaryGeneratedColumn()
    id: number

    @Column({nullable: true})
    tg_link: string

    @Column({nullable: true})
    inst_link: string

    @Column({nullable: true})
    vk_limk: string

    
}

export default LinkModel