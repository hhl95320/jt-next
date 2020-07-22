package com.jt.service;

import com.jt.pojo.ItemParam;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITable1;


public interface ItemParamService {
	
	ItemParam findItemParamById(Long id);
	
	
	EsayUITable1 findItemParamPage(Integer page, Integer rows);
	
	
	Integer saveItemParam(ItemParam itemParam);
	
	Integer 	deleteItemParam(Long ...ids);
	
}
