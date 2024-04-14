import express from "express";
import dotenv from "dotenv";
import morgan from "morgan";
import cors from "cors";
import cookieParser from "cookie-parser";
import multer from "multer";
import userRoutes from "./routes/user.routes";
import comunidadRoutes from "./routes/comunidad.routes";
import facturacionRoutes from "./routes/facturacion.routes";
import proyectosRoutes from "./routes/proyectos.routes";
import pagosRoutes from "./routes/pagos.routes";
import inversionesRoutes from "./routes/inversiones.routes";

// Cargar variables de entorno
dotenv.config();

const app = express();

const Storage = multer.diskStorage({
  destination(req, file, callback) {
    callback(null, "./images");
  },
  filename(req, file, callback) {
    callback(null, `${file.fieldname}_${Date.now()}_${file.originalname}`);
  },
});

const upload = multer({ storage: Storage });

// Configuraciones
app.use(morgan("dev"));
app.use(cors());
app.use(express.json());
app.use(cookieParser());

app.use("/api/v1/user", userRoutes);
app.use("/api/v1/comunidad", comunidadRoutes);
app.use("/api/v1/facturacion", facturacionRoutes);
app.use("/api/v1/proyectos", proyectosRoutes);
app.use("/api/v1/pagos", pagosRoutes);
app.use("/api/v1/inversiones", inversionesRoutes);

app.get("/", (req, res) => {
  res.json("Server On!");
});

export default app;
