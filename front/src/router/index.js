import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Hem',
    component: Home
  },
  {
    path: '/vissla',
    name: 'Vissla',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "whistle" */ '../views/blower/Whistle.vue')
  },
  {
    path: '/nyttarende',
    name: 'Nytt ärende',
    component: () => import('../views/blower/NewIssue.vue')
  },
  {
    path: '/sakerinloggning',
    name: 'Logga in',
    component: () => import ('../views/blower/Login.vue')
  },
  {
    path: '/forhandsgranska',
    name: 'Förhandsgranska',
    component: () => import ('../views/blower/PreviewIssue.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
