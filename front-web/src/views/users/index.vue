<template>
  <div class="list">
    <div class="search">
      <div style="margin-right: 20px">
        <el-input v-model="val" placeholder="请输入用户姓名" />
      </div>
      <el-button type="primary" size="mini" @click="search">查询</el-button>
    </div>
    <div class="addBtn">
      <el-button type="primary" size="mini" @click="addBtn">添加</el-button>
    </div>
    <div class="table">
      <el-table :data="tableData" style="width: 100%" border v-loading="loading">
        <!-- <el-table-column prop="date" label="序号" width="180" align="center" /> -->
        <el-table-column prop="name" label="姓名" width="180" align="center" />
        <el-table-column prop="loginName" label="账号" align="center" />
        <el-table-column prop="sex" label="性别" align="center">
          <template #default="scope">
            {{ scope.row.sex == 0 ? "女" : "男" }}
          </template>
        </el-table-column>
        <el-table-column prop="age" label="年龄" align="center" />
        <el-table-column prop="phone" label="联系电话" align="center" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope"
            ><el-button type="primary" size="mini" @click="edit(scope.row)">修改</el-button>
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
    <add-user
      :dialogTableVisible="dialogTableVisible"
      @close="closeDialog"
      @update="updateData"
      :id="id"
      v-if="dialogTableVisible"
    ></add-user>
  </div>
</template>
<script>
import { onMounted, reactive, toRefs } from "vue-demi";
import pageHooks from "@/utils/pageHooks";
import { getUserList,delUser} from "@/api/user";
import AddUser from "./addInfoWindow.vue";
import { ElMessage } from 'element-plus';
export default {
  components: {
    AddUser,
  },
  setup() {
    const { pageParams, handlePageChange, handleSizeChange } =
      pageHooks(getList);
    async function getList() {
      let params = {
        page:states.page,
        pageSize:states.list_rows,
        keyWord: states.val,
      };
      states.loading = true;
      let res = await getUserList(params).finally(()=>{
        states.loading=false
      });
      states.tableData = res.data.data;
      states.total = res.data.total
    }
    onMounted(() => {
      getList();
    });
    let updateData = () => {
      getList();
    };
    let states = reactive({
      ...toRefs(pageParams),
      val: "",
      tableData: [],
      dialogTableVisible: false,
      id:"",
      loading:false
    });
    let search = () => {
      states.page = 1;
      getList();
    };
    let closeDialog = () => {
      states.id = ""
      states.dialogTableVisible = false;
    };
    let addBtn = () => {
      states.dialogTableVisible = true;
    };
    let handleDelete = async (index, item) => {
      let res = await delUser({id:item.id});
      if(res.code==200){
        ElMessage({
          type:"success",
          message:"删除成功"
        })
      }
      getList()
      console.log(res,"res")
    };
    let edit = (item)=>{
      states.id=item.id;
      states.dialogTableVisible = true;
    }
    return {
      ...toRefs(states),
      handlePageChange,
      handleSizeChange,
      search,
      closeDialog,
      addBtn,
      updateData,
      handleDelete,
      edit
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
  .page {
    margin-top: 10px;
    text-align: right;
  }
  .addBtn {
    text-align: right;
  }
}
</style>