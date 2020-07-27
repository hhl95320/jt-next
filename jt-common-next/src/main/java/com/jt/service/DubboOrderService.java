package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.pojo.Order;

public interface DubboOrderService {

	String saveOrder(Order order);

	Order findOrderById(String  id);
}
