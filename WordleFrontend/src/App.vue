<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import Button from '@/components/ui/button/Button.vue'
import { errorMessage, clearError } from '@/stores/errorStore'
import ErrorPopup from '@/components/ui/error/ErrorPopupComponent.vue'
</script>

<template>
  <div class="app-container flex flex-col h-screen">
    <nav class="flex justify-evenly space-x-2 h-16">
      <Button><RouterLink to="/">Home</RouterLink></Button>
      <Button><RouterLink to="/main">Main</RouterLink></Button>
      <Button><RouterLink to="/about">About</RouterLink></Button>
      <Button><RouterLink to="/register">Sign up</RouterLink></Button>
      <Button><RouterLink to="/login">Login</RouterLink></Button>
    </nav>
    <div class="flex-grow">
      <RouterView :key="$route.fullPath" />
    </div>
    <ErrorPopup v-if="errorMessage" :message="errorMessage" @close="clearError" />
  </div>
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  background-color: var(--color-background);
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}
</style>
