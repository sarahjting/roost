import { goto } from '$app/navigation';
import { store } from '../../../hooks/auth';

export function load() {
    store.subscribe((user) => {
        if (user) {
            goto("/");
        }

        return { user };
    });
}
