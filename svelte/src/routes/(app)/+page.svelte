<script>
	import LayoutSidebar from './LayoutSidebar.svelte';
</script>

<main class="flex-1 overflow-y-auto">
	<div class="mx-auto max-w-7xl px-4 sm:px-6 pt-4 sm:pt-0 lg:px-6">
		<div class="flex">
			<div class="ml-6 flex items-center rounded-lg bg-gray-100 p-0.5 sm:hidden">
				<button
					type="button"
					class="rounded-md p-1.5 text-gray-400 hover:bg-white hover:shadow-sm focus:outline-none focus:ring-2 focus:ring-inset focus:ring-rose-500"
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
					class="ml-0.5 rounded-md bg-white p-1.5 text-gray-400 shadow-sm focus:outline-none focus:ring-2 focus:ring-inset focus:ring-rose-500"
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

		<!-- Tabs -->
		<div class="mt-3 sm:mt-2">
			<div class="sm:hidden">
				<label for="tabs" class="sr-only">Select a tab</label>
				<!-- Use an "onChange" listener to redirect the user to the selected tab URL. -->
				<select
					id="tabs"
					name="tabs"
					class="block w-full rounded-md border-gray-300 py-2 pl-3 pr-10 text-base focus:border-rose-500 focus:outline-none focus:ring-rose-500 sm:text-sm"
				>
					<option selected>Recent</option>
					<option>Favorites</option>
				</select>
			</div>
			<div class="hidden sm:block">
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
					<div class="ml-6 hidden items-center rounded-lg bg-gray-100 p-0.5 sm:flex">
						<button
							type="button"
							class="rounded-md p-1.5 text-gray-400 hover:bg-white hover:shadow-sm focus:outline-none focus:ring-2 focus:ring-inset focus:ring-rose-500"
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
							class="ml-0.5 rounded-md bg-white p-1.5 text-gray-400 shadow-sm focus:outline-none focus:ring-2 focus:ring-inset focus:ring-rose-500"
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

		<!-- Gallery -->
		<section class="mt-8 pb-16" aria-labelledby="gallery-heading">
			<h2 id="gallery-heading" class="sr-only">Recently viewed</h2>
			<ul
				role="list"
				class="grid grid-cols-2 gap-x-4 gap-y-8 sm:grid-cols-3 sm:gap-x-6 md:grid-cols-4 lg:grid-cols-3 xl:grid-cols-4 xl:gap-x-8"
			>
				{#each Array.from(Array(100).keys()) as i}
					<li class="relative">
						<div
							class="{i == 0
								? 'ring-4 ring-rose-500 ring-offset-2'
								: 'focus-within:ring-4 focus-within:ring-rose-500 focus-within:ring-offset-2 focus-within:ring-offset-gray-100'} 
                aspect-w-10 aspect-h-7 group block w-full overflow-hidden rounded-lg bg-gray-100"
						>
							<img
								src="https://cdn.discordapp.com/attachments/281968307576504320/1080673318383923240/placeholder.jpg"
								alt=""
								class="pointer-events-none object-cover {i == 0 ? '' : 'group-hover:opacity-75'}"
							/>
							<button type="button" class="absolute inset-0 focus:outline-none">
								<span class="sr-only">View details for IMG_4985.HEIC</span>
							</button>
						</div>
						<p class="pointer-events-none mt-2 block truncate text-sm font-medium text-gray-900">
							IMG_4985.HEIC
						</p>
						<p class="pointer-events-none block text-sm font-medium text-gray-500">3.9 MB</p>
					</li>
				{/each}

				<!-- More files... -->
			</ul>
		</section>
	</div>
</main>

<!-- Details sidebar -->
<LayoutSidebar />
