package com.lxr.carsystem;

import com.alibaba.druid.pool.DruidDataSource;
import com.lxr.carsystem.config.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@EnableConfigurationProperties({DataSourceConfig.class})
public class CarSystemApplication {

	@Autowired
	DataSourceConfig dataSourceConfig;

	public static void main(String[] args) {
		SpringApplication.run(CarSystemApplication.class, args);
	}

	@Bean
	public DataSource getDataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(dataSourceConfig.getUrl());
		dataSource.setUsername(dataSourceConfig.getUsername());
		dataSource.setPassword(dataSourceConfig.getPassword());
		return dataSource;
	}

}

