<template>
  <div class="menus">
    <div v-for="(item, index) in menus" :key="index">
      <div class="item noselect" @click="clickItem(item)">
        <span>{{ item.name }}</span
        ><i class="iconfont">&#xe654;</i>
      </div>
      <div class="subItem noselect" v-show="item.expand" v-for="(subItem,inx) in item.childrens" :key="inx" @click="clickSubItem(subItem)">
        <span>{{subItem.name}}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "vue-demi";
import router from '@/router';
export default {
  setup() {
    let states = reactive({
      menus: [
        {
          name: "设备管理",
          expand: false,
          childrens: [{ name: "IMSI采集列表",path:"imsList"}],
        },
        { name: "系统管理", expand: false ,childrens: [{ name: "用户信息",path:"users" }],},
        { name: "权限管理", expand: false ,childrens: [{ name: "角色管理" }],},
      ],
    });
    let clickItem = (item) => {
      item.expand = !item.expand;
    };
    let clickSubItem=(item)=>{
        router.push(item.path)
    }
    return {
      ...toRefs(states),
      clickItem,
      clickSubItem
    };
  },
};
</script>

<style lang="scss" scoped>
.menus {
  background-color: rgba(246, 246, 246, 1);
  height: 100%;
  width: 100%;
  .item {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px;
    cursor: pointer;
    font-weight: 700;
    font-style: normal;
    color: #333333;
    font-size: 14px;
    .iconfont {
      color: #80808085;
    }
  }
  .subItem {
    background-color: white;
    padding: 10px 20px;
    font-size: 14px;
    cursor: pointer;
    position: relative;
    &:hover{
        color: #2950B8;
        &::before{
            content: "";
            height: 100%;
            width: 3px;
            background-color: #2950B8;
            display: block;
            position: absolute;
            left: 0;
            top: 0;
        }
    }
    
  }
}
</style>