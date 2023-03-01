import { redirect } from "@sveltejs/kit";
import { store } from "../../hooks/auth";

export async function load() {
    store.subscribe((user) => {
        if (!user) {
            throw redirect(302, "/auth/login");
        }

        return { user };
    });
}