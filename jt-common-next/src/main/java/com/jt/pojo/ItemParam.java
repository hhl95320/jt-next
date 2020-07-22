package com.jt.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@TableName("tb_item_param")
@Data
@Accessors(chain = true)
public class ItemParam extends BasePojo{

	@TableId(type = IdType.AUTO)//主键自增
	private Long id;
	private Long itemCatId;
	private String  paramData;
}
