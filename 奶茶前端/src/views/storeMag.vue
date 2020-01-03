<template>
  <div class="body">
    <div class="cart">
      <div class="cart-header">
        <div class="cart-header-title">仓库列表</div>
        <div class="cart-header-main">
          <div class="cart-info">原料信息</div>
          <div class="cart-price">剩余量</div>
          <div class="cart-cost">进货</div>
          <div class="cart-delete">出货</div>
        </div>
      </div>
      <div class="cart-content">
        <div class="cart-content-main" v-for="(item,index) in storeList" :key="item.store_id">
          <div class="cart-info">
            <span>{{item.store_name}}</span>
          </div>
          <div class="cart-price">{{item.store_number}}</div>
          <div class="cart-count">
           <el-button type="info" round @click="handleCount(index,1)">增加</el-button>
            <!-- <span class="cart-control-minus" @click="handleCount(index,-1)">-</span> -->
          </div>
          <div class="cart-count">
            <el-button type="info" round @click="handleCount(index,-1)">减少</el-button>
              <!-- <span class="cart-control-add" @click="handleCount(index,1)">+</span> -->
          </div>
           
        </div>
      </div>
      <div class="cart-footer">
        <div class="cart-footer-desc">
          <div class="cart-control-order" @click="handleStore">确定修改</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      leg:0
    };
  },
  computed: {
    storeList() {
      return this.$store.state.storeList;
    }
  },
  created() {
    this.$store.dispatch("getStoreList");
  },
  methods: {
    handleCount(index, count) {
      if (count < 0 && this.storeList[index].store_number == 0) {
        return;
      }
      this.$store.commit("editStoreCount", {
       store_id: this.storeList[index].store_id,
        count: parseInt(this.storeList[index].store_number) + count
      });
    },
    handleDelete(index) {
      this.$store.commit("deleteCart", this.cartList[index].product_id);
    },
    handleStore(){
      this.leg = this.$store.state.changeList.length
      this.$store.state.changeList.forEach(item=>{
        let store = {}
        store.store_number = item.count
        store.store_id = item.store_id
        this.correct(store)
      })
    },
     correct (store) {
      console.log(store)
      this.$axios.get('/api/store/update', {params:store}).then(res => {
        console.log(res)
        this.leg--
        if(!this.leg){
           this.$message({
        message:"修改成功！",
        type: "success"
        });
        this.$store.commit('emptyStoreCount')
        }
      }).catch(err => {
          this.$message({
        message:"修改失败,请先登录！",
        type: "fail"
        });
        console.log(err)
      })
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
  width: 70%;
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
  padding: 15px 32px;
  border-radius: 6px;
  background: #2d8cf0;
  color: #fff;
  cursor: pointer;
}
.cart-footer {
  padding: 32px;
  text-align: center;
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
