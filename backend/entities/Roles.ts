import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  BaseEntity,
} from "typeorm";
import { User } from "./User";

/* 
  1- Inversor
  2- Trabajador
  3- Representante
 */

@Entity()
export class Roles extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ default: false })
  inversor: boolean;

  @Column({ default: false })
  trabajador: boolean;

  @Column({ default: false })
  representante: boolean;

  @Column({ default: false })
  admin: boolean;

  @ManyToOne(() => User, (user) => user.roles)
  user: User;
}
