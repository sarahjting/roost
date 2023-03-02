<script lang="ts">
	import { createEventDispatcher } from 'svelte';
	import WarehouseIcon from '../../../../../components/icons/WarehouseIcon.svelte';
	import BasicModal from '../../../../../components/modals/BasicModal.svelte';
	const dispatch = createEventDispatcher();

	let driver = 'b2';

	export let show: boolean;
</script>

<BasicModal
	icon={WarehouseIcon}
	bind:show
	doCloseOnClickOut={false}
	on:close={() => dispatch('close')}
>
	<div class="mt-3 sm:mt-0 sm:ml-4 sm:text-left w-full">
		<div class="grid divide-gray-200 divide-y">
			<h3 class="text-base font-semibold leading-6 text-gray-900 my-3" id="modal-title">
				Add storage
			</h3>

			<div class="sm:grid sm:grid-cols-3 sm:items-start gap-4 py-3">
				<label for="name" class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
					Storage name
				</label>
				<div class="mt-1 sm:col-span-2 sm:mt-0">
					<input
						type="text"
						name="name"
						id="name"
						class="block w-full max-w-lg rounded-md border-gray-300 shadow-sm focus:border-rose-500 focus:ring-rose-500 sm:max-w-xs sm:text-sm"
					/>
					<div class="text-gray-400 text-xs font-light">
						For personal reference only, is not used for linking.
					</div>
				</div>
			</div>

			<div class="sm:grid sm:grid-cols-3 sm:items-start gap-4 py-3">
				<label for="driver" class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2"
					>Driver</label
				>
				<div class="mt-1 sm:col-span-2 sm:mt-0">
					<select
						bind:value={driver}
						id="driver"
						name="driver"
						class="mt-1 block w-full rounded-md border-gray-300 py-2 pl-3 pr-10 text-base focus:border-rose-500 focus:outline-none focus:ring-rose-500 sm:text-sm"
					>
						<option value="b2" selected>Backblaze B2</option>
						<option value="s3">AWS S3</option>
						<option value="custom">Custom</option>
					</select>
					{#if driver === 'custom'}
						<div class="text-gray-400 text-xs font-light">
							Will work with any S3-compatible provider.
						</div>
					{/if}
				</div>
			</div>

			{#if driver !== 's3'}
				<div class="sm:grid sm:grid-cols-3 sm:items-start gap-4 py-3">
					<label for="endpoint" class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
						Endpoint
					</label>
					<div class="mt-1 sm:col-span-2 sm:mt-0">
						<input
							type="text"
							name="endpoint"
							id="endpoint"
							class="block w-full max-w-lg rounded-md border-gray-300 shadow-sm focus:border-rose-500 focus:ring-rose-500 sm:max-w-xs sm:text-sm"
						/>
						{#if driver === 'b2'}
							<div class="text-gray-400 text-xs font-light">
								Under "Buckets", find the bucket you want to link, and copy in the "Endpoint" URL.
							</div>
						{/if}
					</div>
				</div>
			{:else}
				<div class="sm:grid sm:grid-cols-3 sm:items-start gap-4 py-3">
					<label for="region" class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
						Region
					</label>
					<div class="mt-1 sm:col-span-2 sm:mt-0">
						<input
							type="text"
							name="region"
							id="region"
							class="block w-full max-w-lg rounded-md border-gray-300 shadow-sm focus:border-rose-500 focus:ring-rose-500 sm:max-w-xs sm:text-sm"
						/>
					</div>
				</div>
			{/if}

			<div class="sm:grid sm:grid-cols-3 sm:items-start gap-4 py-3">
				<label for="region" class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
					Access key
				</label>
				<div class="mt-1 sm:col-span-2 sm:mt-0">
					<input
						type="text"
						name="region"
						id="region"
						class="block w-full max-w-lg rounded-md border-gray-300 shadow-sm focus:border-rose-500 focus:ring-rose-500 sm:max-w-xs sm:text-sm"
					/>
					{#if driver === 'b2'}
						<div class="text-gray-400 text-xs font-light">Backblaze's "keyID".</div>
					{/if}
				</div>
			</div>

			<div class="sm:grid sm:grid-cols-3 sm:items-start gap-4 py-3">
				<label for="region" class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
					Secret key
				</label>
				<div class="mt-1 sm:col-span-2 sm:mt-0">
					<input
						type="text"
						name="region"
						id="region"
						class="block w-full max-w-lg rounded-md border-gray-300 shadow-sm focus:border-rose-500 focus:ring-rose-500 sm:max-w-xs sm:text-sm"
					/>
					{#if driver === 'b2'}
						<div class="text-gray-400 text-xs font-light">
							Backblaze's "applicationKey", only revealed once immediately after creating the key.
						</div>
					{/if}
				</div>
			</div>

			<div class="sm:grid sm:grid-cols-3 sm:items-start gap-4 py-3">
				<label for="region" class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
					Bucket name
				</label>
				<div class="mt-1 sm:col-span-2 sm:mt-0">
					<input
						type="text"
						name="region"
						id="region"
						class="block w-full max-w-lg rounded-md border-gray-300 shadow-sm focus:border-rose-500 focus:ring-rose-500 sm:max-w-xs sm:text-sm"
					/>
				</div>
			</div>
		</div>
	</div>
	<div slot="footer">
		<button
			type="button"
			class="mb-3 sm:mb-0 inline-flex w-full justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-base font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-rose-500 focus:ring-offset-2 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
			on:click={() => dispatch('close')}>Cancel</button
		>
		<button
			type="button"
			class="inline-flex w-full justify-center rounded-md border border-transparent bg-rose-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2 sm:ml-3 sm:w-auto sm:text-sm"
			>Add storage</button
		>
	</div>
</BasicModal>
