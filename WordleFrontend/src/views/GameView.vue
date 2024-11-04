<script setup lang="ts">
import { onMounted, ref } from 'vue'
import GameGridComponent from '@/components/game/GameGridComponent.vue'
import GameKeyboardComponent from '@/components/game/GameKeyboardComponent.vue'
import { useRoute } from 'vue-router'

const router = useRoute()
const gameData = ref(JSON.parse(router.query.gameData as string))

onMounted(() => {
  console.log(gameData.value)
})
</script>

<template>
  <div class="game">
    <GameGridComponent :gameData="gameData"/>
    <GameKeyboardComponent/>
  </div>
</template>

