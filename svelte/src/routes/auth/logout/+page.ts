import { redirect } from '@sveltejs/kit';
import { auth } from '$lib/stores/auth';

export function load() {
    auth.logout();
    throw redirect(302, "/");
}
