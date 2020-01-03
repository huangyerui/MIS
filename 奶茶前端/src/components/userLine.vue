<template>
  <div id="userLine" :style="{width:'100%',height:'100%'}"></div>
</template>
<script>
import echarts from "echarts";
export default {
  props: ["userNameArr", "userNumberArr"],
  mounted(){
    this.initData()
     this.initChart()
  },
  watch: {  
　　userNameArr: {  
　　　　handler(newValue, oldValue) {  
　　　　　　for (let i = 0; i < newValue.length; i++) {  
　　　　　　　　if (oldValue[i] != newValue[i]) {  
// 　　　　　　　　　　console.log(newValue)  
　　　　　　　　}  
　　　　　　}  
        this.initData()
　　　　},  
　　　　deep: true  
　　},
   userNumberArr: {  
　　　　handler(newValue, oldValue) {  
　　　　　　for (let i = 0; i < newValue.length; i++) {  
　　　　　　　　if (oldValue[i] != newValue[i]) {  
// 　　　　　　　　　　console.log(newValue)  
　　　　　　　　}  
　　　　　　}  
        this.initData()
        this.initChart()
　　　　},  
　　　　deep: true  
　　}  
},
  data() {
    return {
      userLine: {
        title: {
          text: "收银员业绩条形图",
          textStyle: {
            color: "#fff",
            fontSize: 16
          }
        },
        color: "rgba(122,139,139, 0.8)",
        tooltip: {},
        xAxis: {
          data: [],
          axisLabel: {
            textStyle: {
              color: "white",
              fontSize: 13
            }
          },
          name: "收银员名称",
          nameTextStyle: {
            color: "rgba(255, 255, 255, 0.69)"
          },
          type: "category",
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          }
        },
        yAxis: {
          name: "业绩",
          axisLabel: {
            textStyle: {
              color: "white",
              fontSize: 13
            }
          },
          nameTextStyle: {
            color: "rgba(255, 255, 255, 0.69)"
          },
          axisLine: {
            show: false
          },
          nameLocation: "end",
          nameGap: 20,
          nameRotate: 0,
          axisTick: {
            show: false
          },
          splitLine: {
            lineStyle: {
              color: ["rgba(230, 230, 230, 0.2)"]
            }
          }
        },
        legend: {
          data: ["业绩"],
          textStyle: {
            color: "white"
          }
        },
        series: [
          {
            name: "业绩",
            type: "bar",
            data: []
          }
        ]
      }
    };
  },
  //  watch: {
  //   userNameArr: {
  //     handler(newValue, oldValue) {
  //       this.params.wgDate = newValue.wgDate;
  //       this.params.endDate = newValue.endDate;
  //       this.init();
  //     },
  //     deep: true
  //   }
  // },
  methods: {
    initChart() {
      let myChart = echarts.init(document.getElementById("userLine"));
      myChart.setOption(this.userLine);
    },
    initData() {
       this.userLine.xAxis.data=this.userNameArr
       this.userLine.series[0].data=this.userNumberArr
      // console.log(this.userLine);
    }
  }
};
</script>