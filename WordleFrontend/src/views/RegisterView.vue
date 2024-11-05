<template>
  <div class="mt-12">
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

      <FormField v-slot="{ componentField }" name="email">
        <FormItem>
          <FormLabel>Email</FormLabel>
          <FormControl>
            <Input type="email" placeholder="Email" v-bind="componentField" />
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
          <Button type="submit">Submit</Button>
          <Button variant="link" href="/login">
            <a href="/login" class="text-sm font-medium leading-none">
              You have account? Log in
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
import { ref } from 'vue'
import { useForm } from 'vee-validate'
import { toTypedSchema } from '@vee-validate/zod'
import * as z from 'zod'
import axios, { type AxiosResponse } from 'axios'

import {
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'

import type { ErrorData } from '@/interfaces/ErrorData'
import type { RegisterData } from '@/interfaces/RegisterData'
import router from '@/router'

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
    email: z.string().email({ message: 'Invalid email address' }),
    password: z
      .string()
      .min(8, { message: 'Password must have at least 8 characters' })
      .max(20, { message: 'Password must contain at most 20 characters' }),
  }),
)

const form = useForm<RegisterData>({
  validationSchema: formSchema,
})

const onSubmit = form.handleSubmit(async (values: RegisterData) => {
  const HOST = import.meta.env.VITE_API_HOST
  const PORT = import.meta.env.VITE_API_PORT

  try {
    const response: AxiosResponse = await axios.post(
      `${HOST}:${PORT}/auth/register`,
      values,
      {
        headers: {
          Accept: 'application/json',
        },
      },
    )

    console.log('Register successful:', response.data)
    await router.push({ name: '/login'})
  } catch (error) {
    errorData.value = {
      message: error.response.data.message,
      errors: error.response.data.errors,
      statusCode: error.response.status,
    }
  }
})
</script>
