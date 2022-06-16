<template>
  <div class="othersCompents">
    <Search :searchData="searchData"/>
  </div>
</template>

<script>
import Search from "@/components/search.vue";
import { reactive, toRefs } from "@vue/reactivity";
export default {
  components: {
    Search,
  },
  setup() {
    let states = reactive({
      searchData: [
        {
          id: 1,
          name: "集团平台",
          created_at: "2022-05-05 09:41:43",
          desc: "测试",
          area_unique: "集团平台1",
          categories: [
            {
              id: 5,
              area_id: 1,
              parent_id: "0",
              name: "行政",
              created_at: "2020-02-16 22:21:46",
              desc: null,
              area_unique: "行政5",
              children: [
                {
                  id: 296,
                  area_id: 1,
                  parent_id: "5",
                  name: "aaaad",
                  created_at: "2022-06-09 10:41:48",
                  desc: null,
                  area_unique: "aaaad296",
                },
              ],
            },
            {
              id: 6,
              area_id: 1,
              parent_id: "0",
              name: "法务",
              created_at: "2020-02-16 22:21:46",
              desc: "",
              area_unique: "法务6",
              children: [],
            },
            {
              id: 55,
              area_id: 1,
              parent_id: "0",
              name: "海尔兄弟",
              created_at: "2020-02-16 22:21:46",
              desc: "",
              area_unique: "海尔兄弟55",
              children: [],
            },
            {
              id: 7,
              area_id: 1,
              parent_id: "0",
              name: "其他",
              created_at: "2020-02-16 22:21:46",
              desc: "",
              area_unique: "其他7",
              children: [],
            },
          ],
        },
        {
          id: 2,
          name: "海尔智家",
          created_at: "2022-04-18 10:02:03",
          desc: "",
          area_unique: "海尔智家2",
          categories: [
            {
              id: 66,
              area_id: 2,
              parent_id: "0",
              name: "制冷",
              created_at: "2020-04-11 14:36:43",
              desc: "",
              area_unique: "制冷66",
              children: [
                {
                  id: 57,
                  area_id: 2,
                  parent_id: "66",
                  name: "家用冰箱",
                  created_at: "2020-03-09 07:06:08",
                  desc: null,
                  area_unique: "家用冰箱57",
                },
                {
                  id: 78,
                  area_id: 2,
                  parent_id: "66",
                  name: "商用冷柜",
                  created_at: "2020-04-11 14:57:35",
                  desc: "",
                  area_unique: "商用冷柜78",
                },
                {
                  id: 8,
                  area_id: 2,
                  parent_id: "66",
                  name: "家用冷柜",
                  created_at: "2020-02-16 22:21:46",
                  desc: "",
                  area_unique: "家用冷柜8",
                },
                {
                  id: 79,
                  area_id: 2,
                  parent_id: "66",
                  name: "酒柜",
                  created_at: "2020-04-11 15:01:45",
                  desc: "",
                  area_unique: "酒柜79",
                },
                {
                  id: 80,
                  area_id: 2,
                  parent_id: "66",
                  name: "冰吧",
                  created_at: "2020-04-11 15:02:09",
                  desc: "",
                  area_unique: "冰吧80",
                },
              ],
            },
          ],
        },
        {
          id: 13,
          name: "水站",
          created_at: "2022-04-18 10:02:03",
          desc: "",
          area_unique: "水站13",
          categories: [
            {
              id: 198,
              area_id: 13,
              parent_id: "0",
              name: "健康水站",
              created_at: "2021-06-03 14:38:44",
              desc: "",
              area_unique: "健康水站198",
              children: [],
            },
          ],
        },
        {
          id: 14,
          name: "教育",
          created_at: "2022-04-18 10:02:03",
          desc: "",
          area_unique: "教育14",
          categories: [
            {
              id: 211,
              area_id: 14,
              parent_id: "0",
              name: "海尔学校",
              created_at: "2022-02-28 15:23:32",
              desc: "",
              area_unique: "海尔学校211",
              children: [],
            },
            {
              id: 212,
              area_id: 14,
              parent_id: "0",
              name: "基金会",
              created_at: "2022-02-28 15:24:14",
              desc: "",
              area_unique: "基金会212",
              children: [],
            },
          ],
        },
      ],
    });
    return {
        ...toRefs(states)
    }
  },
};
</script>

<style lang="scss" scoped>
.othersCompents{
    height: 100%;
    width: 100%;
}
</style>