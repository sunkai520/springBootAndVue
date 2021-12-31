package com.sunkai.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.sunkai.test.bean.Result;
import com.sunkai.test.bean.User;
import com.sunkai.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//用户信息相关接口
public class UserList {
    @Autowired
    UserService userService;
    @RequestMapping(value="/getUserList",method = RequestMethod.POST)
    public Result getUserList(@RequestBody Map obj){
        Integer mpage = Integer.parseInt(obj.get("page").toString());
        Integer pageSize =Integer.parseInt(obj.get("pageSize").toString());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name",obj.get("keyWord"));
        queryWrapper.or();
        queryWrapper.like("phone",obj.get("keyWord"));
        Page page = new Page(mpage,pageSize);
//        List<User> list = userService.list(queryWrapper);
        IPage pageMaps = userService.pageMaps(page,queryWrapper);
        Long total = pageMaps.getTotal();
        List data = pageMaps.getRecords();
        Map map = new HashMap();
        map.put("total",total);
        map.put("data",data);
        Result result = new Result();
        result.setData(map);
        return result;
    }
    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public Result addUser(@RequestBody User user){
        user.setPwd("666666");
        Integer cout = userService.addUser(user);
        Result result = new Result();
        if(cout==1){
            result.setMsg("添加成功");
            result.setCode(200);
        }
        return result;
    }
    @RequestMapping(value = "/delUser",method = RequestMethod.POST)
    public Result delUser(@RequestBody Map map){
        Integer id = Integer.parseInt(map.get("id").toString());
        Result result = new Result();
        Boolean bool = userService.removeById(id);
        if(bool){
            result.setCode(200);
            result.setMsg("删除成功");
        }
        return result;
    }
    @RequestMapping(value="/getOneInfo",method = RequestMethod.POST)
    public  Result getOneInfo(@RequestBody Map obj){
        Integer id = Integer.parseInt(obj.get("id").toString());
        Result result = new Result();
        User user = userService.getById(id);
        if(user!= null){
            result.setCode(200);
            result.setData(user);
        }
        System.out.println(user);
        return  result;
    }
    @RequestMapping(value="/updateUser",method = RequestMethod.POST)
    public  Result updateUserInfo(@RequestBody User user){
        Result result = new Result();
        Boolean bool =  userService.updateById(user);
        if(bool){
            result.setCode(200);
            result.setMsg("更新成功");
        }
        return  result;
    }
}
