<script lang="ts">
	import SpinnerIcon from '../icons/SpinnerIcon.svelte';

	export let className = '';
	export let size: string = 'sm';
	export let full: boolean = false;
	export let loading: boolean = false;
	export let type: 'button' | 'submit' | 'reset' | null = 'button';
	const padding = {
		xs: [2, 1],
		sm: [4, 2]
	}[size] ?? [4, 2];
</script>

<button
	{type}
	class="
		inline-flex items-center rounded 
		px-{padding[0]} py-{padding[1]} text-{size} 
		font-medium shadow-sm 
		{className} 
		{full ? 'w-full justify-center' : ''}
	"
	on:click
>
	{#if loading}
		<SpinnerIcon className="w-5 h-5 animate-spin opacity-50" />
	{:else}
		<slot />
	{/if}
</button>
