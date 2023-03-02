import { redirect } from "@sveltejs/kit";
import { auth } from "../../stores/auth";

export async function load() {
    auth.subscribe((user) => {
        if (!user) {
            throw redirect(302, "/auth/login");
        }

        return { user };
    });
}