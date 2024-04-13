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
}
