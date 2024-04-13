import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  BaseEntity,
} from "typeorm";

@Entity()
export class Facturacion extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  descripcion: string;

  @Column("decimal", { precision: 10, scale: 2 })
  monto: number;

  @Column()
  fecha: Date;

  @Column()
  tipo: number;
}
