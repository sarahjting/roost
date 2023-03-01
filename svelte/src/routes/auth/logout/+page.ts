import { goto } from '$app/navigation';
import { store } from '../../../hooks/auth';

export function load() {
    store.set(null);
    goto("/");
}
