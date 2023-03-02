<script lang="ts">
	export let title: string;
	export let description: string | null = null;
	export let to: string;
	export let icon: ConstructorOfATypedSvelteComponent;
	export let active: boolean = false;
</script>

<!-- Current: "bg-blue-50 bg-opacity-50", Default: "hover:bg-blue-50 hover:bg-opacity-50" -->
<a
	href={to}
	class="{active
		? 'bg-rose-50 bg-opacity-50'
		: 'hover:bg-rose-50 hover:bg-opacity-50'} border-slate-200 flex border-b p-6"
	aria-current="page"
>
	<svelte:component this={icon} className="-mt-0.5 h-6 w-6 flex-shrink-0 text-slate-400" />
	<div class="ml-3 text-sm">
		<p class="font-medium text-slate-900">{title}</p>
		{#if description}
			<p class="mt-1 text-slate-500">
				{description}
			</p>
		{/if}
	</div>
</a>
