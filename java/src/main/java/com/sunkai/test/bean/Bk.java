package com.sunkai.test.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("bklist")
public class Bk {
    private String title;
    private String des;
    private String code;
    private Integer userid;
    @TableId(type = IdType.AUTO)
    private Integer id;
}
