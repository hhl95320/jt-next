package com.jt.service;

import java.util.List;

import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITree;

public interface ItemCatService {
	
	 ItemCat queryItemName(Integer itemCatId) ;
	 
	 List<EsayUITree> findItemCatTree();
	 
	 List<EsayUITree> findItemCatTreeByParentId(Long parentId);
	 
	 ItemCat  findItemCatById(Long id);
}
