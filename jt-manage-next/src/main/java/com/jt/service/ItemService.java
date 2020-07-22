package com.jt.service;

import java.util.List;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParamItem;
import com.jt.vo.EsayUITable;

public interface ItemService {
	/**
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	EsayUITable<Item> findItemPage(Integer page,Integer rows);
	/**
	 * 
	 * @param status
	 * @param ids
	 * @return
	 */
	 Integer updateStock(Integer status,Integer ...ids) ;
	 
	 /**
	  * 
	  * @param ids
	  * @return
	  */
	 Integer deleteItem( Integer ...ids) ;
	 /**
	  *保存商品表时   同时保存    商品规格与商品的关系表 和   商品描述表---需要采用事务处理机制
	  * @param item
	  * @param itemDesc
	  * @param itemParamItem
	  * @return
	  */
	 Integer insertItem(Item item,ItemDesc itemDesc,ItemParamItem itemParamItem);
	 
	 /**
	  *修改商品表时   同时保存    商品规格与商品的关系表 和   商品描述表---需要采用事务处理机制
	  * @param item
	  * @param itemDesc
	  * @param itemParamItem
	  * @return
	  */
	 Integer updateItem(Item item,ItemDesc itemDesc,ItemParamItem itemParamItem);
}
