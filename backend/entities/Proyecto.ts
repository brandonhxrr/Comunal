import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  ManyToOne,
  BaseEntity,
} from "typeorm";

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
}
