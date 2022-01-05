<template>
  <div class="infoWindow">
    <el-dialog
      :modelValue="dialogTableVisible"
      :title="title"
      @close="closeDialog"
      :width="800"
    >
      <div>
        <el-form
          ref="rule"
          :model="ruleForm"
          status-icon
          :rules="rules"
          label-width="120px"
          class="demo-ruleForm"
          v-loading="loading"
          element-loading-text="努力加载中..."
        >
          <div class="formDiv">
            <div>
              <el-form-item label="姓名" prop="name">
                <el-input
                  v-model="ruleForm.name"
                  autocomplete="off"
                ></el-input>
              </el-form-item>
              <el-form-item label="手机号" prop="phone">
                <el-input v-model.number="ruleForm.phone"></el-input>
              </el-form-item>
            </div>
            <div>
              <el-form-item label="年龄" prop="age">
                <el-input
                  v-model="ruleForm.age"
                  autocomplete="off"
                ></el-input>
              </el-form-item>
              <el-form-item label="账号" prop="loginName">
                <el-input
                  v-model="ruleForm.loginName"
                  autocomplete="off"
                ></el-input>
              </el-form-item>
            </div>
          </div>
          <div class="service">
            <el-form-item label="性别" prop="sex">
              <el-select
                v-model="ruleForm.sex"
                placeholder="选择性别"
              >
                <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.value"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </div>
          <div class="btn">
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')"
                >保存</el-button
              >
            </el-form-item>
          </div>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { reactive, ref, toRefs } from "@vue/reactivity";
import { onMounted, warn } from "@vue/runtime-core";
import {addUser,getUserInfo,updateUserInfo} from "@/api/user"
export default {
  props: {
    dialogTableVisible: {
      type: Boolean,
      default: false,
    },
    id: {
      type: Number,
    },
  },
  setup(props, ctx) {
    let rule = ref(null);
   
    let states = reactive({
      title: props.id ? "编辑用户" : "新增用户",
      ruleForm: {
        name: "",
        loginName: "",
        phone: "",
        age: "",
        sex: 1,
      },
      rules: {},
      options: [
        { id: 1, value: "男" },
        { id: 2, value: "女" },
      ],
      loading: false,
    });
    let submitForm = () => {
      rule.value.validate(async (valid) => {
        if (valid) {
          if(props.id){
            states.ruleForm.id = props.id;
            let res = await updateUserInfo(states.ruleForm);
          }else{
            let res = await addUser(states.ruleForm)
          }
          ctx.emit("update")
          closeDialog();
        }
      });
    };
    let closeDialog = () => {
      ctx.emit("close");
    };
    onMounted(()=>{
       if(props.id){
         getInfo()
       }
    })
    let getInfo=async ()=>{
      let res = await getUserInfo({id:props.id});
      states.ruleForm = res.data;
      console.log(res,"res")
    }
    // onMounted(() => {

    // });
    return {
      closeDialog,
      ...toRefs(states),
      submitForm,
      rule
    };
  },
};
</script>

<style lang="scss" scoped>
.formDiv {
  > div {
    display: inline-block;
    width: 350px;
    vertical-align: top;
  }
}
.service {
  width: 620px;
  ::v-deep .el-select {
    width: 100%;
  }
}
.btn {
  width: 700px;
  text-align: right;
}
</style>