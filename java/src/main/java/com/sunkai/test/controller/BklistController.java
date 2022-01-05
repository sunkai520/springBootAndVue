package com.sunkai.test.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunkai.test.bean.Bklist;
import com.sunkai.test.bean.Result;
import com.sunkai.test.service.IBklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
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
        bklist.setDate(new Date());
        bklist.setVisitor(0);
        Boolean bool = service.save(bklist);
        Result result = new Result();
        return  result;
    }
    @RequestMapping(value="/getBkList",method = RequestMethod.POST)
    public Result getBkList(@RequestBody Map obj){
        System.out.println(obj.get("userId"));
        Integer mpage = Integer.parseInt(obj.get("page").toString());
        Integer pageSize =Integer.parseInt(obj.get("pageSize").toString());
        String keyWord = obj.get("keyWord").toString();
        QueryWrapper<Bklist> queryWrapper = new QueryWrapper<Bklist>();
        queryWrapper.eq("userid",obj.get("userId"));
//        queryWrapper.like("title",keyWord);
//        queryWrapper.or();
//        queryWrapper.like("des",keyWord);
        queryWrapper.and(wrapper->wrapper.like("title",keyWord).or().like("des",keyWord));
        Page page = new Page(mpage,pageSize);
        IPage pageMaps = service.pageMaps(page,queryWrapper);
        Long total = pageMaps.getTotal();
        List data = pageMaps.getRecords();
        Map map = new HashMap();
        map.put("total",total);
        map.put("data",data);
//        List list = service.list(queryWrapper);
        Result result = new Result();
        result.setData(map);
        return result;
    }
    @RequestMapping(value="/delBk",method = RequestMethod.POST)
    public Result delBk(@RequestBody Map map){
        Result result = new Result();
        Integer id = Integer.parseInt(map.get("id").toString());
        Boolean bool = service.removeById(id);
        if(!bool){
            result.setCode(201);
            result.setMsg("删除失败!");
        }
        return result;
    }
    @RequestMapping(value = "getBkInfo",method = RequestMethod.POST)
    public  Result getBkInfo(@RequestBody Map map){
        Result result  = new Result();
        Integer id = Integer.parseInt(map.get("id").toString());
        Bklist bklist = service.getById(id);

        if(bklist!=null){
            if(bklist.getText()==null){
                bklist.setText("");
            }
            result.setData(bklist);
        }else{
            result.setCode(201);
            result.setMsg("抱歉,未找到对应文章");
        }
      return result;
    }
    @RequestMapping(value="/updateBk",method = RequestMethod.POST)
    public  Result updateUserInfo(@RequestBody Bklist bklist){
        Result result = new Result();
        Boolean bool =  service.updateById(bklist);
        if(bool){
            result.setCode(200);
            result.setMsg("更新成功");
        }
        return  result;
    }
}
