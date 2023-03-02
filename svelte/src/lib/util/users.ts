import axios from "axios";
import { api } from "./api";

export interface CreateUserForm {
    email: string,
    password: string,
};

export const createUser = (user: CreateUserForm) => {
    return api.post("users", {data: user})
        .then((res) => {
            return res.data;
        });
};

