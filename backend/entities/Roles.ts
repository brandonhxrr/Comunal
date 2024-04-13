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
  2- Supervisor
  3- Trabajador
  4- Prestatario
  5- Representante
 */

@Entity()
export class Roles extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ default: false })
  inversor: boolean;

  @Column({ default: false })
  supervisor: boolean;

  @Column({ default: false })
  trabajador: boolean;

  @Column({ default: false })
  prestatario: boolean;

  @Column({ default: false })
  representante: boolean;

  @ManyToOne(() => User, (user) => user.roles)
  user: User;
}
