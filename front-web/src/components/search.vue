<template>
  <div class="search">
    <div class="instr">
      <div class="instr_1">
        <label class="tx">{{desc}}:</label>
        <div class="instr_2" :class="isexpand ? 'expand' : ''" id="search">
          <div class="instr_item" v-for="(k, index) in searchData" :key="index">
            <div
              class="instr_tt item noselect"
              @click.stop="oneItemClick(k, index)"
              :class="k.checked ? 'oneActive' : ''"
            >
              {{ k.name }}
            </div>
            <div class="instr_ts">
              <div
                v-for="(t, ind) in k.categories"
                :key="ind"
                class="instr_secondItem item noselect"
                :class="t.checked ? 'oneActive' : ''"
                @click.stop="
                  secondItemClick(t, index + '-' + ind, k.categories)
                "
              >
                {{ t.name }}
                <span
                  class="iconfont"
                  v-if="t.children && t.children.length > 0"
                >
                  &#xe60c;
                </span>
                <div
                  id="triangle-up"
                  v-if="secondIndex == index + '-' + ind && threeExpand && wd"
                  :style="'left:' + wd / 2 + 'px'"
                ></div>
              </div>
            </div>
          </div>
          <div
            @click.stop=""
            v-if="threeList.length > 0 && threeExpand"
            class="instr_three noselect"
            :style="'top:' + cobj.top + 'px;left:' + 0 + 'px'"
          >
            <div
              class="instr_threeItem"
              v-for="(f, id) in threeList"
              :key="id"
              :class="f.checked ? 'threeActive' : ''"
              @click.stop="threeItemClick(f, id)"
            >
              {{ f.name }}
            </div>
          </div>
          <div class="zhankai" @click.stop="expandCLick">
            展开<span class="iconfont" :class="isexpand ? 'dd' : 'dc'"
              >&#xe60c;</span
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, ref, toRefs } from "@vue/reactivity";
import { onMounted } from "@vue/runtime-core";
export default {
  props: {
    /**
     * searchData格式(可以根据结构进行适当修改)
     * [id:1,name:xxx,categories:[id:2,name:xxxx,children:[id:3,name:xxx]]]
     */
    searchData: {
      type: Array,
      default: ()=>[],
    },
    desc:{
      type:String,
      default:"三级筛选"
    }
  },
  setup(props) {
    let states = reactive({
      isexpand: false,
      threeExpand: false,
      threeList: [],
      cobj: { left: 0, top: 0 },
      secondIndex: -1,
      wd: 0,
    });
    onMounted(() => {
      document.getElementsByTagName("body")[0].onclick = () => {
        if (document.getElementById("search")) {
          document.getElementById("search").scrollTop = 0;
        }
        states.threeExpand = false;
        states.isexpand = false;
      };
      console.log(props);
    });
    let expandCLick = () => {
      states.isexpand = !states.isexpand;
      if (!states.isexpand) {
        states.threeExpand = false;
      }
    };
    let setCheked = (arr, bool) => {
      if (arr && arr.length > 0) {
        arr.forEach((t) => {
          t.checked = bool;
          if (t.children && t.children.length > 0) {
            setCheked(t.children, bool);
          }
        });
      }
    };
    let selectChecked = (arrs) => {
      let bool = false;
      arrs.forEach((item) => {
        if (item.checked) {
          bool = true;
        }
      });
      return bool;
    };
    //一级点击事件
    let oneItemClick = (item, index) => {
      item.checked = !item.checked;
      setCheked(item.categories, item.checked);
      states.threeExpand = false;
    };

    //二级点击事件
    let secondItemClick = (item, index, arrs) => {
      let bool = selectChecked(arrs);
      item.checked = !item.checked;
      states.threeExpand = false;
      //三级菜单重置
      let dom = event.currentTarget;
      states.wd = 0;

      states.cobj.left = dom.offsetLeft - 88;
      states.cobj.top = dom.offsetTop + 40;
      states.secondIndex = index;
      //若存在三级则赋值
      if (item.children && item.children.length > 0) {
        if (!bool) {
          states.isexpand = true;
        }
        states.threeExpand = !bool;
        states.threeList = item.children;
        item.children.forEach((t) => {
          t.checked = item.checked;
        });
        states.wd = dom.offsetWidth;
      }
    };
    //三级点击事件
    let threeItemClick = (item, index) => {
      item.checked = !item.checked;
    };
    //查询返回选中数据
    let pullDataAndResert = (bool = false) => {
      let arrs = [];
      function getArrays(arr) {
        arr.forEach((t) => {
          if (bool) {
            t.checked = false;
          } else {
            if (t.checked) {
              arrs.push(t.id);
            }
          }
          if (t.children && t.children.length > 0) {
            getArrays(t.children);
          }
        });
      }
      props.searchData.forEach((item) => {
        if (bool) {
          item.checked = false;
        }
        getArrays(item.categories);
      });
      console.log(arrs);
      return arrs;
    };

    return {
      ...toRefs(states),
      expandCLick,
      secondItemClick,
      threeItemClick,
      oneItemClick,
      pullDataAndResert,
    };
  },
};
</script>

