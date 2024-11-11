<script setup lang="ts">
import { onBeforeMount, ref } from 'vue'
import type { GameData } from '@/interfaces/GameData'
import { getGamesData } from '@/utils/getGamesData'
import router from '@/router'
import { Button } from '@/components/ui/button'

const games = ref<GameData[]>([])
const fetchGamesData = async () => {
  games.value = await getGamesData()
}

onBeforeMount(() => {
  fetchGamesData()
})
</script>

<template>
  <div class="flex flex-col items-center">
    <h1 class="text-2xl font-bold mb-4">Games</h1>
    <div v-if="games.length === 0">
      <p>No games found</p>
    </div>
    <div v-else>
      <div class="grid grid-cols-3 gap-4">
        <div v-for="game in games" :key="game.gameId" class="bg-gray-200 p-4 rounded-lg">
          <p class="text-xl font-bold">{{ game.finished ?  game.word : 'Not finished'   }}</p>
          <p class="text-lg">{{ game.finished ? game.status ? 'You won' : 'You lose' : 'Not resolved' }}</p>
          <p class="text-lg">{{ game.attempts }} Attempts</p>
          <Button @click="router.push({ name: 'game', params: { id: game.gameId }})">{{ game.finished ? 'Look at guesses' : 'Return to the game'   }}</Button>
        </div>
      </div>
    </div>
  </div>

</template>

<style scoped>

</style>
