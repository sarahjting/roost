<script lang="ts">
	import { invalidate } from '$app/navigation';
	import CheckCircleIcon from '$lib/components/icons/CheckCircleIcon.svelte';
	import ImageAddIcon from '$lib/components/icons/ImageAddIcon.svelte';
	import SpinnerIcon from '$lib/components/icons/SpinnerIcon.svelte';
	import { createUploads } from '$lib/util/uploads';
	import type { AxiosError } from 'axios';
	import { createEventDispatcher } from 'svelte';
	import BasicModal from '../../modals/BasicModal.svelte';
	import { uploadsLastUpdatedAt } from '$lib/stores/layout';
	const dispatch = createEventDispatcher();

	export let show: boolean = false;

	const STATE = {
		WAITING: 1,
		LOADING: 2,
		ERROR: 3,
		COMPLETE: 4
	};

	let state = STATE.WAITING;

	let files: FileList | null = null;
	let errors = [];

	$: if (files) {
		state = STATE.LOADING;
		errors = [];
		createUploads(files)
			.then(() => {
				uploadsLastUpdatedAt.set(Date.now());
				state = STATE.COMPLETE;
				setTimeout(() => {
					state = STATE.WAITING;
					dispatch('complete');
					files = null;
				}, 1000);
			})
			.catch((e: AxiosError) => {
				state = STATE.ERROR;
				errors = e.response?.data?.errors;
			});
	}
</script>

<BasicModal {show} footer={false} on:close>
	{#if state === STATE.WAITING || state === STATE.ERROR}
		<label
			for="file-upload"
			class="w-full flex cursor-pointer justify-center rounded-md border-2 border-dashed {state ===
			STATE.ERROR
				? 'border-rose-400 hover:border-rose-500'
				: 'border-gray-300 hover:border-gray-400'} px-6 pt-5 pb-6 focus:outline-none focus:ring-2 focus:ring-rose-600 focus:ring-offset-2"
		>
			<div class="space-y-1 py-20 text-center">
				<ImageAddIcon className="mx-auto h-12 w-12 text-gray-400" />
				<div class="text-center text-sm text-gray-600">
					<input
						bind:files
						id="file-upload"
						name="file-upload"
						type="file"
						class="sr-only"
						multiple
					/>
					Upload a file or drag and drop
				</div>
				<p class="text-xs text-gray-500">PNG, JPG, GIF up to 10MB</p>
				{#if state === STATE.ERROR}
					<div class="pt-4 text-xs text-rose-500">An error occurred during upload</div>
					{#if errors.length > 0}
						<ul class="pt-4 text-xs text-rose-500">
							{#each errors as error}
								<li>{error.message}</li>
							{/each}
						</ul>
					{/if}
				{/if}
			</div>
		</label>
	{:else if state === STATE.LOADING}
		<div
			class="w-full flex flex-col justify-center rounded-md border-2 border border-gray-300 px-6 pt-5 pb-6 space-y-6 divide-y divide-gray-200"
		>
			<div class="text-center">
				<SpinnerIcon className="w-16 h-16 animate-spin mb-2 opacity-20 mx-auto" />
			</div>
			{#if files}
				<ul class="divide-y divide-gray-200">
					{#each Array.from(files) as file}
						<li class="py-3">
							<div class="flex items-center">
								<img
									src={URL.createObjectURL(file)}
									alt={file.name}
									class="mr-6 h-16 w-16 rounded object-cover object-center"
								/>
								<div>
									<div class="font-medium text-rose-600">{file.name}</div>
									<div class="mt-1 text-gray-400">{file.type}</div>
								</div>
							</div>
						</li>
					{/each}
				</ul>
			{/if}
		</div>
	{:else if state === STATE.COMPLETE}
		<div
			class="w-full flex justify-center rounded-md border-2 border border-gray-300 px-6 pt-5 pb-6"
		>
			<div class="text-center">
				<CheckCircleIcon className="mx-auto h-12 w-12 text-gray-300" />
				<div class="flex text-gray-400 mt-3">Upload complete</div>
			</div>
		</div>
	{/if}
</BasicModal>
