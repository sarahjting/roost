import { writable } from "svelte/store";
import axios, { type AxiosResponse } from "axios";
import { api } from "../lib/util/api";
import { Buffer } from "buffer";

export const store = writable(null);

export interface User {
    id: string,
    email: string,
    createdAt: Date,
    lastActivityAt: Date
}

export const getUserDetails = (token: string): Promise<User|null> => {
    return axios.get(api("me"), {
        headers: {
            Authorization: `Basic ${token}`
        },
    }).then((res: AxiosResponse): User => {
        return {
            ...res.data,
            token
        };
    }).catch(() => {
        return null;
    });
}

export const generateBasicToken = (email: string, password: string): string => {
    return Buffer.from(`${email}:${password}`).toString("base64");
}

export interface CreateUserForm {
    email: string,
    password: string,
}

export const createUser = (user: CreateUserForm) => {
    return axios.post(api("users"), user)
        .then((res): User => {
            return res.data;
        });
}
