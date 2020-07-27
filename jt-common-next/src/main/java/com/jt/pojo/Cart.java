package com.jt.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_cart")
@Data
@Accessors(chain = true)
public class Cart extends BasePojo implements Serializable{


	private static final long serialVersionUID = 3853364142121823273L;
	@TableId(type = IdType.AUTO)//主键自增
	private Long id;
	private Long userId;
	private Long itemId;
	private String  itemTitle;
	private String  itemImage;
	private Long itemPrice;
	private Integer num;
}
