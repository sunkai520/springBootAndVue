<template>
  <div class="home">
    <div class="header">
      <div>
        <img src="../../assets/logo.png" alt="/" />
        <span class="tit">文件智能管理平台</span>
      </div>
      <div class="usr">
        <img src="../../assets/logo.png" alt="/" />
        <span>{{user.name}}</span>
      </div>
    </div>
    <div class="content">
      <router-view />
    </div>  
  </div>
</template>

<script>
import { onMounted, reactive, toRefs } from '@vue/runtime-core';
export default {
  setup() {
    let states = reactive({
      user:{name:""}
    })
    onMounted(()=>{
      states.user = JSON.parse(localStorage.getItem("user"));
    })
    return {
      ...toRefs(states)
    }
  },
};
</script>

<style lang="scss" scoped>
.home {
  height: 100%;
  .header {
    padding: 10px 25px;
    background-color: #0c60d2;
    display: flex;
    justify-content: space-between;
    .tit {
      font-size: 28px;
      font-family: FZLanTingHeiS-R-GB;
      font-weight: bold;
      color: #ffffff;
      vertical-align: middle;
      padding-left: 17px;
    }
    img {
      vertical-align: middle;
    }
    .usr {
      img {
        width: 36px;
        height: 36px;
        border-radius: 36px;
        margin-right: 14px;
      }
      span {
        height: 17px;
        font-size: 18px;
        font-family: FZLanTingHeiS-R-GB;
        font-weight: 400;
        color: #ffffff;
        vertical-align: middle;
      }
    }
  }
  .content{
    padding: 25px;
    height: calc(100% - 60px);
    overflow-y: auto;
  }
}
</style>