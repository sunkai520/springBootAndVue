<template>
  <div class="list">
    <div class="search">
      <div style="margin-right: 20px">
        <el-input v-model="val" placeholder="请输入IMSI信息" />
      </div>
      <el-button type="primary" size="mini">查询</el-button>
    </div>
    <div class="table">
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="date" label="编号" width="180" align="center" />
        <el-table-column prop="name" label="采集信息" width="180" align="center"/>
        <el-table-column prop="address" label="制式" align="center"/>
        <el-table-column prop="address" label="信道" align="center"/>
        <el-table-column prop="address" label="运营商" align="center"/>
        <el-table-column prop="address" label="IMSI" align="center"/>
      </el-table>
    </div>
    <div class="page">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handlePageChange"
        :current-page="page"
        layout="total,prev, pager, next,sizes"
        :total="total"
        :page-size="list_rows"
        :page-sizes="[10, 20, 30, 40, 50]"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { onMounted, reactive, toRefs } from "vue-demi";
import pageHooks from "@/utils/pageHooks";
export default {
  setup() {
    const { pageParams, handlePageChange, handleSizeChange } =
      pageHooks(getList);
    function getList() {
      console.log("获取列表");
    }
    onMounted(()=>{
        getList()
    })
    let states = reactive({
      ...toRefs(pageParams),
      val: "",
      tableData: [
        {
          date: "2016-05-03",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles",
        },
        {
          date: "2016-05-02",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles",
        },
        {
          date: "2016-05-04",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles",
        },
        {
          date: "2016-05-01",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles",
        },
      ],
    });
    return {
      ...toRefs(states),
      handlePageChange,
      handleSizeChange,
    };
  },
};
</script>

<style lang="scss" scoped>
.list {
  .search {
    display: flex;
  }
  .table {
    width: 100%;
    margin-top: 10px;
  }
  .page{
      margin-top: 10px;
      text-align: right;
  }
}
</style>