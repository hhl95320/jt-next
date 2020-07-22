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
import com.jt.mapper.ItemCatMapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.ContentCategory;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITree;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	ContentCategoryMapper contentcategoryMapper;

	@Override
	public List<ContentCategory> contentCategoryObject() {
		
		return contentcategoryMapper.selectList(null);
	}
	

	
	
	
}
