import { redirect } from '@sveltejs/kit';
import { auth } from '../../../stores/auth';

export function load() {
    auth.subscribe((user) => {
        if (user) {
            throw redirect(302, "/");
        }
    })
}
