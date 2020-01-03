<template>
  <div id="proLine" :style="{width:'100%',height:'100%'}"></div>
</template>
<script>
import echarts from "echarts";
export default {
  props: ["proNameArr", "proNumberArr"],
  mounted() {
    this.initData();
    this.initChart();
  },
  data() {
    return {
      proLine: {}
    };
  },
  watch: {
    proNameArr: {
      handler(newValue, oldValue) {
        for (let i = 0; i < newValue.length; i++) {
          if (oldValue[i] != newValue[i]) {
            // console.log(newValue);
          }
        }
        this.initData();
      },
      deep: true
    },
    proNumberArr: {
      handler(newValue, oldValue) {
        for (let i = 0; i < newValue.length; i++) {
          if (oldValue[i] != newValue[i]) {
            // console.log(newValue);
          }
        }
        this.initData();
        this.initChart();
      },
      deep: true
    }
  },
  methods: {
    initChart() {
      let myChart = echarts.init(document.getElementById("proLine"));
      myChart.setOption(this.proLine);
    },
    initData() {
      // console.log(this.proNameArr)
      // console.log(this.proNumberArr)
      this.proLine = {
        title: {
          text: "奶茶销售额条形图",
          textStyle: {
            color: "#fff",
            fontSize: 16
          }
        },
        color: "rgba(	139,126,102, 0.5)",
        tooltip: {},
        xAxis: {
          data: this.proNameArr,
          axisLabel: {
            textStyle: {
              color: "white",
              fontSize: 13
            }
          },
          name: "奶茶名称",
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
          name: "销量",
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
          data: ["销量"],
          textStyle: {
            color: "white"
          }
        },
        series: [
          {
            name: "销量",
            type: "bar",
            data: this.proNumberArr
          }
        ]
      };
      // console.log(this.proLine);
    }
  }
};
</script>