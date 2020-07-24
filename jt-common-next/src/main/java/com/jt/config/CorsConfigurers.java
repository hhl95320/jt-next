package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurers implements WebMvcConfigurer{
	
	
	 @Override
	public void addCorsMappings(CorsRegistry registry) {
		
		 registry.addMapping("/**")
		 			 .allowedOrigins("*")
		 			 .allowedMethods("POST","GET")
		 			 .allowCredentials(true)
		 			 .maxAge(1800);//跨域请求时间
		//WebMvcConfigurer.super.addCorsMappings(registry);
	}

}
