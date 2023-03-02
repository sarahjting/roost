<script lang="ts">
	export let title: string;
	export let icon: ConstructorOfATypedSvelteComponent;
	export let to: string;
</script>

<a
	href={to}
	class="text-rose-200 hover:bg-rose-900 group flex items-center px-3 py-2 text-sm font-medium rounded-md"
>
	<svelte:component
		this={icon}
		className="text-rose-300 group-hover:text-rose-200 flex-shrink-0 -ml-1 mr-3 h-6 w-6"
	/>
	<span class="truncate group-hover:text-rose-100">{title}</span>
</a>
