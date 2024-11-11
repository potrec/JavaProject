import { createRouter, createWebHistory } from 'vue-router'
import { isAuthenticated } from '@/utils/auth'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: import('../views/HomeView.vue'),
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/RegisterView.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('../views/MainView.vue'),
    },
    {
      path: '/game/:id',
      name: 'game',
      component: () => import('../views/GameView.vue'),
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/games',
      name: 'games',
      component: () => import('../views/GamesView.vue'),
    }
  ],
})

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/', '/about']
  const authRequired = !publicPages.includes(to.path)
  window.history.pushState(null, '', window.location.href);
  if (authRequired && !isAuthenticated()) {
    console.log('Not authenticated')
    return next('/login')
  }

  next()
})

export default router
