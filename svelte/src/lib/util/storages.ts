import type { CreateStorageForm } from "$lib/types/forms/CreateStorageForm";
import type { Storage } from "$lib/types/Storage";
import type { AxiosResponse } from "axios";
import { api } from "./api";

export const createStorage = (storage: CreateStorageForm) => {
    return api.post("me/storages", {data: storage})
        .then((res: AxiosResponse<Storage>): Storage => {
            return res.data;
        });
};
