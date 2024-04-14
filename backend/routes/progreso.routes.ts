import { getProgresosByProyecto } from "../controllers/progreso.controllers";

  import { isLogged} from "../middlewares";
  import { Router } from "express";
  
  const router = Router();
  
  router.get("/progreso", isLogged, getProgresosByProyecto);
  
  export default router;