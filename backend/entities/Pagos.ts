import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  BaseEntity,
} from "typeorm";
import { User } from "./User";
import { Proyecto } from "./Proyecto";

@Entity()
export class Pagos extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  descripcion: string;

  @Column("decimal", { precision: 10, scale: 2 })
  monto: number;

  @Column()
  fecha: Date;

  // Relacion de muchos pagos a un usuario
  @ManyToOne(() => User, (user) => user.pagos)
  user: User;

  // Relacion de muchos pagos a un proyecto
  @ManyToOne(() => Proyecto, (proyecto) => proyecto.pagos)
  proyecto: Proyecto;
}
