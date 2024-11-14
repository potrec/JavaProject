<script setup lang="ts">
import { watch } from 'vue'
import { useGameStore } from '@/stores/gameData'

const gameStore = useGameStore()

const rows = [
  ['a','ą','b','c','ć','d','e','ę','f','g','h','i'],
  ['j','k','l','ł','m','n','ń','o','ó','p','q','r'],
  ['s','ś','t','u','v','w','x','y','z','ź','ż']
]

const colors = {
  basic: 'bg-gray-200',
  good: 'bg-green-400',
  wrongPlace: 'bg-yellow-300',
  none: 'bg-gray-400',
}

const getColorClass = (letter: string): string => {
  if (!gameStore.gameData) return colors.basic

  const noneLetters = new Set<string>()
  const greenLetters = new Set<string>()
  const wrongPlaceLetters = new Set<string>()

  gameStore.gameData.gameGuesses.forEach((guess) => {
    guess.word.split('').forEach((guessLetter, guessIndex) => {
      if (guessLetter === gameStore.gameData.word.split('')[guessIndex]) {
        greenLetters.add(guessLetter)
      } else if (gameStore.gameData.word.split('').includes(guessLetter)) {
        wrongPlaceLetters.add(guessLetter)
      } else {
        noneLetters.add(guessLetter)
      }
    })
  })

  if (greenLetters.has(letter)) {
    return colors.good
  } else if (wrongPlaceLetters.has(letter)) {
    return colors.wrongPlace
  } else if (noneLetters.has(letter)) {
    return colors.none
  } else {
    return colors.basic
  }
}

watch(() => gameStore.gameData, (newGameData) => {
  console.log('Game data changed', gameStore.gameData)
}, { deep: true })
</script>

<template>
  <div class="mt-2">
    <div v-for="(row, rowIndex) in rows" :key="rowIndex" class="flex justify-center mb-1">
      <div
        v-for="(letter, index) in row"
        :key="index"
        :class="`w-8 h-16 flex items-center justify-center text-primary text-2xl font-bold ${getColorClass(letter)}`"
        class="rounded m-1"
      >
        {{ letter.toUpperCase() }}
      </div>
    </div>
  </div>
</template>

<style scoped>
.w-8 {
  width: 2rem;
}
.h-16 {
  height: 4rem;
}
</style>
