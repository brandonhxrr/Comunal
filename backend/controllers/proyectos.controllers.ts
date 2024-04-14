import { Proyecto, Comunidad, User } from "../entities";
import { Request, Response } from "express";
import { RequestUser } from "../interfaces";


export const createProyecto = async (req: RequestUser, res: Response) => {};

export const getProyectosByComunidad = async ( req: RequestUser, res: Response) => {};

export const getProyectosByUser = async (req: RequestUser, res: Response) => {};

export const getProyectos = async (req: RequestUser, res: Response) => {};
