import { auth } from "$lib/stores/auth";
import { redirect } from "@sveltejs/kit";

auth.subscribe((user) => {
    if (!user) {
        throw redirect(302, '/auth/login');
    }
});
