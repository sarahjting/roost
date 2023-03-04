<script lang="ts">
	import type { PageablePage } from '$lib/types/common/PageablePage';
	import type { Upload } from '$lib/types/Upload';
	import { indexUploads } from '$lib/util/uploads';
	import UploadGallery from './UploadGallery.svelte';
	import UploadSidebar from './UploadSidebar.svelte';
	import UploadFilters from './UploadFilters.svelte';
	import LoadingPage from '$lib/components/common/LoadingPage.svelte';

	let uploads: PageablePage<Upload> | null = null;
	indexUploads().then((res: PageablePage<Upload>) => {
		uploads = res;
	});
</script>

<main class="flex-1 overflow-y-auto">
	<div class="mx-auto max-w-7xl px-4 sm:px-6 pt-4 sm:pt-0 lg:px-6">
		<!-- Tabs -->
		<UploadFilters />

		{#if uploads === null}
			<LoadingPage />
		{:else}
			<UploadGallery {uploads} />
		{/if}
	</div>
</main>

<!-- Details sidebar -->
<UploadSidebar />
