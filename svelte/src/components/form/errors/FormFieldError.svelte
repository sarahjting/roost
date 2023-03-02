<script lang="ts">
	export let errors: Array<any> = [];
	export let field: string;
</script>

{#if errors.filter((e) => e.field === field).length}
	<ul class="mt-2 text-rose-500">
		{#each errors.filter((e) => e.field === field) as error}
			<li>{error.defaultMessage}</li>
		{/each}
	</ul>
{/if}
