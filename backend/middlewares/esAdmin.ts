import { Response, NextFunction } from "express";
import { RequestUser } from "../interfaces";

export const esAdmin = (
  req: RequestUser,
  res: Response,
  next: NextFunction
) => {
  try {
    if (!req.user)
      return res
        .status(401)
        .json({ message: "No tienes permisos para realizar esta acción" });

    const rolAdmin = req.user.roles.find((rol) => rol.admin);

    if (!rolAdmin)
      return res
        .status(401)
        .json({ message: "No tienes permisos para realizar esta acción" });

    next();
  } catch (error) {}
};
