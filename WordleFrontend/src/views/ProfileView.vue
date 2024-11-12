<script setup lang="ts">

import { onMounted, ref } from 'vue'
import type { GameData } from '@/interfaces/GameData'
import { getGamesData } from '@/utils/getGamesData'

const games = ref<GameData[]>([])
const playedGames = ref(0)
const finishedGames = ref(0)
const wonGames = ref(0)
const winPercentage = ref(0)
const averageGuesses = ref(0)
const currentStreak = ref(0)
const longestStreak = ref(0)

const calculateStats = (games: GameData[]) => {
  playedGames.value = games.length
  finishedGames.value = games.filter(game => game.finished).length
  wonGames.value = games.filter(game => game.finished).filter(game => game.status).length
  winPercentage.value = finishedGames.value ? (wonGames.value / finishedGames.value) * 100 : 0
  averageGuesses.value = finishedGames.value ? games.filter(game => game.finished).reduce((sum, game) => sum + game.attempts, 0) / finishedGames.value : 0
  let streak = 0
  games.forEach(game => {
    if (game.status && game.finished) {
      streak++
      currentStreak.value = streak
    } else {
      streak = 0
    }
    longestStreak.value = Math.max(longestStreak.value, streak)
  })
}

onMounted(async () => {
  games.value = await getGamesData()
  calculateStats(games.value)
})
</script>

<template>
  <div class="profile-stats">
    <h2 class="text-2xl font-bold mb-4">Profile Statistics</h2>
    <div class="grid grid-cols-2 gap-4">
      <div class="stat-item">
        <p class="text-lg font-medium">Played Games</p>
        <p class="text-xl">{{ playedGames }}</p>
      </div>
      <div class="stat-item">
        <p class="text-lg font-medium">Finished Games</p>
        <p class="text-xl">{{ finishedGames }}</p>
      </div>
      <div class="stat-item">
        <p class="text-lg font-medium">Won Games</p>
        <p class="text-xl">{{ wonGames }}</p>
      </div>
      <div class="stat-item">
        <p class="text-lg font-medium">% Won Games</p>
        <p class="text-xl">{{ winPercentage.toFixed(2) }}%</p>
      </div>
      <div class="stat-item">
        <p class="text-lg font-medium">Average Number of Guesses</p>
        <p class="text-xl">{{ averageGuesses.toFixed(2) }}</p>
      </div>
      <div class="stat-item">
        <p class="text-lg font-medium">Current Streak</p>
        <p class="text-xl">{{ currentStreak }}</p>
      </div>
      <div class="stat-item">
        <p class="text-lg font-medium">Longest Streak</p>
        <p class="text-xl">{{ longestStreak }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.profile-stats {
  padding: 1rem;
}
.stat-item {
  background-color: #f9f9f9;
  padding: 1rem;
  border-radius: 0.5rem;
  text-align: center;
}
</style>
