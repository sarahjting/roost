
import { auth } from '$lib/stores/auth';
import { redirect } from '@sveltejs/kit';
import { browser } from '$app/environment';
import { goto } from '$app/navigation';

export function load() {
	auth.subscribe((user) => {
		if (!user) {
			if (!browser) {
				throw redirect(302, '/auth/login');
			}
			goto('/auth/login');
		}
	});
}
