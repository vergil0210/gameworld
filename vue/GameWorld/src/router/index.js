import Vue from 'vue'
import Router from 'vue-router'
import Menu from '@/components/Menu'
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
      path: '/menu',
      name: 'Menu',
      component: Menu,
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
