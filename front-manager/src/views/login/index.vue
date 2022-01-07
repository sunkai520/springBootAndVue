<template>
  <div class="login">
    <start-back></start-back>
    <div class="loginForm">
      <div class="title">博客后台管理</div>
      <div class="subTitle">博客</div>
      <el-form
        ref="rform"
        :model="ruleForm"
        status-icon
        label-width="120px"
        class="demo-ruleForm"
        :rules="rules"
      >
        <el-form-item label-width="0" prop="loginName">
          <el-input
            v-model="ruleForm.loginName"
            autocomplete="off"
            placeholder="请输入用户名"
          >
            <template #prefix>
              <i class="iconfont">&#xe619;</i>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label-width="0" prop="pwd">
          <el-input
            v-model="ruleForm.pwd"
            type="password"
            autocomplete="off"
            placeholder="请输入登录密码"
          >
            <template #prefix>
              <i class="iconfont">&#xe8b2;</i>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label-width="0">
          <div style="width: 100%; margin-top: 20px">
            <el-button type="primary" @click="submitForm">登录</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import startBack from "@/components/shootStartBk.vue";
import { reactive, ref, toRefs } from "vue-demi";
import router from '@/router';
import {login} from "@/api/login"
import { ElMessage } from 'element-plus';
export default {
  components: {
    startBack,
  },
  setup() {
    let rform = ref()
    let states = reactive({
      ruleForm: {
        loginName: "",
        pwd: "",
      },
      rules: {
        loginName: [{ required: true, message: "请输入账号", trigger: "blur" }],
        pwd: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    });
    let submitForm = () => {
      // router.push("/firstPage")
      rform.value.validate(async (valid) => {
        if (valid) {
          let res = await login(states.ruleForm);
          if(res.code==200){
            router.push("/firstPage")
            localStorage.setItem("token",res.data.token)
            localStorage.setItem("user",JSON.stringify(res.data))
          }else{
            ElMessage({
              type:"error",
              message:res.msg
            })
          }
        }
      });
    };
    return {
      ...toRefs(states),
      submitForm,
      rform
    };
  },
};
</script>

<style lang="scss" scoped>
.login {
  height: 100%;
  width: 100%;
  overflow: hidden;
  position: relative;
  .loginForm {
    background-color: white;
    padding: 40px;
    position: absolute;
    left: 50%;
    top: 50%;
    width: 400px;
    height: 400px;
    transform: translate(-50%, -50%);
    border-radius: 10px;
    .title {
      margin-top: 25px;
      color: #0079fe;
      font-weight: 600;
      font-size: 18px;
      text-align: center;
    }
    .subTitle {
      margin-bottom: 20px;
      color: #0079fe;
      font-size: 20px;
      text-align: center;
    }
  }
  ::v-deep .el-button {
    width: 100%;
  }
  .iconfont {
    font-size: 18px;
  }
}
</style>