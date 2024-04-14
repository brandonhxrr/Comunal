import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  BaseEntity,
  CreateDateColumn,
} from "typeorm";
import { User } from "./User";
import { Comunidad } from "./Comunidad";

@Entity()
export class Pagos extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  descripcion: string;

  @Column("decimal", { precision: 10, scale: 2 })
  monto: number;

  @CreateDateColumn({ name: "created_at" })
  createdAt: Date;

  // Relacion de muchos pagos a un usuario
  @ManyToOne(() => User, (user) => user.pagos)
  user: User;

  // Una comunidad hace muchos pagos
  @ManyToOne(() => Comunidad, (comunidad) => comunidad.pagos)
  comunidad: Comunidad;
}
