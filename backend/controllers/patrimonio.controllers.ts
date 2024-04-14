import { Request, Response } from "express";
import { RequestUser } from "../interfaces";
import { Comunidad, Progreso, Proyecto, User, Patrimonio } from "../entities";

export const createProgreso = async (req: RequestUser, res: Response) => {
  try {
    const id = parseInt(req.params.id);
    const { descripcion, nombre, cantidad } = req.body;
    const userInfo = req.user;

    if (!userInfo) return res.status(401).json({ message: "No autorizado" });

    if (!descripcion || !nombre || !cantidad)
      return res.status(400).json({ message: "Faltan Datos" });

    const proyecto = await Proyecto.findOne({
      where: {
        id,
        representantes: {
          id: userInfo.id,
        },
      },
    });

    if (!proyecto)
      return res.status(404).json({ message: "Proyecto no existe" });

    const progreso = new Progreso();

    progreso.descripcion = descripcion;
    progreso.proyecto = proyecto;
    progreso.nombre = nombre;
    progreso.cantidad = cantidad;

    const comunidad = await Comunidad.findOne({
      where: { nombre_comunidad: userInfo.comunidad.nombre_comunidad },
      select: ["proyecto"],
    });

    if (!comunidad)
      return res.status(404).json({ message: "Comunidad no existe" });

    // ver si existe patrimonio por proyecto
    const patrimonio = await Patrimonio.findOne({
      where: { proyecto: proyecto },
    });

    if (!patrimonio) {
      const newPatrimonio = new Patrimonio();
      newPatrimonio.proyecto = proyecto;
      await newPatrimonio.save();
    }

    await progreso.save();

    return res.status(200).json({ message: "Progreso creado" });
  } catch (error) {
    console.log("Error en createProgreso: ", error);
    return res.status(500).json({ message: "Error en el servidor" });
  }
};
