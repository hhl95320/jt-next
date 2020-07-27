package com.jt.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.service.DubboItemService;
import com.jt.service.DubboUserService;
import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping({"/cart/"})
public class CartController {

	
	@Reference(check = false,loadbalance="leastactive")
	DubboCartService dubboCartService;
	@Autowired
	JedisCluster jedisCluster;
	
	@RequestMapping("show")
	public String showCart(Model model ,HttpServletRequest request) {
		User user=(User) request.getAttribute("JT_USER");
		List<Cart> list=dubboCartService.findCartsByUserId(user.getId());
		model.addAttribute("cartList", list);
		return "cart";
	}
	/**
	 * 修改用户购物车数量
	 * @param cart
	 * @return
	 */
	@RequestMapping("update/num/{itemId}/{num}")
	@ResponseBody
	public String updateCart(Cart cart,HttpServletRequest request) {
		
		User user=(User) request.getAttribute("JT_USER");
		cart.setUserId(user.getId());
		dubboCartService.updateCartsByMergeId(cart);
		
		return "cart";
	}
	/**
	 * 提交用户购物车信息
	 * @param cart
	 * @return
	 */
	@RequestMapping("add/{itemId}")
	//@ResponseBody
	public String addCart(Cart cart,HttpServletRequest request) {
		
		User user=(User) request.getAttribute("JT_USER");
		cart.setUserId(user.getId());
		dubboCartService.addCartsByMergeId(cart);
		
		return "redirect:/cart/show.html";//重新刷新购物车信息
	}
	@RequestMapping("delete/{itemId}")
	public String deleteCart(Cart cart,HttpServletRequest request) {
		
		User user=(User) request.getAttribute("JT_USER");
		cart.setUserId(user.getId());
		dubboCartService.deleteCartsByMergeId(cart);
		
		return "redirect:/cart/show.html";//重新刷新购物车信息
	}
	
}
