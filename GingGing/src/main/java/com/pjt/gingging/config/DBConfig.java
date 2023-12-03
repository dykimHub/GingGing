package com.pjt.gingging.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.pjt.gingging.model.dao")
public class DBConfig {

}
