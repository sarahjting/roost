<script lang="ts">
	import CreateUploadButton from '$lib/components/common/uploads/CreateUploadButton.svelte';
</script>

<div class="text-center relative block w-full p-12 text-center">
	<h3 class="mt-2 text-sm font-semibold text-gray-900">No files uploaded</h3>
	<p class="mt-1 text-sm text-gray-500">Click the button below to get started.</p>
	<div class="mt-6 mx-auto flex justify-center">
		<CreateUploadButton />
	</div>
</div>
