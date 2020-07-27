package com.jt.service;

import java.util.List;

import com.jt.pojo.Cart;


public interface DubboCartService {
	
	List<Cart> findCartsByUserId(Long userId);
	
	 Integer updateCartsByMergeId(Cart cart );
	 
	 Integer addCartsByMergeId(Cart cart);
	 
	 Integer deleteCartsByMergeId(Cart cart);
	 
	 List<Cart> findCartsListByUserId(Long userId);
}
