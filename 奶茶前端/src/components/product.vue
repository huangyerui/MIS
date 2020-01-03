<template>
  <div>
    <div class="product">
      <router-link :to="'/product/'+ infor.product_id" class="product-main">
        <div class="img">
          <img :src="infor.product_src" />
        </div>
        <h4>{{infor.product_name}}</h4>
        <div class="product-cost">￥{{infor.product_price}}</div>
        <div class="product-add-cart" @click.prevent="handleCart(infor.product_name)">加入购物车</div>
      </router-link>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    infor: Object
  },
  data() {
    return {};
  },
  methods: {
    handleCart(e) {
      console.log(e);
      this.$store.commit("addCart", this.infor.product_id);
      this.$message({
        message: "商品" + e + "加入购物车成功！",
        type: "success"
      });
    }
  }
};
</script>
<style scoped>
.product {
  width: 25%;
  float: left;
}
.product-main {
  display: block;
  margin: 16px;
  padding: 16px;
  border: 1px solid #dddee1;
  border-radius: 6px;
  overflow: hidden;
  background: #fff;
  text-align: center;
  position: relative;
}
.img {
   position:relative;
    width:100%;
    height:0;
    padding-top: 70%;
}
.img img{
  position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
}
h4 {
  margin: 10px 0px;
  color: #222;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.product-main:hover h4 {
  color: #0070c9;
}
.product-cost {
  color: #de4037;
  margin-top: 6px;
}
.product-add-cart {
  display: none;
  padding: 4px 8px;
  background: #2d8cf0;
  color: #fff;
  font-size: 12px;
  border-radius: 3px;
  cursor: pointer;
  position: absolute;
  top: 5px;
  right: 5px;
}
.product-main:hover .product-add-cart {
  display: inline-block;
}
a {
  text-decoration: none;
}
</style>