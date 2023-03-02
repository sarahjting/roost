<script>
	import SettingsNavbar from '../SettingsNavbar.svelte';
</script>

<div class="flex h-full w-full">
	<div class="flex min-w-0 flex-1 flex-col overflow-hidden">
		<main class="flex flex-1 overflow-hidden">
			<div class="flex flex-1 flex-col overflow-y-auto xl:overflow-hidden">
				<!-- Breadcrumb -->
				<nav aria-label="Breadcrumb" class="border-b border-slate-200 bg-white xl:hidden">
					<div class="mx-auto flex max-w-3xl items-start py-3 px-4 sm:px-6 lg:px-8">
						<a
							href="/settings"
							class="-ml-1 inline-flex items-center space-x-3 text-sm font-medium text-slate-900"
						>
							<svg
								class="h-5 w-5 text-slate-400"
								viewBox="0 0 20 20"
								fill="currentColor"
								aria-hidden="true"
							>
								<path
									fill-rule="evenodd"
									d="M12.79 5.23a.75.75 0 01-.02 1.06L8.832 10l3.938 3.71a.75.75 0 11-1.04 1.08l-4.5-4.25a.75.75 0 010-1.08l4.5-4.25a.75.75 0 011.06.02z"
									clip-rule="evenodd"
								/>
							</svg>
							<span>Settings</span>
						</a>
					</div>
				</nav>

				<div class="flex flex-1 xl:overflow-hidden">
					<SettingsNavbar />

					<!-- Main content -->
					<div class="flex-1 xl:overflow-y-auto">
						<div class="mx-auto py-4 px-4 sm:px-6 sm:py-6 lg:py-8 lg:px-8">
							<slot />
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</div>
