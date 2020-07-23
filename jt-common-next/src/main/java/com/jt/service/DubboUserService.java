package com.jt.service;

import com.jt.vo.SysResult;


public interface DubboUserService {

	SysResult checkUser(String info,Integer type);
}
