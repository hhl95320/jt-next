package com.jt.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.DubboItemService;
import com.jt.service.DubboUserService;
import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping({"/items/"})
public class ItemController {

	
	@Reference(check = false,loadbalance="leastactive")
	DubboItemService dubboItemService;
	@Autowired
	JedisCluster jedisCluster;
	

	@RequestMapping("{itemId}")
	//@ResponseBody
	public String findItemById(@PathVariable Long itemId,Model model) {
	  Item item=dubboItemService.findItemById(itemId);
      ItemDesc itemDesc=dubboItemService.findItemDescById(itemId);
      model.addAttribute("item", item);
      model.addAttribute("itemDesc", itemDesc);
		return  "item";
	}


}
