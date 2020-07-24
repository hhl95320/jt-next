package com.jt.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors
@NoArgsConstructor
@AllArgsConstructor
public class SysResult  implements Serializable {

	
	private Integer status;
	private String  msg;
	private Object data;
	
	public SysResult(Integer status) {
		this.status = status;
	}
	//失败
	public  static SysResult fail() {
		return new SysResult(201,"业务调用失败",null);
	}
	//失败
	public  static SysResult fail(String msg) {
		return new SysResult(201,msg,null);
	}
	//成功
	public  static SysResult sucess() {
		return new SysResult(200,"业务调用成功",null);
	}
	//成功并传参给前端
	public  static SysResult sucess(Object data) {
		return new SysResult(200,"业务调用成功",data);
	}
	//成功告诉前端服务器信息，及数据
	public  static SysResult sucess(String msg,Object data) {
		return new SysResult(200,msg,data);
	}
}
