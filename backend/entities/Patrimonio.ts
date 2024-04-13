import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  ManyToOne,
  BaseEntity,
} from "typeorm";
import { User } from "./User";
import { Proyecto } from "./Proyecto";

@Entity()
export class Patrimonio extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  nombre: string;

  @Column("decimal", { precision: 10, scale: 2 })
  valor: number;

  @Column()
  cantidad: number;

  @Column("decimal", { precision: 10, scale: 2 })
  metros: number;

  // Relacion de muchos patrimonios a un usuario
  @ManyToOne(() => User, (user) => user.patrimonio)
  user: User;

  // Relacion de muchos patrimonios a un proyecto
  @ManyToOne(() => Proyecto, (proyecto) => proyecto.patrimonio)
  proyecto: Proyecto;
}
