<script setup lang="ts">
import { onMounted, ref, watch } from 'vue'
import type { GameData } from '@/interfaces/GameData'
import type { GameGuessData } from '@/interfaces/GameGuessData'
import { guessWord } from '@/utils/guessWord'
import type { GameGuessDto } from '@/interfaces/GameGuessDto'

const grid = ref(Array.from({ length: 6 }, () => Array(5).fill('')))
const colors: object = {
  basic: 'bg-gray-200',
  good: 'bg-green-400',
  wrongPlace: 'bg-yellow-300',
  none: 'bg-gray-400',
}
const props = defineProps<{ gameData: GameData }>()
const arrayOfGuesses: GameGuessData[] = props.gameData.gameGuesses
const attempt = ref(props.gameData.attempts)
const getColorClass = (columnIndex: number, cellIndex: number): string => {
  if (arrayOfGuesses[attempt.value].word.split('')[cellIndex] === grid.value[columnIndex][cellIndex]) {
    return colors.good
  }
  if (arrayOfGuesses[attempt.value].word.split('').includes(grid.value[columnIndex][cellIndex])) {
    return colors.wrongPlace
  }
  return colors.none
}
onMounted(() => {
  console.log('arrayOfGuesses:', arrayOfGuesses)
  arrayOfGuesses.forEach((guess, index) => {
    guess.word.split('').forEach((letter, letterIndex) => {
      grid.value[index][letterIndex] = letter
    })
  })
  console.log('grid:', grid.value)
})
const input = ref('')

addEventListener('keydown', (event) => {
  const letterArray = ['a','ą','b','c','ć','d','e','ę','f','g','h','i',
    'j','k','l','ł','m','n','ń','o','ó','p','q','r','s','ś','t','u','v',
    'w','x','y','z','ź','ż']

  if (event.key === 'Enter') {
    if(input.value.length === 5) {
      const gameGuessData: GameGuessDto = {
        word: input.value,
        gameId: props.gameData.gameId,
      }
      guessWord(gameGuessData)
    }
    else {
      console.log('Word is too short')
    }
  }
  if (letterArray.includes(event.key)) {
    console.log('Key pressed:', event.key)
    if(input.value.length < 5) {
      input.value += event.key
    }
    else {
      console.log('Word is too long')
    }
  }
  if (event.key === 'Backspace') {
    console.log('Backspace key pressed')
    input.value = input.value.slice(0, -1)
  }
  if (event.key === 'Escape') {
    console.log('Escape key pressed')
    input.value = ''
  }
  console.log('input:', input.value)

})

watch(input, (newValue) => {
  console.log('input:', newValue)
  grid.value[attempt.value] = Array.from({ length: 5 }, () => '')
  newValue.split('').forEach((letter, index) => {
    grid.value[attempt.value][index] = letter
  })
  console.log('grid:', grid.value)
})
console.log('arrayOfGuesses:', props.gameData)
</script>

<template>
  <div class="grid grid-rows-6 gap-4 mb-4 mt-4">
    <div
      v-for="(column, columnIndex) in grid"
      :key="columnIndex"
      class="flex flex-row space-x-4 justify-center"
    >
      <div
        v-for="(cell, cellIndex) in column"
        :key="cellIndex"
        class="w-16 h-16 bg-gray-200 flex items-center justify-center"
      >
        <div class="text-2xl font-bold">
          <div v-if="grid[columnIndex]">
            {{  grid[columnIndex][cellIndex].toUpperCase() }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
