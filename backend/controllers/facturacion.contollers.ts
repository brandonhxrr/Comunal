import { Comunidad } from "../entities";
import { Response } from "express";
import { RequestUser } from "../interfaces";
import { Proyecto, Facturacion } from "../entities";

export const createFactura = async (req: RequestUser, res: Response) => {
  try {
    const id = parseInt(req.params.id);
    const { descripcion, monto, tipo } = req.body;
    const userInfo = req.user;

    if (!userInfo) return res.status(401).json({ message: "No autorizado" });

    if (!descripcion || !monto || !tipo)
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

    const nombreComunidad = userInfo.comunidad.nombre_comunidad;

    if (!nombreComunidad)
      return res.status(404).json({ message: "Usuario no tiene comunidad" });

    const factura = new Facturacion();

    factura.descripcion = descripcion;
    factura.monto = monto;
    factura.tipo = tipo;
    factura.proyecto = proyecto;

    await factura.save();

    const comunidad = await Comunidad.findOne({
      where: { nombre_comunidad: nombreComunidad },
    });

    if (!comunidad)
      return res.status(404).json({ message: "Comunidad no existe" });

    if (tipo === 1) {
      comunidad.presupuesto = comunidad.presupuesto + monto;
    }

    if (tipo === 2) {
      comunidad.presupuesto = comunidad.presupuesto - monto;
    }

    await comunidad.save();

    return res.status(200).json({ message: "Factura creada" });
  } catch (error) {
    console.error("Error al crear la factura:", error);
    res.status(500).json({ message: "Hubo un error al crear la factura." });
  }
};

export const getFacturas = async (req: RequestUser, res: Response) => {
  try {
    const id = parseInt(req.params.id);
    const page = parseInt(req.params.page);
    const limit = 10;
    const offset = (page - 1) * limit;

    const facturas = await Facturacion.find({
      where: { proyecto: { id } },
      order: { createdAt: "DESC" },
      relations: ["enfoque"],
      skip: offset,
      take: limit,
      select: ["id", "monto", "tipo", "descripcion"],
    });

    return res.status(200).json({ facturas });
  } catch (error) {
    console.error("Error al obtener las facturas:", error);
    res.status(500).json({ message: "Hubo un error al obtener las facturas" });
  }
};
