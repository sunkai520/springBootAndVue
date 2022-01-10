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
            autosize
          ></el-input>
        </el-form-item>
        <!-- <el-form-item label="tag" prop="tag">
          <el-select
            v-model="ruleForm.tagss"
            multiple
            placeholder="请选择tag"
          >
            <el-option
              v-for="item in tagOptions"
              :key="item.id"
              :label="item.tagName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item> -->
        <!-- <el-form-item label="代码" prop="code">
          <el-input
            v-model="ruleForm.code"
            autocomplete="off"
            type="textarea"
            autosize
          ></el-input>
      
        </el-form-item> -->

        <el-form-item label="上传图片">
          <el-upload
            class="avatar-uploader"
            action="/api/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <div v-if="ruleForm.imageUrl" class="avatar">
              <img :src="'api/' + ruleForm.imageUrl" />
            </div>
            <el-button v-else type="primary" size="mini">上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="内容">
          <div style="height: 500px">
            <MdEditor
              v-model="ruleForm.text"
              @onUploadImg="onUploadImg"
              :pageFullScreen="pageFullScreen"
              @save="save"
            ></MdEditor>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="mini" @click="submitForm('ruleForm')"
            >保存</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity";
import { ElMessage } from "element-plus";
import { createBk, getBkInfo, updateBk } from "@/api/bkList";
import router from "@/router";
import { onMounted } from "@vue/runtime-core";
import { useRoute } from "vue-router";
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import axios from "axios";
import {getTagsList} from "@/api/tags"
export default {
  components: { MdEditor },
  setup(props) {
    let route = useRoute();
    let id = route.query.id;
    let states = reactive({
      ruleForm: {
        title: "",
        des: "",
        imageUrl: "",
        text: "",
        // tagss:[]
      },
      id: id,
      rules: [],
      pageFullScreen: false,
      tagOptions:[]
    });
    onMounted(() => {
      if (states.id) {
        getOneBkInfo();
      }
      getList()
    });
     async function getList() {
      let params = {
        keyWord: "",
      };
      let res = await getTagsList(params)
      states.tagOptions = res.data;
      
    }
    let submitForm = async () => {
      console.log(states.ruleForm, "???");
      if (states.id) {
        let res = await updateBk(states.ruleForm);
      } else {
        let user = JSON.parse(localStorage.getItem("user"));
        let params = {
          ...states.ruleForm,
          userid: user.id,
        };
        let res = await createBk(params);
      }

      ElMessage({
        type: "success",
        message: "保存成功!",
      });
      router.push("/");
    };
    let beforeAvatarUpload = (file) => {
      let size = file.size / 1024 / 1024;
      if (size < 10) {
        return true;
      } else {
        ElMessage({
          type: "error",
          message: "文件大小不能超过10M",
        });
        return false;
      }
    };
    let handleAvatarSuccess = (res) => {
      states.ruleForm.imageUrl = res.data;
    };

    async function getOneBkInfo() {
      let res = await getBkInfo({ id: states.id });
      states.ruleForm = res.data;
    }
    let onUploadImg = async (files, callback) => {
      let res = await Promise.all(
        Array.from(files).map((file) => {
          let bool = beforeAvatarUpload(file);
          if (bool) {
            return new Promise((rev, rej) => {
              let form = new FormData();
              form.append("file", file);
              axios
                .post("/api/upload", form, {
                  headers: {
                    "Content-Type": "multipart/form-data",
                  },
                })
                .then((res) => rev(res))
                .catch((error) => {
                  rej(error);
                });
            });
          }
        })
      );
      console.log(res, "res");
      callback(res.map((item) => "api/" + item.data));
    };
    let save = () => {
      states.pageFullScreen = false;
      submitForm();
    };
    return {
      ...toRefs(states),
      submitForm,
      beforeAvatarUpload,
      handleAvatarSuccess,
      onUploadImg,
      save,
    };
  },
};
</script>

<style lang="scss" scoped>
.content {
  width: 70%;
}
.addBk {
  background-color: white;
  padding: 20px;
}
.avatar {
  width: 100px;
  height: 80px;
  img {
    height: auto;
    max-width: 100%;
    max-height: 100%;
  }
}
</style>