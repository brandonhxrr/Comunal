import { createProgreso } from "../controllers/patrimonio.controllers";

  import { isLogged, esRepresentante } from "../middlewares";
  import { Router } from "express";
  
  const router = Router();
  
  router.post("/patrimonio", isLogged, createProgreso, esRepresentante);
  
  export default router;
  