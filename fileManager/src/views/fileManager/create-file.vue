<template>
  <div class="createFile">
    <el-dialog
      v-model="dialogVisible"
      :title="type == 1 ? '编辑' : type == 2 ? '查看' : '新建'"
      width="35%"
      :before-close="handleClose"
    >
      <el-scrollbar
        height="500px"
        v-loading="loading"
        element-loading-text="文件数量过大,请稍后..."
      >
        <el-form label-position="right" :model="formVal" label-width="80px">
          <el-form-item label="标题">
            <el-input v-model="formVal.title"></el-input>
          </el-form-item>
          <div class="fenge"></div>
          <el-form-item label="标签">
            <div class="lab">
              <el-select
                v-model="formVal.tags"
                class="m-2"
                multiple
                placeholder="选择标签"
                size="large"
                clearable
                multiple-limit="4"
              >
                <el-option
                  v-for="item in tagList"
                  :key="item.id"
                  :label="item.tagName"
                  :value="item.id"
                >
                </el-option>
              </el-select>
              <div class="crtLab">
                <el-input placeholder="新建标签" v-model="tagsName"></el-input>
                <span class="add" @click="addTag">+</span>
              </div>
            </div>
          </el-form-item>
          <el-form-item label="备注">
            <el-input
              v-model="formVal.descri"
              type="textarea"
              rows="3"
            ></el-input>
          </el-form-item>
          <el-form-item label="添加附件">
            <!-- <div class="btnUpload">
              <el-upload
                class="avatar-uploader"
                action="/api/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
              >
                <el-button type="primary" size="mini">上传文件</el-button>
              </el-upload>
            </div> -->

            <el-upload
              :file-list="fileList"
              class="upload-demo"
              drag
              action="#"
              multiple
              :auto-upload="false"
              ref="upd"
              :on-change="uploadChange"
              :on-remove="removeFile"
            >
              <!-- <el-button type="primary" size="mini">上传文件</el-button> -->
              <div style="margin-top: 40px">
                <img src="../../assets/file1.png" />
              </div>
              <div class="el-upload__text">
                点击或将文件拖拽上传<em>click to upload</em>
              </div>
            </el-upload>
          </el-form-item>
        </el-form>
      </el-scrollbar>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="handleClose">取消</el-button>
          <el-button type="primary" @click="save">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { reactive, ref, toRefs } from "@vue/reactivity";
import { ElMessage } from "element-plus";
import { upload, createFile, addTags, upDateList } from "@/api/upload.ts";
import { getFileType } from "@/utils/common.js";
import { onMounted } from "@vue/runtime-core";
export default {
  props: {
    dialogVisible: {
      type: Boolean,
      default: true,
    },
    type: {
      type: Number,
      default: "",
    },
    editItem: {
      type: Object,
      default: () => {},
    },
    tagList: {
      type: Array,
      default: () => [],
    },
  },
  setup(props, ctx) {
    let upd = ref(null);
    let states = reactive({
      formVal: { title: "", fileList: [], tagsName: "", tags: [], descri: "" },
      options: [],
      fileList: [],
      tagsName: "",
      loading: false,
    });
    onMounted(() => {
      if (props.type == 1) {
        states.formVal.title = props.editItem.title;
        states.formVal.descri = props.editItem.descri;
        if (props.editItem.tags) {
          let temp = [];
          props.editItem.tags.split(",").forEach((tem) => {
            temp.push(Number(tem));
          });
          states.formVal.tags = temp;
        }
        // states.formVal.tags = props.editItem.tags.split(",")
      }
    });
    let handleClose = () => {
      ctx.emit("close");
      upd.value.clearFiles();
    };
    //文件移除
    let removeFile = (file, files) => {
      states.fileList = files;
    };
    //上传的文件状态改变
    let uploadChange = (file, files) => {
      states.fileList = files;
    };
    //多文件合并为一个文件上传
    let save = async () => {
     
      tagsChange();
      let formData = new FormData();
      let size = 0;
      states.fileName = [];
      let bool = false
      states.fileList.forEach((file) => {
        if (file.size == 0) {
          bool = true
        } else {
          formData.append("file", file.raw);
          size = file.size + size;
        }
      });
      if(bool){
        ElMessage({
          type:"error",
          message:"存在错误文件类型,请检查文件是否存在。"
        })
        return
      }
      let ss = size / 1024 / 1024;
      if (ss < 1025) {
         states.loading = true;
        if (states.fileList.length > 0) {
          let res = await upload(formData);
          states.formVal.fileList = res.data;
          states.formVal.fileList.forEach((file) => {
            file.type = getFileType(file.name);
          });
        }
        // if(props.type!=1){
        //    let rd = await createFile(states.formVal);
        // }else{
        //   let dd = await upDateList({...states.formVal,id:props.editItem.id})
        // }
        let rd = await createFile({
          ...states.formVal,
          id: props.editItem ? props.editItem.id : "",
        }).finally(() => {
          states.loading = false;
        });
        ctx.emit("updateData");
        handleClose();
      } else {
        ElMessage({
          type: "error",
          message: "文件大小不能超过1G",
        });
      }
      //   let res = upd.value.submit();
    };
    let addTag = async () => {
      if (!states.tagsName) {
        return;
      }
      let res = await addTags({ tagName: states.tagsName });
      states.tagsName = "";
      ElMessage({
        type: "success",
        message: "创建成功",
      });
      ctx.emit("updateTags");
    };
    let tagsChange = () => {
      console.log(states.formVal.tags);
      let temp = [];
      states.formVal.tags.forEach((tag) => {
        let ind = props.tagList.findIndex((it) => {
          return it.id == tag;
        });
        if(ind>=0){
          temp.push(props.tagList[ind].tagName);
        }
        
      });
      states.formVal.tagsName = temp.join(",");
    };
    return {
      handleClose,
      ...toRefs(states),
      uploadChange,
      removeFile,
      save,
      upd,
      addTag,
      tagsChange,
    };
  },
};
</script>

<style lang="scss" scoped>
.createFile {
  .btnUpload {
    margin-bottom: 16px;
  }
  .lab {
    display: flex;
    .crtLab {
      position: relative;
      flex: 1;
      margin-left: 15px;
      .add {
        position: absolute;
        width: 26px;
        height: 26px;
        background: #3377ff;
        border-radius: 3px;
        color: white;
        right: 5px;
        top: 7px;
        line-height: 26px;
        text-align: center;
        font-size: 20px;
        cursor: pointer;
      }
      ::v-deep(.el-input__inner) {
        padding-right: 35px;
      }
    }
  }
}
</style>