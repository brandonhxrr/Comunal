import { User, Roles } from "../entities";
import { Request, Response } from "express";
import { RequestUser } from "../interfaces";

export const singup = async (req: Request, res: Response) => {
  try {
    const { email, firebaseId, name, url } = req.body;

    if (!email || !firebaseId || !name) {
      return res.status(400).json({ message: "Faltan campos" });
    }

    // crear instancia usuario
    const user = new User();

    user.email = email;
    user.firebaseId = firebaseId;
    user.name = name;

    if (url) {
      user.foto = url;
    }

    await user.save();

    const roles = new Roles();

    roles.user = user;

    await roles.save();

    return res.status(201).json({ message: "Usuario creado" });
  } catch (error) {
    console.log("Error en singup: ", error);
    return res.status(500).json({ message: "Error en el servidor" });
  }
};

export const updatePhoto = async (req: RequestUser, res: Response) => {
  try {
    const userInfo = req.user;
    const { url } = req.body;

    if (!userInfo) {
      return res.status(401).json({ message: "Necesitas estar loggeado" });
    }

    if (!url) {
      return res.status(400).json({ message: "Faltan campos" });
    }

    const user = await User.findOne({
      where: { firebaseId: userInfo.firebaseId },
    });

    if (!user) {
      return res.status(404).json({ message: "Usuario no encontrado" });
    }

    user.foto = url;

    await user.save();

    return res.status(200).json({ message: "Foto actualizada" });
  } catch (error) {
    console.log("Error en updatePhoto: ", error);
    return res.status(500).json({ message: "Error en el servidor" });
  }
};
