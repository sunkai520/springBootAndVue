<template>
  <div class="addBk">
    <ScollTop @scollTop="scollTop"></ScollTop>
    <el-scrollbar class="cxt" v-loading="loading" ref="scrollbarRef" @scroll="scroll">
      <div class="title">
        <h2>{{ ruleForm.title }}</h2>
      </div>
      <MdEditor v-model="ruleForm.text" previewOnly></MdEditor>
    </el-scrollbar>
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
export default {
  components: { MdEditor, ScollTop },
  setup(props) {
    let scrollbarRef = ref(null)
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
    });
    onMounted(() => {
      if (states.id) {
        getOneBkInfo();
      }
    });
    async function getOneBkInfo() {
      states.loading = true;
      let res = await getBkInfo({ id: states.id }).finally(() => {
        states.loading = false;
      });
      states.ruleForm = res.data;
    }
    //置顶
    let scollTop = ()=>{
      scrollbarRef.value.setScrollTop(0)
    }
    //滚动
    let scroll = ({scrollTop })=>{
      console.log(scrollTop)
    }
    return {
      scollTop,
      ...toRefs(states),
      scrollbarRef,
      scroll
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