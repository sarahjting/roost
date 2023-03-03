<script lang="ts">
	import type { PageableSlice } from '$lib/types/common/PageableSlice';
	import { api } from '$lib/util/api';
	import type { AxiosResponse } from 'axios';
	import LoadingPage from '../../../../../components/common/LoadingPage.svelte';
	import NewStorageButton from './NewStorageButton.svelte';
	import NewStorageModal from './NewStorageModal.svelte';
	import StorageEmptyState from './StorageEmptyState.svelte';
	import StorageIndex from './StorageIndex.svelte';

	let storages: Array<Storage> | null = null;
	let showNewStorageModal: boolean = false;

	function loadStorages() {
		api.get('me/storages').then((res: AxiosResponse<PageableSlice<Storage>>) => {
			storages = res.data.content;
		});
	}

	loadStorages();
</script>

{#if storages === null}
	<LoadingPage />
{:else}
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
		<StorageEmptyState on:click={() => (showNewStorageModal = true)} />
	{:else}
		<StorageIndex {storages} on:complete={loadStorages} />
	{/if}
{/if}

<NewStorageModal
	show={showNewStorageModal}
	on:close={() => (showNewStorageModal = false)}
	on:complete={() => {
		showNewStorageModal = false;
		loadStorages();
	}}
/>
