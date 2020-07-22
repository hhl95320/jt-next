package com.jt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.vo.SysResult;

@RestController
public class Testjson {

	
	@RequestMapping("/web/testJSONP")
	public JSONPObject jsonp(String callback) {
		User user=new User().
				setEmail("123@qq.com").
				setPassword("我是passwoed").
				setPhone("12345678900").
				setUsername("CGB").setId(11L);
		return new JSONPObject(callback, user);
	}
	@RequestMapping("/web/test")
	@ResponseBody
	public SysResult jsoncors() {
		System.out.println("执行了服务器端");
		User user=new User().
				setEmail("123@qq.com").
				setPassword("我是passwoed").
				setPhone("12345678900").
				setUsername("CGB").setId(11L);
		System.out.println(user);
		return SysResult.sucess(user);
	}
}
