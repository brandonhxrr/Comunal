import { Router } from "express";
import {
  createComunidad,
  getComunidades,
  requestComunidad,
  getComunidadByName,
  createEnfoque,
} from "../controllers/comunidad.controllers";
import { esAdmin, esRepresentante, isLogged } from "../middlewares";

const router = Router();

router.post("/accept", isLogged, esAdmin, createComunidad);

router.post("/request", isLogged, requestComunidad);

router.get("/", isLogged, getComunidades);

router.get("/:nombre", isLogged, getComunidadByName);

router.post("/enfoque", isLogged, esRepresentante, createEnfoque);

export default router;
