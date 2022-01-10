<template>
  <div class="infoWindow">
    <el-dialog
      :modelValue="dialogTableVisible"
      :title="title"
      @close="closeDialog"
      :width="400"
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
              <el-form-item label="tag" prop="tagName">
                <el-input v-model="ruleForm.tagName" autocomplete="off"></el-input>
              </el-form-item>
            </div>
            <div style="text-align:right">
              <el-form-item>
                <el-button
                  type="primary"
                  @click="submitForm('ruleForm')"
                  size="mini"
                  >保存</el-button
                >
              </el-form-item>
            </div>
          </div>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { reactive, ref, toRefs } from "@vue/reactivity";
import { onMounted, warn } from "@vue/runtime-core";
import {createTags,getTag,updateTag} from "@/api/tags"
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
      title: props.id ? "编辑tag" : "新增tag",
      ruleForm: {
        tagName: "",
      },
      rules: {},
      loading: false,
    });
    let submitForm = () => {
      rule.value.validate(async (valid) => {
        if (valid) {
          if (props.id) {
            states.ruleForm.id = props.id;
            let res = await updateTag(states.ruleForm);
          } else {
            let res = await createTags(states.ruleForm);
          }
          ctx.emit("update");
          closeDialog();
        }
      });
    };
    let closeDialog = () => {
      ctx.emit("close");
    };
    onMounted(() => {
      if (props.id) {
        getInfo();
      }
    });
    let getInfo = async () => {
      let res = await getTag({ id: props.id });
      states.ruleForm = res.data;
      console.log(res, "res");
    };
    // onMounted(() => {

    // });
    return {
      closeDialog,
      ...toRefs(states),
      submitForm,
      rule,
    };
  },
};
</script>

<style lang="scss" scoped>
.formDiv {
  > div {
    display: inline-block;
    width: 300px;
    vertical-align: top;
  }
}
</style>