import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  BaseEntity,
} from "typeorm";
import { Comunidad } from "./Comunidad";

@Entity()
export class Enfoque extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  nombre: string;

  // Relacion de muchos enfoques a una comunidad
  @ManyToOne(() => Comunidad, (comunidad) => comunidad.enfoque)
  comunidad: Comunidad;
}
