import { Response, NextFunction } from "express";
import { User } from "../entities";
import { RequestUser } from "../interfaces";

export const isLogged = async (
  req: RequestUser,
  res: Response,
  next: NextFunction
) => {
  try {
    const id: string = req.headers.id?.toString() || "";

    if (!id) {
      return res.status(401).json({ message: "Necesitas estar loggeado" });
    }

    const user = await User.findOne({
      where: { firebaseId: id },
      relations: ["roles"],
      select: ["id", "firebaseId", "email", "roles", "name"],
    });

    if (!user) {
      return res.status(401).json({ message: "El usuario no existe" });
    }

    req.user = user;

    next();
  } catch (error) {
    console.log("Error en isLogged: ", error);
    return res.status(500).json({ message: "Error en el servidor" });
  }
};
