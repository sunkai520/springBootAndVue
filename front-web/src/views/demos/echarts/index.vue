<template>
  <div class="echarts">
    <div class="echarts-item">
      <div class="title" @click="goLink('http://sunkaibk.xyz/detail?id=47')">地图可视化</div>
      <MapEcharts></MapEcharts>
    </div>
    <div class="echarts-item">
      <div class="title">折线图可视化</div>
      <LineEcharts></LineEcharts>
    </div>
  </div>
</template>

<script>
//地图
import MapEcharts from "./map/index.vue";
//折线图
import LineEcharts from "./line/index.vue";
export default {
  components: {
    MapEcharts,
    LineEcharts,
  },
  setup(){
    let goLink =(url)=>{
        window.open(url)
    }
    return{
        goLink
    }
  }
};
</script>

<style lang="scss" scoped>
.echarts {
  padding: 20px;
  height: 100%;
  width: 100%;
  overflow-y: auto;
  background-image: url("../../../assets/img/echarts/back.png");
  display: flex;
  flex-wrap: wrap;
  .echarts-item {
    box-shadow: 0 0 5px black;
    width: calc((100% - 20px) / 3);
    height: 600px;
    .title {
      text-align: center;
      cursor: pointer;
      background: linear-gradient(10deg, red 10%, #fff);
      -webkit-background-clip: text;
      color: transparent;
      font-weight: 600;
      margin-top: 10px;
      font-weight: bold;
    }
  }
  .echarts-item:not(:nth-child(3n)) {
    margin-right: 10px;
  }
}
</style>