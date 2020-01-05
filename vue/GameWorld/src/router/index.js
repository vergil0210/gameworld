import Vue from 'vue'
import Router from 'vue-router'
import homePage from '@/components/HomePage'
import headTop from '@/components/MenuTemplate/headTop'
import index from '@/components/Index'

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
    }
  ]
})
