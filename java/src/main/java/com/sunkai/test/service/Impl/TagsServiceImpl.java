package com.sunkai.test.service.impl;

import com.sunkai.test.bean.Tags;
import com.sunkai.test.mapper.TagsMapper;
import com.sunkai.test.service.ITagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunkai
 * @since 2022-01-10
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {

}
