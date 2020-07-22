package com.jt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.mapper.ItemParamItemMapper;
import com.jt.mapper.ItemParamMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParam;
import com.jt.pojo.ItemParamItem;
import com.jt.vo.EsayUITable;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	
	@Autowired
	private ItemParamItemMapper itemParamItemMapper;

	@Override
	public ItemParamItem findItemParamItemById(Long id) {
			
		ItemParamItem item=new ItemParamItem().setItemId(id);
		QueryWrapper<ItemParamItem> queryWrapper=new QueryWrapper<ItemParamItem>(item);
		
		ItemParamItem selectList = itemParamItemMapper.selectOne(queryWrapper);
		
		return selectList;
	}
	

}
