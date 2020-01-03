<template>
  <div id="userBroLine" :style="{width:'100%',height:'100%'}"></div>
</template>
<script>
import echarts from "echarts";
export default {
  
   watch: {  
　　userMonth: {  
　　　　handler(newValue, oldValue) {  
　　　　　　console.log(newValue)  
          this.initData()
          this.initChart()
　　　　},  
　　　　deep: true,  //对象内部的属性监听，也叫深度监听
       immediate: false //immediate表示在watch中首次绑定的时候，是否执行handler，值为true则表示在watch中声明的时候，就立即执行handler方法，值为false，则和一般使用watch一样，在数据发生变化的时候才执行handler
　　}  
   },
  mounted() {
    // this.initData();
    // this.initChart();
  },
  props:["userMonth"],
   data() {
    return {
      color:['#E0EEE0','#036BC8','#696969','#668B8B','#528B8B','#8B6914','#8B7355'],
      userBroLine:{
        title: {
          text: "收银员业绩折线图",
          textStyle: {
            color: "#fff",
            fontSize: 16
          }
        },
        color: "rgba(139,71, 38, 0.8)",
        tooltip: {},
        xAxis: {
          data: [ "一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
          axisLabel: {textStyle: {color: "white",fontSize: 13}},
          name: "月份", nameTextStyle: {color: "rgba(255, 255, 255, 0.69)"},
          type: "category",
          axisLine: {show: false},
          tooltip : {trigger : 'axis' },
          axisTick: {show: false}},
        yAxis: {
          name: "业绩",
          axisLabel: {textStyle: {color: "white",fontSize: 13}}
          ,nameTextStyle: {color: "rgba(255, 255, 255, 0.69)"},
          axisLine: {show: false},
          nameLocation: "end",
          nameGap: 20,
          nameRotate: 0,
          axisTick: {show: false},
          splitLine: { lineStyle: { color: ["rgba(230, 230, 230, 0.2)"]}}
        },
        series: [
          // #E0EEE0,#036BC8,#036BC8
        ],
        legend: {}
      },
    }
  },
  methods: {
    initChart() {
      let myChart =  echarts.init(document.getElementById("userBroLine"));
      myChart.setOption(this.userBroLine);
    },
    initData() {
        // console.log(this.userMonth)
        const newMonth= this.userMonth
        const monthArr = Object.keys(newMonth)
        this.userBroLine.legend={data:monthArr,orient: 'vertical', x:'right',textStyle:{color:'#ffffff'}}
        monthArr.forEach((key,index)=>{
           let month= {
            name: "",
            type: "line",
            stack: "总量",
            data: [],
            itemStyle: {
              //设置折线的样式
              normal: {
                //设置统计表上的折线样式
                color: "#E0EEE0",
                lineStyle: {
                  //设置统计表中的折线样式
                  color: "#E0EEE0"
                }
              }
            }
          }
           let bgColor = this.color[index]
           month.itemStyle.normal.color =  bgColor
           month.itemStyle.normal.lineStyle.color =  bgColor
           month.name=key
           month.data= newMonth[key]
           this.userBroLine.series.push(month)
         })
        //  console.log(this.userBroLine)
    }
  }
};
</script>