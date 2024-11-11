import type { GameGuessData } from '@/interfaces/GameGuessData'

interface GameData {
  gameId: number;
  userId: number;
  word: string;
  attempts: number;
  status: boolean;
  finished: boolean;
  date: string;
  gameGuesses: GameGuessData[];
}

export type { GameData }
