import { redirect } from "@sveltejs/kit";
import { store } from "../../hooks/auth";

export async function load() {
    store.subscribe((user) => {
        console.log(user);
        if (!user) {
            throw redirect(302, "/auth/login");
        }

        return { user };
    });
}