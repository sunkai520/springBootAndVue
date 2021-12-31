package com.sunkai.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunkai.test.bean.Bklist;
import com.sunkai.test.bean.Result;
import com.sunkai.test.service.IBklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sunkai
 * @since 2021-12-31
 */
@RestController

public class BklistController {
    @Autowired
    IBklistService service;
    @RequestMapping(value = "/createBk",method = RequestMethod.POST)
    public Result createBk(@RequestBody Bklist bklist){
        Boolean bool = service.save(bklist);
        Result result = new Result();
        return  result;
    }
    @RequestMapping(value="/getBkList",method = RequestMethod.POST)
    public Result getBkList(@RequestBody Map obj){
        System.out.println(obj.get("userId"));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userid",obj.get("userId"));
        List list = service.list(queryWrapper);
        Result result = new Result();
        if(list!=null&&list.size()>0){
            result.setData(list);
        }
        return result;
    }
}
