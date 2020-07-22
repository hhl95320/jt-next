package com.jt.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.vo.EsayUITree;

public interface ItemCatMapper extends BaseMapper<ItemCat>{
	
	List<EsayUITree> findItemCatTree();
}
