import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  BaseEntity,
  CreateDateColumn,
} from "typeorm";
import { Proyecto } from "./Proyecto";

@Entity()
export class Facturacion extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  descripcion: string;

  @Column("decimal", { precision: 10, scale: 2 })
  monto: number;

  @CreateDateColumn ({ name: "created_at" })
  createdAt: Date;

  @Column()
  tipo: number;

  // Relacion de muchas facturaciones a un proyecto
  @ManyToOne(() => Proyecto, (proyecto) => proyecto.facturacion)
  proyecto: Proyecto;
}
