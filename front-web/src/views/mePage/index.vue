<template>
  <div class="me" :class="load ? 'load' : ''">
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
      <div>
        <Heart style="margin: auto"></Heart>
      </div>
      <div class="beian">
        <a href="https://beian.miit.gov.cn">鲁ICP备2022001968号-1</a>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity";
import { onMounted } from "@vue/runtime-core";
import Heart from "@/components/cssCompents/heart.vue";
export default {
  components: { Heart },
  setup() {
    let states = reactive({
      src: require("../../assets/img/head.png"),
      load: false,
    });
    let mover = () => {
      states.src = require("../../assets/img/head.jpg");
    };
    let mout = () => {
      states.src = require("../../assets/img/head.png");
    };
    onMounted(() => {
      window.onload = () => {
        states.load = true;
      };
    });
    return {
      ...toRefs(states),
      mover,
      mout,
    };
  },
};
</script>

<style lang="scss" scoped>
.load {
  left: 0 !important;
}
.me {
  position: absolute;
  top: 0;
  height: 100%;
  z-index: 888;
  left: -350px;
  transition: left 1s;
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
  .beian {
    margin-top: 60px;
    text-align: center;
    a {
      color: white;
      font-size: 14px;
    }
  }
}
</style>