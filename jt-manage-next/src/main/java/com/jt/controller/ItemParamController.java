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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParam;
import com.jt.pojo.ItemParamItem;
import com.jt.service.ItemCatService;
import com.jt.service.ItemDescService;
import com.jt.service.ItemParamItemService;
import com.jt.service.ItemParamService;
import com.jt.service.ItemService;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITable1;
import com.jt.vo.EsayUITree;
import com.jt.vo.JsonResult;
import com.jt.vo.SysResult;


@Controller
@RequestMapping({"/item/param/"})
public class ItemParamController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemCatService itemCatService;
	@Autowired
	private ItemDescService itemDescService;
	@Autowired
	private ItemParamItemService itemParamItemService;
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("list")
	@ResponseBody
	public EsayUITable1 list(Integer page, Integer rows) {
		return  itemParamService.findItemParamPage(page, rows);
	}
	@RequestMapping("query")
	@ResponseBody
	public SysResult itemcatid(Long itemcatid ) {
		
		return SysResult.sucess(itemParamService.findItemParamById(itemcatid));
	}
	
	@RequestMapping("save")
	@ResponseBody
	public SysResult save(ItemParam itemParam ) {
		
		 itemParamService.saveItemParam(itemParam);
		return SysResult.sucess();
	}
	@RequestMapping("delete")
	@ResponseBody
	public SysResult delete(Long ... ids ) {
		
		itemParamService.deleteItemParam(ids);
		return SysResult.sucess();
	}
	
	
	@RequestMapping("item/query/{id}")
	@ResponseBody
	public SysResult query(@PathVariable Long id) {

		return SysResult.sucess(itemParamItemService.findItemParamItemById(id));
	}
	

}


