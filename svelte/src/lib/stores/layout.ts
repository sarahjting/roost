import { writable } from "svelte/store";

export const store = writable({
    isMobileMenuOpen: false,
});