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
import { onMounted } from '@vue/runtime-core';
export default {
  setup() {
    let states = reactive({
      code: "1",
    });
    onMounted(()=>{
      setTimeout(()=>{
        states.code="let a = 2;function(){conso.log(2)}"
      },2000)
    })
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