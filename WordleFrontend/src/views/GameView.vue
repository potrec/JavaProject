<script setup lang="ts">
import { onMounted, ref } from 'vue'
import GameGridComponent from '@/components/game/GameGridComponent.vue'
import GameKeyboardComponent from '@/components/game/GameKeyboardComponent.vue'
import { Skeleton } from '@/components/ui/skeleton'
import { useRoute } from 'vue-router'
import type { GameData } from '@/interfaces/GameData'
import { getGameData } from '@/utils/getGameData'
import { Button } from '@/components/ui/button'

const router = useRoute()
const gameData = ref<GameData>()
const loading = ref(true)
const show = ref(false)
onMounted(async () => {
  const gameId = router.params.id
  try {
    gameData.value = await getGameData(gameId)
  } catch (error) {
    console.error(error)
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
      <GameGridComponent :gameData="gameData"/>
      <div class="flex justify-center space-x-4">
        <Button @click="show = !show">Show answer</Button>
      </div>
      <div v-if="show" class="text-center">
        <p class="text-2xl font-bold">Answer: {{ gameData.word }}</p>
      </div>
      <GameKeyboardComponent/>
    </div>
  </div>
</template>

