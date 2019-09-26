package com.kenny.smart.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: MybatisConfig
 * Function:  MyBatis配置类
 * Date:      2019/9/20 10:52
 * @author Kenny
 * version    V1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.kenny.smart.mapper","com.kenny.smart.dao"})
public class MybatisConfig {
}
