package com.sunkai.test.service.impl;

import com.sunkai.test.bean.Comments;
import com.sunkai.test.mapper.CommentsMapper;
import com.sunkai.test.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunkai
 * @since 2022-06-17
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

}
