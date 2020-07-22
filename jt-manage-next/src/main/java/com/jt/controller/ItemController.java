package com.jt.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.objectmapper.ObjectMapperUtil;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParamItem;
import com.jt.service.ItemCatService;
import com.jt.service.ItemDescService;
import com.jt.service.ItemParamItemService;
import com.jt.service.ItemParamService;
import com.jt.service.ItemService;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITree;
import com.jt.vo.JsonResult;
import com.jt.vo.SysResult;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;


@Controller
@Slf4j
@RequestMapping({"/item/","/item/cat","/item/query/item/"})//"/item/param/"
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemCatService itemCatService;
	@Autowired
	private ItemDescService itemDescService;
	
	@RequestMapping("query")
	@ResponseBody
	public EsayUITable<Item> query(Integer page, Integer rows) {
		return itemService.findItemPage(page, rows);
	}
	
	@RequestMapping("queryItemName")
	@ResponseBody
	public String  queryItemName(Integer itemCatId) {
		
		return itemCatService.queryItemName(itemCatId).getName();
	}
	/**
	 * 两种方式
	 *1  一次获取全部树形结构
	 *   （通过高级映射，适用于数据量小）
	 *2  异步请求多次点击获取，
	 */

	@RequestMapping("list")
	@ResponseBody
	public List<EsayUITree>  list( @RequestParam(value = "id" ,defaultValue = "0") Long parentId) {
		
		//return itemCatService.findItemCatTree();
		
		 List<EsayUITree> list = itemCatService.findItemCatTreeByParentId(parentId);
		
		return list;
	}
	
	/**
	 * 添加商品信息
	 * @param item
	 * @return  Object[] itemParams
	 */
	@RequestMapping("save")
	@ResponseBody
	public SysResult  save(Item item ,ItemDesc itemDesc,String itemParams) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		
		ItemParamItem itemParamItem=new ItemParamItem().setParamData(itemParams);
		itemService.insertItem(item,itemDesc,itemParamItem);
		
		return SysResult.sucess();
	}
	@RequestMapping("update")
	@ResponseBody
	public SysResult  update(Item item ,ItemDesc itemDesc,String itemParams) {
		item.setUpdated(new Date());
		ItemParamItem itemParamItem=new ItemParamItem().setParamData(itemParams);
				itemParamItem.setUpdated(item.getUpdated());
				itemDesc.setUpdated(item.getUpdated());
		itemService.updateItem(item,itemDesc,itemParamItem);
		
		return SysResult.sucess();
	}
	@RequestMapping("desc/{id}")
	@ResponseBody
	public SysResult  desc(@PathVariable Long id) {
		
		ItemDesc updatebeforeById = itemDescService.updatebeforeById(id);
		
		return SysResult.sucess(updatebeforeById);
	}
	/**
	 * restful风格解决上架，下架--减低代码量
	 * @param ids
	 */
	@RequestMapping("updateStatus/{status}")
	@ResponseBody
	public SysResult  instock(@PathVariable Integer status,Integer ...ids) {
		itemService.updateStock(status, ids);
		return  SysResult.sucess();
	}
//	@RequestMapping("instock")
//	@ResponseBody
//	public SysResult  instock(Integer ...ids) {
//		itemService.updateStock(2, ids);
//		return  SysResult.sucess();
//	}

//	@RequestMapping("reshelf")
//	@ResponseBody
//	public SysResult  reshelf(Integer ...ids) {
//		itemService.updateStock(1, ids);
//		return SysResult.sucess();
//	}
	
	@RequestMapping("delete")
	@ResponseBody
	public SysResult  delete(Integer ...ids) {
		itemService.deleteItem( ids);
		return  SysResult.sucess();

	}
	

}


