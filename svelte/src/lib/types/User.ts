import type { Storage } from "./Storage";

export type User = {
    id: string,
    email: string,
    createdAt: Date,
    lastActivityAt: Date,
    defaultStorage: Storage,
};
