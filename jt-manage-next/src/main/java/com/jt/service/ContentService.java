package com.jt.service;


import com.jt.pojo.Content;
import com.jt.vo.EsayUITable;


public interface ContentService {
	
	EsayUITable<Content> contentPage(Integer categoryId, Integer page, Integer rows);
	 
}
