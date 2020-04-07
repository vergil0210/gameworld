import Vue from 'vue'
import Router from 'vue-router'
import Cookie from 'js-cookie'
import store from '@/store/index'
import login from '@/components/Login'
import home from '@/components/Home'
import addProduct from '@/components/productControl/AddProduct'
import deleteProduct from '@/components/productControl/DeleteProduct'
import onSale from '@/components/productControl/OnSale'
import addType from '@/components/productControl/AddType'
import addOrder from '@/components/orderControl/AddOrder'
import orderList from '@/components/orderControl/OrderList'
import orderReturn from '@/components/orderControl/OrderReturn'
import register from '@/components/Register'
import authControl from '@/components/authControl/AuthControl'
Vue.use(Router)

// export default new Router({
//   routes: [
//     {
//       name: login,
//       path: '/',
//       component: login
//     },
//     {
//       name: home,
//       path: '/home',
//       component: home,
//       meta: {
//         requireAuth: true
//       },
//       children: [
//         {
//           name: addProduct,
//           path: 'addProduct',
//           component: addProduct
//         },
//         {
//           name: deleteProduct,
//           path: '/',
//           component: deleteProduct
//         },
//         {
//           name: deleteProduct,
//           path: 'deleteProduct',
//           component: deleteProduct
//         },
//         {
//           name: addType,
//           path: 'addType',
//           component: addType
//         },
//         {
//           name: onSale,
//           path: 'onSale',
//           component: onSale
//         },{
//           name: orderReturn,
//           path: 'orderReturn',
//           component: orderReturn
//         },
//         {
//           name: addOrder,
//           path: 'addOrder',
//           component: addOrder
//         },
//         {
//           name: orderList,
//           path: 'orderList',
//           component: orderList
//         },
//       ]
//     },
//   ]
// })

const router = new Router({
  routes: [
    {
      name: login,
      path: '/',
      component: login
    },
    {
      name: register,
      path: '/register',
      component: register
    },
    {
      name: home,
      path: '/home',
      component: home,
      meta: {
        requireAuth: true
      },
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
          name: addType,
          path: 'addType',
          component: addType
        },
        {
          name: onSale,
          path: 'onSale',
          component: onSale
        },{
          name: orderReturn,
          path: 'orderReturn',
          component: orderReturn
        },
        {
          name: addOrder,
          path: 'addOrder',
          component: addOrder
        },
        {
          name: orderList,
          path: 'orderList',
          component: orderList
        },
        {
          name: authControl,
          path: 'authControl',
          component: authControl
        }
      ]
    },
  ]
})

export default router
const _this = this

router.beforeEach((to, from, next) => {
  console.log('=================开始验证用户token=================')
  if (to.matched.some(record => record.meta.requireAuth)){ // 判断该路由是否需要登录权限
    let token = Cookie.get("token")
    if (token) { // 判断当前的token是否存在
      console.log('=================验证通过,开始跳转页面=================')
      next();
    }
    else {
      console.log('=================验证失败即将返回登录页面=================')
      next({
        path: '/',
        query: {redirect: to.fullPath} // 将要跳转路由的path作为参数，传递到登录页面
      })
    }
  }
  else {
    next();
  }
})

// export default router
