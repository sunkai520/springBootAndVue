import axios from "axios";


//获取bk列表
export function getBkList(params:any){
    return axios.post('/api/getBkList',params)
}

//获取单个bk信息
export function getBkInfo(params:any){
    return axios.post('/api/getBkInfo',params)
}
//获取关键词云
//获取tags列表
export function getTagsList(params: any) {
    return axios.post('/api/getTagList', params);
}
