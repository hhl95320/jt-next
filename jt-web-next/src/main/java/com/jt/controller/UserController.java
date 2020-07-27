package com.jt.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping({"/user/"})
public class UserController {

	/**
	 * 为了实现业务的功能拦截.html结尾的
	 * @return
	 */
	@Reference(check = false,loadbalance="leastactive")
	DubboUserService dubboUserService;
	@Autowired
	JedisCluster jedisCluster;
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	@RequestMapping("register")
	public String register() {
		return "register";
	}
	/**
	 * cookie特点
	 * 默认条件，只能在自己的网址下面进行展现，
	 * Domin 指定域名下数据共享
	 * path 只允许特定路径下，才可以获取cookie
	 *      一般设置/ 表示在任意路径下共享
	 * 一般不设置Doman cookie数据不会共享
	 * 
	 */
	@RequestMapping("doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {
		 dubboUserService.saveUser(user);

		return  SysResult.sucess();
	}
	@RequestMapping("doLogin")
	@ResponseBody
	public SysResult doLogin(User user ,HttpServletResponse response) {
		String stringticket = dubboUserService.doLogin(user);

		if (stringticket!=null) {

			Cookie cookie =new Cookie("JT_TICKET",stringticket);
			cookie.setPath("/");
			cookie.setMaxAge(7*24*3600);
			cookie.setDomain("jt.com");
			response.addCookie(cookie);
			return  SysResult.sucess();
		}
		return  SysResult.fail();
	}
	/**
	 * 
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null&&cookies.length>0) {//判断请求cookie是否被恶意清除
		for (Cookie c:cookies) {//判断存在cookie
			if(c.getName().equals("JT_TICKET")) {//判断是否浏览器存在指定的cookie
				String val=c.getValue();
				c.setMaxAge(0);//暂时不删除0，等关闭浏览器删除-1
				c.setDomain("jt.com");
				c.setPath("/");
				response.addCookie(c);//返回清除JT_TIKET cookie
	
				if(jedisCluster.exists(val)) {//判断是否redis key，在即清除redis
					jedisCluster.del(val);
//					jedisCluster.expire(val, 0);
				}
				break;
			}
		}
		}
		return  "redirect:/";//重定向原页面
		//return  "redirect:http://www.jt.com";//重定向原页面
	}
}
