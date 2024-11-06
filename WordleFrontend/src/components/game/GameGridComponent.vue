<script setup lang="ts">
import { ref } from 'vue'
import type { GameData } from '@/interfaces/GameData'
import type { GameGuessData } from '@/interfaces/GameGuessData'

const grid = ref(Array.from({ length: 6 }, () => Array(5).fill('')))
const colors: object = {
  basic: 'bg-gray-200',
  good: 'bg-green-400',
  wrongPlace: 'bg-yellow-300',
  none: 'bg-gray-400',
}
const props = defineProps<{ gameData: GameData }>()
const testWord = props.gameData.word

const arrayOfGuesses: GameGuessData[] = props.gameData.gameGuesses

const getColorClass = (letter: string, index: number): string => {
  // if (testWord[index] === letter) {
  //   return colors.good
  // }
  // if (testWord.includes(letter) && letter != '') {
  //   console.log("testword:",testWord,"letter:",letter)
  //   return colors.wrongPlace
  // }
  return colors.none
}

</script>

<template>
  <div class="grid grid-rows-6 gap-4 mb-4 mt-4">
    <div
      v-for="(column, columnIndex) in grid"
      :key="columnIndex"
      class="flex flex-row space-x-4 justify-center"
    >
<!--      <div-->
<!--        v-for="(cell,cellIndex) in row"-->
<!--        :key="cellIndex"-->
<!--        :class="getColorClass(arrayOfGuesses[cellIndex][rowIndex],rowIndex)+' w-16 h-16 bg-gray-200 flex items-center justify-center'"-->
<!--      >-->
<!--        <div class="text-2xl font-bold">{{arrayOfGuesses[cellIndex][rowIndex]}}</div>-->
<!--      </div>-->
      <div
        v-for="(cell, cellIndex) in column"
        :key="cellIndex"
        class="w-16 h-16 bg-gray-200 flex items-center justify-center"
      >
<!--        <div class="text-2xl font-bold">-->
<!--          <div v-if="arrayOfGuesses[rowIndex]">-->
<!--            {{ arrayOfGuesses[rowIndex].word.split('')[cellIndex] }}-->
<!--          </div>-->
<!--        </div>-->
      </div>
    </div>
  </div>
</template>

<style scoped></style>
