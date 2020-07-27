package com.jt.service.lmpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.annotation.CacheFind;
import com.jt.mapper.UserMapper;
import com.jt.objectmapper.ObjectMapperUtil;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Service(timeout=5000)
public class DubboUserServicelmpl implements DubboUserService{
	
	@Autowired
	UserMapper userMapper;
	@Autowired(required=true)
	JedisCluster jedis;
	
	@Override
	@Transactional
	public Integer saveUser(User user) {
		user.setCreated(new Date());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		int insert = userMapper.insert(user);
		return insert;
	}

	//@CacheFind(key = "token",seconds = 604800)//7天
	@Override
	@Transactional
	public String  doLogin(User user) {
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
		User userDB= userMapper.selectOne(queryWrapper);
		if(userDB==null)
			return null;
		//修改登录时间
		User user2=new User().setId(userDB.getId());
		user2.setUpdated(new Date());
		userMapper.updateById(user2);
		String ticket = UUID.randomUUID().toString().replace("-", "");
		//脱敏处理
		userDB.setPassword("猜猜看？");
		String json = ObjectMapperUtil.toJson(userDB);
		jedis.setex(ticket, 7*24*3600,json );
		return ticket;
	}

}
