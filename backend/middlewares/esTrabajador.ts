import { Response, NextFunction } from "express";
import { RequestUser } from "../interfaces";

export const esTrabajador = async (
  req: RequestUser,
  res: Response,
  next: NextFunction
) => {
  try {
    if (!req.user)
      return res.status(401).json({ message: "Necesitas estar loggeado" });

    const rolTrabajador = req.user.roles.find((rol) => rol.trabajador);

    if (!rolTrabajador)
      return res
        .status(401)
        .json({ message: "No tienes permisos para realizar esta acción" });

    next();
  } catch (error) {
    console.log("Error en esInversor: ", error);
    return res.status(500).json({ message: "Error en el servidor" });
  }
};