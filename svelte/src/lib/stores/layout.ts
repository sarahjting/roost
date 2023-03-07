import { writable } from "svelte/store";

export const isMobileMenuOpen = writable(false);

// we use this to notify when to reload the gallery page
export const uploadsLastUpdatedAt = writable(null);
