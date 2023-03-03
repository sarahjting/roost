<script lang="ts">
	import BaseButton from './BaseButton.svelte';
	export let disabled: boolean = false;
	export let size: string = 'sm';
</script>

<BaseButton
	{size}
	className="
		{disabled && 'border border-gray-200 bg-gray-100 text-gray-300 cursor-not-allowed'}
		{!disabled &&
		'border border-gray-300 bg-white text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-rose-500 focus:ring-offset-2'}"
	on:click
>
	<slot />
</BaseButton>
