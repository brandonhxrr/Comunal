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
import { Inversiones } from "./Inversiones";
import { Pagos } from "./Pagos";
import { Proyecto } from "./Proyecto";
import { Patrimonio } from "./Patrimonio";

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

  @Column()
  foto: string;

  @CreateDateColumn({ name: "created_at" })
  createdAt: Date;

  // Relacion de un usuario a muchos roles
  @OneToMany(() => Roles, (roles) => roles.user)
  roles: Roles[];

  // Relacion de un usuario a una comunidad
  @ManyToOne(() => Comunidad, (comunidad) => comunidad.Users)
  comunidad: Comunidad;

  //  Relacion de un usuario a muchas inversiones
  @OneToMany(() => Inversiones, (inversiones) => inversiones.user)
  inversiones: Inversiones[];

  // Relacion de un usuario a muchos pagos
  @OneToMany(() => Pagos, (pagos) => pagos.user)
  pagos: Pagos[];

  // Relacion de un usuario a muchos patrimonios
  @OneToMany(() => Patrimonio, (patrimonio) => patrimonio.user)
  patrimonio: Patrimonio[];

  // Relacion de un usuario a muchos proyectos
  @OneToMany(() => Proyecto, (proyecto) => proyecto.trabajadores)
  proyecto: Proyecto[];

  // Relacion de un usuario a muchos proyectos
  @OneToMany(() => Proyecto, (proyecto) => proyecto.representantes)
  proyectoRepresentante: Proyecto[];

  @UpdateDateColumn({ name: "updated_at" })
  updatedAt: Date;
}
