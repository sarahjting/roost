<script lang="ts">
	import '$src/app.css';
	import LayoutHeader from './LayoutHeader.svelte';
	import LayoutNavbar from './LayoutNavbar.svelte';
</script>

<div class="flex h-full">
	<LayoutNavbar />

	<!-- Content area -->
	<div class="flex flex-1 flex-col overflow-hidden">
		<LayoutHeader />
		<!-- Main content -->
		<div class="flex flex-1 items-stretch overflow-hidden">
			<slot />
		</div>
	</div>
</div>

<style>
	.app {
		display: flex;
		flex-direction: column;
		min-height: 100vh;
	}

	main {
		flex: 1;
		display: flex;
		flex-direction: column;
		padding: 1rem;
		width: 100%;
		max-width: 64rem;
		margin: 0 auto;
		box-sizing: border-box;
	}

	footer {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		padding: 12px;
	}

	footer a {
		font-weight: bold;
	}

	@media (min-width: 480px) {
		footer {
			padding: 12px 0;
		}
	}
</style>
