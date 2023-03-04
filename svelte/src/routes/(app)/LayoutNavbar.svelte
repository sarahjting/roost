<script lang="ts">
	import CrowRegular from '$lib/components/icons/CrowRegular.svelte';
	import HomeIcon from '$lib/components/icons/HomeIcon.svelte';
	import SettingsIcon from '$lib/components/icons/SettingsIcon.svelte';
	import StatsIcon from '$lib/components/icons/StatsIcon.svelte';
	import LayoutNavbarFolder from './LayoutNavbarFolder.svelte';
	import LayoutNavbarLink from './LayoutNavbarLink.svelte';
	import LayoutNavbarTag from './LayoutNavbarTag.svelte';
</script>

<div class="hidden bg-rose-800 lg:col-span-3 lg:block xl:col-span-2 p-2">
	<nav aria-label="Sidebar" class="sticky top-4 divide-y divide-rose-900">
		<div class="flex justify-center pb-3 lg:static xl:col-span-2">
			<div class="flex flex-shrink-0 items-center text-rose-100">
				<a href="#" class="flex items-center">
					<CrowRegular className="w-8 h-8 text-rose-100" />
					<span class="ml-2 font-bold hidden lg:block"> ROOST </span>
				</a>
			</div>
		</div>
		<div class="space-y-1 py-3">
			<LayoutNavbarLink icon={HomeIcon} title="Home" to="/uploads" />
			<LayoutNavbarLink icon={SettingsIcon} title="Settings" to="/settings" />
			<LayoutNavbarLink icon={StatsIcon} title="Stats" to="/stats" />
		</div>
		<div class="py-4">
			<p class="px-3 text-sm font-medium text-rose-600" id="folders-headline">Folders</p>
			<div class="mt-3" aria-labelledby="folders-headline">
				<LayoutNavbarFolder title="Art" url="#" />
				<LayoutNavbarFolder title="Gaming" url="#" />
				<LayoutNavbarFolder title="Tech" url="#" />
				<LayoutNavbarFolder title="Food" url="#" />
			</div>
		</div>
		<div class="pt-4">
			<p class="px-3 text-sm font-medium text-rose-500" id="folders-headline">Tags</p>
			<div class="mt-3" aria-labelledby="folders-headline">
				<LayoutNavbarTag title="app: Adobe Photoshop" url="#" />
				<LayoutNavbarTag title="app: Discord" url="#" />
				<LayoutNavbarTag title="app: Chrome" url="#" />
			</div>
		</div>
	</nav>
</div>
