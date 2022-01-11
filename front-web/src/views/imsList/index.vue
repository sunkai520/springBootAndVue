<template>
  <div class="list">
    
    <div class="midSection">
      <div class="search">
        <div style="margin-right: 20px">
          <el-input v-model="val" placeholder="请输入关键词" clearable/>
        </div>
        <el-button type="primary" size="mini" @click="search">查询</el-button>
      </div>
      <div class="table" v-loading="loading" v-if="tableData.length>0">
        <div class="item" v-for="(k, index) in tableData" :key="index" @click="itemDetail(k)" >
          <div class="card">
            <div class="imgg">
              <el-image :src="'api/' + k.imageUrl">
                <template #error>
                  <div class="image-slot">
                    <img :src="getImg(k)" />
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
            :page-sizes="[5,10, 20, 30, 40, 50]"
          >
          </el-pagination>
        </div>
      </div>
      <div v-else class="noData">
        暂无数据
      </div>
    </div>
    <div class="rightSection">
      <div v-if="cloudList.length>0">
        <div class="tt"><span>标签云</span></div>
        <CloudWork :data ="cloudList" @search="cloudSearch"></CloudWork>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, reactive, toRefs } from "vue-demi";
import pageHooks from "@/utils/pageHooks";
import { getBkList,getTagsList} from "@/api/bkList";
import router from "@/router";
import CloudWork from "@/components/cloudWork.vue"
export default {
  components:{CloudWork},
  setup() {
    const { pageParams, handlePageChange, handleSizeChange } =
      pageHooks(getList);
    async function getList() {
      states.loading=true;
      let res = await getBkList({
        userId: 1,
        page: states.page,
        pageSize: states.list_rows,
        keyWord: states.val,
      }).finally(()=>{
        states.loading=false
      });
      states.tableData = res.data.data;
      states.total = res.data.total;
      console.log(res, "res");
    }
    onMounted(() => {
      states.list_rows=5;
      getList();
      getTagList()

    });
    async function getTagList(){
      let res = await getTagsList({keyWord:""});
      states.cloudList = res.data
    }
    let states = reactive({
      ...toRefs(pageParams),
      val: "",
      tableData: [],
      loading:false,
      cloudList:[]
    });
    let itemDetail=(item)=>{
      router.push({name:"addBk",query:{id:item.id}})
    }
    let search = () => {
      states.page = 1;
      getList();
    };
   let cloudSearch=(item)=>{
     states.val=item.tagName;
     search()
   }
   let getImg = (item)=>{
     console.log(item,"item")
     let title = item.title;
     if(title.includes("java")){
       return require("../../../src/assets/img/java.webp")
     }
     if(title.includes("js")){
       return require("../../../src/assets/img/js.jpg")
     }
     if(title.includes("linux")){
       return require("../../../src/assets/img/linux.jpg")
     }
     if(title.includes("node")){
       return require("../../../src/assets/img/node.jpg")
     }
     if(title.includes("springboot")){
       return require("../../../src/assets/img/springboot.jpg")
     }
      if(title.includes("vue")){
       return require("../../../src/assets/img/vue.jpg")
     }
     let defaul = require("../../../src/assets/img/default.png")
     return defaul
   }
    return {
      ...toRefs(states),
      handlePageChange,
      handleSizeChange,
      itemDetail,
      search,
      cloudSearch,
      getImg
    };
  },
};
</script>

<style lang="scss" scoped>
.list {
  height: 100%;
  width: 100%;
  position: relative;
  .search {
    display: flex;
    padding: 10px;
    background-color: white;
    box-shadow: 1px 1px 10px #5f5f5f;
  }
  .midSection {
    width: 700px;
    height: 100%;
    padding: 15px;
    margin: auto;
  }
  .rightSection{
    position: absolute;
    right: 20px;
    top: 15px;
    overflow: hidden;
    background-color: white;
    padding: 10px;
    border-radius: 5px;
    box-shadow: 1px 1px 10px #5f5f5f;
    //  background: url("../../assets/img/bk.png") no-repeat;
    //  background-size: 100% 100%;
    .tt{
      border-left: 5px solid #2950b8;
      span{
        font-size: 18px;
        font-weight: 600;
        padding-left: 5px;
      }
    }
  }
  .noData{
    text-align: center;
    margin-top: 50px;
    color: gray;
    font-size: 18px;
    font-weight: 600;
  }
  .image-slot{
    height: 100%;
    width: 100%;
  }
  .table {
    width: 100%;
    margin-top: 10px;
    margin-top: 20px;

    .item {
      background-color: white;
      box-shadow: 1px 1px 10px #5f5f5f;
      padding: 10px;
      border-radius: 5px;
      cursor: pointer;
      .foot {
        background-color: #466dd6;
        width: 100%;
        padding-left: 20px;
        .shu {
          padding-left: 5px;
          padding-right: 5px;
          display: inline-block;
          color: white;
        }
        span {
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
          width: calc(100% - 140px);
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
      }
    }
    .item:not(:last-child) {
      margin-bottom: 20px;
    }
  }
  .page {
    margin-top: 10px;
    text-align: right;
    // background-color: white;
    // box-shadow: 1px 1px 10px #5f5f5f;
    // padding: 10px;
  }
}
</style>