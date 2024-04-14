import { Comunidad } from "../entities";
import { Request, Response } from "express";
import { RequestUser } from "../interfaces";
import { Proyecto, Facturacion } from "../entities";

export const createFactura = async (req: RequestUser, res: Response) => {
  try {
    const id = parseInt(req.params.id);
    const { descripcion, monto, tipo } = req.body
  } catch (error) {}
};

export const getFacturas = async (req: RequestUser, res: Response) => {};
