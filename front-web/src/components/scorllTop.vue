<template>
  <div class="scollTop noselect" @click="toTop">
    {{text}}
    <slot></slot>
  </div>
</template>

<script>
import { reactive, toRefs } from '@vue/reactivity'
export default {
    props:{
        text:{
            type:String,
            default:"Top"
        }
    },
    setup(props,{emit}){
       let toTop = ()=>{
        emit("scollTop")
       }
        return {
            toTop
        }
    }
}
</script>

<style lang="scss" scoped>
    .scollTop{
        height: 60px;
        width: 60px;
        border-radius: 60px;
        text-align: center;
        line-height: 60px;
        position: absolute;
        right: 10px;
        top: 10px;
        cursor: pointer;
        background-color: white;
        box-shadow: 0 0 6px darkgrey;
    }
</style>