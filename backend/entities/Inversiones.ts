import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  BaseEntity,
} from "typeorm";
import { User } from "./User";
import { Comunidad } from "./Comunidad";

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

  // Relacion de muchas inversiones a un usuario
  @ManyToOne(() => User, (user) => user.inversiones)
  user: User;

  // Relacion de muchas inversiones a una comunidad
  @ManyToOne(() => Comunidad, (comunidad) => comunidad.inversiones)
  comunidad: Comunidad;
}
