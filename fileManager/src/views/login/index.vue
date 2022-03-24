<template>
  <div class="login">
    <div class="bk"></div>
    <div class="loginForm" v-loading="loginLoading">
      <div class="df">
        <img src="../../assets/loginLogo.png" /><span>文件智能管理平台</span>
      </div>
      <div class="lg">
        <el-input placeholder="账号" v-model="fm.loginName"></el-input>
        <el-input
          placeholder="密码"
          v-model="fm.pwd"
          type="password"
        ></el-input>
        <el-button type="primary" @click="loginSys">登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity";
import { login } from "@/api/upload.ts";
import router from "@/router";
export default {
  setup() {
    let states = reactive({
      fm: {
        loginName: "",
        pwd: "",
      },
      loginLoading: false,
    });
    let loginSys = async () => {
      states.loginLoading = true;
      let res = await login(states.fm).finally(() => {
        states.loginLoading = false;
      });
      localStorage.setItem("user", JSON.stringify(res.data));
      localStorage.setItem("token", res.data.token);
      router.push("/");
    };
    return {
      ...toRefs(states),
      loginSys,
    };
  },
};
</script>

<style lang="scss" scoped>
.login {
  position: relative;
  height: 100%;
  .bk {
    height: 368px;
    background-image: url("../../assets/loginBack.png");
    background-size: 100% 100%;
  }
  .lg {
    width: 355px;
    height: 48px;
    margin: auto;
    margin-top: 100px;
    ::v-deep(.el-input:not(:first-child)) {
      margin-top: 20px;
    }
    ::v-deep(.el-button--primary) {
      width: 100%;
      margin-top: 40px;
    }
  }
  .loginForm {
    background-image: url("../../assets/logForm.png");
    background-size: 100% 100%;
    height: 530px;
    width: 530px;
    margin: auto;
    position: absolute;
    top: 223px;
    left: 50%;
    margin-left: -265px;
    .df {
      text-align: center;
      margin-top: 20px;
      span {
        font-size: 43px;
        font-family: SimHei;
        font-weight: 400;
        color: #ffffff;
        vertical-align: middle;
      }
      img {
        vertical-align: middle;
        margin-right: 10px;
        height: 105px;
        width: 105px;
      }
    }
  }
}
</style>