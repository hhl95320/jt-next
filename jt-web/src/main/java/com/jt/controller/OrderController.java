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
import com.jt.pojo.Order;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.service.DubboItemService;
import com.jt.service.DubboOrderService;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping({"/order/"})
public class OrderController {

	
	@Reference(check = false,loadbalance="leastactive")
	DubboCartService dubboCartService;
	@Reference(check = false,loadbalance="leastactive")
	DubboOrderService dubboOrderService;
	@Autowired
	JedisCluster jedisCluster;
	
	@RequestMapping("create")
	public String createCart(Model model ,HttpServletRequest request) {
		User user=(User) request.getAttribute("JT_USER");
		List<Cart> list=dubboCartService.findCartsListByUserId(user.getId());
		
		model.addAttribute("carts", list);
		return "order-cart";
	}
	@RequestMapping("submit")
	@ResponseBody
	public SysResult submitOrder(Order order,HttpServletRequest request) {
		User user=(User) request.getAttribute("JT_USER");
		order.setUserId(user.getId());
		String orderId=dubboOrderService.saveOrder(order);
		if(orderId==null)
			return SysResult.fail();
		
		return SysResult.sucess(orderId);
	}
	@RequestMapping("success")
	//@ResponseBody
	public String submitSuccessOrder(String id,Model model) {
		Order order=dubboOrderService.findOrderById(id);
		model.addAttribute("order", order);
		return "success";
	}
	
	
}
