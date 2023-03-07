<script lang="ts">
	import type { PageablePage } from '$lib/types/common/PageablePage';
	import type { Upload } from '$lib/types/Upload';
	import { UploadType, UploadTypeOptions } from '$lib/util/uploads';
	import { createEventDispatcher } from 'svelte';
	import { formatBytes } from '$lib/util/string';

	const dispatch = createEventDispatcher();
	export let uploads: PageablePage<Upload>;
	export let currentUpload: Upload | null = null;
</script>

<!-- Gallery -->
<section class="my-8" aria-labelledby="gallery-heading">
	<h2 id="gallery-heading" class="sr-only">Recently viewed</h2>
	<ul
		role="list"
		class="grid grid-cols-2 gap-x-4 gap-y-8 sm:grid-cols-3 sm:gap-x-6 md:grid-cols-4 lg:grid-cols-3 xl:grid-cols-4 xl:gap-x-8"
	>
		{#each uploads.content as upload, i}
			<li class="relative">
				<!-- svelte-ignore a11y-click-events-have-key-events -->
				<div
					class="{upload.id == currentUpload?.id
						? 'ring-4 ring-rose-500 ring-offset-2'
						: 'focus-within:ring-4 focus-within:ring-rose-500 focus-within:ring-offset-2 focus-within:ring-offset-gray-100'} 
                aspect-w-10 aspect-h-7 group block w-full overflow-hidden rounded-lg bg-gray-100"
					on:click={() => dispatch('select', upload)}
				>
					{#if upload.type == UploadType.IMAGE}
						<img
							src={upload.fileUrl}
							alt={upload.fileName}
							class="pointer-events-none object-cover {upload.id == currentUpload?.id
								? ''
								: 'group-hover:opacity-75'}"
						/>
					{:else}
						<div class="w-full h-full bg-gray-200 flex justify-center items-center">
							<svelte:component
								this={UploadTypeOptions[upload.type].icon}
								className="w-6 h-6 text-gray-300"
							/>
						</div>
					{/if}
					<button type="button" class="absolute inset-0 focus:outline-none">
						<span class="sr-only">View details for {upload.fileName}</span>
					</button>
				</div>
				<p class="pointer-events-none mt-2 block truncate text-sm font-medium text-gray-900">
					{upload.fileName}
				</p>
				<p class="pointer-events-none block text-sm font-medium text-gray-500">
					{formatBytes(upload.fileSize)}
				</p>
			</li>
		{/each}
	</ul>
</section>
