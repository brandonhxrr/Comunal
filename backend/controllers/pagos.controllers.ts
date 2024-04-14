import { User, Comunidad, Pagos } from "../entities";
import { Response } from "express";
import { RequestUser } from "../interfaces";

export const makePago = async (req: RequestUser, res: Response) => {
  try {
    const { email, monto, descripcion } = req.body;

    if (!email || !monto || !descripcion) {
      return res.status(400).json({ message: "Faltan campos por llenar" });
    }

    const user = await User.findOne({ where: { email } });

    if (!user) {
      return res.status(404).json({ message: "Usuario no encontrado" });
    }

    const comunidad = await Comunidad.findOne({
      where: { id: user.comunidad.id },
    });

    if (!comunidad) {
      return res.status(404).json({ message: "Comunidad no encontrada" });
    }

    const pago = new Pagos();

    pago.monto = monto;
    pago.descripcion = descripcion;
    pago.user = user;
    pago.comunidad = comunidad;

    await pago.save();

    return res.status(200).json({ message: "Pago realizado" });
  } catch (error) {
    console.error("Error al realizar el pago:", error);
    res.status(500).json({ message: "Hubo un error al realizar el pago" });
  }
};

export const getPagosByComunidad = async (req: RequestUser, res: Response) => {
  try {
    const page = parseInt(req.params.page);
    const limit = 10;
    const offset = (page - 1) * limit;
    const comunidadId = req.user?.comunidad.id;

    if (!comunidadId) return res.status(400).json({ message: "No autorizado" });

    if (isNaN(page))
      return res.status(400).json({ message: "Página no válida" });

    const comunidad = await Comunidad.findOne({
      where: { id: comunidadId },
    });

    if (!comunidad)
      return res.status(404).json({ message: "Comunidad no existe" });

    const pagos = await Pagos.find({
      where: { comunidad: comunidad },
      order: { createdAt: "DESC" },
      relations: ["usuario"],
      skip: offset,
      take: limit,
      select: ["id", "monto", "createdAt", "descripcion", "user"],
    });

    return res.status(200).json({ pagos });
  } catch (error) {
    console.error("Error al obtener las facturas:", error);
    res.status(500).json({ message: "Hubo un error al obtener las facturas" });
  }
};
