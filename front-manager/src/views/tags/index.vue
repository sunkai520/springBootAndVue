<template>
  <div class="tags">
    <div class="search">
      <div style="margin-right: 20px">
        <el-input v-model="val" placeholder="请输入tags" />
      </div>
      <el-button type="primary" size="mini" @click="search">查询</el-button>
    </div>
    <div class="addBtn">
      <el-button type="primary" size="mini" @click="addBtn">添加</el-button>
    </div>
    <div class="table">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
      >
        <!-- <el-table-column prop="date" label="序号" width="180" align="center" /> -->
        <el-table-column prop="tagName" label="名称" align="center" />

        <el-table-column label="操作" align="center" width="180">
          <template #default="scope"
            ><el-button type="primary" size="mini" @click="edit(scope.row)"
              >修改</el-button
            >
            <el-popconfirm
              title="确定要删除本条数据?"
              @confirm="handleDelete(scope.$index, scope.row)"
            >
              <template #reference>
                <el-button size="mini" type="danger">删除</el-button>
              </template>
            </el-popconfirm></template
          >
        </el-table-column>
      </el-table>
    </div>
   
    <AddTag
      :dialogTableVisible="dialogTableVisible"
      @close="closeDialog"
      @update="updateData"
      :id="id"
      v-if="dialogTableVisible"
    ></AddTag>
  </div>
</template>

<script>
import { onMounted, reactive, toRefs } from "vue-demi";
import { ElMessage } from "element-plus";
import AddTag from "./addtag.vue";
import {getTagsList,delTag} from "@/api/tags"
export default {
  components: {
    AddTag,
  },
  setup() {
  
    async function getList() {
      let params = {
        keyWord: states.val,
      };
      states.loading = true;
      let res = await getTagsList(params).finally(() => {
        states.loading = false;
      });
      states.tableData = res.data;
      
    }
    onMounted(() => {
      getList();
    });
    let updateData = () => {
      getList();
    };
    let states = reactive({
      val: "",
      tableData: [],
      dialogTableVisible: false,
      id: "",
      loading: false,
    });
    let search = () => {
      states.page = 1;
      getList();
    };
    let closeDialog = () => {
      states.id = "";
      states.dialogTableVisible = false;
    };
    let addBtn = () => {
      states.dialogTableVisible = true;
    };
    let handleDelete = async (index, item) => {
      let res = await delTag({ id: item.id });
      if (res.code == 200) {
        ElMessage({
          type: "success",
          message: "删除成功",
        });
      }
      getList();
    };
    let edit = (item) => {
      states.id = item.id;
      states.dialogTableVisible = true;
    };
    return {
      ...toRefs(states),
      search,
      closeDialog,
      addBtn,
      updateData,
      handleDelete,
      edit,
    };
  },
};
</script>

<style lang="scss" scoped>
.tags {
  .search {
    display: flex;
  }
  .table {
    width: 100%;
    margin-top: 10px;
  }
  .page {
    margin-top: 10px;
    text-align: right;
  }
  .addBtn {
    text-align: right;
  }
}
</style>