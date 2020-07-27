package com.jt.aop;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.annotation.CacheFind;
import com.jt.objectmapper.ObjectMapperUtil;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;


@Aspect
@Component
@Slf4j
public class RedisCacheAspect {

	@Autowired(required=true)
	JedisCluster jedis;
	//ShardedJedis jedis;
	//Jedis jedis;
	
//	@Autowired
//	CacheFind c;
	
	//@Pointcut("bean(itemServiceImpl)")
	//@Pointcut("within(com.jt.*..*ServiceImpl)")
	//@Pointcut("execution(com.jt.*..*ServiceImpl.*(..))")
	//@Pointcut("@annotation(cacheFind)")
//	@Pointcut("@annotation(com.jt.annotation.CacheFind)")
	//void cacheRedis(CacheFind cacheFind) {}
	
//	@Before("cacheRedis()")
//	public void cacheRedisBefore() {
//		try {
//			System.out.println("我是前置通知开始");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("我是前置通知异常");
//		}
//	}
	
	@Around("@annotation(cacheFind)")
	public Object cacheRedisAround( ProceedingJoinPoint jp,CacheFind cacheFind) throws Throwable {
		Object object=null;
		try {
			      object=jp.proceed();

					Map map = keyCache(jp,cacheFind);
					String key=(String)map.get("key");
					int second=(int)map.get("second");
					System.out.println("key:"+key);
					if(key.equals("token")) {
				    if(second==-1)//没有设置过期时间默认30分钟
				    	  jedis.setex(key, 30*60,ObjectMapperUtil.toJson(object));
				    else //设置过期时间
				    jedis.setex(key, second, ObjectMapperUtil.toJson(object));	
					}
				 return object;
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
private Map keyCache(JoinPoint jp,CacheFind cacheFind) throws NoSuchMethodException, SecurityException, JsonProcessingException {
	 Class<? extends Object> class1 = jp.getTarget().getClass();
	 
	 
	MethodSignature ms=(MethodSignature)jp.getSignature();
	//第一种
	//Method declaredMethod = class1.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
	//CacheFind annotation = declaredMethod.getAnnotation(CacheFind.class);
	//String key = annotation.key()==null?"0": annotation.key();
	//第二种
	String key = cacheFind.key()==null?"0": cacheFind.key();
	int second = cacheFind.seconds();
	//int second = annotation.seconds();
	Map<String , Object> obj=new HashMap<>();
//	String keyend = jp.getArgs()[0].toString();
	String keyend=new ObjectMapper().writeValueAsString(jp.getArgs());
	System.out.println("当前访问类及方法："+class1.getName()+"."+ms.getName());
	obj.put("key", key+keyend);
	obj.put("second", second);
	obj.put("returnType", ms.getReturnType());
	return obj;
}

	
//	@After("cacheRedis()")
//	public void cacheRedisAfter() {
//		try {
//			System.out.println("我是后置通知开始");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("我是后置通知异常");
//		}
//	}
//	@AfterReturning("cacheRedis()")
//	public void cacheRedisAfterReturning() {
//		try {
//			System.out.println("我是最终通知开始");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("我是最终通知异常");
//		}
//	}
//	@AfterThrowing("cacheRedis()")
//	public void cacheRedisAfterThrowing() {
//		try {
//			System.out.println("我是后置异常通知开始");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("我是后置异常通知异常");
//		}
//	}
	
	
}
