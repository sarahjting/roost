<script lang="ts">
	export let className = '';
	export let size: string = 'sm';
	const padding = {
		xs: [2.5, 1.5],
		sm: [4, 2]
	}[size] ?? [4, 2];
</script>

<button
	type="button"
	class="inline-flex items-center rounded px-{padding[0]} py-{padding[1]} text-{size} font-medium shadow-sm {className}"
	on:click
>
	<slot />
</button>
