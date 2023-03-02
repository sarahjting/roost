<script lang="ts">
	export let storages: Array<any>;
	import PrimaryButton from '../../../../../components/buttons/PrimaryButton.svelte';
	import SecondaryButton from '../../../../../components/buttons/SecondaryButton.svelte';
	import WarehouseIcon from '../../../../../components/icons/WarehouseIcon.svelte';
</script>

<div class="overflow-hidden bg-white shadow sm:rounded-md">
	<ul role="list" class="divide-y divide-gray-200">
		{#each storages as storage, i}
			<li>
				<div class="flex flex-col sm:flex-row justify-between hover:bg-gray-50">
					<!-- Left -->
					<div class="flex-1 px-4 py-4 sm:px-6">
						<div class="truncate text-sm font-medium text-rose-600">
							{storage.name}
							{#if i == 0}
								<span
									class="inline-flex rounded-full bg-rose-100 px-2 text-xs ml-2 font-semibold text-rose-800"
								>
									Default storage
								</span>
							{/if}
						</div>

						<div class="mt-2 sm:flex sm:justify-between">
							<div class="sm:flex">
								<p class="flex items-center text-sm text-gray-500">
									<WarehouseIcon className="text-gray-400 w-5 h-5 mr-2" />
									{storage.driver}
								</p>
							</div>
						</div>
					</div>
					<!-- Right -->

					<div class="flex-1 items-center px-4 py-4 sm:px-6 text-right">
						<SecondaryButton disabled={i === 0} size="xs">Set as default</SecondaryButton>
						<PrimaryButton size="xs">Remove</PrimaryButton>
					</div>
				</div>
			</li>
		{/each}
	</ul>
</div>
