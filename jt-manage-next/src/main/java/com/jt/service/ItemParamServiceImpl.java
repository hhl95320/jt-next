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
import com.github.pagehelper.PageHelper;
import com.jt.mapper.ItemCatMapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.mapper.ItemParamItemMapper;
import com.jt.mapper.ItemParamMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParam;
import com.jt.pojo.ItemParamItem;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITable1;
import com.jt.vo.ItemParamVo;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private ItemParamMapper itemParamMapper;
	@Autowired
	private ItemCatMapper   itemCatMapper;

	@Override
	public ItemParam findItemParamById(Long id) {
			
		ItemParam item=new ItemParam().setItemCatId(id);
		QueryWrapper<ItemParam> queryWrapper=new QueryWrapper<ItemParam>(item);
		
		ItemParam selectList = itemParamMapper.selectOne(queryWrapper);
		
		return selectList;
	}

	@Override
	public EsayUITable1 findItemParamPage(Integer page, Integer rows) {
		
//		Page<ItemParam> mp = new Page<>(page, rows);
//		
//		QueryWrapper<ItemParam> queryWrapper = new QueryWrapper<>();
//		queryWrapper.orderByDesc("updated");
//		mp=itemParamMapper.selectPage(mp, queryWrapper);
		com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(page, rows);
		List<ItemParamVo> findItemParamPage = itemParamMapper.findItemParamPage();
		
		return new EsayUITable1((int)startPage.getTotal(),findItemParamPage);
		
	}

	@Override
	public Integer saveItemParam(ItemParam itemParam) {
		
		int insert = itemParamMapper.insert(itemParam);
		return insert;
	}

	@Override
	public Integer deleteItemParam(Long... ids) {
		List list=new ArrayList<>();
		list=Arrays.asList(ids);
		Integer row=itemParamMapper.deleteBatchIds(list);
		return row;
	}
	

}
