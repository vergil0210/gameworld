import Vue from 'vue'
import Router from 'vue-router'
import homePage from '@/components/HomePage'
import index from '@/components/Index'
import regLogin from '@/components/RegLogin'
import headTop from '@/components/MenuTemplate/headTop'
import login from '@/components/RegLogin/Login'
import register from '@/components/RegLogin/register'

Vue.use(Router)

export default new Router({
  base: '/gameWorld/',
  
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/index',
      name: 'index',
      component: index
    },
    {
      path: '/homePage',
      name: 'HomePage',
      component: homePage,
      children: [
        {
          path: '/',
          name: 'headTop',
          component: headTop
        }
      ]
    },
    {
      path: '/regLogin',
      name: 'regLogin',
      component: regLogin,
      children: [
        {
          path: '/',
          name: 'login',
          component: login
        },
        {
          path: 'login',
          name: 'login',
          component: login
        },
        {
          path: 'register',
          name: 'register',
          component: register
        },
      ]
    }
  ]
})
