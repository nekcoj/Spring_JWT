import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import store from '../store'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '',
      name: 'Hem',
      component: Home
    },
    {
      path: '/vissla',
      name: 'Vissla',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "whistle" */ '../views/blower/Whistle.vue'),
    },
    {
      path: '/user',
      name: 'Vad göra?',
      component: () => import('../views/blower/Dash.vue'),
      beforeEnter: (to, from, next) => {
        if(store.state.authUser === '/user'){
          next()
        } else {
          next('/')
        }
      },
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
    },
    {
      path: '/bekraftelse',
      name: 'Bekräftelse',
      component: () => import ('../views/blower/Confirmation.vue')
    },
    {
      path: '/admin',
      component: () => import ('../views/admin/Dashboard-base.vue'),
      children: [
        {path: '', name: 'AdminDash' ,component: () => import ('../views/admin/Dashboard.vue')},
        {path: 'arenden', component: () => import ('../views/admin/NewPosts.vue')}
      ],
      beforeEnter: (to, from, next) => {
        if(store.state.authUser === '/admin'){
          next()
        } else {
          next('/')
        }
      }
    },
    {
      path: '/inloggning',
      name: 'Logga in admin',
      component: () => import ('../views/admin/LoginAdmin.vue'),
      children: [
        {path: '', name: 'GDPR', component: () => import ('../modules/gdpr-module.vue') }
      ],
    },
    {
      path: '/lawyer',
      component: () => import ('../views/lawyer/Dashboard-base.vue'),
      children: [
        {path: '', name: 'LawyerDash' ,component: () => import ('../views/lawyer/Dashboard-content.vue')},
        {path: 'arenden', component: () => import ('../views/lawyer/NewPosts.vue')},
        {path: 'postbox/:issueId', name: 'Safe postbox jurist', 
        children: [
          {path: '', name: 'FetchResponse', component: () => import ('../modules/fetch-response-module.vue') }
        ],
        component: () => import ('../views/lawyer/Safepostbox.vue')}, 
      ],
      beforeEnter: (to, from, next) => {
        if(store.state.authUser === '/lawyer'){
          next()
        } else {
          next('/')
        }
      }
    },
    {
      path: '/safepostbox',
      name: 'Safe postbox anmälare',
      component: () => import ('../views/blower/Safepostbox.vue'),
      children: [
        {path: '', name: 'FetchResponse', component: () => import ('../modules/fetch-response-module.vue') }
      ],
      beforeEnter: (to, from, next) => {
        if(store.state.authUser === '/user'){
          next()
        } else {
          next('/')
        }
      }
    }
  ]
});


export default router


