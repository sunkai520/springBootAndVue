import axios from "axios";
//多文件上传
export function upload(formaData:any){
    return axios.post('/api/upload',formaData,{ headers: { 'Content-Type': 'multipart/form-data'} })
}
//创建文件
export function createFile(params:any){
    return axios.post('/api/createFile',params)
}
//获取文件列表
export function getFileList(params:any){
    return axios.post('/api/getFileList',params)
}
//删除文件
export function removeFile(params:any){
    return axios.post("/api/delFile",params)
}
//获取具体文件列表
export function getFfList(params:any){
    return axios.post("/api/getFfList",params)
}
//删除列表
export function removeList(params:any){
    return axios.post("/api/removeList",params)
}
//获取tags
export function getTagList(){
    return axios.post("/api/getTagList")
}
//删除tags
export function delTag(params){
    return axios.post("/api/delTag",params)
}
//增加tags
export function addTags(params){
    return axios.post("/api/createTags",params)
}
//增加tags
export function upDateList(params){
    return axios.post("/api/upDateList",params)
}
//d登录接口
export function login(params){
    return axios.post("/api/login",params)
}
//下载文件
export function downLoadFile(params){
    return axios.post("/api/downLoadFile",params)
}