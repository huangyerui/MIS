<template>
  <el-row class="menu_page">
    <el-col>
      <el-menu
        mode="vertical"
        background-color="#324057"
        text-color="#fff"
        active-text-color="#409eff"
        class="el-menu-vertical-demo"
      >
        <router-link to="/commodity">
          <el-submenu index="0">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>商品展览</span>
            </template>
          </el-submenu>
        </router-link>
        <template v-for="item in items">
          <el-submenu v-if="item.children" :index="item.path" :key="item.path">
            <template slot="title">
              <i :class="'el-icon-' + item.icon"></i>
              <span>{{item.name}}</span>
            </template>
            <router-link v-for="(citem,cindex) in item.children" :key="cindex" :to="citem.path">
              <el-menu-item :index="citem.path">
                <span slot="title">{{citem.name}}</span>
              </el-menu-item>
            </router-link>
          </el-submenu>
        </template>
        <router-link to="/count">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-edit-outline"></i>
              <span>信息统计</span>
            </template>
          </el-submenu>
        </router-link>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          icon: "coin",
          name: "交易管理",
          path: "fund",
          children: [{ path: "/order", name: "订单管理" },{ path: "/cart", name: "购物车" }]
        },
        {
          icon: "goods",
          name: "仓库管理",
          path: "info",
          children: [{ path: "/storeMag", name: "原料库存" }]
        }
      ]
    };
  }
};
</script>

<style scoped>
.menu_page {
  position: fixed;
  top: 71px;
  left: 0;
  min-height: 100%;
  background-color: #324057;
  z-index: 99;
}
.el-menu {
  border: none;
}
.fa-margin {
  margin-right: 5px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 150px;
  min-height: 400px;
}
.el-menu-vertical-demo {
  width: 35px;
}
.el-submenu .el-menu-item {
  min-width: 150px;
}

.hiddenDropdown,
.hiddenDropname {
  display: none;
}
a {
  text-decoration: none;
}
</style>