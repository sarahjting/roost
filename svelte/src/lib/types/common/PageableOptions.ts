import type { SortOptions } from "./SortOptions";

export type PageableOptions = {
    offset: number,
    pageNumber:  number,
    pageSize: number,
    paged: boolean,
    sort: SortOptions,
    unpaged: boolean,
};
