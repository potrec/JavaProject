<template>
  <div class="main flex justify-center items-center h-screen overflow-hidden">
    <div class="flex flex-col space-y-3 max-w-screen-md w-full text-center">
      <h1 class="text-5xl font-bold">Welcome to Main View</h1>
      <h2 class="text-2xl font-semibold">Guess the 5-letter word in 6 moves and win</h2>
      <Button href="/game">Play Game</Button>
      <Button href="/games">View Games</Button>
      <Button href="/profile">View Profile</Button>
      <Button href="/logout">Logout</Button>
      <Skeleton v-if="loading" />
      <div v-else>
        <p v-for="game in games" :key="game.gameId">{{ game }}</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios, { type AxiosResponse } from 'axios'
import { Skeleton } from '@/components/ui/skeleton'
import type { GameData } from '@/interfaces/GameData'
import { Button } from '@/components/ui/button'

const loading = ref(true)
const games = ref<GameData[]>([])

const getAuthUserGames = async () => {
  const HOST = import.meta.env.VITE_API_HOST
  const PORT = import.meta.env.VITE_API_PORT
  try {
    const response: AxiosResponse = await axios.get(`${HOST}:${PORT}/api/v1/game/user`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    })
    games.value = response.data.data
    console.log(response.data.data)
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}
onMounted(() => {
  getAuthUserGames()
});
</script>
