<template>
  <div class="body">
    <div class="cart">
      <div class="cart-header">
        <div class="cart-header-title">购物清单</div>
        <div class="cart-header-main">
          <div class="cart-info">商品信息</div>
          <div class="cart-price">单价</div>
          <div class="cart-count">数量</div>
          <div class="cart-cost">小计</div>
          <div class="cart-delete">删除</div>
        </div>
      </div>
      <div class="cart-content">
        <div class="cart-content-main" v-for="(item,index) in cartList" :key="item.product_id">
          <div class="cart-info">
            <img :src="productDictList[item.product_id].product_src" />
            <span>{{productDictList[item.product_id].product_name}}</span>
          </div>
          <div class="cart-price">￥ {{productDictList[item.product_id].product_price}}</div>
          <div class="cart-count">
            <span class="cart-control-minus" @click="handleCount(index,-1)">-</span>
            {{item.count}}
            <span class="cart-control-add" @click="handleCount(index,1)">+</span>
          </div>
          <div class="cart-cost">￥ {{productDictList[item.product_id].product_price*item.count}}</div>
          <div class="cart-delete">
            <span class="cart-control-delete" @click="handleDelete(index)">删除</span>
          </div>
        </div>
        <div class="cart-empty" v-if="!cartList.length">购物车为空</div>
      </div>
      <div class="cart-footer" v-show="cartList.length">
        <div class="cart-footer-desc">
          共计
          <span>{{countAll}}</span>件商品
        </div>
        <div class="cart-footer-desc">
          应付总额
          <span>￥ {{costAll}}</span>
        </div>
        <div class="cart-footer-desc">
          <div class="cart-control-order" @click="handleStore">现在结算</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      leg: 0,
      storeleg: 0
    };
  },
  computed: {
    storeList() {
      return this.$store.state.storeList;
    },
    storeDictList() {
      const dict = {};
      this.storeList.forEach(item => {
        dict[item.store_id] = item;
      });
      return dict;
    },
    cartLength() {
      return this.$store.state.cartList.length;
    },
    cartList() {
      return this.$store.state.cartList;
    },
    productList() {
      return this.$store.state.productList;
    },
    productDictList() {
      const dict = {};
      this.productList.forEach(item => {
        dict[item.product_id] = item;
      });
      return dict;
    },
    countAll() {
      let count = 0;
      this.cartList.forEach(item => {
        count += item.count;
      });
      return count;
    },
    costAll() {
      let totalPrice = 0;
      this.cartList.forEach(item => {
        totalPrice +=
          this.productDictList[item.product_id].product_price * item.count;
      });
      return totalPrice;
    }
  },
  mounted(){
      if(!this.$store.state.user.userName){
        this.$router.push('/login')
       }
  },
  methods: {
    //仓库的数量
    handleStore() {
      const newList = JSON.parse(JSON.stringify(this.storeDictList));
      try {
        this.cartList.forEach(item => {
          this.productDictList[item.product_id].product_store
            .split("-")
            .forEach(ele => {
              let store = {
                store_id: 0,
                count: 0
              };
              store.store_id = ele;
              store.count = newList[ele].store_number - 1 * item.count;
              if (store.count >= 0) {
                this.$store.commit("editStoreCount", store);
              } else {
                throw new Error(this.storeDictList[ele].store_name);
              }
            });
        });
        this.handleSubmit();
      } catch (e) {
        this.$store.commit("emptyStoreCount");
        this.$message({
          message: "原料" + e.message + "不足！",
          type: "fail"
        });
      }
    },
    //提交修改申请
    handleSubmit() {
      console.log(this.$store.state.changeList);
      this.storeleg = this.$store.state.changeList.length;
      this.$store.state.changeList.forEach(item => {
        let store = {};
        store.store_number = item.count;
        store.store_id = item.store_id;
        this.correct(store);
      });
    },
    //修改仓库
    correct(store) {
      console.log(store);
      this.$axios
        .get("/api/store/update", { params: store })
        .then(res => {
          console.log(res);
          this.storeleg--;
          if (!this.storeleg) {
            this.handleOrder();
            this.$store.commit("emptyStoreCount");
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    //购物车商品的数量
    handleCount(index, count) {
      if (count < 0 && this.cartList[index].count == 1) {
        return;
      }
      this.$store.commit("editCartCount", {
        product_id: this.cartList[index].product_id,
        count: count
      });
    },
    handleDelete(index) {
      this.$store.commit("deleteCart", this.cartList[index].product_id);
    },
    handleOrder() {
      this.leg = this.cartLength;
      let orderNumber = Date.now();
      let orderDate = this.formatTime(new Date());
      this.cartList.forEach(item => {
        let order = {};
        order.product_id = item.product_id;
        order.order_id = 0;
        order.order_number = orderNumber;
        order.order_price =
          this.productDictList[item.product_id].product_price * item.count;
        order.user_id = this.$store.state.user.userId;
        order.order_productNumber = item.count;
        order.order_date = orderDate;
        console.log(order);
        this.buy(order);
      });
    },
    buy(order) {
      console.log(order);
      this.$axios
        .get("/api/order/save", { params: order })
        .then(res => {
          console.log(res);
          this.leg--;
          if (!this.leg) {
            this.$message({
              message: "结算成功！",
              type: "success"
            });
            this.$store.commit("emptyCart");
          }
        })
        .catch(err => {
          this.$message({
            message: "结算失败,请先登录！",
            type: "fail"
          });
          console.log(err);
        });
    },
    formatNumber() {
      return n => {
        n = n.toString();
        return n[1] ? n : "0" + n;
      };
    },
    // 时间格式化
    formatTime(date) {
      const year = date.getFullYear();
      const month = date.getMonth() + 1;
      const day = date.getDate();
      const hour = date.getHours();
      const minute = date.getMinutes();
      const second = date.getSeconds();
      return (
        [year, month, day].map(this.formatNumber()).join("-") +
        " " +
        [hour, minute, second].map(this.formatNumber()).join(":")
      );
    }
  }
};
</script>
<style scoped>
.body {
  box-sizing: border-box;
  width: 100%;
  min-height: 100%;
  overflow: hidden;
  background: #f7f8f8;
}

.cart {
  box-sizing: border-box;
  margin: 32px;
  background: #fff;
  border: 1px solid #dddee1;
  border-radius: 10px;
}
.cart-header-title {
  padding: 16px 32px;
  border-bottom: 1px solid #dddee1;
  border-radius: 10px 10px 0 0;
  background: #f8f8f9;
}
.cart-header-main {
  text-align: center;
  padding: 8px 32px;
  overflow: hidden;
  border-bottom: 1px solid #dddee1;
  background-color: #eee;
  overflow: hidden;
}
.cart-empty {
  text-align: center;
  padding: 32px;
}
.cart-header-main div {
  box-sizing: border-box;
  text-align: center;
  float: left;
  padding: 25px;
}
div .cart-info {
  width: 60%;
  text-align: left;
}
.cart-count {
  width: 10%;
}
.cart-cost {
  width: 10%;
}
.cart-delete {
  width: 10%;
}
.cart-price {
  width: 10%;
}
.cart-content-main {
  padding: 0 32px;
  height: 60px;
  line-height: 60px;
  text-align: center;
  border-bottom: 1px dashed #e9eaec;
  overflow: hidden;
}
.cart-content-main div {
  float: left;
}
.cart-content-main img {
  width: 40px;
  height: 40px;
  margin-right: 10px;
  position: relative;
  top: 10px;
}
.cart-content-main div {
  float: left;
}
.cart-control-minus,
.cart-control-add {
  display: inline-block;
  margin: 0 4px;
  width: 24px;
  height: 24px;
  line-height: 22px;
  text-align: center;
  background-color: #f8f8f9;
  border-radius: 50%;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
  cursor: pointer;
}
.cart-control-delete {
  cursor: pointer;
  color: #2d8cf0;
}
.cart-control-order {
  display: inline-block;
  padding: 8px 32px;
  border-radius: 6px;
  background: #2d8cf0;
  color: #fff;
  cursor: pointer;
}
.cart-footer {
  padding: 32px;
  text-align: right;
}
.cart-footer-desc {
  display: inline-block;
  padding: 0 16px;
}
.cart-footer-desc span {
  color: #f2352e;
  font-size: 20px;
}
</style>