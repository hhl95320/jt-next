package com.jt.service.lmpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

@Service
public class UserServicelmpl implements UserService{
	
	@Autowired
	UserMapper userMapper;
	private static Map<Integer,String> paramMap;
	static {
		paramMap=new HashMap<>();
		paramMap.put(1, "username");
		paramMap.put(2, "phone");
		paramMap.put(3, "email");
	}
	@Override
	public SysResult checkUser(String info, Integer type) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(paramMap.get(type), info);
		List<User> list = userMapper.selectList(queryWrapper);
		
		
		if(list==null || list.size()==0) {
			return SysResult.sucess();
		}
		return SysResult.sucess(list);
	}

}
