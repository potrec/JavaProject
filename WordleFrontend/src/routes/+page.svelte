<script lang="ts">
  import { AppShell } from '@skeletonlabs/skeleton';
  import { goto } from '$app/navigation';
</script>

<div class="flex flex-col items-center justify-center min-h-screen py-2">
  <div class="flex flex-col items-center justify-center">
      <h1 class="text-4xl font-bold mb-4">Project Name</h1>
      <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-8 w-full" on:click={() => goto('/login')}>Login</button>
      <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full" on:click={() => goto('/register')}>Register</button>
  </div>
</div>
