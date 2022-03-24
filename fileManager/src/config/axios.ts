import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios';
import { ElMessage } from "element-plus";
import router from '@/router';
import { isWhiteList } from './whiteForm'
//接口请求超时时间
axios.defaults.timeout = 50000
//请求打断函数
const requestConfig = (config: AxiosRequestConfig) => {
  if (!isWhiteList(config.url)) {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `bearer ${token}`;
    }
  }
  return config;
};
const requestError = (error: AxiosError) => {
  ElMessage({
    type: 'error',
    message: '接口请求失败(请检查电脑网络)',
  });
  return Promise.reject(error);
};
//响应打断函数
const responseConfig = (response: AxiosResponse) => {
  //响应数据异常处理
  if (response.data.code != 200) {
    if(response.data.code==6001){
      ElMessage({
        type: 'error',
        message: `token:(${response.data.msg})`,
      })
      router.replace("/login")
      return Promise.reject(`token异常`);
    }
    if(response.data.code==201){
      ElMessage({
        type: 'error',
        message: `${response.data.msg}`,
      })
      return Promise.reject(response.data.msg);
    }
    return Promise.reject(`接口响应异常`);
  }
  return response.data;
};
const responseError = (error: AxiosError) => {
  ElMessage({
    type: 'error',
    message: `接口响应失败(${error})`,
  });
  return Promise.reject(`接口响应异常${error}`)
};
//请求前
axios.interceptors.request.use(requestConfig, requestError);
//响应
axios.interceptors.response.use(responseConfig, responseError);
