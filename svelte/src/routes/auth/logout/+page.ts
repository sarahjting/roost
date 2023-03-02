import { redirect } from '@sveltejs/kit';
import { auth } from '../../../stores/auth';

export function load() {
    auth.logout();
    throw redirect(302, "/");
}
