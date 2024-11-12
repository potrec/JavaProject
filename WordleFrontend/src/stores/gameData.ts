import { defineStore } from 'pinia'
import type { GameData } from '@/interfaces/GameData'

export const useGameStore = defineStore('game', {
  state: () => ({
    gameData: null as GameData | null,
  }),
  actions: {
    setGameData(data: GameData) {
      this.gameData = data
    },
    setFinished(finished: boolean) {
      if (this.gameData) {
        this.gameData.finished = finished
      }
    },
  },
})
