package com.jt.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.ItemParam;
import com.jt.vo.ItemParamVo;


public interface ItemParamMapper extends BaseMapper<ItemParam>{
	
	List<ItemParamVo> findItemParamPage();
}
