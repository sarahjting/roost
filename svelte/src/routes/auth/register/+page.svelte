<script>
	import '../../../app.css';
	import CrowRegular from '../../../components/icons/CrowRegular.svelte';
	import EmailRegisterForm from './EmailRegisterForm.svelte';
</script>

<div class="flex min-h-full flex-col justify-center py-12 sm:px-6 lg:px-8 max-w-md mx-auto">
	<div class="sm:mx-auto sm:w-full sm:max-w-md">
		<div class="flex justify-center">
			<CrowRegular size={10} color="rose-500" />
		</div>
		<h2 class="mt-6 text-center text-3xl font-bold tracking-tight text-gray-900">Register</h2>
		<p class="mt-2 text-center text-sm text-gray-600">
			Existing user?
			<a href="/auth/login" class="font-medium text-rose-600 hover:text-rose-500"
				>Go to sign-in instead</a
			>
		</p>
	</div>
	<EmailRegisterForm />
</div>
