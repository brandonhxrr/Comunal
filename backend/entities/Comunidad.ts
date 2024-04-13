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
}
