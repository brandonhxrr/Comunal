import { DataSource } from "typeorm";
import {
  User,
  Pagos,
  Facturacion,
  Enfoque,
  Inversiones,
  Patrimonio,
  Roles,
  Comunidad,
  Progreso,
  Proyecto,
} from "../entities";

// Conexion a la base de datos
export const appDataSource = new DataSource({
  type: "postgres",
  host: process.env.PGHOST,
  username: process.env.PGUSER,
  password: process.env.PGPASSWORD,
  port: process.env.PGPORT ? parseInt(process.env.PGPORT) : undefined,
  database: process.env.PGDATABASE,
  entities: [
    User,
    Pagos,
    Facturacion,
    Enfoque,
    Inversiones,
    Patrimonio,
    Roles,
    Comunidad,
    Progreso,
    Proyecto,
  ],
  ssl: true,
  synchronize: true,
});
