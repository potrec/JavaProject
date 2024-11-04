<script setup lang="ts">
import { ref } from 'vue'

const grid = ref(Array.from({ length: 5 }, () => Array(6).fill('')))
const colors = ['bg-gray-200', 'bg-gray-400', 'bg-yellow-300 ', 'bg-green-500']
const round = ref(0)
const finished = ref(false)

</script>

<template>
  <div class="grid grid-cols-5 gap-4 mb-4 mt-4">
    WE ARE GUESSING THE WORD {{}}
    <div
      v-for="(row, rowIndex) in grid"
      :key="rowIndex"
      class="flex flex-col space-y-4"
    >
      <div
        v-for="(cell, cellIndex) in row"
        :key="cellIndex"
        class="w-16 h-16 bg-gray-200 flex items-center justify-center"
      >
        {{ cell  }}
      </div>
    </div>
  </div>
</template>

<style scoped></style>
