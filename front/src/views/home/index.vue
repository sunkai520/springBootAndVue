<template>
  <div class="firstPage">
    <div>
      <div class="title">123</div>
      <div class="desc">
        <p>xxxxxxxx</p>
      </div>
      <div id="codeView" v-highlight>
        <pre><code v-html="code"></code></pre>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity";
export default {
  setup() {
    let states = reactive({
      code: "var a = 1;function(){conso.log(2)}",
    });
    return {
      ...toRefs(states),
    };
  },
};
</script>

<style lang="scss" scoped>
.firstPage {
  height: 100%;
  width: 100%;
  .title{
    font-weight: 600;
    font-size: 18px;
  }
}
</style>