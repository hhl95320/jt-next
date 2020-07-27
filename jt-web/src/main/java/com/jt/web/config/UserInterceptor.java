package com.jt.web.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jt.objectmapper.ObjectMapperUtil;
import com.jt.pojo.User;

import redis.clients.jedis.JedisCluster;
//mvc提供的拦截器的接口
@Configuration
public class UserInterceptor implements HandlerInterceptor {
/**
 * true放行
 * false拦截一般配置重定向使用
 */
	
	@Autowired
	JedisCluster jedisCluster;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0)
		for(Cookie c:cookies) {
			if(c.getName().equals("JT_TICKET")) {
				String val=c.getValue();
				if(jedisCluster.exists(val)) {
					User user = ObjectMapperUtil.toObject(jedisCluster.get(val), User.class);
					request.setAttribute("JT_USER", user);
					return true;
				}
			}
		}
		
		response.sendRedirect("/user/login.html");
		return false;
	}
}
