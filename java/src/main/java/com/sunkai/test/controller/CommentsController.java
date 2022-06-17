package com.sunkai.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunkai.test.bean.Comments;
import com.sunkai.test.bean.Result;
import com.sunkai.test.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  评论
 * </p>
 *
 * @author sunkai
 * @since 2022-06-17
 */
@RestController
public class CommentsController {
    @Autowired
    ICommentsService commentsService;
    @RequestMapping(value = "/createComment",method = RequestMethod.POST)
    public Result createComment(@RequestBody Comments comments){
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comments.setCreateTime(dateFormat.format(d));
        Boolean bool = commentsService.save(comments);
        Result result = new Result();
        return result;
    }
    @RequestMapping(value = "/getCommentsList",method = RequestMethod.POST)
    public Result getCommentList(@RequestBody Map obj){
      QueryWrapper<Comments> queryWrapper = new QueryWrapper<Comments>();
      queryWrapper.eq("articleId",obj.get("articleId"));
      queryWrapper.orderByDesc("createTime");
      List list = commentsService.list(queryWrapper);
      Result result = new Result();
      result.setData(list);
      return result;
    }
}
