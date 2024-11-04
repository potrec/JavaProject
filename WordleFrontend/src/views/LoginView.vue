<template>
  <div class="w-1/3 mt-12">
    <form @submit="onSubmit" class="space-y-4">
      <FormField v-slot="{ componentField }" name="username">
        <FormItem>
          <FormLabel>Username</FormLabel>
          <FormControl>
            <Input type="text" placeholder="Username" v-bind="componentField" />
          </FormControl>
          <FormMessage />
        </FormItem>
      </FormField>

      <FormField v-slot="{ componentField }" name="password">
        <FormItem>
          <FormLabel>Password</FormLabel>
          <FormControl>
            <Input
              type="password"
              placeholder="Password"
              v-bind="componentField"
            />
          </FormControl>
          <FormMessage />
        </FormItem>
      </FormField>

      <FormItem class="flex items-center">
        <div class="flex items-center space-x-2">
          <Checkbox id="remember" />
          <label
            for="remember"
            class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
          >
            Remember me
          </label>
        </div>
      </FormItem>

      <FormItem class="flex items-center">
        <div class="flex items-center space-x-2">
          <Button type="submit">Submit</Button>
          <Button variant="link" href="/register">
            <a href="/register" class="text-sm font-medium leading-none">
              No account? Sign In
            </a></Button
          >
        </div>
      </FormItem>
    </form>
    <div class="errors">
      <p v-if="errorData.errors.length" class="text-sm font-medium text-destructive">
        {{ errorData.statusCode }} {{ errorData.message }}
        <template v-for="error in errorData.errors">

          {{ error + ' ' }}
        </template>
      </p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useForm } from 'vee-validate'
import { toTypedSchema } from '@vee-validate/zod'
import * as z from 'zod'

import {
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Checkbox } from '@/components/ui/checkbox'
import axios, { type AxiosResponse } from 'axios'
import { ref } from 'vue'
import type { ErrorData } from '@/interfaces/ErrorData'
import { useLocalStorage } from '@vueuse/core'
import router from '@/router'

interface LoginData {
  username: string
  password: string
}

const errorData = ref<ErrorData>({
  message: '',
  errors: [],
  statusCode: 0,
})

const formSchema = toTypedSchema(
  z.object({
    username: z
      .string()
      .min(5, { message: 'Username must have at least 5 characters' })
      .max(20, { message: 'Username must contain at most 20 characters' }),
    password: z
      .string()
      .min(8, { message: 'Password must have at least 8 characters' })
      .max(20, { message: 'Password must contain at most 20 characters' }),
  }),
)

const form = useForm<LoginData>({
  validationSchema: formSchema,
})

const jwtToken = useLocalStorage('jwt', '')

const onSubmit = form.handleSubmit(async (values: LoginData) => {
  const HOST = import.meta.env.VITE_API_HOST
  const PORT = import.meta.env.VITE_API_PORT

  console.log(HOST, PORT)
  try {
    const response: AxiosResponse = await axios.post(
      `${HOST}:${PORT}/auth/login`,
      values,
      {
        headers: {
          Accept: 'application/json',
        },
      },
    )
    jwtToken.value = response.data.data.jwt
  } catch (error) {
    errorData.value = {
      message: error.response.data.message,
      errors: error.response.data.errors,
      statusCode: error.response.status,
    }
  } finally {
    await router.push('/main')
  }
})
</script>
