import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  ManyToOne,
  BaseEntity,
  OneToMany,
} from "typeorm";
import { User } from "./User";

@Entity()
export class Comunidad extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  foto: string;

  @Column()
  nombre_comunidad: string;

  @Column()
  representate: number;

  @Column()
  supervisor: number;

  @Column()
  localidad: string;

  @CreateDateColumn({ name: "created_at" })
  createdAt: Date;

  @Column("decimal", { precision: 10, scale: 2 })
  presupuesto: number;

  @OneToMany(() => User, (user) => user.comunidad)
  Users: User[]

}
