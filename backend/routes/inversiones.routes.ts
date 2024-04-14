import { Router } from "express";
import {
  getInversionesByComunidad,
  makeInversion,
  makePatrimonio,
  getInversionesByUser,
} from "../controllers/inversiones.controllers";
import { isLogged, esInversor, esRepresentante } from "../middlewares";

const router = Router();

router.post("/", isLogged, esInversor, makeInversion);

router.post("/patrimonio", isLogged, esInversor, makePatrimonio);

router.get("/comunidad/:page", isLogged, getInversionesByComunidad);

router.get("/user/:page", isLogged, esInversor, getInversionesByUser);

export default router;
