import { redirect } from '@sveltejs/kit';
import { auth } from '$lib/stores/auth';
import { browser } from '$app/environment';
import { goto } from '$app/navigation';

export function load() {
    auth.logout();
    if (!browser) {
        throw redirect(302, "/");
    }
    goto("/");
}
