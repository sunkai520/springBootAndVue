<template>
  <div
    class="fileManager"
    v-loading="loading"
    element-loading-text="文件数量过大,请稍后..."
  >
    <el-scrollbar class="search" max-height="210px">
      <div class="hd">
        <div class="tt">
          <img src="../../assets/label.png" />
          <span>标签</span>
        </div>
        <div>
          <img
            src="../../assets/edit.png"
            alt="/"
            @click="del"
            style="cursor: pointer"
          />
        </div>
      </div>
      <div class="lab">
        <div
          class="labelItem shengluehao noselect"
          v-for="(k, index) in tagList"
          :key="index"
          @click="tagClick(index, k)"
          :class="tagIndex.includes(index) ? 'active' : ''"
        >
          {{ k.tagName
          }}<img
            src="../../assets/cha.png"
            v-show="isDel"
            @click.stop="delTags(k)"
          />
        </div>
      </div>
      <div class="ss">
        <div class="ipt">
          <el-input
            placeholder="请输入标题,标签,描述"
            v-model="keyWord"
          ></el-input>
          <img
            src="../../assets/search.png"
            class="iptSearch"
            @click="search"
          />
        </div>
        <div>
          <el-button type="primary" @click="createFiles"
            ><span style="padding-right: 5px">+</span>新建</el-button
          >
        </div>
      </div>
    </el-scrollbar>
    <div class="ctn">
      <div class="fileTable">
        <div class="tt">
          <img src="../../assets/list.png" />
          <span>列表</span>
        </div>
        <div class="table">
          <el-table
            :data="tableData"
            style="width: 100%"
            height="450"
            @row-click="rowClick"
            v-loading="listLoading"
          >
            <el-table-column prop="num" label="序号" width="80" align="center">
              <template #default="scope">{{ scope.$index + 1 }}</template>
            </el-table-column>
            <el-table-column prop="title" label="标题" align="center" />
            <el-table-column prop="tagsName" label="标签" align="center" />
            <el-table-column
              prop="date"
              label="创建时间"
              width="180"
              align="center"
            />
            <el-table-column
              prop="cnt"
              label="文件数"
              width="80"
              align="center"
            />
            <el-table-column label="操作" width="120" align="center">
              <template #default="scope">
                <!-- <span class="opt">查看</span> -->
                <span class="opt" @click.stop="edit(scope.row)">编辑</span>
                <el-popconfirm
                  confirm-button-text="确认"
                  cancel-button-text="取消"
                  icon-color="red"
                  title="此操作将永久删除,已经上传文件."
                  @confirm="removeb(scope.row)"
                >
                  <template #reference>
                    <span style="margin-left: 5px; color: red; cursor: pointer"
                      >删除</span
                    >
                  </template>
                </el-popconfirm>
              </template>
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
      </div>
      <div class="fileList">
        <div class="tt">
          <img src="../../assets/file.png" />
          <span>文件</span>
        </div>
        <div class="files">
          <div class="itd">
            <div
              class="itd-item shengluehao"
              v-for="(k, index) in filesTypes"
              :key="index"
              :class="fileInd == index ? 'active' : ''"
              @click="fileClick(index, k)"
            >
              {{ k.name }}
            </div>
          </div>
          <el-scrollbar class="fi" height="450px" v-loading="floading" v-if="ffileList.length>0">
            <div class="fi-item" v-for="(k, index) in ffileList" :key="index">
              <div class="sub">
                <img src="../../assets/page.png" />
                <div class="sub-title">
                  <div class="title shengluehao">
                    {{ k.name }}
                  </div>
                  <div style="display: flex; justify-content: space-between">
                    <span>{{ k.date }}</span>
                    <span>{{ bytesToSize(k.size) }}</span>
                  </div>
                </div>
              </div>
              <div class="opt">
                <img src="../../assets/downLoad.png" @click="downLoad(k)" />

                <el-popconfirm
                  confirm-button-text="确认"
                  cancel-button-text="取消"
                  icon-color="red"
                  title="此操作将永久删除,已经上传文件."
                  @confirm="remove(k)"
                >
                  <template #reference>
                    <img src="../../assets/del.png" />
                  </template>
                </el-popconfirm>
              </div>
            </div>
          </el-scrollbar>
          <div class="noData" v-if="ffileList.length==0">暂无文件</div>

        </div>
      </div>
    </div>
    <create-file
      :dialogVisible="dialogVisible"
      @close="diagClose"
      @updateData="getTableList"
      @updateTags="getTagLists"
      v-if="dialogVisible"
      :type="editType"
      :editItem="editItem"
      :tagList="tagList"
    ></create-file>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity";
