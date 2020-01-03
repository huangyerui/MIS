<template>
  <div class="dashboard">
    <div class="flex-container column">
      <div
        class="item one"
        @click="clickChart('1')"
        style="transform: translate(-22.4%,-33.5%) scale(0.33)"
      >
        <proLine :proNameArr="getProNameArr" :proNumberArr="getProNumArr"></proLine>
      </div>
      <div
        class="item two"
        @click="clickChart('2')"
        style="transform: translate(-22.4%,0.5%) scale(0.33)"
      >
        <proBroLine :proMonth="getProMonth"></proBroLine>
      </div>
      <div
        class="item three"
        @click="clickChart('3')"
        style="transform: translate(-22.4%,34.5%) scale(0.33)"
      >
        <userLine :userNameArr="getUserNameArr" :userNumberArr="getUserNumArr"></userLine>
      </div>
      <div
        class="item four active"
        @click="clickChart('4')"
        style="transform: translate(43.7%, 0) scale(1)"
      >
        <userBroLine :userMonth="getUserMonth"></userBroLine>
      </div>
    </div>
  </div>
</template>
<script>
import proLine from "../components/proLine.vue";
import userLine from "../components/userLine.vue";
import userBroLine from "../components/userBroLine.vue";
import proBroLine from "../components/proBroLine.vue";
export default {
  name: "mycharts",
  data() {
    return {
      proNameArr: [],
      proNumberArr: [],
      items: []
    };
  },
  components: {
    proLine,
    userLine,
    userBroLine,
    proBroLine
  },
  computed: {
    //-----------------------------------------
    //产品销售列表
    proSaleList() {
      console.log(1)
      return this.$store.state.proSaleList;
    },

    //获得产品：业绩对象
    getProObj() {
      console.log("getProObj");
      const s = new Set();
      const m = {};
      this.proSaleList.forEach(key => s.add(key.product_name));
      Array.from(s).forEach(key => (m[key] = 0));
      this.proSaleList.forEach(
        key => (m[key.product_name] += key.order_productPrice)
      );
      return m;
    },

    //获得产品：月销售（数组）对象
    getProMonth() {
      const m = {};
      this.getProNameArr.forEach(key => (m[key] = new Array(12).fill(0)));
      this.proSaleList.forEach(key => {
        let month = +key.order_date.substr(key.order_date.length - 2, 2);
        m[key.product_name][month - 1] = key.order_productPrice;
      });
      return m;
    },

    //产品名称数组
    getProNameArr() {
      console.log(Object.keys(this.getProObj));
      return Object.keys(this.getProObj);
    },

    //产品数量数组
    getProNumArr() {
      console.log("getProNumArr");
      return Object.values(this.getProObj);
    },
    //-------------------------------------------------------


    //用户业绩列表
    userSaleList() {
      return this.$store.state.userSaleList;
    },
    //获得用户：业绩对象
    getUserObj() {
      const s = new Set();
      const m = {};
      this.userSaleList.forEach(key => s.add(key.user_name));
      Array.from(s).forEach(key => (m[key] = 0));
      this.userSaleList.forEach(
        key => (m[key.user_name] += key.order_userPrice)
      );
      return m;
    },
    //获得用户：月销售（数组）对象
    getUserMonth() {
      const m = {};
      this.getUserNameArr.forEach(key => (m[key] = new Array(12).fill(0)));
      this.userSaleList.forEach(key => {
        let month = +key.order_date.substr(key.order_date.length - 2, 2);
        m[key.user_name][month - 1] = key.order_userPrice;
      });
      return m;
    },
    //用户名称数组
    getUserNameArr() {
      return Object.keys(this.getUserObj);
    },
    //用户业绩数组
    getUserNumArr() {
      return Object.values(this.getUserObj);
    }
  },
  mounted() {
    this.$store.dispatch("getProductSale");
    this.$store.dispatch("getUserSale");
    this.toinit();
  },
  methods: {
    _resize() {
      this.$root.charts.forEach(myChart => {
        myChart.resize();
      });
    },
    toinit() {
      this.items = document.querySelectorAll(".flex-container .item");
      for (let i = 0; i < this.items.length; i++) {
        this.items[i].dataset.order = i + 1;
      }
    },
    clickChart(clickIndex) {
      let activeItem = document.querySelector(".flex-container .active");
      let activeIndex = activeItem.dataset.order;
      let clickItem = this.items[clickIndex - 1];
      if (activeIndex === clickIndex) {
        return;
      }
      activeItem.classList.remove("active");
      clickItem.classList.add("active");
      this._setStyle(clickItem, activeItem);
    },
    _setStyle(el1, el2) {
      let transform1 = el1.style.transform;
      let transform2 = el2.style.transform;
      el1.style.transform = transform2;
      el2.style.transform = transform1;
    }
  }
};
</script>
<style lang="stylus" scoped>
* {
  box-sizing: border-box;
}

// .point, .multipleColumn, .columnChart, .lineChart {
// height: 100% !important;
// width: 100% !important;
// background: none !important;
// }
.item {
  padding: 0px;
  margin: 0px;
  width: 68%;
  height: 100%;
  position: absolute;
  transform: scale(0.33);
  text-align: center;
  transition: all 0.8s;
  background: rgba(32, 32, 35, 0.5);
}

.dashboard {
  box-sizing: border-box;
  position: relative;
  width: 100%;
  height: 100%;
  // padding-top :71px;
  padding-top: 5%;
  background: url('../assets/bg.png');
  background-size: 100% 100%;
}

.flex-container.column {
  position: relative;
  height: 90%;
  width: 95%;
  overflow: hidden;
  margin: 0 auto;
  box-sizing: content-box;
}

.active {
  height: 100%;
  width: 69%;
  margin-left: 10px;
  line-height: 300px;
}
</style>
