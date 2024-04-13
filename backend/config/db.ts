import { DataSource } from "typeorm";

// Conexion a la base de datos
export const appDataSource = new DataSource({
  type: "postgres",
  host: process.env.PGHOST,
  username: process.env.PGUSER,
  password: process.env.PGPASSWORD,
  port: process.env.PGPORT ? parseInt(process.env.PGPORT) : undefined,
  database: process.env.PGDATABASE,
  entities: [],
  ssl: true,
  synchronize: true,
});
