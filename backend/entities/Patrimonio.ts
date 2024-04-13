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
export class Patrimonio extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  nombre: string;

  @Column("decimal", { precision: 10, scale: 2 })
  valor: number;

  @Column()
  cantidad: number;

  @Column("decimal", { precision: 10, scale: 2 })
  metros: number;
}
