<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import Button from '@/components/ui/button/Button.vue'
</script>

<template>
  <nav class="space-x-8">
    <Button><RouterLink to="/">Home</RouterLink></Button>
    <Button><RouterLink to="/about">About</RouterLink></Button>
    <Button><RouterLink to="/register">Sign up</RouterLink></Button>
    <Button><RouterLink to="/login">Login</RouterLink></Button>
  </nav>
  <RouterView />
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
