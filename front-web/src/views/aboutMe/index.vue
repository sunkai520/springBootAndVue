<template>
  <div class="aboutMe">
    <div class="pubuliu">
      <div v-for="(k, index) in dataList" class="item" :key="index" ref="item" @click="goItem(k)">
        <div class="ct">
          <div class="title">{{k.title||"--"}}</div>
          <img :src="k.url" @load="lodImg"/>
          <div class="des">
            <p>{{k.desc||"--"}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, ref, toRefs } from "@vue/reactivity";
import { nextTick, onMounted } from "@vue/runtime-core";
import { waterFall } from "@/utils/pubuliu";
import router from '@/router';
export default {
  setup() {
    let item = ref("");
    let states = reactive({
      dataList: [
        { url: require("../../assets/img/echarts/echarts.jpg"),desc:"echarts可视化demo",title:"echarts实例",path:"/echarts"},
        { url: require("../../assets/img/head.png"),desc:"文件分类管理系统",title:"文件分类管理",path:`${window.location.origin}:3000`},
        { url: require("../../assets/img/linux.jpg") },
        { url: require("../../assets/img/head.png") },
        { url: require("../../assets/img/linux.jpg") },
        { url: require("../../assets/img/head.png") },
        { url: require("../../assets/img/linux.jpg") },
        { url: require("../../assets/img/head.png") },
      ],
      imgLoad:[]
    });

    onMounted(() => {
      let aboutme = document.querySelector(".aboutMe");
      window.onresize = function () {
        waterFall(aboutme, 5);
      };
    });
    //因为图片是异步加载会导致js计算瀑布流时获取高度失效,所以要确保所有图片加载完毕后进行瀑布流的计算
    let lodImg = function(e){
      if(e.target.height){
        states.imgLoad.push(1)
      }else{
        states.imgLoad.push(2)
      }
      if(states.imgLoad.length==states.dataList.length&&!states.imgLoad.includes(2)){
        waterFall(document.querySelector(".aboutMe"), 5);
      }
    }
    let goItem = (item)=>{
      item.path&&window.open(item.path)
    }
    return {
      ...toRefs(states),
      item,
      lodImg,
      goItem
    };
  },
};
</script>

<style scoped lang="scss">
.aboutMe {
  width: calc(100% - 350px);
  margin-left: 350px;
  height: 100%;
  padding: 20px;
  .pubuliu {
    position: relative;
    width: 100%;
    .item {
      position: absolute;
      padding: 10px;
      cursor: pointer;
      box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 2px;
      background: white;
      img {
        width: 100%;
        height: 100%;
      }
      .title {
        color: #2950b8;
        font-size: 18px;
        font-weight: 600;
        margin-bottom: 20px;
      }
      .des {
        p {
          font-size: 15px;
          color: #858585;
          margin-top: 10px;
        }
      }
    }
    .item:hover {
      box-shadow: rgba(0, 0, 0, 0.3) 0px 0px 5px;
    }
  }
}
</style>