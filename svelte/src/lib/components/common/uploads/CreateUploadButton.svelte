<script lang="ts">
	import CreateUploadModal from './CreateUploadModal.svelte';

	let doShowUploadModal = false;
</script>

<button
	type="button"
	class="flex items-center justify-center rounded-full bg-rose-600 p-1 text-white hover:bg-rose-700 focus:outline-none focus:ring-2 focus:ring-rose-500 focus:ring-offset-2"
	on:click={() => (doShowUploadModal = true)}
>
	<svg
		class="h-6 w-6"
		fill="none"
		viewBox="0 0 24 24"
		stroke-width="1.5"
		stroke="currentColor"
		aria-hidden="true"
	>
		<path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
	</svg>
	<span class="sr-only">Add file</span>
</button>

<CreateUploadModal
	show={doShowUploadModal}
	on:complete={() => (doShowUploadModal = false)}
	on:close={() => (doShowUploadModal = false)}
/>
