package com.jt.webMvcConfig;

import org.apache.ibatis.javassist.runtime.Inner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 图片本绝对路径与虚拟地址的映射

 *
 */
@Configuration
public  class WebMvcConfig implements WebMvcConfigurer{
	 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//和页面有关的静态目录都放在项目的static目录下
        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/upload");
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
		//注意前面要加file,不然是访问不了的
		 //registry.addResourceHandler("/showImg/**").addResourceLocations("file:d:/gangtong/upload/drugferment/");
		//registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/upload/");
		registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/CGB4work/jt-next/jt-manage-next/src/main/resources/upload/");
	}
}
