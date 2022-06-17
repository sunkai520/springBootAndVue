<template>
  <el-dialog v-model="comDialogVisible" :before-close="close" width="30%" :title="title">
    <el-form
      :model="form"
      label-width="80px"
      label-position="top"
      :rules="rules"
      ref="workForm"
    >
      <el-form-item label="姓名:" prop="name">
        <el-input v-model="form.name" :disabled="isEditName"></el-input>
      </el-form-item>
      <el-form-item label="内容:" prop="content">
        <el-input type="textarea" rows="4" v-model="form.content"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="close">取消</el-button>
      <el-button type="primary" @click="commit">确认</el-button>
    </template>
  </el-dialog>
</template>

<script>
import { reactive, ref, toRefs } from "@vue/reactivity";
import { onMounted } from '@vue/runtime-core';
export default {
  props: {
    //是否显示对话框
    comDialogVisible: {
      type: Boolean,
      default: false
    },
    title:{
        type:String,
        default:"评论"
    }
  },
  setup(props,{emit}) {
    let states = reactive({
      rules: {
        name:[{ required: true, message: '请输入姓名!', trigger: 'blur' }],
        content:[{ required: true, message: '请输入内容!', trigger: 'blur' }]
      },
      form:{
        name:"",
        content:""
      },
      isEditName:false
    });
    onMounted(()=>{
      let name = localStorage.getItem("commentName");
      if(name){
        states.form.name = name;
        states.isEditName = true
      }else{
        states.isEditName = false
      }
    })
    let workForm = ref(null);
    //评论提交
    let commit = ()=>{
        workForm.value.validate(async (bool)=>{
          if(bool){
            localStorage.setItem("commentName",states.form.name)
            emit("commit",states.form)
          }
        })
    }
    //评论关闭
    let close = ()=>{
      emit("close")
    }
    return {close, workForm, ...toRefs(states),commit};
  },
};
</script>

<style lang="scss" scoped>
</style>