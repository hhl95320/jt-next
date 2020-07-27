package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.objectmapper.ObjectMapperUtil;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;


@RestController
@RequestMapping("/user/")
public class UserController {

	//@Autowired  check = false,

	@Autowired
	UserService userService;
	@Autowired
	JedisCluster jedisCluster;
	
	@RequestMapping("/getMsg")
	public String msg() {
		 
		return "单点登录成功"; 
	}
	@RequestMapping("check/{info}/{type}")
	public JSONPObject check( @PathVariable String info ,@PathVariable Integer type
			,String callback) {
		SysResult checkUser = userService.checkUser(info,type);
		//System.out.println(1/0);
		return new JSONPObject(callback, checkUser);
	}
	/**
	 * 会先用户登录信息
	 * @param ticket
	 * @param callback
	 * @return
	 */
	@RequestMapping("query/{ticket}")
	public JSONPObject check( @PathVariable String ticket ,String callback) {
		if(ticket==null)
			return new JSONPObject(callback, SysResult.fail());
		if(!jedisCluster.exists(ticket))
			return new JSONPObject(callback, SysResult.fail());
		return new JSONPObject(callback, SysResult.sucess(jedisCluster.get(ticket)));
	}

	
	
}
