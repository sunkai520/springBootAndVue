<template>
  <div class="line">
    <div class="line-a" id="line"></div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import { onMounted, reactive, toRefs } from "@vue/runtime-core";
export default {
  setup() {
    let states = reactive({
      lineList: [],
    });
    function createLineEchart(xData) {
      let optionLine = {
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: xData,
          axisLine: {
            show: true,
            lineStyle: {
              color: "#6779AE",
            },
          },
          axisTick: {
            show: false,
          },
        },
        yAxis: {
          type: "value",
          // boundaryGap: [0, "35%"],
          axisLine: {
            show: true,
            lineStyle: {
              color: "#6779AE",
            },
          },
          splitLine: {
            show: true,
            lineStyle: {
              width: 1,
              type: "dashed",
              color: "rgba(147, 221, 255, 0.5)",
              opacity: 0.5,
            },
          },
        },

        axisLabel: {
          color: "#D7E8FF",
          fontSize: 14,
        },

        grid: {
          height: "70%",
          top: 20,
          width: "80%",
        },
        tooltip: {
          trigger: "axis",
          confine: true,
          padding: 5,
          borderWidth: 1,
          borderColor: "rgba(0, 175, 255, 1)",
          backgroundColor: "rgba(5,16,60,0.7)",
          formatter: function (params, ticket, callback) {
            let list = params[0].data.child;
            let date = params[0].data.date;
            let value = params[0].data.value;
            let text =
              "<div style='text-align:center;color:white'>" + date + "</div>";
            if (list.length > 0) {
              list.forEach((item) => {
                text +=
                  '<div style="padding:0 15px;border-radius:3px;margin-bottom:5px"><div><label style="color:#FFDE17">' +
                  item.name +
                  ": " +
                  '</label><span style="color:white">' +
                  '<span style="padding-left:10px;padding-right:10px">' +
                  item.value +
                  "</span>" +
                  "<span>" +
                  item.rate +
                  "</span></span></div></div>";
              });
            }
            return text;
          },
        },
        series: [
          {
            type: "line",
            smooth: true,
            lineStyle: {
              color: "#00a0e9",
              width: 2,
            },
            showSymbol: false,
            areaStyle: {
              color: {
                type: "linear",
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  {
                    offset: 0,
                    color: "rgba(2, 235, 250, 0.8)", // 100% 处的颜色
                  },
                  {
                    offset: 1,
                    color: "rgba(1, 33, 153, 0.15)", // 0% 处的颜色
                  },
                ],
              },
            },
            data: states.lineList,
          },
        ],
      };
      let lineChart = echarts.init(document.getElementById("line"));
      lineChart.clear();
      lineChart.setOption(optionLine);
    }
    let dateLineChange = async () => {
      //通过接口获取的数据
      let res = [
        {
          date: "2022-06-08",
          value: 4,
          child: [
            {
              name: "特级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "一级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "二级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "三级舆情",
              value: 4,
              rate: "100%",
            },
          ],
        },
        {
          date: "2022-06-09",
          value: 11,
          child: [
            {
              name: "特级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "一级舆情",
              value: 1,
              rate: "9.09%",
            },
            {
              name: "二级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "三级舆情",
              value: 10,
              rate: "90.91%",
            },
          ],
        },
        {
          date: "2022-06-10",
          value: 37,
          child: [
            {
              name: "特级舆情",
              value: 2,
              rate: "5.41%",
            },
            {
              name: "一级舆情",
              value: 30,
              rate: "81.08%",
            },
            {
              name: "二级舆情",
              value: 1,
              rate: "2.7%",
            },
            {
              name: "三级舆情",
              value: 4,
              rate: "10.81%",
            },
          ],
        },
        {
          date: "2022-06-13",
          value: 4,
          child: [
            {
              name: "特级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "一级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "二级舆情",
              value: 1,
              rate: "25%",
            },
            {
              name: "三级舆情",
              value: 3,
              rate: "75%",
            },
          ],
        },
        {
          date: "2022-06-14",
          value: 10,
          child: [
            {
              name: "特级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "一级舆情",
              value: 1,
              rate: "10%",
            },
            {
              name: "二级舆情",
              value: 1,
              rate: "10%",
            },
            {
              name: "三级舆情",
              value: 8,
              rate: "80%",
            },
          ],
        },
        {
          date: "2022-06-15",
          value: 5,
          child: [
            {
              name: "特级舆情",
              value: 1,
              rate: "20%",
            },
            {
              name: "一级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "二级舆情",
              value: 0,
              rate: "0%",
            },
            {
              name: "三级舆情",
              value: 4,
              rate: "80%",
            },
          ],
        },
      ];
      states.lineList = res;
      let temp = states.lineList.map((item) => {
        return item.date;
      });
      createLineEchart(temp);
    };
    onMounted(()=>{
        dateLineChange();
    })
    return {
      ...toRefs(states),
    };
  },
};
</script>

<style lang="scss" scoped>
.line {
    height: 100%;
    width: 100%;
    .line-a{
        height: 200px;
        width: 100%;
    }
  }
</style>