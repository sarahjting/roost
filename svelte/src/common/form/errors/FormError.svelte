<script lang="ts">
	export let errors: Array<any> = [];
</script>

{#if errors.length}
	<div class="mt-2 text-rose-500">
		There was a problem with your form. Please re-review the supplied fields.
	</div>
{/if}
