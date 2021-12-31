<template>
  <div class="addBk">
    <div class="content">
      <el-form
        ref="rule"
        :model="ruleForm"
        status-icon
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="ruleForm.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="des">
          <el-input
            v-model="ruleForm.des"
            autocomplete="off"
            type="textarea"
          ></el-input>
        </el-form-item>
        <el-form-item label="代码" prop="code">
          <el-input
            v-model="ruleForm.code"
            autocomplete="off"
            type="textarea"
          ></el-input>
        </el-form-item>
        <el-form-item label="上传图片">
          <el-upload
            class="avatar-uploader"
            action="/api/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="ruleForm.imageUrl" :src="'api/'+ruleForm.imageUrl" class="avatar" />
            <el-button v-else type="primary">上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >Create</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity";
import { ElMessage } from 'element-plus';
import { createBk }from '@/api/bkList'
export default {
  setup() {
    let states = reactive({
      ruleForm: {
        title: "",
        code: "",
        des: "",
        imageUrl:"",
      },
      
      rules: [],
    });
    let submitForm = async () => {
      console.log(states.ruleForm, "???");
      let user = JSON.parse(localStorage.getItem("user")); 
      let params = {
        ...states.ruleForm,
        userid:user.id
      }
      let res = await createBk(params)
      console.log(res,"res")
    };
    let beforeAvatarUpload=(file)=>{
      let size = file.size/1024/1024;
      if(size<10){
        return true
      }else{
        ElMessage({
          type:"error",
          message:"文件大小不能超过10M"
        })
        return false
      }
      
    }
    let handleAvatarSuccess =(res)=>{
      states.ruleForm.imageUrl =res.data
      console.log(res,"上传成功")
    }
    return { ...toRefs(states), submitForm,beforeAvatarUpload,handleAvatarSuccess};
  },
};
</script>

<style lang="scss" scoped>
</style>