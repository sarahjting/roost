<script lang="ts">
	export let icon: ConstructorOfATypedSvelteComponent | null = null;
	export let show: boolean;
	export let footer: boolean = true;
	export let doCloseOnClickOut: boolean = true;
	import { createEventDispatcher } from 'svelte';
	const dispatch = createEventDispatcher();

	function onClickOverlay() {
		if (doCloseOnClickOut) {
			dispatch('close');
		}
	}
</script>

{#if show}
	<div class="relative z-10" aria-labelledby="modal-title" role="dialog" aria-modal="true">
		<!-- overlay background -->
		<div
			class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity {show
				? 'opacity-100 ease-out duration-300'
				: 'opacity-0 ease-in duration-200'}"
		/>

		<!-- svelte-ignore a11y-click-events-have-key-events -->
		<div class="fixed inset-0 z-10 overflow-y-auto" on:click={onClickOverlay}>
			<div class="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
				<div
					class="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 w-full sm:max-w-lg md:max-w-2xl
				{show
						? 'ease-out duration-300 opacity-100 translate-y-0 sm:scale-100'
						: 'opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95 ease-in duration-200'}"
					on:click|stopPropagation
				>
					<div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
						<div class="sm:flex sm:items-start">
							{#if icon}
								<div
									class="mx-auto flex h-12 w-12 flex-shrink-0 items-center justify-center rounded-full bg-rose-100 sm:mx-0 sm:h-10 sm:w-10"
								>
									<svelte:component this={icon} className="text-rose-600 w-5 h-5" />
								</div>
							{/if}
							<slot />
						</div>
					</div>
					{#if footer}
						<div class="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
							<slot name="footer">
								<button
									type="button"
									class="mt-3 inline-flex w-full justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-base font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
									on:click={() => dispatch('close')}
								>
									Close
								</button>
							</slot>
						</div>
					{/if}
				</div>
			</div>
		</div>
	</div>
{/if}

<style>
	dialog {
		max-width: 32em;
		border-radius: 0.2em;
		border: none;
		padding: 0;
	}
	dialog::backdrop {
		background: rgba(0, 0, 0, 0.3);
	}
	dialog > div {
		padding: 1em;
	}
	dialog[open] {
		animation: zoom 0.3s cubic-bezier(0.34, 1.56, 0.64, 1);
	}
	@keyframes zoom {
		from {
			transform: scale(0.95);
		}
		to {
			transform: scale(1);
		}
	}
	dialog[open]::backdrop {
		animation: fade 0.2s ease-out;
	}
	@keyframes fade {
		from {
			opacity: 0;
		}
		to {
			opacity: 1;
		}
	}
	button {
		display: block;
	}
</style>
