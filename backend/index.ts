import "reflect-metadata";
import app from "./app";
import { appDataSource } from "./config/db";

// Funcion principal
const main = async () => {
  try {
    // Inicializar la base de datos
    await appDataSource.initialize();

    console.log("Database connected");

    // Inicializar el servidor
    const PORT = process.env.PORT || 8777;
    app.listen(PORT);
  } catch (error) {
    console.error(error);
  }
};

// Ejecuta la funcion principal
main();
