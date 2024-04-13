import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  BaseEntity,
  OneToMany,
} from "typeorm";
import { User } from "./User";
import { Proyecto } from "./Proyecto";
import { Inversiones } from "./Inversiones";
import { Enfoque } from "./Enfoque";

@Entity()
export class Comunidad extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  foto: string;

  @Column({unique: true})
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
  Users: User[];

  // Relacion de una comunidad a muchos proyectos
  @OneToMany(() => Proyecto, (proyecto) => proyecto.comunidad)
  proyecto: Proyecto[];

  // Relacion de una comunidad a muchas inversiones
  @OneToMany(() => Inversiones, (inversiones) => inversiones.comunidad)
  inversiones: Inversiones[];

  // Relacion de una comunidad a muchos enfoques
  @OneToMany(() => Enfoque, (enfoque) => enfoque.comunidad)
  enfoque: Enfoque[];
}
