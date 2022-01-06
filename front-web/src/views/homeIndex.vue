<template>
  <div class="home">
    <div class="header">
      <div class="tit">个人博客/技术博客</div>
      <div class="menu">
        <span @click="goHome">首页</span>
        <span class="shu">/</span>
        <span @click="goMe">关于我</span>
      </div>
    </div>
    <div class="ct">
      <div class="content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive, toRefs } from "vue-demi";
import router from "@/router";
export default {
  setup() {
    let loginOut = () => {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      router.replace("/login");
    };
    let states = reactive({});
    let goHome = ()=>{
      router.push("/")
    }
    let goMe=()=>{
      router.push("/myCurriculum")
    }
    return {
      ...toRefs(states),
      loginOut,
      goHome,
      goMe
    };
  },
};
</script>

<style lang="scss" scoped>
.home {
  height: 100%;
  width: 100%;
  .header {
    height: 60px;
    width: 100%;
    background-color: #2950b8;
    background-image: linear-gradient(
      to right,
      rgb(33, 30, 163),
      #2950b8 20%,
      #466ca5
    );
    line-height: 60px;
    padding: 0 20px;
    position: relative;
    text-align: center;
    .tit {
      color: white;
      font-weight: 600;
      font-size: 20px;
      position: absolute;
    }
    .menu {
      color: white;
      .shu{
        padding-left: 5px;
        padding-right: 5px;
      }
      span{
        cursor: pointer;
      }
    }
  }
  .ct {
    height: calc(100% - 60px);
    > div {
      display: inline-block;
      vertical-align: top;
    }
    .natRouter {
      width: 150px;
      height: 100%;
    }
    .content {
      width: 100%;
      height: 100%;
    }
  }
}
</style>