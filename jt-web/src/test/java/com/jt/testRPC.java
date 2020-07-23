package com.jt;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jt.objectmapper.ObjectMapperUtil;
import com.jt.pojo.User;
import com.jt.vo.SysResult;

@SpringBootTest
public class testRPC {

	/**
	 * 1利用httpclient机制访问百度服务器
	 * 2步骤
	 * 
	 * 定义请求网址
	 * 定义httpclient工具对昂
	 * 定义请求类型
	 * 发起请求获取返回请求结果 ，根据状态码
	 * 校验返回值
	 * 获取返回值结果数据
	 * 
	 * 200 404 400 406  500 504
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void testhttp() throws ClientProtocolException, IOException {
		String url="http://www.baidu.com";
		url="http://manage.jt.com/web/test";
		CloseableHttpClient createDefault = HttpClients.createDefault();
		HttpGet get=new HttpGet(url);
		CloseableHttpResponse response = createDefault.execute(get);
		
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode==200) {
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity,"utf8");
			User user=(com.jt.pojo.User) ObjectMapperUtil.toObject(string, SysResult.class).getData();
			System.out.println(user);
		}	
		if(statusCode==400) {
			
		}
		if(statusCode==404) {
			
		}
		if(statusCode==406) {
			
		}
		if(statusCode==500) {
			
		}
		if(statusCode==504) {
			
		}
		else {
		}
			
		}
	
	@Test
		void tsettt() {
//			Class sClass=this.getClass();
//			System.out.println(sClass);
//			System.out.println(sClass.getClass());
//			System.out.println(sClass.getClass().getClass());
//			System.out.println(sClass.getClass().getClass().getClass());
//			
//			System.out.println(ClassLoader.getSystemClassLoader());
//			System.out.println(ClassLoader.getSystemClassLoader().getParent());
//			System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
		byte[] cc= {1,2,3,5,6,4,6,4};
		
	
			
		}
	
}
