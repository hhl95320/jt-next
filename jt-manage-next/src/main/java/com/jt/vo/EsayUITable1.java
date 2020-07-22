package com.jt.vo;

import java.util.List;

import com.jt.pojo.Item;
import com.jt.pojo.ItemParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EsayUITable1 {

	private Integer total;
	private List<ItemParamVo> rows;

}
