import {
  makePago,
  getPagosByComunidad,
} from "../controllers/pagos.controllers";
import { isLogged, esRepresentante, esTrabajador } from "../middlewares";
import { Router } from "express";

const router = Router();

router.post("/", isLogged, makePago);

router.get("/:page", isLogged, esRepresentante, getPagosByComunidad);

export default router;
