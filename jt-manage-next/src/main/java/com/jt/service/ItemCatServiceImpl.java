package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.annotation.CacheFind;
import com.jt.mapper.ItemCatMapper;
import com.jt.mapper.ItemMapper;
import com.jt.objectmapper.ObjectMapperUtil;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.vo.EsayUITable;
import com.jt.vo.EsayUITree;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Service
@Slf4j
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	ItemCatMapper itemCatMapper;
	//@Autowired
	//Jedis jedis;

	//@CacheFind(key="queryItemName" )
	@Override
	public ItemCat queryItemName(Integer itemCatId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", itemCatId);
		ItemCat selectOne = itemCatMapper.selectOne(queryWrapper);
		
		return selectOne;
	}
	@Override
	public List<EsayUITree> findItemCatTree() {

		return itemCatMapper.findItemCatTree();
	}
	
	@CacheFind(key = "ItemCat")
	@Override
	public  List<EsayUITree> findItemCatTreeByParentId(Long parentId) {
		
	//	List<EsayUITree> list =new ArrayList<>();
//		if(jedis!=null && jedis.exists("ItemCat::"+parentId )) {
//			System.out.println("-----使用Redis缓存------");
//			long s=System.currentTimeMillis();
//			List object = ObjectMapperUtil.toObject(jedis.get("ItemCat::"+parentId), list.getClass());
//			long e=System.currentTimeMillis();
//			log.info("excute{}",e-s);
//			return object;
//		}
		long s=System.currentTimeMillis();
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id",parentId );
		
		 List<ItemCat> selectList = itemCatMapper.selectList(queryWrapper);
		 List<EsayUITree> trees=new ArrayList<>();
		 for(ItemCat iCat: selectList) {
			 Long id=iCat.getId();
			 String text=iCat.getName();
			 String state=iCat.getIsParent()?"closed":"open";
			 EsayUITree esay=new EsayUITree(id,text,state);
			 trees.add(esay);
		 } 
			//每60s刷新缓存
		 //jedis.setex("ItemCat::"+parentId, 1800,ObjectMapperUtil.toJson(trees));
		 //long e=System.currentTimeMillis();
			//log.info("excute{}",e-s);
		return trees;
	}
	@Override
	public ItemCat findItemCatById(Long id) {
		ItemCat selectById = itemCatMapper.selectById(id);
		return selectById;
	}
	
//	@Override
//	public List<EsayUITree> findItemCatTree() {
//		
//		return itemCatMapper.findItemCatTree();
//	}
	
	
	
	
	
	
	
}
