<template>
  <div class="main flex justify-center items-center h-full">
    <div class="flex flex-col space-y-3 max-w-screen-md w-full text-center p-2">
      <h1 class="text-5xl font-bold">Welcome to Worlde game</h1>
      <h2 class="text-2xl font-semibold">Guess the 5-letter word in 6 moves and win</h2>
      <div class="flex justify-evenly">
        <Button @click="createGame">Play Game</Button>
        <Button @click="getAuthUserGames">View Games</Button>
        <Button href="/profile">View Profile</Button>
      </div>
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
import router from '@/router'

const loading = ref(true)
const games = ref<GameData[]>([])

const getAuthUserGames = async () => {
  const HOST = import.meta.env.VITE_API_HOST
  const PORT = import.meta.env.VITE_API_PORT
  try {
    const response: AxiosResponse = await axios.get(`${HOST}:${PORT}/api/v1/game/user`, {
      headers: {
        Accept: 'application/json',
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    })
    games.value = response.data.data
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const createGame = async () => {
  const HOST = import.meta.env.VITE_API_HOST
  const PORT = import.meta.env.VITE_API_PORT
  const config = {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Accept': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('jwt')}`,
    }
  }

  try {
    const response: AxiosResponse = await axios.post(`${HOST}:${PORT}/api/v1/game/create`,'',config)
    const gameData: GameData = response.data.data

    await router.push({ name: 'game', params: { id: gameData.gameId }})
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const logout = () => {
  localStorage.removeItem('jwt')
  router.push('/home')
}
onMounted(() => {
  getAuthUserGames()
});
</script>
