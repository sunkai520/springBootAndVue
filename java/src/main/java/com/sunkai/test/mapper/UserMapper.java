package com.sunkai.test.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunkai.test.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//    @Insert("insert into person(name,sex,age) values(#{name},#{sex},#{age})")
//    @Options(useGeneratedKeys = true,keyProperty = "id")
//    public void  insert(Person person);
@Select("select * from user where loginName=#{loginName}")
    User getUser(String loginName);
}
