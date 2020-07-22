package com.jt.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.Content;
import com.jt.pojo.ContentCategory;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParamItem;
import com.jt.service.ContentCategoryService;
import com.jt.service.ContentService;
import com.jt.service.ItemCatService;
import com.jt.service.ItemDescService;
import com.jt.service.ItemParamItemService;
import com.jt.service.ItemParamService;
import com.jt.service.ItemService;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITree;
import com.jt.vo.JsonResult;
import com.jt.vo.SysResult;


@Controller
@RequestMapping({"/content/","/content/category/"})
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("list")
	@ResponseBody
	public List<ContentCategory>  list() {
		
		return contentCategoryService.contentCategoryObject();
	}
	@RequestMapping("query/list")
	@ResponseBody
	public EsayUITable<Content>  list(Integer categoryId,Integer page, Integer rows) {
		
		return contentService.contentPage(categoryId,page,rows);
	}
	

}


