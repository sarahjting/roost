import type { CreateUserForm } from "$lib/types/forms/CreateUserForm";
import type { User } from "$lib/types/User";
import type { AxiosResponse } from "axios";
import { api } from "./api";

export const createUser = (user: CreateUserForm) => {
    return api.post("users", {data: user})
        .then((res: AxiosResponse): User => {
            return res.data;
        });
};
