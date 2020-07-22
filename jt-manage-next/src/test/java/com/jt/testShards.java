package com.jt;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class testShards {

	
	
	@SuppressWarnings("resource")
	@Test
	void shards() {
		
	List<JedisShardInfo> shards = new ArrayList<>();
	//添加节点
	shards.add(new JedisShardInfo("192.168.126.129",6379));
	shards.add(new JedisShardInfo("192.168.126.129",6380));
	shards.add(new JedisShardInfo("192.168.126.129",6381));
	ShardedJedis shardedJedis = new ShardedJedis(shards)	;
	//创建分片对象 //该对象已通过一致性hash算法
	shardedJedis.set("shards", "redis shards准备好");
	System.out.println(shardedJedis.get("shards"));
	
	}
}
