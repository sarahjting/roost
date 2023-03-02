<script lang="ts">
	import { createEventDispatcher } from 'svelte';
	const dispatch = createEventDispatcher();
	import WarehouseIcon from '../../../../../components/icons/WarehouseIcon.svelte';
	import NewStorageButton from './NewStorageButton.svelte';
</script>

<div
	class="text-center relative block w-full rounded-lg border-2 border-dashed border-gray-300 p-12 text-center"
>
	<WarehouseIcon className="text-gray-400 h-12 w-12 mx-auto" />
	<h3 class="mt-2 text-sm font-semibold text-gray-900">No storages configured</h3>
	<p class="mt-1 text-sm text-gray-500">
		You will need to create a storage to start uploading files.
	</p>
	<div class="mt-6">
		<NewStorageButton on:click={() => dispatch('click')} />
	</div>
</div>
