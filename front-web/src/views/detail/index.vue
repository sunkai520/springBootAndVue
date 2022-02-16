<template>
  <div class="addBk">
    <div class="cxt" v-loading="loading">
      <div class="title"><h2>{{ruleForm.title}}</h2></div>
      <MdEditor v-model="ruleForm.text" previewOnly></MdEditor>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity";
import { createBk, getBkInfo, updateBk } from "@/api/bkList";
import { onMounted } from "@vue/runtime-core";
import { useRoute } from "vue-router";
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
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
      },
      id: id,
      loading:false
    });
    onMounted(() => {
      if (states.id) {
        getOneBkInfo();
      }
    });
    async function getOneBkInfo() {
      states.loading = true;
      let res = await getBkInfo({ id: states.id }).finally(()=>{
        states.loading=false;
      });
      states.ruleForm = res.data;
    }
    return {
      ...toRefs(states),
    };
  },
};
</script>

<style lang="scss" scoped>
.addBk {
  height: 100%;
  width: 100%;
  padding: 15px;
  .cxt {
    width: 800px;
    margin: auto;
    background-color: white;
    box-shadow: 1px 1px 10px #5f5f5f;
    padding: 10px;
    border-radius: 5px;
    .title{
      text-align: center;
      color: #466ca5;
    }
  }
}
</style>