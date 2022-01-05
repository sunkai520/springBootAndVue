import axios from "axios";

//创建bk
export function createBk(params: any) {
    return axios.post('/api/createBk', params);
}
//获取bk列表
export function getBkList(params:any){
    return axios.post('/api/getBkList',params)
}
//删除bk列表
export function delBk(params:any){
    return axios.post('/api/delBk',params)
}
//获取单个bk信息
export function getBkInfo(params:any){
    return axios.post('/api/getBkInfo',params)
}
//编辑修改bk信息
export function updateBk(params:any){
    return axios.post("/api/updateBk",params)
}