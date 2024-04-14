import { Proyecto, Comunidad, User } from "../entities";
import { Response } from "express";
import { RequestUser } from "../interfaces";

export const createProyecto = async (req: RequestUser, res: Response) => {
  try {
    const {
      nombre,
      descripcion,
      comunidadId,
      objetivo,
      objetivo_economico,
      inversion,
    } = req.body;
    const userInfo = req.user;

    if (!userInfo) return res.status(401).json({ message: "No autorizado" });

    if (!nombre || !descripcion || !comunidadId)
      return res.status(400).json({ message: "Faltan Datos" });

    const comunidad = await Comunidad.findOne({
      where: { id: comunidadId },
      select: ["presupuesto"],
    });

    if (!comunidad)
      return res.status(404).json({ message: "Comunidad no existe" });

    if (inversion > comunidad.presupuesto)
      return res.status(400).json({ message: "No hay suficiente presupuesto" });

    const proyecto = new Proyecto();

    proyecto.project_name = nombre;
    proyecto.description = descripcion;
    proyecto.comunidad = comunidad;
    proyecto.representantes = [userInfo];
    proyecto.objetivo = objetivo;
    proyecto.objetivo_economico = objetivo_economico;
    // una semana mas a partir de la fecha de hoy
    proyecto.inicio = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000);
    proyecto.inversion = inversion;

    await proyecto.save();

    return res.status(200).json({ message: "Proyecto creado" });
  } catch (error) {
    console.error("Error al crear el proyecto:", error);
    res.status(500).json({ message: "Hubo un error al crear el proyecto" });
  }
};

export const getProyectosByComunidad = async (
  req: RequestUser,
  res: Response
) => {
  try {
    const id = parseInt(req.params.id);
    const page = parseInt(req.params.page);
    const limit = 10;
    const offset = (page - 1) * limit;
    const comunidadId = req.user?.comunidad.id;

    if (!comunidadId) return res.status(400).json({ message: "No autorizado" });

    if (isNaN(page))
      return res.status(400).json({ message: "Página no válida" });

    const proyectos = await Proyecto.find({
      where: { comunidad: { id: comunidadId } },
      relations: ["representantes"],
      skip: offset,
      take: limit,
      select: [
        "id",
        "project_name",
        "description",
        "representantes",
        "duracion",
        "fin",
        "inicio",
        "objetivo",
        "objetivo_economico",
      ],
    });

    if (!proyectos)
      return res.status(404).json({ message: "No hay proyectos" });

    return res.status(200).json({ proyectos });
  } catch (error) {
    console.error("Error al obtener los proyectos por comunidad:", error);
    res.status(500).json({
      message: "Hubo un error al obtener los proyectos por comunidad",
    });
  }
};

export const getProyectosByUser = async (req: RequestUser, res: Response) => {
  try {
    const userInfo = req.user;

    if (!userInfo) return res.status(401).json({ message: "No autorizado" });

    const proyectos = await Proyecto.find({
      where: { representantes: { id: userInfo.id } },
      relations: ["comunidad"],
      select: [
        "id",
        "project_name",
        "description",
        "duracion",
        "fin",
        "inicio",
        "objetivo",
        "objetivo_economico",
        "comunidad",
      ],
    });

    if (!proyectos)
      return res.status(404).json({ message: "No hay proyectos asignados" });

    return res.status(200).json({ proyectos });
  } catch (error) {
    console.error("Error al obtener los proyectos por usuario:", error);
    res.status(500).json({
      message: "Hubo un error al obtener los proyectos por usuario",
    });
  }
};

export const getProyectos = async (req: RequestUser, res: Response) => {
  try {
    const proyectos = await Proyecto.find({
      relations: ["comunidad", "representantes"],
      select: [
        "id",
        "project_name",
        "description",
        "duracion",
        "fin",
        "inicio",
        "objetivo",
        "objetivo_economico",
        "comunidad",
        "representantes",
      ],
    });

    if (!proyectos)
      return res.status(404).json({ message: "No hay proyectos registrados" });

    return res.status(200).json({ proyectos });
  } catch (error) {
    console.error("Error al obtener todos los proyectos:", error);
    res.status(500).json({ message: "Hubo un error al obtener los proyectos" });
  }
};
