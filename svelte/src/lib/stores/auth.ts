import { writable } from "svelte/store";
import { type AxiosResponse } from "axios";
import { api } from "$lib/util/api";
import { Buffer } from "buffer";

const {subscribe, set, update} = writable(null);
export const auth = {
    subscribe,
    login: (email: string, password: string) => {
        const token = generateBasicToken(email, password);
        return api.withToken(token).get("me")
            .then((res: AxiosResponse) => {
                api.setToken(token);
                set({ ...res.data, token });
            });
    },
    logout: () => {
        set(null);
    },
    revalidate: () => {
        return api.get("me").then((res: AxiosResponse) => {
            set({ ...res.data, token: api.getToken() });
        }).catch(() => {
            set(null);
        });
    },
};

export const generateBasicToken = (email: string, password: string): string => {
    return Buffer.from(`${email}:${password}`).toString("base64");
}

export interface User {
    id: string,
    email: string,
    createdAt: Date,
    lastActivityAt: Date
}
