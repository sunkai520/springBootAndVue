package com.sunkai.test.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunkai.test.bean.Result;
import com.sunkai.test.bean.Tags;
import com.sunkai.test.service.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sunkai
 * @since 2022-01-10
 */
@RestController
public class TagsController {
    @Autowired
    ITagsService tagsService;
    @RequestMapping(value = "/createTags",method = RequestMethod.POST)
    public Result createBk(@RequestBody Tags tags){
        Boolean bool = tagsService.save(tags);
        Result result = new Result();
        return  result;
    }
    @RequestMapping(value="/getTagList",method = RequestMethod.POST)
    public Result getBkList(@RequestBody Map obj){
        String keyWord = obj.get("keyWord").toString();
        Result result = new Result();
        QueryWrapper<Tags> queryWrapper = new QueryWrapper<Tags>();
        queryWrapper.like("tagName",keyWord);
        List list = tagsService.list(queryWrapper);
        result.setData(list);
        return result;
    }
    @RequestMapping(value="/delTag",method = RequestMethod.POST)
    public Result delTag(@RequestBody Map map){
        Result result = new Result();
        Integer id = Integer.parseInt(map.get("id").toString());
        Boolean bool = tagsService.removeById(id);
        if(bool){
            result.setCode(200);
            result.setMsg("更新成功");
        }
        return result;
    }
    @RequestMapping(value = "/getTag",method = RequestMethod.POST)
    public  Result getBkInfo(@RequestBody Map map){
        Result result  = new Result();
        Integer id = Integer.parseInt(map.get("id").toString());
        Tags tag = tagsService.getById(id);

        if(tag!=null){
          result.setData(tag);
        }else{
            result.setCode(201);
            result.setMsg("抱歉,未找到对应文章");
        }
        return result;
    }
    @RequestMapping(value="/updateTag",method = RequestMethod.POST)
    public  Result updateUserInfo(@RequestBody Tags tags){
        Result result = new Result();
        Boolean bool =  tagsService.updateById(tags);
        if(bool){
            result.setCode(200);
            result.setMsg("更新成功");
        }
        return  result;
    }
}
