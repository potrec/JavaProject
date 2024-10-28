<template>
  <div class="home">
    <h1>Welcome to Home View</h1>
    <p>{{ message }}</p>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'

export default defineComponent({
  name: 'HomeView',
  setup() {
    const message = ref('This is the home view of your Wordle project.')

    return {
      message,
    }
  },
})
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 50px;
}
</style>
