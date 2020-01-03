<template>
  <div class="body">
    <div class="list-control">
      <div class="list-control-order">
        <span>排序：</span>
        <span
          class="list-control-order-item"
          :class="{on:order===''}"
          @click="handleOrderDefault"
        >默认</span>
        <span
          class="list-control-order-item"
          :class="{on:order.indexOf('cost')>-1}"
          @click="handleOrderCost"
        >
          价格
          <template v-if="order==='cost-asc'">↑</template>
          <template v-if="order==='cost-desc'">↓</template>
        </span>
      </div>
    </div>
    <product :infor="item" v-for="item in filterPro" :key="item.product_id"></product>
  </div>
</template>
<script>
import product from "../components/product.vue";
export default {
  data() {
    return {
      order: ""
    };
  },
  computed: {
    product() {
      return this.$store.state.productList;
    },
    storeList() {
      return this.$store.state.storeList;
    },
    storeDictList() {
      const dict = {};
      this.storeList.forEach(item => {
        dict[item.store_id] = item.store_number;
      });
      return dict;
    },
    filterPro() {
      let list = [...this.product];
      let newList = list.filter(item => {
        // console.log(item)
       return  item.product_store.split("-").every(ele => {
          return this.storeDictList[ele] != 0;
        });
      });
      console.log(newList)
      if (this.order !== "") {
        if (this.order === "cost-asc") {
          newList = newList.sort((a, b) => a.product_price - b.product_price);
        } else if (this.order === "cost-desc") {
          newList = newList.sort((a, b) => b.product_price - a.product_price);
        }
      }
      return newList;
    }
  },
  mounted() {
    this.$store.dispatch("getProductList");
     this.$store.dispatch("getStoreList");
  },
  components: {
    product
  },
  methods: {
    handleOrderDefault() {
      this.order = "";
    },
    handleOrderCost() {
      if (this.order === "cost-desc") {
        this.order = "cost-asc";
      } else {
        this.order = "cost-desc";
      }
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
.list-control {
  background: #fff;
  border-radius: 6px;
  margin: 16px;
  padding: 16px;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
}
.list-control-fliter {
  margin-bottom: 16px;
}
.list-control-filter-item,
.list-control-order-item {
  cursor: pointer;
  display: inline-block;
  border: 1px solid #e9eaec;
  border-radius: 4px;
  margin-right: 6px;
  padding: 2px 6px;
}
.on {
  background: #324057;
  border: 1px solid #324057;
  color: #fff;
}
</style>
