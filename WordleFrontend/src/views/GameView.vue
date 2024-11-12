<script setup lang="ts">
import { onMounted, ref } from 'vue'
import GameGridComponent from '@/components/game/GameGridComponent.vue'
import GameKeyboardComponent from '@/components/game/GameKeyboardComponent.vue'
import { Skeleton } from '@/components/ui/skeleton'
import { useRoute, useRouter } from 'vue-router'
import { getGameData } from '@/utils/getGameData'
import { Button } from '@/components/ui/button'
import { setError } from '@/stores/errorStore'
import { useGameStore } from '@/stores/gameData'

const route = useRoute()
const router = useRouter()
const gameStore = useGameStore()
const loading = ref(true)
const show = ref(false)

onMounted(async () => {
  const gameId = route.params.id
  try {
    const data = await getGameData(gameId)
    gameStore.setGameData(data)
  } catch (error) {
    console.error(error)
    setError('Game not found or you do not have access to this game redirecting in 3 seconds')
    setTimeout(() => {
      router.push({ name: 'main' })
    }, 3000)
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div class="flex flex-col items-center">
    <h1 class="text-2xl font-bold mb-4">Game</h1>
    <div v-if="loading">
      <Skeleton class="h-[696px] w-[428px] rounded-xl bg-primary" />
    </div>
    <div v-else>
      <GameGridComponent :gameData="gameStore.gameData"/>
      <div class="flex justify-center space-x-4">
        <Button @click="show = !show">Show answer</Button>
      </div>
      <div v-if="show" class="text-center">
        <p class="text-2xl font-bold">Answer: {{ gameStore.gameData.word }}</p>
      </div>
      <GameKeyboardComponent :gameData="gameStore.gameData"/>
    </div>
  </div>
</template>

