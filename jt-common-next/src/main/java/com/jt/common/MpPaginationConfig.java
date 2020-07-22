package com.jt.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration
public class MpPaginationConfig {

	@Bean
	PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
}
