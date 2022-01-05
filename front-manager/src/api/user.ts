import axios from "axios";
import { extractTimeFormat } from "element-plus";

//登录接口
export function getUserList(params: any) {
    return axios.post('/api/getUserList', params);
}
//添加用户
export function addUser(params:any){
    return axios.post('/api/addUser',params)
}
//删除用户
export function delUser(params:any){
    return axios.post('/api/delUser',params)
}
//获取单条用户信息
export function getUserInfo(params:any){
    return axios.post('/api/getOneInfo',params)
}
//获取单条用户信息
export function updateUserInfo(params:any){
    return axios.post('/api/updateUser',params)
}