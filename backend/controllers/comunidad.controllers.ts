import { RequestUser } from "../interfaces";
import { Response } from "express";
import { User, Roles, Enfoque, Comunidad } from "../entities";

export const createComunidad = async (req: RequestUser, res: Response) => {
  try {
    const { nombre } = req.body;

    if (!nombre) {
      return res.status(400).json({ message: "Faltan datos" });
    }

    const comunidad = await Comunidad.findOne({
      where: { nombre_comunidad: nombre },
    });

    if (!comunidad) {
      return res.status(404).json({ message: "Comunidad no existe" });
    }

    comunidad.accepted = true;

    return res.status(200).json({ message: "Comunidad aceptada" });
  } catch (error) {
    console.log("Error en createComunidad: ", error);
    return res.status(500).json({ message: "Error en el servidor" });
  }
};

export const requestComunidad = async (req: RequestUser, res: Response) => {
  try {
    const { nombre, localidad, url, descripcion, enfoque } = req.body;
    const userInfo = req.user;

    if (!nombre || !localidad || !descripcion) {
      return res.status(400).json({ message: "Faltan datos" });
    }

    const user = await User.findOne({
      where: { firebaseId: userInfo?.firebaseId },
    });

    if (!user) {
      return res.status(404).json({ message: "Usuario no encontrado" });
    }

    const comunidad = new Comunidad();

    comunidad.nombre_comunidad = nombre;
    comunidad.localidad = localidad;
    comunidad.descripcion = descripcion;
    comunidad.Users = [user];

    if (url) {
      comunidad.foto = url;
    }

    await comunidad.save();

    // Verificar si hay enfoques y crear registros en la tabla Enfoque
    if (enfoque && Array.isArray(enfoque)) {
      const enfoqueEntities = enfoque.map((item: string) => {
        const enfoqueEntity = new Enfoque();
        enfoqueEntity.nombre = item;
        enfoqueEntity.comunidad = comunidad;
        return enfoqueEntity;
      });

      // Guardar los registros de Enfoque en la base de datos
      await Enfoque.save(enfoqueEntities);
    }

    const rolesUser = await Roles.findOne({
      where: { user: user },
    });

    if (!rolesUser) {
      return res.status(404).json({ message: "Rol no encontrado" });
    }

    rolesUser.representante = true;

    await rolesUser.save();

    return res.status(200).json({ message: "Comunidad creada" });
  } catch (error) {
    console.log("Error en requestComunidad: ", error);
    return res.status(500).json({ message: "Error en el servidor" });
  }
};

export const getComunidades = async (req: RequestUser, res: Response) => {
  try {
    const page = parseInt(req.params.page);
    const limit = 10;
    const offset = (page - 1) * limit;

    const comunidades = await Comunidad.find({
      order: { createdAt: "DESC" },
      relations: ["enfoque"],
      skip: offset,
      take: limit,
      select: ["id", "nombre_comunidad", "localidad", "foto", "enfoque"],
    });

    return res.status(200).json({ comunidades });
  } catch (error) {
    console.error("Error al obtener las comunidades:", error);
    res
      .status(500)
      .json({ message: "Hubo un error al obtener las comunidades." });
  }
};

export const getComunidadByName = async (req: RequestUser, res: Response) => {
  try {
    const nombreComunidad = req.params.name.toString();
    const user = req.user;

    if (!nombreComunidad) {
      return res.status(400).json({ message: "Nombre no válido" });
    }

    const comunidad = await Comunidad.findOne({
      where: { nombre_comunidad: nombreComunidad },
      relations: ["enfoque"],
      select: ["id", "nombre_comunidad", "localidad", "foto", "enfoque"],
    });

    if (!comunidad) {
      return res.status(404).json({ message: "Comunidad no encontrada" });
    }

    return res.status(200).json({ comunidad });
  } catch (error) {
    console.error("Error al obtener la comunidad por nombre:", error);
    res
      .status(500)
      .json({ message: "Hubo un error al obtener la comunidad por nombre." });
  }
};

export const createEnfoque = async (req: RequestUser, res: Response) => {
  try {
    const userInfo = req.user;
    const { nombre } = req.body;

    if (!nombre) {
      return res.status(400).json({ message: "Faltan datos" });
    }

    const user = await User.findOne({
      where: { firebaseId: userInfo?.firebaseId },
      relations: ["comunidad"],
    });

    if (!user) {
      return res.status(404).json({ message: "Usuario no encontrado" });
    }

    const nombreComunidad = user.comunidad?.nombre_comunidad;

    if (!nombreComunidad) {
      return res.status(404).json({ message: "Usuario no tiene comunidad" });
    }

    const comunidad = await Comunidad.findOne({
      where: { nombre_comunidad: nombreComunidad },
    });

    if (!comunidad) {
      return res.status(404).json({ message: "Comunidad no encontrada" });
    }

    const enfoque = new Enfoque();

    enfoque.nombre = nombre;
    enfoque.comunidad = comunidad;

    await enfoque.save();

    return res.status(200).json({ message: "Enfoque creado" });
  } catch (error) {
    console.error("Error al crear enfoque:", error);
    res.status(500).json({ message: "Error al crear enfoque" });
  }
};
