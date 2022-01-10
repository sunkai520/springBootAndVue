//package com.sunkai.test;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import org.junit.jupiter.api.Test;
//public class TestAutoGeneartor {
//    /**
//     * 代码生成器
//     * */
//    @Test
//    void autoGeneartor(){
//        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/bk?serverTimezone=UTC&characterEncoding=utf-8", "root", "12345678")
//                .globalConfig(builder -> {
//                    builder.author("sunkai") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("F://workSpace//springBootAndVue//java//src//main//java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.sunkai.test") // 设置父包名
//                            .entity("bean"); // 更改实体类文件名若不更改默认生成entriy
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("tags")//配置依照那个表进行逆向生成代码
//                    .controllerBuilder().enableRestStyle()//开启@restController注解若不配置默认为controller
//                    .entityBuilder().enableLombok()//开启@bean邮lombok管理若没有会生成setter getter方法
//                    .mapperBuilder().enableMapperAnnotation();//mapper开启@mapper注解
//                })
//                .templateEngine(new FreemarkerTemplateEngine()).templateConfig(builder -> {
//                    builder.mapperXml(null);//因为使用mybatias-plus所以不需要生成xml文件
//        })// 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//
//    }
//}
