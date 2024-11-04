<script setup lang="ts">
const letterArray = ['a','ą','b','c','ć','d','e','ę','f','g','h','i',
  'j','k','l','ł','m','n','ń','o','ó','p','q','r','s','ś','t','u','v',
  'w','x','y','z','ź','ż']
</script>

<template>
  <div class="keyboard">
    <div class="grid grid-cols-12 gap-1">
      <div
        v-for="(letter, index) in letterArray"
        :key="index"
        class="w-8  h-16 flex items-center justify-center text-primary-foreground bg-primary"
      >
        {{ letter }}
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
