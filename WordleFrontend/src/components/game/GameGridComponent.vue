<script setup lang="ts">
import { onBeforeMount, ref, watch } from 'vue'
import { guessWord } from '@/utils/guessWord'
import type { GameGuessDto } from '@/interfaces/GameGuessDto'
import { getGameData } from '@/utils/getGameData'
import { useRoute, useRouter } from 'vue-router'
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from '@/components/ui/alert-dialog'
import { setError } from '@/stores/errorStore'
import { useGameStore } from '@/stores/gameData'

const route = useRoute()
const router = useRouter()
const gameStore = useGameStore()
const grid = ref(Array.from({ length: 6 }, () => Array(5).fill(''))) // 6 rows, 5 columns grid
const colors = {
  basic: 'bg-gray-200',
  good: 'bg-green-400',
  wrongPlace: 'bg-yellow-300',
  none: 'bg-gray-400',
}
const gameId = route.params.id
const attempt = ref(gameStore.gameData?.attempts || 0)

const getColorClass = (columnIndex: number, cellIndex: number): string => {
  if (grid.value[columnIndex][cellIndex] === '' || (columnIndex >= attempt.value)) {
    return colors.basic
  }
  if (grid.value[columnIndex][cellIndex] === gameStore.gameData?.word.split('')[cellIndex]) {
    return colors.good
  }
  if(grid.value[columnIndex].includes(gameStore.gameData?.word.split('')[cellIndex])) {
    return colors.wrongPlace
  }
  return colors.none
}

const showPopup = ref(false)
const checkWin = () => {
  if (gameStore.gameData?.finished) {
    showPopup.value = true
  }
}

onBeforeMount(async () => {
  if (!gameStore.gameData) {
    try {
      const data = await getGameData(Number(gameId))
      gameStore.setGameData(data)
    } catch (error) {
      if (error) {
        setError('Game not found or you do not have access to this game redirecting in 3 seconds')
        setTimeout(() => {
          router.push({ name: 'main' })
        }, 3000)
      }
    }
  }
  gameStore.gameData?.gameGuesses.forEach((guess, index) => {
    guess.word.split('').forEach((letter, letterIndex) => {
      grid.value[index][letterIndex] = letter
    })
  })
})
const input = ref('')

addEventListener('keydown', async (event) => {
  const letterArray = ['a', 'ą', 'b', 'c', 'ć', 'd', 'e', 'ę', 'f', 'g', 'h', 'i',
    'j', 'k', 'l', 'ł', 'm', 'n', 'ń', 'o', 'ó', 'p', 'q', 'r', 's', 'ś', 't', 'u', 'v',
    'w', 'x', 'y', 'z', 'ź', 'ż']
  if(gameStore.gameData?.finished)
  {
    console.log('Game has ended')
    return false
  }
  if (event.key === 'Enter') {
    if (input.value.length === 5) {
      const gameGuessData: GameGuessDto = {
        word: input.value,
        gameId: gameStore.gameData?.gameId || 0,
      }
      await guessWord(gameGuessData)
      const data = await getGameData(gameStore.gameData?.gameId || 0)
      input.value = ''
      gameStore.setGameData(data)
      attempt.value = gameStore.gameData?.attempts || 0
      checkWin()
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
    event.preventDefault()
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
  if (attempt.value < grid.value.length) {
    grid.value[attempt.value] = Array.from({ length: 5 }, () => '')
    newValue.split('').forEach((letter, index) => {
      grid.value[attempt.value][index] = letter
    })
  }
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
  <AlertDialog :default-open="gameStore.gameData?.finished" @onUpdate:defaultOpen="gameStore.setFinished($event)">
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
  <transition name="popup">
    <div v-if="showPopup" class="popup">
      <h1 class="text-primary text-2xl">{{gameStore.gameData?.status ? "Congratulations! You won the game!" : "You lose, try again."}}</h1>
    </div>
  </transition>
</template>

<style scoped>
.w-8 {
  width: 2rem;
}
.h-16 {
  height: 4rem;
}
.popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 2rem;
  border-radius: 1rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  z-index: 1000;
}
.popup-enter-active, .popup-leave-active {
  transition: opacity 0.5s;
}
.popup-enter, .popup-leave-to {
  opacity: 0;
}
</style>
