package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.DubboUserService;
import com.jt.service.UserService;
import com.jt.vo.SysResult;


@RestController
@RequestMapping("/user/")
public class UserController {

	//@Autowired  check = false,
	@Reference(loadbalance="leastactive")
	DubboUserService dubboUserService;
//	UserService userService;
	
	@RequestMapping("/getMsg")
	public String msg() {
		 
		return "单点登录成功"; 
	}
	@RequestMapping("check/{info}/{type}")
	public JSONPObject check( @PathVariable String info ,@PathVariable Integer type
			,String callback) {
		SysResult checkUser = dubboUserService.checkUser(info,type);
		//System.out.println(1/0);
		return new JSONPObject(callback, checkUser);
	}
	
	
}
