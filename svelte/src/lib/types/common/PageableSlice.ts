import type { PageableOptions } from "./PageableOptions";
import type { SortOptions } from "./SortOptions";

export type PageableSlice<T> = {
    content: Array<T>,
    empty: boolean,
    first: boolean,
    last: boolean,
    number: number,
    numberOfElements: number,
    size: number,
    pageable: PageableOptions,
    sort: SortOptions,
}
