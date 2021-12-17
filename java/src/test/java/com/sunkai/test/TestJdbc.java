package com.sunkai.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestJdbc {
    @Autowired
    public DataSource dataSource;
    @Autowired
    public JdbcTemplate jt;
    @Test
    void testDataSource(){
        List<Map<String, Object>> maps = jt.queryForList("select * from user");
        System.out.println(maps);
    }
}
