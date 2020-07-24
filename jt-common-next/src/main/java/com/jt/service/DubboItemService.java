package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

@Service
public interface DubboItemService {

	Item findItemById(Long itemId);
	
	ItemDesc findItemDescById(Long itemId);
}
