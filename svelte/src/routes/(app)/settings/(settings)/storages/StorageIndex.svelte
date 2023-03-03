<script lang="ts">
	import { createEventDispatcher } from 'svelte';
	import { auth } from '$lib/stores/auth';
	export let storages: Array<any>;
	import PrimaryButton from '$lib/components/buttons/PrimaryButton.svelte';
	import SecondaryButton from '$lib/components/buttons/SecondaryButton.svelte';
	import WarehouseIcon from '$lib/components/icons/WarehouseIcon.svelte';
	import {
		deleteStorage as apiDeleteStorage,
		setStorageDefault as apiSetStorageDefault
	} from '$lib/util/storages';
	import type { Storage } from '$lib/types/Storage';
	const dispatch = createEventDispatcher();

	let errors: Array<{ id: string; message: string }> = [];

	function setStorageDefault(storage: Storage) {
		errors = [];
		return apiSetStorageDefault(storage.id)
			.then(() => dispatch('complete'))
			.catch(() => {
				errors.push({ id: storage.id, message: 'An error occurred, please try again.' });
				errors = errors;
			});
	}

	function deleteStorage(storage: Storage) {
		// TODO: add an error dialog
		errors = [];
		apiDeleteStorage(storage.id)
			.then(() => {
				dispatch('complete');
			})
			.catch(() => {
				errors.push({ id: storage.id, message: 'An error occurred, please try again.' });
				errors = errors;
			});
	}
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
						<div>
							<SecondaryButton
								disabled={storage.id === $auth.defaultStorage?.id}
								size="xs"
								on:click={() => i !== $auth.defaultStorage?.id && setStorageDefault(storage)}
								>Set as default</SecondaryButton
							>
							<PrimaryButton size="xs" on:click={() => deleteStorage(storage)}>Remove</PrimaryButton
							>
						</div>

						{#if errors.length && errors.find((e) => e.id == storage.id)}
							<div class="mt-2 text-rose-500 text-sm">
								{errors.find((e) => e.id == storage.id).message}
							</div>
						{/if}
					</div>
				</div>
			</li>
		{/each}
	</ul>
</div>
