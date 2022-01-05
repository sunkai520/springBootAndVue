<template>
  <div class="list">
    <div class="search">
      <div style="margin-right: 20px">
        <el-input v-model="val" placeholder="请输入关键词" />
      </div>
      <el-button type="primary" size="mini" @click="search">查询</el-button>
    </div>
    <div class="addBtn">
      <el-button type="primary" size="mini" @click="addBk">新增</el-button>
    </div>
    <div class="table">
      <div class="item" v-for="(k, index) in tableData" :key="index">
        <div class="card">
          <div class="imgg">
            <el-image :src="'api/' + k.imageUrl">
              <template #error>
                <div class="image-slot">
                  <img src="../../../src/assets/img/default.png" />
                </div>
              </template>
            </el-image>
          </div>
          <div class="content">
            <div class="title">{{ k.title }}</div>
            <div class="des">
              <p class="p1">{{ k.des }}</p>
            </div>
          </div>
          <div class="options">
            <!-- <el-button type="primary" size="mini" @click="seeBk(k)"
            >查看</el-button
          > -->
            <el-button type="primary" size="mini" @click="editBk(k)"
              >编辑</el-button
            >
            <el-popconfirm
              title="确定要删除本条数据?"
              @confirm="handleDelete(k)"
            >
              <template #reference>
                <el-button size="mini" type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </div>
        </div>
        <div class="foot">
          <span>发布时间:</span>
          <span>{{ k.date }}</span>
          <div class="shu">|</div>
          <span>浏览量:</span>
          <span>{{ k.visitor || 0 }}</span>
        </div>
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
  </div>
</template>

<script>
import { onMounted, reactive, toRefs } from "vue-demi";
import pageHooks from "@/utils/pageHooks";
import { getBkList, delBk } from "@/api/bkList";
import { ElMessage } from "element-plus";
import router from "@/router";
export default {
  setup() {
    const { pageParams, handlePageChange, handleSizeChange } =
      pageHooks(getList);
    async function getList() {
      let user = JSON.parse(localStorage.getItem("user"));
      let res = await getBkList({
        userId: user.id,
        page: states.page,
        pageSize: states.list_rows,
        keyWord: states.val,
      });
      states.tableData = res.data.data;
      states.total = res.data.total;
      console.log(res, "res");
    }
    onMounted(() => {
      getList();
    });
    let states = reactive({
      ...toRefs(pageParams),
      val: "",
      tableData: [],
    });
    let handleDelete = async (item) => {
      console.log(item);
      let res = await delBk({ id: item.id });
      states.page = 1;
      ElMessage({
        type: "success",
        message: "删除成功!",
      });
      getList();
    };
    let search = () => {
      states.page = 1;
      getList();
    };
    let addBk = () => {
      router.push("/addBk");
    };
    let editBk = (item) => {
      router.push({ path: "/addBk", query: { id: item.id } });
    };
    return {
      ...toRefs(states),
      handlePageChange,
      handleSizeChange,
      handleDelete,
      search,
      addBk,
      editBk,
    };
  },
};
</script>

<style lang="scss" scoped>
.list {
  .search {
    display: flex;
    background: white;
    padding: 10px;
  }
  .addBtn {
    text-align: right;
    margin-top: 20px;
  }
  .table {
    width: 100%;
    margin-top: 10px;
    background: white;
    box-shadow: 0 0 3px white;
    padding: 10px;
    margin-top: 20px;

    .item {
      .foot {
        background-color: black;
        width: 100%;
        padding-left: 20px;
        .shu{
          padding-left: 5px;
          padding-right: 5px;
          display: inline-block;
          color: white;
        }
        span{
          color: white;
          font-size: 14px;
        }
      }
      .card {
        margin-bottom: 5px;
        > div {
          display: inline-block;
          vertical-align: top;
        }
        .imgg {
          width: 100px;
          height: 80px;
          margin-right: 10px;
          ::v-deep img {
            width: 100%;
            height: 100%;
            border-radius: 5px;
          }
          ::v-deep .el-image {
            width: 100%;
            height: 100%;
          }
        }
        .content {
          width: calc(100% - 340px);
          .title {
            font-size: 16px;
            font-weight: 600;
            width: 500px;
            overflow: hidden;
          }
          .des {
            font-size: 14px;
            margin-top: 5px;
            .p1 {
              overflow: hidden;
              text-overflow: ellipsis;
              display: -webkit-box;
              -webkit-line-clamp: 3;
              -webkit-box-orient: vertical;
            }
          }
        }
        .options {
          width: 200px;
          margin-left: 20px;
        }
      }
    }
    .item:not(:last-child) {
      margin-bottom: 20px;
    }
  }
  .page {
    margin-top: 10px;
    text-align: right;
  }
}
</style>