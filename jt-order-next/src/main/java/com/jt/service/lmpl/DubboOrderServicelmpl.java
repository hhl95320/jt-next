package com.jt.service.lmpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;
import com.jt.service.DubboOrderService;
@Service
public class DubboOrderServicelmpl  implements DubboOrderService{

	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderItemMapper orderItemMapper;
	@Autowired
	OrderShippingMapper orderShippingMapper;
	
	@Override
	@Transactional
	public String saveOrder(Order order) {
	    Date date = new Date();
	    String orderId=""+order.getUserId()+System.currentTimeMillis();
		//1
	    order.setStatus(1);
		order.setOrderId(orderId).setCreated(date);
		int insert = orderMapper.insert(order);
		System.out.println("订单添加成功");
		//2
		OrderShipping orderShipping=order.getOrderShipping();
		orderShipping.setOrderId(orderId).setCreated(date).setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		System.out.println("订单物流添加成功");
		//3
		for(OrderItem orderItem :order.getOrderItems()) {
			orderItem.setOrderId(orderId).setCreated(date).setUpdated(date);
			orderItemMapper.insert(orderItem);
		}System.out.println("订单商品添加成功");
		
		return orderId;
	}

	@Override
	@Transactional
	public Order findOrderById(String id) {
		//1
		QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("order_id", id);
		Order order = orderMapper.selectOne(queryWrapper);
		//2
		QueryWrapper<OrderShipping> queryWrapper1= new QueryWrapper<>();
		queryWrapper1.eq("order_id", id);
		OrderShipping shipping = orderShippingMapper.selectOne(queryWrapper1);
		//3
		QueryWrapper<OrderItem> queryWrapper2= new QueryWrapper<>();
		queryWrapper2.eq("order_id", id);
		List<OrderItem> list = orderItemMapper.selectList(queryWrapper2);
		
		order.setOrderShipping(shipping).setOrderItems(list);
		return order;
	}

}
