package com.jt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.tomcat.util.digester.ArrayStack;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.util.ConcurrentBag;
import com.zaxxer.hikari.util.ConcurrentBag.IConcurrentBagEntry;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.params.SetParams;

public class testRedis {

	/**
	 * spring整合redis
	 * 报错FAQ:
	 * 检查redis配置文件是否正确
	 * 检查redis启动是否
	 * 检查防火墙开关
	 */
	
	@Test
	void tsetsSpring01() {
		//创建jedis对象 --主机 端口号
		Jedis jedis = new Jedis("192.168.126.129",6379);
		//操作redis--如果是字节数组需要将数据序列化与反序列化
		jedis.set("a", "aa");
		System.out.println(jedis.get("a"));
		
	}
	@Test
	void tsetsSpring02() {
		//创建jedis对象 --主机 端口号
		Jedis jedis = new Jedis("192.168.126.129",6379);
	if(jedis.exists("a"))
		jedis.set("a", "11");
	
	System.out.println(jedis.get("a"));
		
	}
	@Test
	void tsetsSpring03() {
		//创建jedis对象 --主机 端口号
		Jedis jedis = new Jedis("192.168.126.129",6379);
		jedis.flushDB();
	
			jedis.setnx("a", "11");
		jedis.setnx("a", "12");
		jedis.setnx("a", "13");
		
		System.out.println(jedis.get("a"));
		
	}
	@Test
	void tsetsSpring04() throws InterruptedException {
		//创建jedis对象 --主机 端口号
		Jedis jedis = new Jedis("192.168.126.129",6379);
		jedis.flushDB();
		
		jedis.set("a","aaa");
		//程序报错将永不超时
		jedis.expire("a", 2);
		
		Thread.sleep(2000);
		System.out.println(jedis.get("a")+jedis.ttl("a"));
		
		jedis.setex("a",2,"aaa");
		//程序报错将永不超时
		//setex保证赋值和添加超时时间原子性操作
		Thread.sleep(2000);
		System.out.println("赋值与超时间原子性操作");
		System.out.println(jedis.get("a")+jedis.ttl("a"));
	}


	@SuppressWarnings("resource")
	@Test
	void tsetsSpring05() throws InterruptedException {
//		//创建jedis对象 --主机 端口号
//		Jedis jedis = new Jedis("192.168.126.129",6379);
//		jedis.flushDB();
//		SetParams params = new SetParams();
//		params.ex(2);
//		jedis.set("a","aaa",params);
//		jedis.set("a","bb",params);
//		
//		System.out.println(jedis.get("a"));
		
		
	}

@Test
void tsetsSpring06() throws InterruptedException {
	//创建jedis对象 --主机 端口号
	Jedis jedis = new Jedis("192.168.126.129",6379);
	jedis.flushDB();
	
	jedis.hset("user", "id", "1");
	jedis.hset("user", "name", "tomcat");
	jedis.hset("user", "addr", "aps");
	System.out.println(jedis.hgetAll("user"));
	System.out.println(jedis.hvals("user"));
	System.out.println(jedis.hkeys("user"));
	System.out.println(jedis.hget("user", "id"));
	//得到是 map对象
	//hash主要用于存取一组类型数据
}
@Test
void tsetsSpring07() throws InterruptedException {
	//创建jedis对象 --主机 端口号
	Jedis jedis = new Jedis("192.168.126.129",6379);
	jedis.flushDB();
	
	Transaction transaction = jedis.multi();
	transaction.set("a", "aa");
	transaction.set("b", "bb");
	transaction.set("c", "cc");
	//System.out.println(jedis.get("a")+"---------");
	transaction.exec();
	System.out.println(jedis.get("a")+"---");
	System.out.println(jedis.get("b")+"----");
	System.out.println(jedis.get("c")+"---");
}


}

