<script lang="ts">
	import PrimaryButton from '../../../../../components/buttons/PrimaryButton.svelte';
	import SecondaryButton from '../../../../../components/buttons/SecondaryButton.svelte';
	import WarehouseIcon from '../../../../../components/icons/WarehouseIcon.svelte';
	import NewStorageButton from './NewStorageButton.svelte';
	import NewStorageModal from './NewStorageModal.svelte';

	const storages: Array<any> = [
		{
			id: '12345-abcdef-ghijklm',
			name: 'Storage name 1',
			driver: 'b2'
		},
		{
			id: '67890-zyxwvu-tsrpon',
			name: 'Storage name 2',
			driver: 's3'
		}
	];

	let showNewStorageModal: boolean = false;
</script>

<div class="sm:flex sm:items-center mb-4">
	<div class="sm:flex-auto">
		<h1 class="text-base font-semibold leading-6 text-gray-900">Storages</h1>
		<p class="mt-2 text-sm text-gray-700">
			Roost will upload your images to the storage of your choice. You will have to take care of
			creating your own storage buckets to host your files.
		</p>
	</div>
	{#if storages.length > 0}
		<div class="mt-4 sm:mt-0 sm:ml-16 sm:flex-none">
			<NewStorageButton on:click={() => (showNewStorageModal = true)} />
		</div>
	{/if}
</div>

{#if storages.length === 0}
	<div
		class="text-center relative block w-full rounded-lg border-2 border-dashed border-gray-300 p-12 text-center"
	>
		<WarehouseIcon className="text-gray-400 h-12 w-12 mx-auto" />
		<h3 class="mt-2 text-sm font-semibold text-gray-900">No storages configured</h3>
		<p class="mt-1 text-sm text-gray-500">
			You will need to create a storage to start uploading files.
		</p>
		<div class="mt-6">
			<NewStorageButton on:click={() => (showNewStorageModal = true)} />
		</div>
	</div>
{:else}
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
{/if}

<NewStorageModal show={showNewStorageModal} on:close={() => (showNewStorageModal = false)} />
