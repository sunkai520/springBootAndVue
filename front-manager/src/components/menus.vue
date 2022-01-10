<template>
  <div class="menus">
    <div v-for="(item, index) in menus" :key="index">
      <div class="item noselect" @click="clickItem(item)">
        <span>{{ item.name }}</span
        ><i class="iconfont">&#xe654;</i>
      </div>
      <div
        class="subItem noselect"
        v-show="item.expand"
        v-for="(subItem, inx) in item.childrens"
        :key="inx"
        @click="clickSubItem(subItem)"
        :class="subItem.isActive ? 'active' : ''"
      >
        <span>{{ subItem.name }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, reactive, toRefs } from "vue-demi";
import router from "@/router";
export default {
  setup() {
    let states = reactive({
      menus: [
        {
          name: "bk设置",
          expand: false,
          childrens: [{ name: "bk列表", path: "/",code:"/"},{name:"tags列表",path:"tags",code:"tags"}],
        },
        {
          name: "系统管理",
          expand: false,
          childrens: [{ name: "用户信息", path: "users",code:"users"}],
        },
        { name: "权限管理", expand: false, childrens: [{ name: "角色管理" }] },
      ],
    });
    let clickItem = (item) => {
      item.expand = !item.expand;
    };
    let clickSubItem = (item) => {
      setMenuStates();
      item.isActive = true;
      if (item.path) {
        router.push(item.path);
      }
    };
    onMounted(() => {
      setMenuStates(router.currentRoute.value.name, true);
    });
    function setMenuStates(name = "", bool = false) {
      states.menus.forEach((menu) => {
        let tmpArr = menu.childrens;
        if (tmpArr.length > 0) {
          let isBool = false;
          tmpArr.forEach((subMenu) => {
            if (subMenu.code == name) {
              subMenu.isActive = true;
              isBool = true;
            } else {
              subMenu.isActive = false;
            }
          });
          if (bool) {
            menu.expand = isBool;
          }
        }
      });
    }
    return {
      ...toRefs(states),
      clickItem,
      clickSubItem,
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
    &.active {
      color: #2950b8;
      font-weight: 600;
    }
    &:hover {
      color: #2950b8;
      &::before {
        content: "";
        height: 100%;
        width: 3px;
        background-color: #2950b8;
        display: block;
        position: absolute;
        left: 0;
        top: 0;
      }
    }
  }
}
</style>