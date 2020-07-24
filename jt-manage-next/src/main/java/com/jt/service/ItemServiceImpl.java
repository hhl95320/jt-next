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
import com.jt.annotation.CacheFind;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.mapper.ItemParamItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParamItem;
import com.jt.vo.EsayUITable;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Autowired
	private ItemParamItemMapper itemParamItemMapper;
	
	//@CacheFind(key = "ItemPage")
	@Override
	public EsayUITable<Item> findItemPage(Integer page, Integer rows) {
		Page<Item> page2 = new Page<>(page, rows);
		QueryWrapper<Item> queryWrapper = new  QueryWrapper<>();
		queryWrapper.orderByDesc("updated");
		 page2 = itemMapper.selectPage(page2, queryWrapper);

		return new EsayUITable<Item>((int)page2.getTotal(),page2.getRecords());
	}
	
	@Override
	public Integer updateStock(Integer status,Integer ...ids) {
		//多表操作尽量少用in--
		//但也不能循环提交操作sql语句会影响数据库服务器压力与对象创建与销毁的
		Item item=new Item().setStatus(status);
				item.setUpdated(new Date());
				QueryWrapper<Item> queryWrapper = new QueryWrapper<Item>();
				queryWrapper.in("id", ids);
				int update=itemMapper.update(item, queryWrapper);
		
		return update;
	}

	@Transactional
	@Override
	public Integer deleteItem(Integer... ids) {

		List list=new ArrayList<>();
		list=Arrays.asList(ids);
		Integer row1=itemMapper.deleteBatchIds(list);
		
		QueryWrapper<ItemDesc> queryWrapper = new QueryWrapper<ItemDesc>();
		queryWrapper.in("item_id", ids);
		Integer row2=itemDescMapper.delete(queryWrapper);
		QueryWrapper<ItemParamItem> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.in("item_id", ids);
		Integer row3=itemParamItemMapper.delete(queryWrapper1);
		
		return row1;
	}
	/**
	 * 3各表
	 */
	@Transactional
	@Override
	public Integer insertItem(Item item,ItemDesc itemDesc,ItemParamItem itemParamItem) {
		int insert = itemMapper.insert(item);
		//.out.println("测试："+item.getId());
		
		itemDesc.setCreated(new Date()).setUpdated(itemDesc.getCreated()).setItemId(item.getId());
		int insert2 = itemDescMapper.insert(itemDesc);
		
		itemParamItem.setItemId(item.getId()).setCreated(new Date()).setUpdated(itemParamItem.getCreated());
		int insert3 = itemParamItemMapper.insert(itemParamItem);
		return insert;
	}
	/**
	 * 
	 */
	@Transactional
	@Override
	public Integer updateItem(Item item, ItemDesc itemDesc, ItemParamItem itemParamItem) {
		int insert = itemMapper.updateById(item);
		//.out.println("测试："+item.getId());
		
		itemDesc.setItemId(item.getId());
		QueryWrapper<ItemDesc> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("item_id", itemDesc.getItemId());
		int insert2 = itemDescMapper.update(itemDesc, queryWrapper);

		QueryWrapper<ItemParamItem> queryWrapper1 = new QueryWrapper<>();
		queryWrapper.eq("item_id", itemDesc.getItemId());
		int insert3 = itemParamItemMapper.update(itemParamItem, queryWrapper1);
		return insert;
	}

}
