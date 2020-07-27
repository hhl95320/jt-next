package com.jt.service.lmpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import com.jt.service.DubboCartService;

@Service
public class DubboCartServicelmpl  implements DubboCartService{

	@Autowired
	CartMapper cartMapper;
	/**
	 * 展示购物车信息
	 */
	@Override
	public List<Cart> findCartsByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		List<Cart> list = cartMapper.selectList(queryWrapper);
		
		return list;
	}
	/**
	 * 修改购物车信息
	 */
	@Override
	public Integer updateCartsByMergeId(Cart cart) {
		Cart cart2=new Cart().setNum(cart.getNum());
		cart2.setUpdated(new Date());
		QueryWrapper<Cart> queryWrapper= new QueryWrapper<>();
		queryWrapper.eq("user_id", cart.getUserId());
		queryWrapper.eq("item_id", cart.getItemId());
		int update = cartMapper.update(cart2, queryWrapper);
		return update;
	}

	@Override
	@Transactional
	public Integer addCartsByMergeId(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", cart.getUserId());
		queryWrapper.eq("item_id", cart.getItemId());
		Cart userDB = cartMapper.selectOne(queryWrapper);
		if(userDB==null) {
			cart.setCreated(new Date());
			cartMapper.insert(cart);
		}else {
			Cart cart2=new Cart().setNum(cart.getNum()+userDB.getNum());
			cart2.setUpdated(new Date());
			QueryWrapper<Cart> queryWrapper2 = new QueryWrapper<>();
			queryWrapper2.eq("user_id", cart.getUserId());
			queryWrapper2.eq("item_id", cart.getItemId());
			cartMapper.update(cart2, queryWrapper2);
		}
		return 1;
	}

	@Override
	public Integer deleteCartsByMergeId(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>(cart);
		int delete = cartMapper.delete(queryWrapper);
		return delete;
	}

	@Override
	public List<Cart> findCartsListByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		List<Cart> list = cartMapper.selectList(queryWrapper);
		return list;
	}

}
