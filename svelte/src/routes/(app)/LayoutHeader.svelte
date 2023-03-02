<script>
	let isMobileMenuOpen = false;
	let isDropdownMenuOpen = false;
</script>

<header class="w-full">
	<div class="relative z-10 flex h-16 flex-shrink-0 border-b border-gray-200 bg-white shadow-sm">
		<button
			type="button"
			class="border-r border-gray-200 px-4 text-gray-500 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-rose-500 md:hidden"
		>
			<span class="sr-only">Open sidebar</span>
			<svg
				class="h-6 w-6"
				fill="none"
				viewBox="0 0 24 24"
				stroke-width="1.5"
				stroke="currentColor"
				aria-hidden="true"
			>
				<path
					stroke-linecap="round"
					stroke-linejoin="round"
					d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25H12"
				/>
			</svg>
		</button>
		<div class="flex flex-1 justify-between px-4 sm:px-6">
			<div class="flex flex-1">
				<form class="flex w-full md:ml-0" action="#" method="GET">
					<label for="desktop-search-field" class="sr-only">Search all files</label>
					<label for="mobile-search-field" class="sr-only">Search all files</label>
					<div class="relative w-full text-gray-400 focus-within:text-gray-600">
						<div class="pointer-events-none absolute inset-y-0 left-0 flex items-center">
							<svg
								class="h-5 w-5 flex-shrink-0"
								viewBox="0 0 20 20"
								fill="currentColor"
								aria-hidden="true"
							>
								<path
									fill-rule="evenodd"
									d="M9 3.5a5.5 5.5 0 100 11 5.5 5.5 0 000-11zM2 9a7 7 0 1112.452 4.391l3.328 3.329a.75.75 0 11-1.06 1.06l-3.329-3.328A7 7 0 012 9z"
									clip-rule="evenodd"
								/>
							</svg>
						</div>
						<input
							name="mobile-search-field"
							id="mobile-search-field"
							class="h-full w-full border-transparent py-2 pl-8 pr-3 text-base text-gray-900 placeholder-gray-500 focus:border-transparent focus:placeholder-gray-400 focus:outline-none focus:ring-0 sm:hidden"
							placeholder="Search"
							type="search"
						/>
						<input
							name="desktop-search-field"
							id="desktop-search-field"
							class="hidden h-full w-full border-transparent py-2 pl-8 pr-3 text-base text-gray-900 placeholder-gray-500 focus:border-transparent focus:placeholder-gray-400 focus:outline-none focus:ring-0 sm:block"
							placeholder="Search all files"
							type="search"
						/>
					</div>
				</form>
			</div>
			<div class="ml-2 flex items-center space-x-4 sm:ml-6 sm:space-x-6">
				<!-- Profile dropdown -->
				<div class="relative flex-shrink-0">
					<div>
						<button
							type="button"
							class="flex rounded-full bg-white text-sm focus:outline-none focus:ring-2 focus:ring-rose-500 focus:ring-offset-2"
							id="user-menu-button"
							aria-expanded="false"
							aria-haspopup="true"
							on:click={() => (isMobileMenuOpen = !isMobileMenuOpen)}
						>
							<span class="sr-only">Open user menu</span>
							<img
								class="h-8 w-8 rounded-full"
								src="https://cdn.discordapp.com/attachments/281968307576504320/1080673259919515658/profile.webp"
								alt=""
							/>
						</button>
					</div>

					{#if isMobileMenuOpen}
						<div
							class="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"
							role="menu"
							aria-orientation="vertical"
							aria-labelledby="user-menu-button"
							tabindex="-1"
						>
							<!-- Active: "bg-gray-100", Not Active: "" -->
							<a
								href="#"
								class="block px-4 py-2 text-sm text-gray-700"
								role="menuitem"
								tabindex="-1"
								id="user-menu-item-0">Your profile</a
							>

							<a
								href="/auth/logout"
								class="block px-4 py-2 text-sm text-gray-700"
								role="menuitem"
								tabindex="-1"
								id="user-menu-item-1">Sign out</a
							>
						</div>
					{/if}
				</div>

				<button
					type="button"
					class="flex items-center justify-center rounded-full bg-rose-600 p-1 text-white hover:bg-rose-700 focus:outline-none focus:ring-2 focus:ring-rose-500 focus:ring-offset-2"
				>
					<svg
						class="h-6 w-6"
						fill="none"
						viewBox="0 0 24 24"
						stroke-width="1.5"
						stroke="currentColor"
						aria-hidden="true"
					>
						<path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
					</svg>
					<span class="sr-only">Add file</span>
				</button>
			</div>
		</div>
	</div>
</header>
