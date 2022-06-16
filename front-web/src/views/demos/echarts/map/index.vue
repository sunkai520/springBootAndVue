<template>
  <div class="mid">
    <div class="map" id="map"></div>
  </div>
</template>
<script>
import * as echarts from "echarts";
import "../../../../../node_modules/echarts/map/js/china.js";
import { onMounted, reactive, toRefs } from "@vue/runtime-core";
export default {
  setup() {
    let mapChart;
    let timer;
    let d = require("./dataJson/provice.json");
    let states = reactive({
      colors: [
        "rgba(255, 118, 134, 1)",
        "rgba(64, 255, 161, 1)",
        "rgba(1, 169, 250, 1)",
        "rgba(255, 222, 23, 1)",
        "rgba(66, 128, 255, 1)",
        "rgba(2, 236, 250, 1)",
      ],
      mapDataList: [],
      mapData: [],
    });
    //echarts地图配置
    function createMapEchart() {
      let optionMap = {
        tooltip: {
          padding: 0,
          borderWidth: 1,
          borderColor: "rgba(0, 175, 255, 1)",
          backgroundColor: "rgba(5,16,60,0.7)",
          formatter: function (params, ticket, callback) {
            let num = params.data ? params.data.value : 0;
            if (params.data && Array.isArray(params.data.value)) {
              num = params.data.value[2];
            }
            let rate = params.data ? params.data.rate : 0;
            let text =
              '<div style="padding:10px 15px;border-radius:3px;"><div><label style="color:#FFDE17">' +
              params.name +
              ": " +
              '</label><span style="color:white">' +
              '<span style="padding-left:10px;padding-right:10px">' +
              num +
              "</span>" +
              "<span>" +
              rate +
              "</span></span></div></div>";
            callback(() => {
              console.log("回调");
            });
            return text;
          },
        },
        visualMap: {
          min: states.mapDataList[states.mapDataList.length - 1].num,
          max: states.mapDataList[0].num,
          inRange: {
            color: ["#1d88ff", "#003ac2"],
          },
          show: false,
        },
        //阴影地图
        geo: {
          show: true,
          map: "china",
          //放大缩小关闭
          roam: false,
          zoom: 1.23,
          aspectScale: 0.75,
          left: 95,
          silent: true,
          itemStyle: {
            normal: {
              //地图边框颜色
              borderColor: "#010A2F",
              areaColor: "#010A2F",
              opacity: "0.5",
            },
            //鼠标悬停上效果
            emphasis: {
              focus: "none",
            },
          },
          regions: [
            {
              name: "南海诸岛",
              itemStyle: {
                normal: {
                  opacity: 0,
                },
                label: {
                  show: false,
                },
              },
            },
          ],
        },
        series: [
          {
            type: "map",
            map: "china",
            geoIndex: 1,
            zoom: 1.23,
            aspectScale: 0.75,
            data: states.mapData,
            itemStyle: {
              normal: {
                //地图边框颜色
                borderColor: "#1df4ff",
                areaColor: "#bed9fe",
                //   shadowColor: "#1df4ff",
                //   shadowBlur: 5,
              },
              //鼠标悬停上效果
              emphasis: {
                areaColor: "#bed9fe",
                //   shadowBlur: 20,
                //   borderWidth: 0,
                //   shadowColor: "#bed9fe",
              },
            },

            label: {
              normal: {
                show: true,
                color: "#FFFFFF",
                fontSize: 10,
                offset: [12, 0],

                formatter(v) {
                  return getRange(v);
                },
              },
              //关闭悬停显示省会名
              emphasis: {
                show: false,
              },
            },
          },
          {
            type: "effectScatter",
            coordinateSystem: "geo",
            data: getData(),
            symbolSize: function (item) {
              return getDianSize(item);
            },
            rippleEffect: {
              period: 5, //波纹秒数
              brushType: "fill", //stroke(涟漪)和fill(扩散)，两种效果
              scale: 5, //波纹范围
            },
            hoverAnimation: true,
            silent: true,
            itemStyle: {
              normal: {
                color: "rgb(244,233,37)",
              },
            },
          },
        ],
      };
      mapChart = echarts.init(document.getElementById("map"));
      mapChart.clear();
      mapChart.setOption(optionMap);
      if (timer) {
        clearInterval(timer);
        timer = null;
      }
      showTip(mapChart);
      mapChart.on("mouseout", function () {
        if (!timer) {
          showTip(mapChart);
        }
      });
      mapChart.on("mouseover", function () {
        clearInterval(timer);
        timer = null;
      });
    }
    function showTip(mapChart) {
      let ind = 0;
      timer = setInterval(() => {
        ind += 1;
        if (ind == 6) {
          ind = 1;
        }
        mapChart.dispatchAction({
          type: "showTip",
          seriesIndex: 1,
          dataIndex: ind - 1,
        });
      }, 2000);
    }
    //计算小球的大小
    function getDianSize(item) {
      let maxSize = 6;
      let minSzie = 2;
      let max = states.mapDataList[0].num;
      if (item.length == 3) {
        let value = item[2];
        if (value == 0) {
          return 0;
        }
        let size = Math.floor((value / max) * maxSize) + 1;
        if (size > maxSize) {
          return maxSize;
        }
        if (size < minSzie) {
          return minSzie;
        }
        return size;
      } else {
        return minSzie;
      }
    }
    //将值和各省省会坐标进行对应
    function getData() {
      let temp = [];
      states.mapDataList.forEach((dl) => {
        d.forEach((item) => {
          let t = JSON.parse(JSON.stringify(item));
          if (t.name == dl.area) {
            t.visualMap = false;
            t.value = t.value.concat(dl.num);
            t.symbolOffset = [20, 0];
            t.rate = dl.rate;
            temp.push(t);
          }
        });
      });
      return temp;
    }
    //计算排名用于前五名显示名字
    function getRange(item) {
      let index = states.mapDataList.findIndex((d) => {
        return d.area == item.name;
      });

      return index < 5 && index >= 0 ? item.name : "";
    }
    onMounted(() => {
      dateMapChange();
      window.onresize = function () {
        mapChart.resize();
      };
    });
    //组成map地图数据
    let dateMapChange = async () => {
      //通过接口获取的地图数据此处写死
      let res = {
        map_stats: [
          {
            area: "山东",
            num: 604,
            rate: "61.01%",
          },
          {
            area: "吉林",
            num: 16,
            rate: "1.62%",
          },
        ],
      };
      let list = res.map_stats;
      //将数据于省份进行匹配生成新的数据
      d.forEach((df) => {
        let index = list.findIndex((k) => {
          return k.area == df.name;
        });
        if (index == -1) {
          list.push({ area: df.name, num: 0, rate: "0" });
        }
      });
      states.mapDataList = list;
      let temp = [];
      states.mapDataList.forEach((item) => {
        temp.push({ name: item.area, value: item.num, rate: item.rate });
      });
      states.mapData = temp;
      createMapEchart();
    };
    return {
      dateMapChange,
      ...toRefs(states),
    };
  },
};
</script>
<style lang="scss" scoped>
.mid {
  width: 100%;
  height: 100%;
  position: relative;
  padding-top: 20px;
  .mapDt {
    position: absolute;
    left: 40px;
    top: 12px;
    z-index: 999;
  }
  .map {
    width: 100%;
    height: 570px;
  }
  .tooplip {
    background-color: red;
    color: red;
  }
}
</style>