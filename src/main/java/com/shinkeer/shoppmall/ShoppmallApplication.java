package com.shinkeer.shoppmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.shinkeer.shoppmall")//扫描所有的类，查看每个类中的注解，spring管理本目录下的所有注解类
@EnableJpaRepositories(basePackages = "com.shinkeer.shoppmall.dao")//扫描jap的接口
@EnableTransactionManagement(proxyTargetClass = true)//开启事务
@EnableAutoConfiguration(exclude = {JpaRepositoriesAutoConfiguration.class})//开启自动配置，//禁止springboot自动加载持久化bean
@EntityScan(basePackages = "com.shinkeer.shoppmall.entity",basePackageClasses = Jsr310JpaConverters.class)//扫描entity类，Jsr310JpaConverters：对日期的转换处理

public class ShoppmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppmallApplication.class, args);
	}
}
