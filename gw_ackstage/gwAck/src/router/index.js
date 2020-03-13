import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/Login'
import home from '@/components/Home'
import addProduct from '@/components/productControl/AddProduct'
import deleteProduct from '@/components/productControl/DeleteProduct'
import onSale from '@/components/productControl/OnSale'
import addType from '@/components/productControl/AddType'

Vue.use(Router)

export default new Router({
  routes: [
    {
      name: login,
      path: '/',
      component: login
    },
    {
      name: home,
      path: '/home',
      component: home,
      children: [
        {
          name: addProduct,
          path: 'addProduct',
          component: addProduct
        },
        {
          name: deleteProduct,
          path: '/',
          component: deleteProduct
        },
        {
          name: deleteProduct,
          path: 'deleteProduct',
          component: deleteProduct
        },
        {
          name: onSale,
          path: 'onSale',
          component: onSale
        },{
          name: addType,
          path: 'addType',
          component: addType
        }
      ]
    },
  ]
})
