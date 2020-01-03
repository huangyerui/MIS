import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    // 商品列表数据
    productList: [],
    // 订单列表
    orderList: [],
    // 购物车数据
    cartList: [],
    // 用户消息
    user: {},
    // 产品销售数组
    proSaleList: [],
    // 用户销售数组
    userSaleList: [],
    // 仓库产品
    storeList: [],
    // 修改的仓库产品
    changeList: []
  },
  mutations: {
    // 添加订单
    setOrderList(state, data) {
      state.orderList = data
    },
    // 添加订单
    setStoreList(state, data) {
      state.storeList = data
    },
    // 添加用户
    setUser(state, data) {
      state.user = data
    },
    // 添加商品列表
    setProductList(state, data) {
      state.productList = data
    },
    //产品销售
    setProSaleList: function (state, data) {
      state.proSaleList = data
      console.log(state.productList)
    },
    // 用户销售
    setUserSaleList: function (state, data) {
      state.userSaleList = data
    },
    // 加入购物车
    addCart(state, product_id) {
      const isAdded = state.cartList.find(item => item.product_id === product_id);
      if (isAdded) {
        isAdded.count++
      } else {
        state.cartList.push({
          product_id: product_id,
          count: 1
        })
      }
    },
    // 编辑购物车
    editCartCount(state, playload) {
      const product = state.cartList.find(item => item.product_id === playload.product_id)
      product.count += playload.count;
    },
    // 删除购物车商品
    deleteCart(state, product_id) {
      const index = state.cartList.findIndex(item => item.product_id === product_id)
      state.cartList.splice(index, 1)
    },
    // 清空购物车
    emptyCart(state) {
      state.cartList.splice(0, state.cartList.length)
    },
    // 编辑仓库
    editStoreCount(state, playload) {
      state.storeList.forEach(item => {
        if (item.store_id == playload.store_id) {
          item.store_number = playload.count
        }
      })

      const isAdded = state.changeList.length > 0 && state.changeList.find(item => item.store_id === playload.store_id);
      if (isAdded) {
        isAdded.count = playload.count;
      } else {
        state.changeList.push(playload)
        console.log(state.changeList)
      }
    },
    // 清空购物车
    emptyStoreCount(state) {
      state.changeList.splice(0, state.changeList.length)
    }
  },
  actions: {
    getProductList(context) {
      axios
        .get('/api/product/getProAll')
        .then(res => {
          console.log(res)
          context.commit('setProductList', res.data)
          // console.log(this.product)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getProductSale(context) {
      axios
        .get('/api/order/getProductSale', {
          params: {
            order_date: '2019'
          }
        })
        .then(res => {
          console.log(res)
          context.commit('setProSaleList', res.data)
          // console.log(this.product)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getUserSale(context) {
      axios
        .get('/api/order/getUserSale', {
          params: {
            order_date: '2019'
          }
        })
        .then(res => {
          console.log(res)
          context.commit('setUserSaleList', res.data)
          // console.log(this.product)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getOrderList(context) {
      axios.get('/api/order/getSale').then(
        res => {
          console.log(res)
          context.commit('setOrderList', res.data)
        }
      )
    },
    getStoreList(context) {
      axios.get('/api/store/getAll').then(
        res => {
          console.log(res)
          context.commit('setStoreList', res.data)
        }
      )
    }
  },
  modules: {}
})
