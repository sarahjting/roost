<script lang="ts">
	import CrowRegular from '../../../components/icons/CrowRegular.svelte';
	import KeyIcon from '../../../components/icons/KeyIcon.svelte';
	import WarehouseIcon from '../../../components/icons/WarehouseIcon.svelte';
	import SettingsNavbarLink from './SettingsNavbarLink.svelte';

	export let fullScreen: boolean = false;
</script>

<!-- Secondary sidebar -->
<nav
	aria-label="Sections"
	class="{fullScreen
		? 'w-full xl:w-96'
		: 'hidden w-96'} flex-shrink-0 border-r border-slate-200 bg-white xl:flex xl:flex-col"
>
	<div class="flex h-16 flex-shrink-0 items-center border-b border-slate-200 px-6">
		<p class="text-lg font-medium text-slate-900">Settings</p>
	</div>
	<div class="min-h-0 flex-1 overflow-y-auto">
		<SettingsNavbarLink
			active={false}
			icon={CrowRegular}
			title="Profile"
			description="Public profile and display settings."
			to="/settings/profile"
		/>
		<SettingsNavbarLink
			active={false}
			icon={KeyIcon}
			title="Security"
			description="Email and password settings."
			to="/settings/security"
		/>
		<SettingsNavbarLink
			active={false}
			icon={WarehouseIcon}
			title="Storages"
			description="Link your B2 or other storage accounts."
			to="/settings/storages"
		/>
	</div>
</nav>
