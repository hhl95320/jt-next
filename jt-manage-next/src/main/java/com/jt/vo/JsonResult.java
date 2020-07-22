package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {

	private Integer status;
	private String  msg;
	private Object data;
	public JsonResult(Integer status) {
		this.status = status;
	}
	
}
