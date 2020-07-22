package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageVo {
	private Integer error;//错误信息 0正确 1错误
	private String url;
	private Integer width;
	private Integer height;
	
	public static ImageVo  fail() {
		
		return new ImageVo(1,null,null,null);
	}
	public static ImageVo  success(String url) {
		
		return new ImageVo(0,url,null,null);
	}
	public static ImageVo  success(String url ,Integer width,Integer height) {
		
		return new ImageVo(0,url,width,height);
	}
	
}
