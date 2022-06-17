import axios from "axios";
//创建评论
export function createComment(params){
    return axios.post("/api/createComment",params)
}
//获取评论列表
export function getCommentList(params){
    return axios.post("/api/getCommentsList",params)
}
