<script lang="ts">
    export let title: string;
    export let url: string;
</script>

<a href="{url}" class="group flex items-center rounded-md px-3 py-1 text-sm font-medium text-gray-700 hover:bg-gray-50 hover:text-gray-900">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4 mr-2">
        <path fill-rule="evenodd" d="M5.5 3A2.5 2.5 0 003 5.5v2.879a2.5 2.5 0 00.732 1.767l6.5 6.5a2.5 2.5 0 003.536 0l2.878-2.878a2.5 2.5 0 000-3.536l-6.5-6.5A2.5 2.5 0 008.38 3H5.5zM6 7a1 1 0 100-2 1 1 0 000 2z" clip-rule="evenodd" />
    </svg>
      
    <span class="truncate">{title}</span>
</a>
