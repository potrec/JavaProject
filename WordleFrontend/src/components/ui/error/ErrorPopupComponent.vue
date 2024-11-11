<script setup lang="ts">
import { ref } from 'vue'

const props = defineProps<{ message: string }>()
const visible = ref(true)

const close = () => {
  visible.value = false
}
</script>

<template>
  <div v-if="visible" class="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50">
    <div class="bg-white p-4 rounded shadow-lg">
      <h2 class="text-xl font-bold mb-2">Error</h2>
      <p>{{ props.message }}</p>
      <button @click="close" class="mt-4 bg-red-500 text-white px-4 py-2 rounded">Close</button>
    </div>
  </div>
</template>

<style scoped>

</style>
