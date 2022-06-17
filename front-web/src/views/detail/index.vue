<template>
  <div class="addBk">
    <div class="options">
      <ScollTop @scollTop="scollTop"></ScollTop>
      <ScollTop class="pl" @click="showAddComment"
        ><span class="iconfont">&#xe627;</span></ScollTop
      >
    </div>
    <el-scrollbar
      class="cxt"
      v-loading="loading"
      ref="scrollbarRef"
      @scroll="scroll"
    >
      <div class="title">
        <h2>{{ ruleForm.title }}</h2>
      </div>
      <MdEditor v-model="ruleForm.text" previewOnly></MdEditor>
    </el-scrollbar>
    <AddComment
      :comDialogVisible="comDialogVisible"
      @close="close"
      v-if="comDialogVisible"
      @commit="commit"
    ></AddComment>
    <div class="comment">
      <div class="title">评论:</div>
      <CommentList :commentList="commentsList" v-loading="commentLoading"></CommentList>
    </div>
  </div>
</template>

<script>
import { reactive, ref, toRefs } from "@vue/reactivity";
import { createBk, getBkInfo, updateBk } from "@/api/bkList";
import { onMounted } from "@vue/runtime-core";
import { useRoute } from "vue-router";
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import ScollTop from "@/components/scorllTop.vue";
import AddComment from "@/components/addcomment.vue";
import { createComment,getCommentList} from "@/api/comments";
import { ElMessage } from "element-plus/lib/components";
import CommentList from "./components/commentList/index.vue";
export default {
  components: { MdEditor, ScollTop, AddComment, CommentList },
  setup(props) {
    let scrollbarRef = ref(null);
    let route = useRoute();
    let id = route.query.id;
    let states = reactive({
      ruleForm: {
        title: "",
        des: "",
        imageUrl: "",
        text: "",
      },
      id: id,
      loading: false,
      comDialogVisible: false,
      commentsList:[],
      commentLoading:false
    });
    onMounted(() => {
      if (states.id) {
        getOneBkInfo();
        getPlList();
      }
    });
    async function getOneBkInfo() {
      states.loading = true;
      let res = await getBkInfo({ id: states.id }).finally(() => {
        states.loading = false;
      });
      states.ruleForm = res.data;
    }
    //展示评论框
    let showAddComment = () => {
      states.comDialogVisible = true;
    };
    //置顶
    let scollTop = () => {
      scrollbarRef.value.setScrollTop(0);
    };
    //滚动
    let scroll = ({ scrollTop }) => {
      console.log(scrollTop);
    };
    //评论弹窗关闭
    let close = () => {
      states.comDialogVisible = false;
    };
    //提交评论
    let commit = async (params) => {
      close();
      let par = {
        ...params,
        articleId: states.id,
      };
      let res = await createComment(par);
      ElMessage.success("提交成功!");
      getPlList();
    };
    //获取评论列表
    let getPlList = async ()=>{
      states.commentLoading = true;
      let res = await getCommentList({
        articleId:states.id
      }).finally(()=>{
        states.commentLoading =false
      });
      states.commentsList = res.data
      
    }
    return {
      scollTop,
      commit,
      ...toRefs(states),
      scrollbarRef,
      scroll,
      showAddComment,
      close,
    };
  },
};
</script>

<style lang="scss" scoped>
.addBk {
  height: 100%;
  width: 100%;
  padding: 15px;
  position: relative;
  .comment {
    position: absolute;
    bottom: 50px;
    right: 10px;
    width: 20%;
    height: 500px;
    .title{
      margin-bottom: 10px;
      font-size: 18px;
      font-weight: bold;
    }
  }
  .options {
    position: absolute;
    top: 10px;
    right: 10px;
    .pl {
      margin-top: 10px;
      .iconfont {
        font-size: 28px;
      }
    }
  }
  .cxt {
    width: 50%;
    height: 100%;
    margin: auto;
    background-color: white;
    box-shadow: 1px 1px 10px #5f5f5f;
    padding: 10px;
    border-radius: 5px;
    .title {
      text-align: center;
      color: #466ca5;
    }
  }
}
</style>