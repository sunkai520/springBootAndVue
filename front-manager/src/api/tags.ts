import axios from "axios";
//创建tags
export function createTags(params: any) {
    return axios.post('/api/createTags', params);
}
//获取tags列表
export function getTagsList(params: any) {
    return axios.post('/api/getTagList', params);
}
//删除tag
export function delTag(params: any) {
    return axios.post('/api/delTag', params);
}
//获取tag信息
export function getTag(params: any) {
    return axios.post('/api/getTag', params);
}
//更新tag
export function updateTag(params: any) {
    return axios.post('/api/updateTag', params);
}
