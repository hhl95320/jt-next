package com.jt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ContentCategoryMapper;
import com.jt.mapper.ContentMapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Content;
import com.jt.pojo.ContentCategory;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITree;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	ContentMapper contentMapper;

	@Override
	public EsayUITable<Content> contentPage(Integer categoryId, Integer page, Integer rows) {
		Page<Content> mp = new Page<>(page,rows);
		QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("category_id", categoryId);
		mp= contentMapper.selectPage(mp, queryWrapper);
		
		return new EsayUITable<Content>((int)mp.getTotal(),mp.getRecords());
	}
	
	
	
	
}
