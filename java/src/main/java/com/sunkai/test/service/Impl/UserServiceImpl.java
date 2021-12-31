package com.sunkai.test.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunkai.test.bean.User;
import com.sunkai.test.mapper.UserMapper;
import com.sunkai.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    public User getUser(String loginName) {
        return userMapper.getUser(loginName);
    }
    public Integer addUser(User user){return userMapper.insert(user);}

}
