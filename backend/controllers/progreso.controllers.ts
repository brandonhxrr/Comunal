import { Request, Response } from "express";
import { RequestUser } from "../interfaces";
import { Progreso, Proyecto, User } from "../entities";

export const getProgresosByProyecto = async (
  req: RequestUser,
  res: Response
) => {
  try {
    const id = parseInt(req.params.id);
    const page = parseInt(req.params.page);
    const size = 10;
    const offset = (page - 1) * size;

    if (isNaN(id) || isNaN(page))
      return res.status(400).json({ message: "Faltan campos" });

    const userInfo = req.user;

    if (!userInfo) return res.status(401).json({ message: "No autorizado" });

    const proyecto = await Proyecto.find({
      where: {
        id,
        representantes: {
          id: userInfo.id,
        },
      },
      relations: ["progreso"],
      skip: offset,
      take: size,
    });

    if (!proyecto)
      return res.status(404).json({ message: "Proyecto no existe" });

    return res.status(200).json({ proyecto });
  } catch (error) {
    console.log("Error en getProgresoByProyecto: ", error);
    return res.status(500).json({ message: "Error en el servidor" });
  }
};
