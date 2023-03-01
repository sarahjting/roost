import { PUBLIC_API_ROOT } from "$env/static/public";

export function api(path: string): string {
    return `${PUBLIC_API_ROOT}/api/${path}`;
}
