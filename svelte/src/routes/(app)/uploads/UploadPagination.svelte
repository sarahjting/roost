<script lang="ts">
	import ArrowLeftIcon from '$lib/components/icons/ArrowLeftIcon.svelte';
	import ArrowRightIcon from '$lib/components/icons/ArrowRightIcon.svelte';
	import type { PageablePage } from '$lib/types/common/PageablePage';
	import { createEventDispatcher } from 'svelte';

	const dispatch = createEventDispatcher();

	export let page: PageablePage<any>;
	let pageNumbers: Array<number | null> = [];

	$: if (page) {
		pageNumbers = [];
		for (let i = 0; i < Math.min(2, page.totalPages); i++) {
			pageNumbers.push(i);
		}
		if (page.number - 1 > 2) {
			pageNumbers.push(null);
		}
		for (
			let i = Math.max(2, page.number - 1);
			i < Math.min(page.number + 2, page.totalPages);
			i++
		) {
			pageNumbers.push(i);
		}
		if (page.number + 1 < page.totalPages - 3) {
			pageNumbers.push(null);
		}
		for (let i = Math.max(2, page.number + 2, page.totalPages - 2); i < page.totalPages; i++) {
			pageNumbers.push(i);
		}
	}
</script>

<nav class="flex items-center justify-between border-t border-gray-200 px-4 sm:px-0">
	<div class="-mt-px flex w-0 flex-1">
		<a
			href="#"
			class="inline-flex items-center border-t-2 border-transparent pt-4 pr-1 text-sm font-medium {page.first
				? 'text-gray-200 cursor-not-allowed'
				: 'text-gray-500 hover:border-gray-300 hover:text-gray-700'}"
			on:click={() => !page.first && dispatch('go', page.number - 1)}
		>
			<ArrowLeftIcon className="mx-1 h-5 w-5" />
		</a>
	</div>
	<div class="hidden md:-mt-px md:flex">
		{#each pageNumbers as pageNumber, i}
			{#if pageNumber == null}
				<span
					class="inline-flex items-center border-t-2 border-transparent px-4 pt-4 text-sm font-medium text-gray-500"
					>...</span
				>
			{:else}
				<a
					href="#"
					class="inline-flex items-center border-t-2 px-4 pt-4 text-sm font-medium 
            {pageNumber === page.number
						? 'border-rose-500 text-rose-600'
						: 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'}"
					on:click={() => pageNumber !== page.number && dispatch('go', pageNumber)}
					>{pageNumber + 1}</a
				>
			{/if}
		{/each}
	</div>
	<div class="-mt-px flex w-0 flex-1 justify-end">
		<a
			href="#"
			class="inline-flex items-center border-t-2 border-transparent pt-4 pl-1 text-sm font-medium {page.last
				? 'text-gray-200 cursor-not-allowed'
				: 'text-gray-500 hover:border-gray-300 hover:text-gray-700'}"
			on:click={() => !page.last && dispatch('go', page.number + 1)}
		>
			<ArrowRightIcon className="mx-1 h-5 w-5" />
		</a>
	</div>
</nav>
