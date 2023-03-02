<script lang="ts">
	import BaseButton from './BaseButton.svelte';
	export let disabled: boolean = false;
	export let size: string = 'sm';
</script>

<BaseButton
	{size}
	className="
        {disabled && 'border border-rose-100 bg-rose-100 text-rose-300 cursor-not-allowed'}
        {!disabled &&
		'border border-rose-600 bg-rose-600 text-white hover:bg-rose-700 focus:outline-none focus:ring-2 focus:ring-rose-500 focus:ring-offset-2'}"
>
	<slot />
</BaseButton>
