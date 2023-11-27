<script lang="ts">
  // import jwt_decode from 'jsonwebtoken';
  import type { AxiosResponse, AxiosError } from 'axios';
  import Keyboard from '../../components/Keyboard.svelte';
  import CONSTANTS from "../../constants";
  import { afterUpdate, onMount } from 'svelte';
  import { getToken } from '../../scripts/getToken';
  import WordInput from '../../components/WordInput.svelte';
  // import { getUserName } from '../../scripts/getUserName';
  let username = '';
  let isGameCreated = true;
  type User = {
    username: string;
    token: string;
  };
  let user: User = {
    username: '',
    token: '',
  };
  onMount(async () => {
    const userString = localStorage.getItem('user');
    if (userString) {
      const userData = JSON.parse(userString);
      user.username = userData.username;
    }
  user.token = localStorage.getItem('token');

});

</script>


<div class="flex flex-col items-center justify-center min-h-screen py-2 w-full">
  <div class="flex flex-col items-center justify-center w-1/2">
    <h1 class="text-4xl font-bold mb-4">Welcome, {user.username}!</h1>
    {#if isGameCreated == false}
    <button class="btn btn-primary mb-2">Create game</button>
    {:else if isGameCreated == true}
    <div class="w-full h-screen bg-black text-white mx-auto max-w-10 py-10 flex flex-col items-center">
      <h1 class="text-4xl mb-2 text-center">Wordle</h1>
      <div class="flex flex-col gap-y-1 max-w-2xl h-full justify-between items-center">
        <div class="grow">
          {#each Array(CONSTANTS.MAX_GUESSES) as _, i}
            <div class="flex  mx-auto space-x-1 mb-1 text-white">
              <WordInput index={i} />
            </div>
          {/each}
        </div>
        <Keyboard/>
      </div>
    </div>
    {:else}
    {/if}
  </div>
</div>