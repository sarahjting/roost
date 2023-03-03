import type { CreateStorageForm } from "$lib/types/forms/CreateStorageForm";
import type { Storage } from "$lib/types/Storage";
import type { AxiosResponse } from "axios";
import { api } from "./api";

export const deleteStorage = (storageId: string) => {
    return api.delete(`me/storages/${storageId}`)
        .then((res: AxiosResponse<Storage>) => {
            if (res.status !== 202) {
                throw new Error(`Unexpected status code ${res.status}.`);
            }
        });
};

export const setStorageDefault = (storageId: string) => {
    return api.post(`me/storages/${storageId}/set-default`)
        .then((res: AxiosResponse<Storage>) => {
            if (res.status !== 202) {
                throw new Error(`Unexpected status code ${res.status}.`);
            }
            
            return res.data;
        });
};

export const createStorage = (storage: CreateStorageForm) => {
    return api.post("me/storages", {data: storage})
        .then((res: AxiosResponse<Storage>): Storage => {
            return res.data;
        });
};
