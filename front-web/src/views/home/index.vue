<template>
  <div class="home">
    <div class="header">
      <div class="tit">Blog</div>
      <div class="menu" >
        <span @click="go('/')" class="textHov"><i class="iconfont">&#xe699;</i>首页</span>
        <span @click="go('/aboutMe')" class="textHov"
          ><i class="iconfont"> &#xe691;</i>关于我</span
        >
        <span @click="goManager" class="textHov"><i class="iconfont">&#xe60a;</i>后台</span>
        <!-- <span class="shu">/</span>
        <span @click="goMe">关于我</span> -->
      </div>
    </div>
    <div class="ct">
      <MePage></MePage>
      <div class="content">
        <router-view></router-view>
      </div>
      
    </div>
  </div>
</template>
<script>
import { reactive, toRefs } from "vue-demi";
import router from "@/router";
import MePage from "@/views/mePage";
export default {
  components: {
    MePage,
  },
  setup() {
    let loginOut = () => {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      router.replace("/login");
    };
    let states = reactive({});
    let go = (url) => {
      router.push(url);
    };
    let goManager = () => {
      window.open("http://sunkaibk.xyz:8081");
    };

    return {
      ...toRefs(states),
      loginOut,
      goManager,
      go,
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
      background: linear-gradient(to right, red 30%, white 100%);
      -webkit-background-clip: text;
      color: transparent;
    }
    .menu {
      color: white;
      .shu {
        padding-left: 5px;
        padding-right: 5px;
      }
      span {
        cursor: pointer;
        font-size: 14px;
      }
      span:not(:last-child) {
        margin-right: 20px;
      }
      .iconfont {
        padding-right: 5px;
      }
    }
  }
  .ct {
    height: calc(100% - 60px);
    position: relative;
    width: 100%;
    overflow: hidden;
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
      overflow: auto;
      min-width: 1440px;
      // background: url("../assets/img/bk.jpg");
    }
  }
}
</style>