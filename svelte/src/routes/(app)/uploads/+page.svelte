<script lang="ts">
	import type { PageablePage } from '$lib/types/common/PageablePage';
	import type { Upload } from '$lib/types/Upload';
	import { indexUploads } from '$lib/util/uploads';
	import UploadGallery from './UploadGallery.svelte';
	import UploadSidebar from './UploadSidebar.svelte';
	import UploadFilters from './UploadFilters.svelte';
	import LoadingPage from '$lib/components/common/LoadingPage.svelte';
	import { uploadsLastUpdatedAt } from '$lib/stores/layout';
	import UploadPagination from './UploadPagination.svelte';
	import { writable } from 'svelte/store';

	let uploads: PageablePage<Upload> | null = null;
	let currentUpload: Upload | null = null;
	let currentPage = writable(0);

	uploadsLastUpdatedAt.subscribe(loadPage);
	currentPage.subscribe(() => {
		uploads = null;
		loadPage();
	});

	function loadPage() {
		indexUploads({ page: $currentPage }).then((res: PageablePage<Upload>) => {
			uploads = res;
			if (currentUpload == null && uploads.content.length > 0) {
				currentUpload = uploads.content[0];
			}
		});
	}
</script>

<main class="flex-1 overflow-y-auto">
	<div class="mx-auto max-w-7xl px-4 sm:px-6 pt-4 sm:pt-0 lg:px-6">
		<!-- Tabs -->
		<UploadFilters />

		{#if uploads === null}
			<LoadingPage />
		{:else}
			<UploadGallery {uploads} {currentUpload} on:select={(e) => (currentUpload = e.detail)} />
			<UploadPagination page={uploads} on:go={(e) => currentPage.set(e.detail)} />
		{/if}
	</div>
</main>

<!-- Details sidebar -->
<UploadSidebar {currentUpload} />
