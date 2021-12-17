import axios from "axios";

//登录接口
export function login(params: any) {
    return axios.post('/api/login', params);
}
