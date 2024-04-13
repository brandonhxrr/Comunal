import { Request as ExpressRequest } from "express";
import { User } from "../entities";

export interface RequestUser extends ExpressRequest {
  user?: User;
}
