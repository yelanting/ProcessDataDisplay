package com.tianque.datadisplay.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author : Administrator
 * @since : 2019年3月8日 上午9:55:17
 * @see :
 */
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
		        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
		        .maxAge(168000).allowedHeaders("*").allowCredentials(true);
	}
}
