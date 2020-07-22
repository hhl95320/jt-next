package com.jt.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@TableName("tb_item_cat")
@Data
@Accessors(chain = true)
public class ItemCat extends BasePojo{

	@TableId(type = IdType.AUTO)//主键自增
	private Long id;
	
	private Long parentId;
	private String  name;
	private Integer status;
	private Integer sortOrder;
	private Boolean isParent;//对于出现tinyint类型使用boolean

}
