import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  BaseEntity,
} from "typeorm";
import { Comunidad } from "./Comunidad";
import { Facturacion } from "./Facturacion";
import { User } from "./User";
import { Patrimonio } from "./Patrimonio";
import { Pagos } from "./Pagos";
import { Progreso } from "./Progreso";

@Entity()
export class Proyecto extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  project_name: string;

  @Column()
  description: string;

  @Column()
  duracion: string;

  @Column()
  inversion_inicial: number;

  @Column()
  objetivo: string;

  @Column("decimal", { precision: 10, scale: 2 })
  objetivo_economico: number;

  @Column()
  inicio: Date;

  @Column()
  fin: Date;

  @Column("decimal", { precision: 10, scale: 2 })
  presupuesto: number;

  // Relacion de un proyecto a una comunidad
  @ManyToOne(() => Comunidad, (comunidad) => comunidad.proyecto)
  comunidad: Comunidad;

  // Relacion de un proyecto a muchas facturaciones
  @ManyToOne(() => Facturacion, (facturacion) => facturacion.proyecto)
  facturacion: Facturacion[];

  // Relacion de un proyecto a muchos trabajadores (usuarios con rol de trabajador)
  @ManyToOne(() => User, (user) => user.proyecto)
  trabajadores: User[];

  // Relacion de un proyecto a muchos representantes (usuarios con rol de representante)
  @ManyToOne(() => User, (user) => user.proyecto)
  representantes: User[];

  // Relacion de un proyecto a muchos patrimonios
  @ManyToOne(() => Patrimonio, (patrimonio) => patrimonio.proyecto)
  patrimonio: Patrimonio[];

  // Relacion de un proyecto a muchos pagos
  @ManyToOne(() => Pagos, (pagos) => pagos.proyecto)
  pagos: Pagos[];

  // Relacion de un proyecto a muchos progresos
  @ManyToOne(() => Progreso, (progreso) => progreso.proyecto)
  progreso: Progreso[];
}
