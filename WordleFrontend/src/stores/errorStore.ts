import { ref } from 'vue'

export const errorMessage = ref<string | null>(null)

export const setError = (message: string) => {
  errorMessage.value = message
}

export const clearError = () => {
  errorMessage.value = null
}
