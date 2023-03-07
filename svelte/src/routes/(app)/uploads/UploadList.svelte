<script lang="ts">
	import type { PageablePage } from '$lib/types/common/PageablePage';
	import type { Upload } from '$lib/types/Upload';
	import { UploadType, UploadTypeOptions } from '$lib/util/uploads';
	import { createEventDispatcher } from 'svelte';
	import { formatBytes, formatDate } from '$lib/util/string';

	const dispatch = createEventDispatcher();
	export let uploads: PageablePage<Upload>;
	export let currentUpload: Upload | null = null;
</script>

<table class="min-w-full divide-y divide-gray-300">
	<thead>
		<tr>
			<th
				scope="col"
				class="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-0"
			/>
			<th scope="col" class="py-3.5 px-3 text-left text-sm font-semibold text-gray-900 sm:pl-0">
				File
			</th>
			<th scope="col" class="px-2 py-3.5 text-left text-sm font-semibold text-gray-900"> Size </th>
			<th scope="col" class="px-2 py-3.5 text-left text-sm font-semibold text-gray-900">
				Created at
			</th>
		</tr>
	</thead>
	<tbody class="divide-y divide-gray-200 bg-white">
		{#each uploads.content as upload (upload.id)}
			<tr on:click={() => dispatch('select', upload)} class="group cursor-pointer hover:opacity-80">
				<td
					class="whitespace-nowrap py-2 pl-4 pr-1 text-sm font-medium text-gray-900 sm:pl-0 flex justify-left items-center border-l-4 {currentUpload?.id ===
					upload.id
						? 'border-rose-500'
						: 'border-transparent'}"
				>
					<div
						class="h-12 w-12 ml-4 rounded bg-gray-200 flex items-center overflow-hidden ring-2 ring-offset-1 {currentUpload?.id ===
						upload.id
							? 'ring-rose-500'
							: 'ring-transparent'}"
					>
						{#if upload.type === UploadType.IMAGE}
							<img src={upload.fileUrl} class="object-cover" />
						{:else}
							<svelte:component
								this={UploadTypeOptions[upload.type].icon}
								className="w-6 h-6 text-gray-300 mx-auto"
							/>
						{/if}
					</div>
				</td>
				<td
					class="whitespace-nowrap py-4 text-sm {currentUpload?.id === upload.id
						? 'text-rose-600 font-medium'
						: 'text-gray-500'}"
				>
					{upload.fileName}
				</td>
				<td class="whitespace-nowrap px-2 py-4 text-sm text-gray-500">
					{formatBytes(upload.fileSize)}
				</td>
				<td class="whitespace-nowrap px-2 py-4 text-sm text-gray-500"
					>{formatDate(upload.createdAt)}</td
				>
			</tr>
		{/each}
	</tbody>
</table>
