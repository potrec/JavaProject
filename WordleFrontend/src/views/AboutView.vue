<script setup lang="ts">
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
</script>

<template>
  <div class="about-container flex justify-center items-center h-full p-4">
    <Card class="max-w-screen-md w-full">
      <CardHeader>
        <CardTitle>About</CardTitle>
      </CardHeader>
      <CardContent>
        <p>This project is a web-based version of the popular word puzzle game, Wordle, developed with a Vue 3 frontend and a Java Spring backend. The game challenges players to guess a five-letter word within six attempts, providing feedback on each attempt to help them find the correct answer.</p>
        <h2 class="text-xl font-semibold mt-4">Technologies Used:</h2>
        <ul class="list-disc list-inside">
          <li>Frontend: Vue 3 for building a responsive, interactive user interface</li>
          <li>Backend: Java Spring for managing word lists, tracking game logic, and handling user sessions</li>
          <li>API Communication: RESTful API calls for efficient, secure communication between the frontend and backend</li>
        </ul>
        <h2 class="text-xl font-semibold mt-4">Features:</h2>
        <ul class="list-disc list-inside">
          <li>Real-time feedback on each guess, with color-coded hints for correct, misplaced, and incorrect letters</li>
          <li>Play as you wish, click play button to guess one of the 28225 polish words</li>
          <li>User-friendly design that adapts across various screen sizes</li>
          <li>Data persistence for saving and retrieving game history</li>
        </ul>
        <p class="mt-4">This project demonstrates a full-stack approach to modern web development, combining responsive UI with robust backend logic to deliver an engaging game experience.</p>
      </CardContent>
    </Card>
  </div>
</template>

<style scoped>
.about-container {
  text-align: left;
}
</style>
