package com.sunkai.test.bean;

import lombok.Data;

@Data
public class Result {
    private Integer code=200;
    private Object data;
    private String msg="成功";
}
