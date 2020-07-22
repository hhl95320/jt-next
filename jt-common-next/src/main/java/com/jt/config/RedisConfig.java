package com.jt.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

@Configuration//一般配置类都会与bean联用
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {

//动态获取节点信息
	@Value("${redis.nodes}")
	String nodestr;
	
	@Bean
	JedisCluster jedisCluster() {
		String[] nodesArr=nodestr.split(",");
		Set<HostAndPort> list=new HashSet<>();
		for(String c: nodesArr) {
			String ip=c.split(":")[0];
			Integer port=Integer.parseInt(c.split(":")[1]);
			list.add(new HostAndPort(ip,port));
		}
		
		return new JedisCluster(list); 
	}
	
	
	
	
	
	
	
	
	//分片
//	ShardedJedis shardedJedis() {
//		String[] nodesArr=nodestr.split(",");
//		List<JedisShardInfo> list=new ArrayList<>();
//		for(String c: nodesArr) {
//			String ip=c.split(":")[0];
//			Integer port=Integer.parseInt(c.split(":")[1]);
//			list.add(new JedisShardInfo(ip,port));
//		}
//		return new ShardedJedis(list); 
//	}
	
	
	
	//单台redis内存扩容
	
//	@Value("${redis.host}")
//	private String host;
//	@Value("${redis.port}")
//	private Integer port;
//	
//	@Bean
//	public Jedis jedis(){
//		return new Jedis(host, port);
//	}	
}
