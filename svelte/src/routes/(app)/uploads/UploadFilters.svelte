<script lang="ts">
	import { createEventDispatcher } from 'svelte';
	import { LAYOUT_MODE } from './+page.svelte';

	export let layoutMode: number;
	const dispatch = createEventDispatcher();
	console.log(LAYOUT_MODE, layoutMode);
</script>

<div class="mt-3 sm:mt-2">
	<div>
		<div class="flex items-center border-b border-gray-200">
			<nav class="-mb-px flex flex-1 space-x-6 xl:space-x-8" aria-label="Tabs">
				<!-- Current: "border-rose-500 text-rose-600", Default: "border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700" -->
				<a
					href="#"
					aria-current="page"
					class="border-rose-500 text-rose-600 whitespace-nowrap border-b-2 py-4 px-1 text-sm font-medium"
					>Recent</a
				>

				<a
					href="#"
					class="border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 whitespace-nowrap border-b-2 py-4 px-1 text-sm font-medium"
					>Favorites</a
				>
			</nav>
			<div class="ml-6 items-center rounded-lg bg-gray-100 p-0.5">
				<button
					type="button"
					class="rounded-md p-1.5 text-gray-400  focus:outline-none focus:ring-2 focus:ring-inset focus:ring-rose-500
                    {layoutMode === LAYOUT_MODE.LIST
						? 'bg-white shadow-sm'
						: 'hover:bg-white hover:shadow-sm'}"
					on:click={() => dispatch('layout-mode', LAYOUT_MODE.LIST)}
				>
					<svg class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
						<path
							fill-rule="evenodd"
							d="M2 3.75A.75.75 0 012.75 3h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 3.75zm0 4.167a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75a.75.75 0 01-.75-.75zm0 4.166a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75a.75.75 0 01-.75-.75zm0 4.167a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75a.75.75 0 01-.75-.75z"
							clip-rule="evenodd"
						/>
					</svg>
					<span class="sr-only">Use list view</span>
				</button>
				<button
					type="button"
					class="ml-0.5 rounded-md  p-1.5 text-gray-400  focus:outline-none focus:ring-2 focus:ring-inset focus:ring-rose-500
                    {layoutMode === LAYOUT_MODE.GALLERY
						? 'bg-white shadow-sm'
						: 'hover:bg-white hover:shadow-sm'}"
					on:click={() => dispatch('layout-mode', LAYOUT_MODE.GALLERY)}
				>
					<svg class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
						<path
							fill-rule="evenodd"
							d="M4.25 2A2.25 2.25 0 002 4.25v2.5A2.25 2.25 0 004.25 9h2.5A2.25 2.25 0 009 6.75v-2.5A2.25 2.25 0 006.75 2h-2.5zm0 9A2.25 2.25 0 002 13.25v2.5A2.25 2.25 0 004.25 18h2.5A2.25 2.25 0 009 15.75v-2.5A2.25 2.25 0 006.75 11h-2.5zm9-9A2.25 2.25 0 0011 4.25v2.5A2.25 2.25 0 0013.25 9h2.5A2.25 2.25 0 0018 6.75v-2.5A2.25 2.25 0 0015.75 2h-2.5zm0 9A2.25 2.25 0 0011 13.25v2.5A2.25 2.25 0 0013.25 18h2.5A2.25 2.25 0 0018 15.75v-2.5A2.25 2.25 0 0015.75 11h-2.5z"
							clip-rule="evenodd"
						/>
					</svg>
					<span class="sr-only">Use grid view</span>
				</button>
			</div>
		</div>
	</div>
</div>
