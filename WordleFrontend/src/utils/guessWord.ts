import axios from 'axios'
import type { GameGuessDto } from '@/interfaces/GameGuessDto'

const guessWord = async (gameGuessData: GameGuessDto) => {
  const HOST = import.meta.env.VITE_API_HOST
  const PORT = import.meta.env.VITE_API_PORT

  try {
    return await axios.post(
      `${HOST}:${PORT}/api/v1/game/guess`,
      {
        gameId: gameGuessData.gameId,
        word: gameGuessData.word,
      },
      {
        headers: {
          Accept: 'application/json',
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      },
    )
  } catch (error) {
    throw error
  }
}

export { guessWord }
