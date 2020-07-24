package com.jt.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@TableName("tb_item_desc")
@Data
@Accessors(chain = true)
public class ItemDesc  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4378573699118826749L;
	@TableId//主键自增不添加
	private Long itemId;
	private String  itemDesc;
	private Date created;
	private Date updated;
}
