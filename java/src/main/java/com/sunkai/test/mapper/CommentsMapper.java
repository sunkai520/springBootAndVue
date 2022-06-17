package com.sunkai.test.mapper;

import com.sunkai.test.bean.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunkai
 * @since 2022-06-17
 */
@Mapper
public interface CommentsMapper extends BaseMapper<Comments> {

}
