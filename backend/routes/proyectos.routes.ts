import { Router } from "express";
import {
  createProyecto,
  getProyectos,
  getProyectosByComunidad,
  getProyectosByUser,
} from "../controllers/proyectos.controllers";
import {
  isLogged,
  esInversor,
  esTrabajador,
  esRepresentante,
} from "../middlewares";

const router = Router();

router.post("/", isLogged, esRepresentante, createProyecto);

router.get("/", isLogged, getProyectos);

router.get("/comunidad/:page", isLogged, getProyectosByComunidad);

router.get("/user/:page", isLogged, esInversor, getProyectosByUser);

export default router;
