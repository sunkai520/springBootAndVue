package com.sunkai.test.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    private String loginName;
    private String pwd;
    private Integer age;
    private Integer sex;
    private String phone;
    private String name;
    @TableId(type = IdType.AUTO)
    private Integer id;
}