import createFile from "./create-file.vue";
import {
  getFileList,
  removeFile,
  getFfList,
  removeList,
  getTagList,
  delTag,
  downLoadFile
} from "@/api/upload.ts";
import { onMounted, watch } from "@vue/runtime-core";
import pageHooks from "@/utils/pageHooks";
import { ElMessage, ElMessageBox } from "element-plus";
import { bytesToSize } from "@/utils/common.js";
export default {
  components: { createFile },
  setup() {
    const { pageParams, handlePageChange, handleSizeChange } =
      pageHooks(getTableList);
    let states = reactive({
      ...toRefs(pageParams),
      isDel: false,
      dialogVisible: false,
      tableData: [],
      filesTypes: [
        { name: "全部", value: "" },
        { name: "word", value: "word" },
        { name: "Excel", value: "excel" },
        { name: "PPT", value: "ppt" },
        { name: "压缩包", value: "zip" },
        { name: "其他", value: "other" },
      ],
      ffileList: [],
      id: "",
      floading: false,
      fileInd: 0,
      editType: "",
      editItem: {},
      tagList: [],
      tagIndex: [],
      listLoading: false,
      keyWord: "",
      loading: false,
    });
    // watch(
    //   () => states.id,
    //   async (newVal, oldVal) => {}
    // );
    let del = () => {
      states.isDel = !states.isDel;
    };
    let diagClose = () => {
      states.editItem = {};
      states.editType = "";
      states.dialogVisible = false;
    };
    
    let createFiles = () => {
      states.dialogVisible = !states.dialogVisible;
    };
    let rowClick = (row) => {
      states.id = row.id;
      getFiList(states.id);
    };
    let getFiList = async (id, type = "") => {
      states.floading = true;
      let res = await getFfList({ fileId: id, type: type }).finally(() => {
        states.floading = false;
      });
      states.ffileList = res.data;
    };
    async function getTableList() {
      let arr = states.tagList;
      let temp = arr.filter((item, index) => {
        return states.tagIndex.includes(index);
      });
      let pageParams = {
        page: states.page,
        pageSize: states.list_rows,
        keyWord: states.keyWord,
        tags: temp.map((it) => {
          return it.id;
        }),
      };
      states.listLoading = true;
      let res = await getFileList(pageParams).finally(() => {
        states.listLoading = false;
      });
      states.total = res.data.total;
      states.tableData = res.data.data;
      if (!states.id && states.tableData.length > 0) {
        states.id = states.tableData[0].id;
        getFiList(states.id);
      }
    }
    let downLoad =async (item) => {
      window.open("api/downLoadFile?url=" + item.url+"&name="+item.name);
      // let res = await downLoadFile(item)
      // window.open("api/"+item.url+"?fileName="+item.name);
    };
    let fileClick = (index, item) => {
      states.fileInd = index;
      getFiList(states.id, item.value);
    };
    let remove = async (item) => {
      states.loading = true;
      let res = await removeFile({ filePath: item.url, id: item.id }).finally(
        () => {
          states.loading = false;
        }
      );
      ElMessage({
        type: "success",
        message: "删除成功!",
      });
      getFiList(states.id);
      getTableList();
    };
    let removeb = async (item) => {
      states.loading = true;
      let res = await removeList({ id: item.id }).finally(() => {
        states.loading = false;
      });
      ElMessage({
        type: "success",
        message: "删除成功!",
      });
      states.id = "";
      getTableList();
    };
    let edit = (item) => {
      states.dialogVisible = true;
      states.editType = 1;
      states.editItem = item;
    };
    let getTagLists = async () => {
      let res = await getTagList();
      states.tagList = res.data;
    };
    let tagClick = (index, item) => {
      if (states.tagIndex.includes(index)) {
        let t = states.tagIndex.findIndex((ind) => {
          return ind == index;
        });
        states.tagIndex.splice(t, 1);
      } else {
        states.tagIndex.push(index);
      }
    };
    let delTags = async (item) => {
      ElMessageBox.confirm("此操作会永久删除标签?", "提示", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async () => {
          let res = await delTag({ id: item.id });
          getTagLists();
          ElMessage({
            type: "success",
            message: "删除成功!",
          });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "Delete canceled",
          });
        });
    };
    let search = () => {
      getTableList();
    };
    onMounted(() => {
      getTableList();
      getTagLists();
      
      // removeFile({ filePath: "images/1c246cac1eb549c1a517d7a85177a666.conf" });
    });
 
    return {
      ...toRefs(states),
      del,
      createFiles,
      diagClose,
      handlePageChange,
      handleSizeChange,
      rowClick,
      downLoad,
      remove,
      getTableList,
      fileClick,
      removeb,
      edit,
      getTagLists,
      tagClick,
      delTags,
      search,
      bytesToSize,
    };
  },
};
</script>

