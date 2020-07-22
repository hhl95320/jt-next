package com.jt.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性
@TableName("tb_content_category")
@Data
@Accessors(chain=true)
public class ContentCategory extends BasePojo{
	@TableId(type=IdType.AUTO)
	private Long id;				//商品id
	private Long parentId;				//代表一级分类id
	private String name;			//
	private Boolean status;		//'1正常2删除',
	private Boolean isParent;		//comment'1true0false',
	private Integer   sortOrder;			//商品价格 Long > dubbo

}
