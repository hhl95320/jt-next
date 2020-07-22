package com.jt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemCatMapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITree;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	ItemDescMapper itemDescMapper;
	@Override
	public ItemDesc updatebeforeById(Long id) {
		Map<String, Object> map=new HashMap<>();
		map.put("item_id", id);
		
		ItemDesc itemDesc= new ItemDesc().setItemId(id); 
		QueryWrapper<ItemDesc> queryWrapper = new QueryWrapper<>(itemDesc);
		ItemDesc select = itemDescMapper.selectOne(queryWrapper);
		System.out.println(select);
		return select;
	}

	
	
	
}
