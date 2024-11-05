<script setup lang="ts">
import { onMounted, ref } from 'vue'
import GameGridComponent from '@/components/game/GameGridComponent.vue'
import GameKeyboardComponent from '@/components/game/GameKeyboardComponent.vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import type { GameData } from '@/interfaces/GameData'
import type { GameGuessData } from '@/interfaces/GameGuessData'

const router = useRoute()
const gameData = ref<GameData>()

onMounted(() => {
  fetchGameData()
})

const fetchGameData = async () => {
  const HOST = import.meta.env.VITE_API_HOST
  const PORT = import.meta.env.VITE_API_PORT
  const gameId = router.params.id

  try {
    const response = await axios.get(`${HOST}:${PORT}/api/v1/game/${gameId}`, {
      headers: {
        Accept: 'application/json',
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    })
    gameData.value = response.data.data
  } catch (error) {
    console.error(error)
  }
}

const gameGuessData = ref<GameGuessData>()

const guessWord = async () => {
  const HOST = import.meta.env.VITE_API_HOST
  const PORT = import.meta.env.VITE_API_PORT

  try {
    const response = await axios.post(
      `${HOST}:${PORT}/api/v1/game/guess`,
      { gameGuessData },
      {
        headers: {
          Accept: 'application/json',
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      },
    )
    gameData.value = response.data.data
  } catch (error) {
    console.error(error)
  }
}
</script>

<template>
  <div class="flex flex-col items-center">
    <h1 class="text-2xl font-bold mb-4">Game</h1>
    {{ gameData }}
    <GameGridComponent :gameData="gameData"/>
    <GameKeyboardComponent/>
  </div>
</template>

