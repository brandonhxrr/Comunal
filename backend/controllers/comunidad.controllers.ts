import { RequestUser } from "../interfaces";
import { Request, Response } from "express";
import { Comunidad } from "../entities";
import { User, Roles, Enfoque } from "../entities";

export const createComunidad = async (req: RequestUser, res: Response) => {};

export const requestComunidad = async (req: RequestUser, res: Response) => {};

export const getComunidades = async (req: RequestUser, res: Response) => {};

export const getComunidadByName = async (req: RequestUser, res: Response) => {};

export const createEnfoque = async (req: RequestUser, res: Response) => {};
