<template>
  <div class="comments">
    <el-scrollbar class="content">
      <div class="commentsList" v-if="commentList.length>0">
        <div class="comment-item" v-for="(item,index) in commentList" :key="index">
          <div class="item-title">
            <div class="name">{{item.name}}</div>
            <div class="date">{{item.createTime}}</div>
          </div>
          <div class="item-content">{{item.content}}</div>
        </div>
      </div>
      <div v-else class="nodata">
        暂无评论,快点参与讨论吧!~~~
      </div>
    </el-scrollbar>
  </div>
</template>
<script>
export default {
  props: {
    /**
     *结构[{name:"xxx",content:"xxx",id:"xxx",createTime:"xxxx"}] 
     *
     */
    commentList: {
      type: Array,
      default: () => [], 
    },
  },
  setup() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
.comments {
  width: 100%;
  height: 100%;
  background-color: white;
  box-shadow: 0 0 5px darkgrey;
  border-radius: 10px;
  .content {
    height: 100%;
    width: 100%;
    .nodata{
        color: darkgrey;
        text-align: center;
        margin-top: 200px;
    }
    .commentsList {
      padding: 10px;
      .comment-item {
        border-bottom: 1px solid #a9a9a947;
        padding-bottom: 20px;
        .item-title {
          display: flex;
          justify-content: space-between;
          .name {
            font-weight: bold;
            font-size: 16px;
          }
          .date {
            color: darkgrey;
            font-size: 14px;
          }
        }
        .item-content {
          margin-top: 10px;
        }
      }
      .comment-item:not(:first-child) {
        margin-top: 10px;
      }
    }
  }
}
</style>