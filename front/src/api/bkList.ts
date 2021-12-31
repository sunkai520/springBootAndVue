import axios from "axios";

//创建bk
export function createBk(params: any) {
    return axios.post('/api/createBk', params);
}
//获取bk列表
export function getBkList(params:any){
    return axios.post('/api/getBkList',params)
}
