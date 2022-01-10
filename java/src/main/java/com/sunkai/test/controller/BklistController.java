package com.sunkai.test.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunkai.test.bean.Bklist;
import com.sunkai.test.bean.Result;
import com.sunkai.test.service.IBklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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
    IBklistService iBklistService;
    @RequestMapping(value = "/createBk",method = RequestMethod.POST)
    public Result createBk(@RequestBody Bklist bklist){
        //处理前端传过来的数组
//        bklist.setTags(JSONObject.toJSONString(bklist.getTagss()));
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        bklist.setDate(dateFormat.format(d));
        bklist.setVisitor(0);
        Boolean bool = iBklistService.save(bklist);
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
        queryWrapper.orderByDesc("date");
        Page page = new Page(mpage,pageSize);
        IPage pageMaps = iBklistService.pageMaps(page,queryWrapper);
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
        Boolean bool = iBklistService.removeById(id);
        if(!bool){
            result.setCode(201);
            result.setMsg("删除失败!");
        }
        return result;
    }
    @RequestMapping(value = "/getBkInfo",method = RequestMethod.POST)
    public  Result getBkInfo(@RequestBody Map map){
        Result result  = new Result();
        Integer id = Integer.parseInt(map.get("id").toString());
        Bklist bklist = iBklistService.getById(id);

        if(bklist!=null){
            if(bklist.getText()==null){
                bklist.setText("");
            }
//            bklist.setTagss(JSONObject.parse(bklist.getTags()));
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
//        bklist.setTags(JSONObject.toJSONString(bklist.getTagss()));
        Boolean bool =  iBklistService.updateById(bklist);
        if(bool){
            result.setCode(200);
            result.setMsg("更新成功");
        }
        return  result;
    }
}
