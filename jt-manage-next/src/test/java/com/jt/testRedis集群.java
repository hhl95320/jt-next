package com.jt;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;

public class testRedis集群 {

	
	@SuppressWarnings("resource")
	@Test
	void testRdis() {
			HashSet<HostAndPort> set = new HashSet<HostAndPort>();
			
			set.add(new HostAndPort("192.168.126.129", 7001));
			set.add(new HostAndPort("192.168.126.129", 7002));
			set.add(new HostAndPort("192.168.126.129", 7003));
			set.add(new HostAndPort("192.168.126.129", 7004));
			
			set.add(new HostAndPort("192.168.126.129", 7005));
			set.add(new HostAndPort("192.168.126.129", 7000));

			JedisCluster cluster = new JedisCluster(set);
			cluster.set("cluster", "cluster集群准备好");
			System.out.println(cluster.get("cluster"));
		
	}
}
