import { Router } from "express";
import {
  singup,
  updatePhoto,
  getInfoUser,
} from "../controllers/user.controllers";
import { isLogged } from "../middlewares";

const router = Router();

// Crear usuario
router.post("/singup", singup);

// Actualizar foto de perfil
router.put("/update-photo", isLogged, updatePhoto);

// Obtener información del usuario
router.get("/info", isLogged, getInfoUser);

export default router;
