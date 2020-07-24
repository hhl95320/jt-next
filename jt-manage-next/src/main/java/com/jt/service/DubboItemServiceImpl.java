package com.jt.service;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.mapper.ItemParamItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

@com.alibaba.dubbo.config.annotation.Service
public class DubboItemServiceImpl implements DubboItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public Item findItemById(Long itemId) {
		
		return itemMapper.selectById(itemId);
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectById(itemId);
	}


}
