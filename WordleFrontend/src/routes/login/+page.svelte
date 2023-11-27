<script lang="ts">
  import { onMount } from 'svelte';
  import { goto } from '$app/navigation';
  import axios from 'axios';
 
  let username = '';
  let password = '';
  let errors = [];
  async function login() {
    try {
        const response = await axios.post('http://localhost:8080/auth/login', { username, password });
        if (response.status === 200) {
            console.log(response.data);
            const token = response.data.jwt;
            localStorage.setItem('user', JSON.stringify(response.data.user));
            localStorage.setItem('jwt', token);
            goto('/home');
        }
    } catch (err) {
        errors = err.response.data.errors;
    }
}
</script>

<div class="flex flex-col items-center justify-center min-h-screen py-2 w-full">
  <div class="flex flex-col items-center justify-center w-1/2">
    <h1 class="text-4xl font-bold mb-4">Login</h1>
    <input
      type="text"
      bind:value={username}
      placeholder="Username"
      class="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4"
    />
    <input
      type="password"
      bind:value={password}
      placeholder="Password"
      class="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4"
    />
    <button
      class="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
      on:click={login}
    >
      Login
    </button>
    {#if errors.length > 0}
    <div
      class="bg-red-500 text-white p-4 rounded mt-4 error-container overflow-auto w-full"
    >
      <ul>
        {#each errors as error (error)}
        <li>{error}</li>
        {/each}
      </ul>
    </div>
    {/if}
  </div>
</div>