<style lang="scss" scoped>
.fileManager {
  .active {
    background-color: #0c60d2 !important;
    color: white !important;
  }
  .ctn {
    display: flex;
    .fileTable,
    .fileList {
      background: #ffffff;
      border-radius: 10px;
      padding: 20px;
      flex: 0.6;
    }
    .fileList {
      margin-left: 20px;
      padding: 0;
      flex: 0.4;
      .noData{
        text-align: center;
        color: #dad1d1;
        font-weight: bold;
        margin-top: 20px;
      }
      .tt {
        padding: 20px 20px 0px 20px;
      }
      .files {
        margin-top: 18px;
        border-top: 1px solid #f3f6f9;
        padding: 19px 0;
        .itd {
          padding: 0px 20px;
          .itd-item {
            width: 14%;
            height: 35px;
            background: #f2f6fc;
            border: 1px solid #ebeff7;
            border-radius: 5px;
            display: inline-block;
            text-align: center;
            line-height: 32px;
            cursor: pointer;
            padding: 0 5px;
          }
          .itd-item:not(:last-child) {
            margin-right: 13px;
          }
        }
        .fi {
          .fi-item:hover {
            background-color: #f4f8ff;
          }
          .fi-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            margin-top: 5px;
            .sub {
              > div {
                display: inline-block;
              }
              .sub-title {
                width: 220px;
                .title {
                  font-size: 16px;
                  font-family: FZLanTingHeiS-R-GB;
                  font-weight: bold;
                  color: #3a3f47;
                  margin-bottom: 20px;
                }
                span {
                  font-size: 14px;
                  font-family: FZLanTingHeiS-R-GB;
                  font-weight: 400;
                  color: #7f838a;
                }
              }
              img {
                margin-right: 18px;
                vertical-align: top;
              }
            }
            .opt {
              > img:first-child {
                margin-right: 20px;
              }
              img {
                cursor: pointer;
              }
            }
          }
        }
      }
    }
    .table {
      margin-top: 18px;
      border-top: 1px solid #f3f6f9;
      padding: 20px 0px;
      .opt {
        font-size: 14px;
        font-family: FZLanTingHeiS-R-GB;
        font-weight: 400;
        color: #0c60d2;
        cursor: pointer;
      }
      .opt:first-child {
        padding-right: 5px;
      }
      ::v-deep(.el-table th.el-table__cell) {
        background-color: #f3f5fa;
      }
      ::v-deep(.el-table tbody tr:hover > td) {
        background-color: #fdf6ec;
      }
    }
    .page {
      text-align: right;
    }
  }
  .tt {
    img,
    span {
      vertical-align: middle;
    }
    span {
      margin-left: 10px;
      font-size: 20px;
      font-family: FZLanTingHeiS-R-GB;
      font-weight: bold;
      color: #303133;
    }
  }

  .search {
    background: #ffffff;
    border-radius: 10px;
    padding: 15px 22px;
    margin-bottom: 20px;
    // overflow: auto;
    .hd {
      display: flex;
      justify-content: space-between;
    }
    .lab {
      .labelItem {
        display: inline-block;
        padding: 0px 10px;
        width: 6%;
        height: 36px;
        background: #f3f5fa;
        border: 1px solid #ebeff7;
        border-radius: 5px;
        text-align: center;
        line-height: 34px;
        margin-top: 15px;
        font-size: 16px;
        font-family: FZLanTingHeiS-R-GB;
        font-weight: bold;
        color: #3a3f47;
        cursor: pointer;
        position: relative;
        img {
          position: absolute;
          right: 5px;
          top: 10px;
        }
      }
      .labelItem:not(:last-child) {
        margin-right: 16px;
      }
    }
    .ss {
      display: flex;
      margin-top: 16px;
      ::v-deep(.el-input__inner) {
        background-color: #f2f6fc;
        border: 1px solid #ebeff7;
        border-radius: 5px;
        width: 100%;
        padding-right: 30px;
      }
      .ipt {
        width: 592px;
        margin-right: 16px;
        position: relative;
      }
      .iptSearch {
        position: absolute;
        right: 10px;
        top: 10px;
        cursor: pointer;
      }
    }
  }
}
</style>