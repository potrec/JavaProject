<script setup lang="ts">
import { ref } from 'vue'

const grid = ref(Array.from({ length: 5 }, () => Array(6).fill('')))
const colors: object = {
  basic: 'bg-gray-200',
  good: 'bg-green-400',
  wrongPlace: 'bg-yellow-300',
  none: 'bg-gray-400',
}
const props = defineProps<{
  word: string,
  round: number,
}>()
const testWord = 'abaab'

const arrayOfGuesses: string[][] = [['', '', '', '', ''], ['', '', '', '', ''], ['', '', '', '', ''], ['', '', '', '', ''], ['', '', '', '', ''] , ['', '', '', '', '']]
const myGuess = 'acaab'

const getColorClass = (letter: string, index: number): string => {
  if (testWord[index] === letter) {
    return colors.good
  }
  return colors.none
}

arrayOfGuesses[0] = myGuess.split('')
</script>

<template>
  <div class="grid grid-cols-5 gap-4 mb-4 mt-4">
    <div
      v-for="(row, rowIndex) in grid"
      :key="rowIndex"
      class="flex flex-col space-y-4"
    >
      <div
        v-for="(cell,cellIndex) in row"
        :key="cellIndex"
        :class="getColorClass(arrayOfGuesses[cellIndex][rowIndex],rowIndex)+' w-16 h-16 bg-gray-200 flex items-center justify-center'"
      >
        <div class="text-2xl font-bold">{{arrayOfGuesses[cellIndex][rowIndex]}}</div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
