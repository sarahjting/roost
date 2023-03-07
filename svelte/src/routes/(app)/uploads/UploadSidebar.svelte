<script lang="ts">
	import CrowRegular from '$lib/components/icons/CrowRegular.svelte';
	import type { Upload } from '$lib/types/Upload';
	import { UploadType, UploadTypeOptions } from '$lib/util/uploads';
	import { formatBytes, formatDate } from '$lib/util/string';

	export let currentUpload: Upload | null = null;
</script>

<aside class="hidden w-96 overflow-y-auto border-l border-gray-200 bg-white p-8 lg:block">
	{#if currentUpload}
		<div class="space-y-3 pb-16">
			<div>
				<div class="block w-full overflow-hidden rounded-lg">
					{#if currentUpload.type === UploadType.IMAGE}
						<img src={currentUpload.fileUrl} alt="" class="object-cover mx-auto" />
					{:else}
						<div class="object-cover bg-gray-200 py-12">
							<svelte:component
								this={UploadTypeOptions[currentUpload.type].icon}
								className="w-6 h-6 text-gray-300 mx-auto"
							/>
						</div>
					{/if}
				</div>
				<div class="mt-4 flex items-start justify-between">
					<div>
						<h2 class="text-lg font-medium text-gray-900">
							<span class="sr-only">Details for </span>{currentUpload.fileName}
						</h2>
						<p class="text-sm font-medium text-gray-500">{formatBytes(currentUpload.fileSize)}</p>
					</div>
					<button
						type="button"
						class="ml-4 flex h-8 w-8 items-center justify-center rounded-full bg-white text-gray-400 hover:bg-gray-100 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-indigo-500"
					>
						<svg
							class="h-6 w-6"
							fill="none"
							viewBox="0 0 24 24"
							stroke-width="1.5"
							stroke="currentColor"
							aria-hidden="true"
						>
							<path
								stroke-linecap="round"
								stroke-linejoin="round"
								d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z"
							/>
						</svg>
						<span class="sr-only">Favorite</span>
					</button>
				</div>
			</div>
			<div>
				<dl class="divide-y divide-gray-200 border-t border-b border-gray-200">
					{#if false}
						<div class="flex justify-between py-3 text-sm font-medium">
							<dt class="text-gray-500">Uploaded by</dt>
							<dd class="whitespace-nowrap text-gray-900">
								<a href="#" class="text-rose-600 flex items-center">
									<CrowRegular />
									<span class="ml-1"> username </span>
								</a>
							</dd>
						</div>
					{/if}

					<div class="flex justify-between py-3 text-sm font-medium">
						<dt class="text-gray-500">Created at</dt>
						<dd class="whitespace-nowrap text-gray-900">{formatDate(currentUpload.createdAt)}</dd>
					</div>

					{#if currentUpload.updatedAt}
						<div class="flex justify-between py-3 text-sm font-medium">
							<dt class="text-gray-500">Modified at</dt>
							<dd class="whitespace-nowrap text-gray-900">{formatDate(currentUpload.updatedAt)}</dd>
						</div>
					{/if}

					{#if currentUpload.type === UploadType.IMAGE}
						<div class="flex justify-between py-3 text-sm font-medium">
							<dt class="text-gray-500">Dimensions</dt>
							<dd class="whitespace-nowrap text-gray-900">
								{currentUpload.imageWidth} x {currentUpload.imageHeight}
							</dd>
						</div>
					{/if}
				</dl>
			</div>
			<div>
				<div class="mt-2 flex items-center justify-between">
					<p class="text-sm italic text-gray-500">Add a description to this image.</p>
					<button
						type="button"
						class="flex h-8 w-8 items-center justify-center rounded-full bg-white text-gray-400 hover:bg-gray-100 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-indigo-500"
					>
						<svg class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
							<path
								d="M2.695 14.763l-1.262 3.154a.5.5 0 00.65.65l3.155-1.262a4 4 0 001.343-.885L17.5 5.5a2.121 2.121 0 00-3-3L3.58 13.42a4 4 0 00-.885 1.343z"
							/>
						</svg>
						<span class="sr-only">Add description</span>
					</button>
				</div>
			</div>
			<div class="flex">
				<button
					type="button"
					class="flex-1 rounded-md border border-transparent bg-rose-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
					>Reupload</button
				>
				<button
					type="button"
					class="ml-3 flex-1 rounded-md border border-gray-300 bg-white py-2 px-4 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
					>Delete</button
				>
			</div>
		</div>
	{/if}
</aside>
