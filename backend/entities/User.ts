import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  OneToMany,
  BaseEntity,
  ManyToOne,
} from "typeorm";
import { Roles } from "./Roles";
import { Comunidad } from "./Comunidad";

@Entity()
export class User extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ nullable: false, unique: true })
  firebaseId: string;

  @Column({ nullable: false })
  name: string;

  @Column({ nullable: false, unique: true })
  email: string;

  @CreateDateColumn({ name: "created_at" })
  createdAt: Date;

  @OneToMany(() => Roles, (roles) => roles.user)
  roles: Roles[];

  @ManyToOne(() => Comunidad, (comunidad) => comunidad.Users)
  comunidad: Comunidad;
}
