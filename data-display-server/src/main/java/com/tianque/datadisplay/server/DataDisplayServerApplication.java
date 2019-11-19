package com.tianque.datadisplay.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;

@EntityScan(value = "com.tianque.datadisplay.server.model")
@MapperScan(basePackages = { "com.tianque.datadisplay.server.mapper" })
@ServletComponentScan
@SpringBootApplication
public class DataDisplayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataDisplayServerApplication.class, args);
	}
}
