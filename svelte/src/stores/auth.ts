import { writable } from "svelte/store";
import axios, { type AxiosResponse } from "axios";
import { api } from "../lib/util/api";
import { Buffer } from "buffer";

export const auth = (() => {
    const {subscribe, set} = writable(null);
    return {
        subscribe,
        login: (email: string, password: string) => {
            const token = generateBasicToken(email, password);
            return axios.get(api("me"), {
                headers: {
                    Authorization: `Basic ${token}`
                },
            }).then((res: AxiosResponse) => {
                set({ ...res.data, token });
            });
        },
        logout: () => {
            set(null);
        },
    }
})();

export const generateBasicToken = (email: string, password: string): string => {
    return Buffer.from(`${email}:${password}`).toString("base64");
}

export interface User {
    id: string,
    email: string,
    createdAt: Date,
    lastActivityAt: Date
}
