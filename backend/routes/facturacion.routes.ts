import { Router } from "express";
import {
  getFacturas,
  createFactura,
} from "../controllers/facturacion.contollers";
import { isLogged, esRepresentante } from "../middlewares";

const router = Router();

router.get("/factura/:page/:id", isLogged, getFacturas);

router.post("/create-factura/:id", isLogged, esRepresentante, createFactura);

export default router;
