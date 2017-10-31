package com.blog.currency;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// 在不同包的情況下springboot必须要配置扫描
@ComponentScan(basePackages = { "com.blog.rest", "com.blog.service", "com.blog.service.impl", "com.blog.cache" })
// 扫描mapper映射器
@MapperScan("com.blog.mapper")
public class StartSpringBoot {

	public static void main(String[] args) {
		SpringApplication.run(StartSpringBoot.class, args);
	}

}
