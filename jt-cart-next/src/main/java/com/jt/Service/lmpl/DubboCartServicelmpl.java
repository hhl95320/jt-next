package com.jt.Service.lmpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import com.jt.service.DubboCartService;
@Service
public class DubboCartServicelmpl implements DubboCartService {

	@Autowired
	CartMapper cartMapper;
	@Override
	public List<Cart> findCartsByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		List<Cart> selectList = cartMapper.selectList(queryWrapper);
		
		return selectList;
	}
	@Override
	public Integer updateCartsByMergeId(Cart cart) {
		Cart cartupdate=new Cart()
				.setNum(cart.getNum());
				cartupdate.setUpdated(new Date());
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", cart.getUserId());
		queryWrapper.eq("item_id", cart.getItemId());
		int updateById = cartMapper.update(cartupdate,queryWrapper);
		return updateById;
	}
	/**
	 * 分第一次添加
	 * 和第n次提交
	 */
	@Override
	public Integer addCartsByMergeId(Cart cart) {
		/**
		 * 先查询数据库是否存在该购物信息
		 */
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("item_id", cart.getItemId());
		queryWrapper.eq("user_id", cart.getUserId());
		Cart selectOne = cartMapper.selectOne(queryWrapper);
		
		if(selectOne==null) {
			cart.setCreated(new Date());
			cartMapper.insert(cart);
		}
		else {
			Cart cartupate=new Cart()
					.setNum(cart.getNum()+selectOne.getNum());
			cartupate.setUpdated(new Date());
			
			QueryWrapper<Cart> queryWrapper2 = new QueryWrapper<>();
			queryWrapper2.eq("id",selectOne.getId());
			cartMapper.update(cartupate, queryWrapper2);
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
