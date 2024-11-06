import axios from 'axios'

const fetchGameData = async (gameId: number) => {
  const HOST = import.meta.env.VITE_API_HOST
  const PORT = import.meta.env.VITE_API_PORT

  try {
    const response = await axios.get(`${HOST}:${PORT}/api/v1/game/${gameId}`, {
      headers: {
        Accept: 'application/json',
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    })
    return response.data.data
  } catch (error) {
    console.error(error)
    throw error
  }
}

export { fetchGameData }
