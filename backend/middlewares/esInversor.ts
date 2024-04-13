import { Response, NextFunction } from "express";
import { RequestUser } from "../interfaces";

export const esInversor = async (
  req: RequestUser,
  res: Response,
  next: NextFunction
) => {
  try {
    if (!req.user)
      return res.status(401).json({ message: "Necesitas estar loggeado" });

    const rolInversor = req.user.roles.find((rol) => rol.inversor);

    if (!rolInversor)
      return res
        .status(401)
        .json({ message: "No tienes permisos para realizar esta acción" });

    next();
  } catch (error) {
    console.log("Error en esInversor: ", error);
    return res.status(500).json({ message: "Error en el servidor" });
  }
};