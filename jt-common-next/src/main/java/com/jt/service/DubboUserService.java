package com.jt.service;

import com.jt.pojo.User;
import com.jt.vo.SysResult;


public interface DubboUserService {
	
	Integer saveUser(User user);
	
	String doLogin(User user);
	
}
