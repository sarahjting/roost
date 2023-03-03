<script lang="ts">
	import { goto } from '$app/navigation';
	import PrimaryButton from '$lib/components/buttons/PrimaryButton.svelte';
	import { auth } from '$lib/stores/auth';

	let email = '';
	let password = '';
	let error = '';
	let isLoading = false;

	function login() {
		error = '';
		isLoading = true;
		auth
			.login(email, password)
			.then(() => goto('/'))
			.catch((e) => {
				if (e.response) {
					error = 'Invalid credentials provided.';
				} else {
					throw e;
				}
			})
			.finally(() => (isLoading = false));
	}
</script>

<div class="mt-6">
	<form on:submit|preventDefault={login} class="space-y-6">
		<div>
			<label for="email" class="block text-sm font-medium text-gray-700">Email address</label>
			<div class="mt-1">
				<input
					bind:value={email}
					id="email"
					name="email"
					type="email"
					autocomplete="email"
					required
					class="block w-full appearance-none rounded-md border border-gray-300 px-3 py-2 placeholder-gray-400 shadow-sm focus:border-rose-500 focus:outline-none focus:ring-rose-500 sm:text-sm"
				/>
			</div>
		</div>

		<div class="space-y-1">
			<label for="password" class="block text-sm font-medium text-gray-700">Password</label>
			<div class="mt-1">
				<input
					bind:value={password}
					id="password"
					name="password"
					type="password"
					autocomplete="current-password"
					required
					class="block w-full appearance-none rounded-md border border-gray-300 px-3 py-2 placeholder-gray-400 shadow-sm focus:border-rose-500 focus:outline-none focus:ring-rose-500 sm:text-sm"
				/>
			</div>
		</div>

		<div class="flex items-center justify-between">
			<div class="flex items-center">
				<input
					id="remember-me"
					name="remember-me"
					type="checkbox"
					class="h-4 w-4 rounded border-gray-300 text-rose-600 focus:ring-rose-500"
				/>
				<label for="remember-me" class="ml-2 block text-sm text-gray-900">Remember me</label>
			</div>

			<div class="text-sm">
				<a href="#" class="font-medium text-rose-600 hover:text-rose-500">Forgot your password?</a>
			</div>
		</div>

		<div>
			<PrimaryButton full loading={isLoading} type="submit">Sign in</PrimaryButton>
			<div class="mt-2 text-rose-500">
				{error}
			</div>
		</div>
	</form>
</div>
