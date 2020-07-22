package com.jt.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性
@TableName("tb_content")
@Data
@Accessors(chain=true)
public class Content extends BasePojo{
	@TableId(type=IdType.AUTO)
	private Long id;				//
	private Long categoryId;				//代表一级分类id
	private String title;			//
	private String subTitle;			// '例如：价格显示',
	private String titleDesc;			//
	private String url;			//
	private String pic;			//
	private String pic2;			//
	private String content;			//comment '活动页',


}
