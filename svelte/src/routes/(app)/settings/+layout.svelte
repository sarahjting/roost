<script>
	import CrowRegular from '../../../components/icons/CrowRegular.svelte';
	import KeyIcon from '../../../components/icons/KeyIcon.svelte';
	import WarehouseIcon from '../../../components/icons/WarehouseIcon.svelte';
	import SettingsNavbarLink from './SettingsNavbarLink.svelte';
</script>

<div class="flex h-full w-full">
	<div class="flex min-w-0 flex-1 flex-col overflow-hidden">
		<main class="flex flex-1 overflow-hidden">
			<div class="flex flex-1 flex-col overflow-y-auto xl:overflow-hidden">
				<!-- Breadcrumb -->
				<nav aria-label="Breadcrumb" class="border-b border-slate-200 bg-white xl:hidden">
					<div class="mx-auto flex max-w-3xl items-start py-3 px-4 sm:px-6 lg:px-8">
						<a
							href="#"
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
					<!-- Secondary sidebar -->
					<nav
						aria-label="Sections"
						class="hidden w-96 flex-shrink-0 border-r border-slate-200 bg-white xl:flex xl:flex-col"
					>
						<div class="flex h-16 flex-shrink-0 items-center border-b border-slate-200 px-6">
							<p class="text-lg font-medium text-slate-900">Settings</p>
						</div>
						<div class="min-h-0 flex-1 overflow-y-auto">
							<SettingsNavbarLink
								active={false}
								icon={CrowRegular}
								title="Profile"
								description="Public profile and display settings."
								to="/settings/profile"
							/>
							<SettingsNavbarLink
								active={false}
								icon={KeyIcon}
								title="Security"
								description="Email and password settings."
								to="/settings/security"
							/>
							<SettingsNavbarLink
								active={false}
								icon={WarehouseIcon}
								title="Storages"
								description="Link your B2 or other storage accounts."
								to="/settings/storages"
							/>
						</div>
					</nav>

					<!-- Main content -->
					<div class="flex-1 xl:overflow-y-auto">
						<div class="mx-auto py-10 px-4 sm:px-6 lg:py-12 lg:px-8">
							<slot />
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</div>
