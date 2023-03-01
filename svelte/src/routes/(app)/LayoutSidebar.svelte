<script lang="ts">
    import LayoutSidebarFolder from "./LayoutSidebarFolder.svelte";
	import LayoutSidebarTag from "./LayoutSidebarTag.svelte";
</script>

<div class="hidden lg:col-span-3 lg:block xl:col-span-2">
    <nav aria-label="Sidebar" class="sticky top-4 divide-y divide-gray-300">
        <div class="space-y-1 pb-3">
            <!-- Current: "bg-gray-200 text-gray-900", Default: "text-gray-700 hover:bg-gray-50" -->
            <a href="#" class="bg-gray-200 text-gray-900 group flex items-center px-3 py-2 text-sm font-medium rounded-md" aria-current="page">
                <svg class="text-gray-500 flex-shrink-0 -ml-1 mr-3 h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25" />
                </svg>
                <span class="truncate">Home</span>
            </a>
            <a href="#" class="text-gray-700 hover:bg-gray-50 group flex items-center px-3 py-2 text-sm font-medium rounded-md">
                <svg class="text-gray-400 group-hover:text-gray-500 flex-shrink-0 -ml-1 mr-3 h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M11.42 15.17L17.25 21A2.652 2.652 0 0021 17.25l-5.877-5.877M11.42 15.17l2.496-3.03c.317-.384.74-.626 1.208-.766M11.42 15.17l-4.655 5.653a2.548 2.548 0 11-3.586-3.586l6.837-5.63m5.108-.233c.55-.164 1.163-.188 1.743-.14a4.5 4.5 0 004.486-6.336l-3.276 3.277a3.004 3.004 0 01-2.25-2.25l3.276-3.276a4.5 4.5 0 00-6.336 4.486c.091 1.076-.071 2.264-.904 2.95l-.102.085m-1.745 1.437L5.909 7.5H4.5L2.25 3.75l1.5-1.5L7.5 4.5v1.409l4.26 4.26m-1.745 1.437l1.745-1.437m6.615 8.206L15.75 15.75M4.867 19.125h.008v.008h-.008v-.008z" />
                </svg>                  
                <span class="truncate">Settings</span>
            </a>
            <a href="#" class="text-gray-700 hover:bg-gray-50 group flex items-center px-3 py-2 text-sm font-medium rounded-md">
                <svg class="text-gray-400 group-hover:text-gray-500 flex-shrink-0 -ml-1 mr-3 h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 3v11.25A2.25 2.25 0 006 16.5h2.25M3.75 3h-1.5m1.5 0h16.5m0 0h1.5m-1.5 0v11.25A2.25 2.25 0 0118 16.5h-2.25m-7.5 0h7.5m-7.5 0l-1 3m8.5-3l1 3m0 0l.5 1.5m-.5-1.5h-9.5m0 0l-.5 1.5m.75-9l3-3 2.148 2.148A12.061 12.061 0 0116.5 7.605" />
                </svg>
                <span class="truncate">Stats</span>
            </a>
        </div>
        <div class="py-4">
            <p class="px-3 text-sm font-medium text-gray-500" id="folders-headline">Folders</p>
            <div class="mt-3" aria-labelledby="folders-headline">
                <LayoutSidebarFolder title="Art" url="#"></LayoutSidebarFolder>
                <LayoutSidebarFolder title="Gaming" url="#"></LayoutSidebarFolder>
                <LayoutSidebarFolder title="Tech" url="#"></LayoutSidebarFolder>
                <LayoutSidebarFolder title="Food" url="#"></LayoutSidebarFolder>
            </div>
        </div>
        <div class="pt-4">
            <p class="px-3 text-sm font-medium text-gray-500" id="folders-headline">Tags</p>
            <div class="mt-3" aria-labelledby="folders-headline">
                <LayoutSidebarTag title="app: Adobe Photoshop" url="#"></LayoutSidebarTag>
                <LayoutSidebarTag title="app: Discord" url="#"></LayoutSidebarTag>
                <LayoutSidebarTag title="app: Chrome" url="#"></LayoutSidebarTag>
            </div>
        </div>
    </nav>
</div>