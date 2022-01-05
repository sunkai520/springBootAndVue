package com.sunkai.test;

import com.sunkai.test.bean.Bklist;
import com.sunkai.test.mapper.BklistMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {
    @Autowired
    BklistMapper bkListMapper;
    @Test
    void contextLoads() {
        Bklist bk = new Bklist();
        bk.setDes("测试数据1");
        bk.setTitle("测试");
        bk.setUserid(2);
       bkListMapper.insert(bk);
    }

}
