<template>
  <div class="product-detail">
    <div v-if="product">
      <div class="product">
        <div class="product-image">
          <img :src="product.productSrc" />
        </div>
        <div class="product-info">
          <h1 class="product-name">{{ product.productName }}</h1>
          <div class="product-cost">¥ {{ product.productPrice }}</div>
          <div class="product-desc">{{ product.productDesc}}</div>
          <div class="product-ice">
            <el-radio v-model="radio1" label="1" border>正常冰</el-radio>
            <el-radio v-model="radio1" label="2" border>少冰</el-radio>
          </div>
          <div class="product-sweet">
            <el-radio v-model="radio2" label="1" border>正常糖</el-radio>
            <el-radio v-model="radio2" label="2" border>少糖</el-radio>
          </div>

          <div class="product-add-cart" @click="handleAddToCart(product.productName)">加入购物车</div>
        </div>
      </div>
      <!-- <div class="product-desc">
            <h2>产品介绍</h2>
            <img v-for="n in 10" :src="product.productSrc">
      </div>-->
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      id: parseInt(this.$route.params.id),
      product: null,
      radio1:'',
      radio2:''
    };
  },
  methods: {
    getProduct() {
      console.log(this.id);
      this.$axios
        .get("/api/product/getProductById", { params: { product_id: this.id } })
        .then(res => {
          this.product = res.data;
          console.log(this.product);
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleAddToCart(e) {
      this.$store.commit("addCart", this.id);
       this.$message({
        message:"商品" + e + "加入购物车成功！",
        type: "success"
      });
    }
  },
  mounted() {
    this.getProduct();
  }
};
</script>
<style scoped>
.product-detail {
  box-sizing: border-box;
  width: 100%;
  min-height: 100%;
  overflow: hidden;
  background: #f7f8f8;
}
.product {
  box-sizing: border-box;
  margin: 32px;
  padding: 32px;
  background: #fff;
  border: 1px solid #dddee1;
  border-radius: 10px;
  overflow: hidden;
}
.product-image {
  width: 50%;
  height: 500px;
  float: left;
  text-align: center;
}
.product-image img {
  height: 100%;
  width: 100%;
}
.product-ice {
  margin: 10px;
}

.product-info {
  width: 50%;
  padding: 150px 0 250px;
  height: 150px;
  float: left;
  text-align: center;
}
.product-cost {
  color: #f2352e;
  margin: 8px 0;
}
/* .product-desc{
        color: #f2352e;
        margin: 8px 0;
    } */
.product-add-cart {
  display: inline-block;
  padding: 8px 64px;
  margin: 8px 0;
  background: #2d8cf0;
  color: #fff;
  border-radius: 4px;
  cursor: pointer;
}
/* .product-desc{
        background: #fff;
        margin: 32px;
        padding: 32px;
        border: 1px solid #dddee1;
        border-radius: 10px;
        text-align: center;
    }
    .product-desc img{
        display: block;
        width: 50%;
        margin: 32px auto;
        padding: 32px;
        border-bottom: 1px solid #dddee1;
    } */
</style>
