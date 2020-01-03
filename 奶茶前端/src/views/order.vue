<template>
    <div class="body">
        <div class="order">
        <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="order_number" label="订单号"></el-table-column>
        <el-table-column prop="order_name" label="订单商品"></el-table-column>
        <el-table-column prop="total_price" label="订单总价"></el-table-column>
        <el-table-column prop="user_name" label="收银员"></el-table-column>
        <el-table-column prop="order_date" label="时间"></el-table-column>
      </el-table>
      </div>
    </div>
</template>
<script>
export default {
    data(){
        return {
        
        }
    },
    computed:{
      tableData() {
          return this.$store.state.orderList
      }
    },
    created(){
        this.$store.dispatch('getOrderList')
    }
}
</script>
<style scoped>
 .body {
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  width: 100%;
  min-height:  100% ;
  overflow: hidden;
  background: #f7f8f8;
}
.order{
    margin: 20px 0px;
    height: auto;
    width: 90%;
}
.el-table{
   border-radius: 30px;
 box-shadow:  0 8px 8px rgba(10,16,20,.24),0 0 8px rgba(10,16,20,.12);
}
</style>
