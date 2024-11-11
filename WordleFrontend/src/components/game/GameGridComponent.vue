<script setup lang="ts">
import { onBeforeMount, ref, watch } from 'vue'
import type { GameData } from '@/interfaces/GameData'
import { guessWord } from '@/utils/guessWord'
import type { GameGuessDto } from '@/interfaces/GameGuessDto'
import { getGameData } from '@/utils/getGameData'
import { useRoute } from 'vue-router'
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from '@/components/ui/alert-dialog'

const router = useRoute()
const grid = ref(Array.from({ length: 6 }, () => Array(5).fill(''))) // 6 rows, 5 columns grid
const colors: object = {
  basic: 'bg-gray-200',
  good: 'bg-green-400',
  wrongPlace: 'bg-yellow-300',
  none: 'bg-gray-400',
}
const props = defineProps<{ gameData: GameData }>()
const gameData = ref(props.gameData)
const gameId = router.params.id
const attempt = ref(gameData.value.attempts)

const gameStatus = ref(gameData.value.status)
const showDialog = ref(false)
const getColorClass = (columnIndex: number, cellIndex: number): string => {
  if (grid.value[columnIndex][cellIndex] === '' || (columnIndex >= attempt.value)) {
    return colors.basic
  }
  if (grid.value[columnIndex][cellIndex] === gameData.value.word.split('')[cellIndex]) {
    return colors.good
  }
  if(grid.value[columnIndex].includes(gameData.value.word.split('')[cellIndex])) {
    return colors.wrongPlace
  }
  return colors.none
}

onBeforeMount(async () => {
  console.log('gameData:', gameData.value)
  if (!props.gameData) {
    gameData.value = await getGameData(Number(gameId))
  }
  gameData.value.gameGuesses.forEach((guess, index) => {
    guess.word.split('').forEach((letter, letterIndex) => {
      grid.value[index][letterIndex] = letter
    })
  })
  console.log('gameData:', gameData.value)
})
const input = ref('')

addEventListener('keydown', async (event) => {
  const letterArray = ['a', 'ą', 'b', 'c', 'ć', 'd', 'e', 'ę', 'f', 'g', 'h', 'i',
    'j', 'k', 'l', 'ł', 'm', 'n', 'ń', 'o', 'ó', 'p', 'q', 'r', 's', 'ś', 't', 'u', 'v',
    'w', 'x', 'y', 'z', 'ź', 'ż']
  if(gameData.value.finished)
  {
    console.log('Game has ended')
    return false
  }
  if (event.key === 'Enter') {
    if (input.value.length === 5) {
      const gameGuessData: GameGuessDto = {
        word: input.value,
        gameId: gameData.value.gameId,
      }
      await guessWord(gameGuessData)
      const data = await getGameData(gameData.value.gameId)
      input.value = ''
      attempt.value = data.attempts
      console.log('getGameData:', data)
    } else {
      console.log('Word is too short')
    }
  }
  if (letterArray.includes(event.key)) {
    console.log('Key pressed:', event.key)
    if (input.value.length < 5) {
      input.value += event.key
    } else {
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
  grid.value[attempt.value] = Array.from({ length: 5 }, () => '')
  newValue.split('').forEach((letter, index) => {
    grid.value[attempt.value][index] = letter
  })
})
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
        :class="getColorClass(columnIndex, cellIndex)"
        class="w-16 h-16 flex items-center justify-center"
      >
        <div class="text-2xl font-bold">
          <div v-if="grid[columnIndex]">
            {{ grid[columnIndex][cellIndex].toUpperCase() }}
          </div>
        </div>
      </div>
    </div>
  </div>
  <AlertDialog v-model:default-open="gameData.finished">
    <AlertDialogContent>
      <AlertDialogHeader>
        <AlertDialogTitle>Game is over</AlertDialogTitle>
        <AlertDialogDescription>
          You cannot play this game anymore because you have used all attempts. Click on the button below to return to the main view or close the dialog to look how the word was guessed.
        </AlertDialogDescription>
      </AlertDialogHeader>
      <AlertDialogFooter>
        <AlertDialogCancel>Close</AlertDialogCancel>
        <AlertDialogAction><a href="/main">Return </a></AlertDialogAction>
      </AlertDialogFooter>
    </AlertDialogContent>
  </AlertDialog>
</template>

<style scoped></style>
