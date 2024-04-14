import { User, Roles, Comunidad, Inversiones } from "../entities";
import { RequestUser } from "../interfaces";
import { Response } from "express";

export const makeInversion = async (req: RequestUser, res: Response) => {
  try {
       const id = parseInt(req.params.id);

    if (!id) return res.status(400).json({ message: "Faltan campos" });

    const { nombre, total } = req.body;
    const userInfo = req.user;

    if (!userInfo) return res.status(401).json({ message: "No autorizado" });

    const user = await User.findOne({
      where: { firebaseId: userInfo?.firebaseId },
    });

    if (!user)
      return res.status(404).json({ message: "Usuario no encontrado" });

    const rolesUser = await Roles.findOne({
      where: { user: user },
    });

    if (!rolesUser)
      return res.status(404).json({ message: "Usuario no tiene roles" });

    const inversion = new Inversiones();

    inversion.nombre = nombre;
    inversion.total = total;

    const comunidad = await Comunidad.findOne({
      where: { id },
      select: ["id", "nombre_comunidad", "localidad", "foto"],
    });

    if (!comunidad)
      return res.status(404).json({ message: "Comunidad no encontrada" });

    inversion.comunidad = comunidad;

    rolesUser.inversor = true;

    await rolesUser.save();
    await inversion.save();
  } catch (error) {
    console.error("Error al realizar la inversión:", error);
    res.status(500).json({ message: "Hubo un error al realizar la inversión" });
  }
};

export const makePatrimonio = async (req: RequestUser, res: Response) => {};

export const getInversionesByUser = async (req: RequestUser, res: Response) => {
  try {
    const page = parseInt(req.params.page);
    const userInfo = req.user;
    const size = 10;
    const offset = (page - 1) * size;

    if (!userInfo)
      return res.status(401).json({ message: "Necesitas estar loggeado" });

    if (isNaN(page))
      return res.status(400).json({ message: "Página no válida" });

    const inversiones = await Inversiones.find({
      where: { user: userInfo },
      order: { createdAt: "DESC" },
      skip: offset,
      take: size,
      relations: ["comunidad"],
      select: ["id", "nombre", "total", "createdAt", "comunidad"],
    });

    return res.status(200).json({ inversiones });
  } catch (error) {
    console.error("Error al obtener las inversiones por usuario:", error);
    res.status(500).json({
      message: "Hubo un error al obtener las inversiones por usuario",
    });
  }
};

export const getInversionesByComunidad = async (
  req: RequestUser,
  res: Response
) => {
  try {
    const page = parseInt(req.params.page);
    const userInfo = req.user;
    const size = 10;
    const offset = (page - 1) * size;

    if (!userInfo)
      return res.status(401).json({ message: "Necesitas estar loggeado" });

    if (isNaN(page))
      return res.status(400).json({ message: "Página no válida" });

    const inversiones = await Inversiones.find({
      where: { user: userInfo },
      order: { createdAt: "DESC" },
      skip: offset,
      take: size,
      relations: ["comunidad"],
      select: ["id", "nombre", "total", "createdAt", "comunidad"],
    });

    return res.status(200).json({ inversiones });
  } catch (error) {
    console.error("Error al obtener las inversiones por comunidad:", error);
    res.status(500).json({
      message: "Hubo un error al obtener las inversiones por comunidad",
    });
  }
};
