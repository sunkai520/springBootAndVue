import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios';
import { ElMessage } from "element-plus";
import router from '@/router';
const { isWhiteList } = require("./whiteForm.js")
//接口请求超时时间
axios.defaults.timeout = 5000
//请求打断函数
const requestConfig = (config: AxiosRequestConfig) => {

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

// 500	50001	数据库连接错误
// 404	50002	方法不存在
// 404	50003	控制器不存在
// 404	50004	模板不存在
// 411	50005	验证器异常（返回为验证不通过内容）
// 系统错误码	50006	返回系统错误提示
// 500	50007	语法等错误（后台代码错误）
// 500	51000	系统层面错误异常
// 用户登录类

// status（状体码）	code（自定义码）	msg（系统错误提示）
// 60000	50006	未登录或登录已过期
// 60001	50006	账号密码错误
// 60002	50006	账号暂停使用
// 60003	50006	请求中未携带Token
// 60004	50006	未查询到Token
// 60005	50006	该令牌位于黑名单宽限期列表中，紧此次有效，刷新token，并请保存新token
// 60006	50006	该令牌在黑名单中
// 60007	50006	该令牌位于黑名单宽限期列表中，紧此次有效，刷新token，并请保存新token
// 60008	50006	在（iat）发出的时间戳不能在将来使用
// 60009	50006	令牌已过期，无法再刷新,请重新登录
// 60010	50006	
// 60011	50006	无法创建token
// 60012	50006	无法解码令牌
// 60013	50006	无法验证令牌签名
// 60014	50006	
// 文件上传类型

// status（状体码）	code（自定义码）	msg（系统错误提示）
// 200	70000	上传失败
// 200	70001	文件类型不符合要求