package com.sunkai.test.controller;

import com.sunkai.test.bean.Result;
import com.sunkai.test.bean.User;
import com.sunkai.test.jwtUtils.JwtUtils;
import com.sunkai.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class login {
    @Autowired
    UserService userService;
    @ResponseBody
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result getUser(@RequestBody Map obj){
        Result result = new Result();

        User user1 =  userService.getUser(obj.get("loginName").toString());
        if(user1==null){
            result.setCode(201);
            result.setMsg("登录失败,账号不存在");
            return result;
        }
        String pwd = user1.getPwd();
        if(!pwd.equals(obj.get("pwd"))){
            result.setCode(201);
            result.setMsg("登录失败,密码不正确。");
            return result;
        }else{
            Map<String,String> map = new HashMap<>();
            map.put("loginName",user1.getLoginName());
            map.put("age",user1.getAge().toString());
            String token = JwtUtils.createJwt(map);
            Map keyValues=new HashMap<>();
            keyValues.put("token",token);
            result.setData(keyValues);
            result.setCode(200);
            result.setMsg("登录成功");
            return result;
        }
    }
}
