import {
    Entity,
    PrimaryGeneratedColumn,
    Column,
    ManyToOne,
    BaseEntity,
  } from "typeorm";
  
  @Entity()
  export class Inversiones extends BaseEntity {
    @PrimaryGeneratedColumn()
    id: number;
  
    @Column()
    nombre: string;
  
    @Column("decimal", { precision: 10, scale: 2 })
    total: number;
  
    @Column()
    fecha: Date;
  
  }