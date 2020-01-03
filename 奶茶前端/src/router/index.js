import Vue from 'vue'
import VueRouter from 'vue-router'
import count from '../views/count.vue'
import notFound from '../views/404.vue'
import login from '../views/login.vue'
import home from '../views/home.vue'
import commodity from '../views/commodity.vue'
import cart from '../views/cart.vue'
import productDetail from '../views/productDetail.vue'
import order from '../views/order.vue'
import storeMag from '../views/storeMag.vue'
Vue.use(VueRouter)
const routes = [{
    path: '/',
    redirect: '/login'
  },
  {
    path: '/404',
    name: 'notFound',
    component: notFound
  },
  {
    path: '/home',
    name: 'home',
    component: home,
    children: [{
        path: '/count',
        name: 'count',
        component: count
      },
      {
        path: '/commodity',
        name: 'commodity',
        component: commodity
      },
      {
        path: '/cart',
        name: 'cart',
        component: cart
      },
      {
        path: '/product/:id',
        name: 'productDetail',
        component: productDetail
      },
      {
        path: '/order',
        name: 'order',
        component: order
      },
      {
        path: '/storeMag',
        name: 'storeMag',
        component: storeMag
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '*',
    name: 'notFound',
    component: notFound
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
export default router
