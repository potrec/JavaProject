<script lang="ts">
  // import jwt_decode from 'jsonwebtoken';
  import type { AxiosResponse, AxiosError } from 'axios';
  import { afterUpdate, onMount } from 'svelte';
  import { getToken } from '../../scripts/getToken';
	import axios from 'axios';
  let username = '';
  let isGameCreated = false;
  type User = {
    username: string;
    token: string;
  };
  let user: User = {
    username: '',
    token: '',
  };
  let word = '';
  let guessingsRow = ['','','','',''];
  let guessingsTable = [guessingsRow, guessingsRow, guessingsRow, guessingsRow, guessingsRow];
  let gameId = 1;
  let errors = [];
  let token = '';
  //let guess = '';
  const data = JSON.stringify({
   word: word,
   gameId: gameId
  });
  const http = axios.create({
		baseURL: 'http://127.0.0.1:8080',
		headers: {
			'X-Requested-With': 'XMLHttpRequest',
			Authorization: 'Bearer ' + token
		},
		withCredentials: true
	});
  async function guess() {
    try {
      const response = await http.post('/api/v1/game/guess', data, {
      headers: {
       'Content-Type': 'application/json'
        },
      })
      if (response.status === 200) {
        // Update the guessingsTable with the response
        guessingsTable = response.data.guesses;
      }
    } catch (err) {
      errors = err.response.data.errors;
      console.log(errors);
    }
  }
  async function createGame() {
    const userString = localStorage.getItem('user');
    if (userString) {
      const userData = JSON.parse(userString);
    }
  try {
    const response = await http.post('/api/v1/game/create');
    if (response.status === 200) {
      isGameCreated = true;
      word = response.data.word;
    }
  } catch (err) {
    errors = err.response.data.errors;
    console.log(errors);
  }
  }
  onMount(async () => {
    const userString = localStorage.getItem('user');
    token = localStorage.getItem('token');
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
    <button class="btn btn-primary mb-2 "on:click={createGame}>Create game</button>
    {:else if isGameCreated == true}
    <div class="w-full h-screen text-white mx-auto max-w-10 py-10 flex flex-col items-center">
      <h1 class="text-4xl mb-2 text-center">Wordle</h1>
      <div class="flex flex-row">
        <input type="text" class="w-full border border-gray-300 rounded-lg" bind:value={word}>
        <button class="btn bg-black" on:click={guess}>Guess</button>
      </div>
      <table class="table-auto">
        {#each guessingsTable as row}
          <tr>
            {#each row as cell}
              <td class="border px-16 py-8">{cell}</td>
            {/each}
          </tr>
        {/each}
       </table>
    </div>
    {:else}
    {/if}
  </div>
 </div>