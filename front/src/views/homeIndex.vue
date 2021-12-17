<template>
  <div class="home">
    <div class="header">
      <div class="tit">5G微热点感知后台管理程序V1.0</div>
      <div class="setting">
        <div class="out" @click="loginOut">
          <i class="iconfont">&#xe634;</i>
          <div>退出</div>
        </div>
      </div>
    </div>
    <div class="ct">
      <div class="natRouter">
        <Menus></Menus>
      </div>
      <div class="content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive, toRefs } from 'vue-demi';
import Menus from "@/components/menus.vue";
import router from '@/router';
export default {
  components:{Menus},
  setup(){
    let loginOut=()=>{
     router.replace("/login");
    }
    let states = reactive({
     
    })
    return {
      ...toRefs(states),
      loginOut
    }
  }
};
</script>

<style lang="scss" scoped>
.home {
  height: 100%;
  width: 100%;
  .header {
    height: 60px;
    width: 100%;
    background-color: #2950B8;
    background-image: linear-gradient(to right,rgb(33, 30, 163),#2950b8 20%,#466ca5);
    line-height: 60px;
    padding: 0 20px;
    display: flex;
    justify-content: space-between;
    .tit{
      color: white;
      font-weight: 600;
      font-size: 20px;
    }
    .setting{
      .out{
        cursor: pointer;
        display: flex;
        color: white;
        font-size: 14px;
        >div{
          margin-left: 5px;
        }
      }
    }
  }
  .ct{
    height: calc(100% - 60px);
      >div{
        display: inline-block;
        vertical-align: top;
      }
      .natRouter{
        width: 150px;
        height: 100%;
      }
      .content{
        width: calc(100% - 150px);
        height:100%;
        padding: 15px;
      }
    }
}
</style>