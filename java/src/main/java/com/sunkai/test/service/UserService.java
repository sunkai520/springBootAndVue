package com.sunkai.test.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunkai.test.bean.User;
public interface UserService extends IService<User> {
    User getUser(String loginName);
    Integer addUser(User user);
}
