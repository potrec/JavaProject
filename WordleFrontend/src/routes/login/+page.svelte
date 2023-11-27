<script lang="ts">
  import { onMount } from 'svelte';
  import { goto } from '$app/navigation';
  import axios from 'axios';
 
  let email = '';
  let password = '';
  let error = '';
 
  async function login() {
   try {
     const response = await axios.post('http://localhost:8080/auth/login', { email, password });
     if (response.status === 200) {
       goto('/');
     }
   } catch (err) {
     error = err.message;
   }
  }
 </script>
 
 <div class="flex flex-col items-center justify-center min-h-screen py-2">
  <div class="flex flex-col items-center justify-center">
    <h1 class="text-4xl font-bold mb-4">Login</h1>
    <input type="email" bind:value={email} placeholder="Email" class="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4" />
    <input type="password" bind:value={password} placeholder="Password" class="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4" />
    <button class="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 rounded" on:click={login}>Login</button>
    {#if error}
    <div class="bg-red-500 text-white p-4 rounded mt-4">
      <p>{error}</p>
    </div>
  {/if}
  </div>
 </div>
 