import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  ManyToOne,
  BaseEntity,
} from "typeorm";
import { Proyecto } from "./Proyecto";

@Entity()
export class Progreso extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  nombre: string;

  @Column()
  descripcion: string;

  @Column()
  fecha: Date;

  @Column("decimal", { precision: 10, scale: 2 })
  cantidad: number;

  @Column()
  foto: string;

  // Relacion de muchos progresos a un proyecto
  @ManyToOne(() => Proyecto, (proyecto) => proyecto.progreso)
  proyecto: Proyecto;
}