<style lang="scss" scoped>
.search {
  .item {
    height: 32px;
    line-height: 32px;
    cursor: pointer;
    padding: 0 10px;
  }
  .instr {
    padding: 20px;
    position: relative;
    .tx {
      width: 80px;
      text-align: left;
      display: inline-block;
      padding-right: 5px;
    }
    padding-bottom: 0;
    .instr_1 {
      border-bottom: 1px dotted #e5e5e5;
      padding-bottom: 13px;
      width: 100%;
      position: relative;
      padding-bottom: 25px;
      padding-top: 10px;
      .instr_2 {
        display: inline-block;
        width: calc(100% - 80px);
        height: 52px;
        transition: height 0.5s;
        position: absolute;
        overflow: hidden;
        padding: 10px 0;
        background-color: white;
        z-index: 999;
        top: -3px;
        .instr_item {
          display: flex;
          > div {
            display: inline-block;
          }
          .instr_secondItem {
            margin-left: 10px;
            background: #ffffff;
            border: 1px solid #e6e6e8;
            border-radius: 6px;
            display: inline-block;
            margin-bottom: 10px;
            position: relative;
          }
          #triangle-up {
            width: 0;
            height: 0;
            border-left: 8px solid transparent;
            border-right: 8px solid transparent;
            border-bottom: 8px solid #80808066;
            position: absolute;
            top: 30px;
            left: 50px;
          }
          .instr_tt {
            background: #ffffff;
            border: 1px solid #3e7bfa;
            border-radius: 6px;
            font-size: 14px;
            font-family: Microsoft YaHei UI;
            font-weight: 400;
            width: 100px;
            text-align: center;
            overflow: hidden;
            color: #3e7bfa;
            cursor: pointer;
          }
          .instr_ts {
            flex: 1;
          }

          .oneActive {
            background: #3e7bfa;
            color: white;
          }
        }
        .instr_item:first-child {
          .instr_ts {
            padding-right: 60px;
          }
        }
        .instr_three {
          position: absolute;
          z-index: 1000;
          background: #ffffff;
          box-shadow: 0px 1px 10px 0px rgba(0, 0, 0, 0.23);
          border-radius: 6px;
          padding: 5px 10px;
          width: 90%;
          max-height: 200px;
          margin-left: 100px;
          overflow-y: auto;
          left: 0;
          top: 50px;
          padding-bottom: 0;
          .instr_threeItem {
            height: 24px;
            background: #06c2702e;
            border-radius: 4px;
            display: inline-block;
            font-size: 12px;
            font-family: Microsoft YaHei UI;
            font-weight: 400;
            color: #06c270;
            line-height: 24px;
            padding: 0 10px;
            margin-right: 10px;
            cursor: pointer;
            margin-bottom: 10px;
          }
          .threeActive {
            background-color: #06c270;
            color: white;
          }
        }
        .zhankai {
          width: 60px;
          font-family: Microsoft YaHei UI;
          font-weight: 400;
          color: #3e7bfa;
          padding-top: 6px;
          position: absolute;
          right: 0;
          top: 10px;
          cursor: pointer;
          .iconfont {
            display: inline-block;
          }
          .dd {
            transform: rotate(180deg);
            display: inline-block;
            padding-right: 5px;
          }
          .dc {
            padding-left: 5px;
          }
        }
      }
      .expand {
        box-shadow: 0px 3px 21px 0px rgba(0, 0, 0, 0.1);
        position: absolute;
        z-index: 999;
        background-color: white;
        top: 0px;
        left: 70px;
        padding: 10px;
        border-radius: 5px;
        height: 450px;
        overflow: auto;
      }
    }
  }
}
</style>