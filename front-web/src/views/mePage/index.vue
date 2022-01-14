<template>
  <div class="me">
    <div class="leftSection">
      <div class="avatar">
        <img :src="src" alt="" @mouseover="mover" @mouseout="mout" />
      </div>
      <div style="margin-top: 20px">
        <div class="na">我的名字：“伯符”</div>
        <div class="wx">=== 微信：ei1115215 ===</div>
        <div class="des">程序猿 / 轻随笔 / 轻生活 / 轻日记 / 轻梦想</div>
        <div class="zy">一份耕耘,一份收获</div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity";
export default {
  setup() {
    let states = reactive({
      src: require("../../assets/img/head.png"),
    });
    let mover = () => {
      states.src = require("../../assets/img/head.jpg");
    };
    let mout = () => {
      states.src = require("../../assets/img/head.png");
    };
    return {
      ...toRefs(states),
      mover,
      mout,
    };
  },
};
</script>

<style lang="scss" scoped>
.me {
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  z-index: 888;
  .leftSection {
    width: 350px;
    height: 100%;
    background: url("../../assets/img/pbk.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    padding-top: 40px;
    .avatar {
      width: 150px;
      height: 150px;
      margin: auto;
      img {
        width: 100%;
        height: 100%;
        border-radius: 150px;
        border: 5px solid white;
      }
    }
    .wx,
    .zy {
      margin: 10px 0;
    }
    .wx,
    .des,
    .na,
    .zy {
      color: white;
      text-align: center;
     
    }
  }
}
</style>