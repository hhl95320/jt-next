package com.jt;

import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jt.mapper.ItemCatMapper;
import com.jt.mapper.ItemParamItemMapper;
import com.jt.pojo.ItemParamItem;
import com.jt.service.ItemCatService;
import com.jt.service.ItemParamServiceImpl;
import com.jt.service.ItemService;
import com.jt.testItem.inter;
import com.jt.vo.EsayUITree;

@SpringBootTest
public class testItem<T> {

	@Autowired
	ItemService itemService;
	@Autowired
	ItemCatService itemCatService;
	@Autowired
	ItemCatMapper itemCatMapper;
	@Autowired
	private ItemParamServiceImpl itemParamItemMapper;
	@Test
	void testitem11() {
		//itemService.deleteItem(11,2,3,4,6,8);
		//ItemParamItem findItemCatTree =itemParamItemMapper.findItemParamItemById(1209087L) ;
		//System.out.println(findItemCatTree);
	}
	@Test
	void testitem() {
		Double ss=5.00_5555;
		//itemService.deleteItem(11,2,3,4,6,8);
		List<EsayUITree> findItemCatTree = itemCatMapper.findItemCatTree();

		System.out.println(findItemCatTree);
	}
	@Test
	void testt() {
		//Arrays.copyOf(original, newLength);
		HashSet<Object> hashSet = new HashSet<Object>();
		HashMap<Object, Object> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", 1);
		map.put("3", 1);
		map.put("4", 1);
		map.put("5", 1);
		Set<Object> keySet = map.keySet();
		Set<Entry<Object, Object>> entrySet = map.entrySet();
		for( Entry<Object, Object> c:entrySet ) {
			System.out.println(c.getKey()+"====="+c.getValue());
		}
		System.out.println(keySet);
		System.out.println(entrySet);
		
		LinkedList<Object> linkedList = new LinkedList<>();
	}
	@Test
	void testyy() {
		double a=0.000000001;
		double b=0.999999999;
		
		BigDecimal aa = new BigDecimal(a+"");
		BigDecimal bb = new BigDecimal(b+"");
		
		//aa.setScale(2, BigDecimal.ROUND_HALF_UP);
		//	bb.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(aa.add(bb));
		DecimalFormat format = new DecimalFormat("0.0000");
		String string = format.format(bb);
		System.out.println(string);
		System.out.println(String.format("%.4f", bb));
		System.out.println(aa.multiply(bb));
		System.out.println(bb.divide(aa,2,BigDecimal.ROUND_HALF_UP));
		System.out.println(aa.subtract(bb));
		
	}
	@Test
	void aa() {
		Scanner scanner = new Scanner(System.in);
		outer:
		for(int i=1;i<50;i++) {
			System.out.println("输入");
			int nextInt = scanner.nextInt();
			for(int j=0;j<100;j=j+1) {
				if(nextInt==j) {
					System.out.println("找到了");
					break outer;
				}else {
					System.out.println("错了");
					continue outer;
				}
			}		
		}
	}
	@Test
	void testtt() {
		int a=0;
		int b=0;
		int c=0;
		System.out.println(a++ + --b -a+++ c);
		System.out.println(0x18);
		System.out.println(0711+"|"+(1+8+64*7));
		
	}
	
	@Test
	void test1() {
		//itemService.deleteItem(11,2,3,4,6,8);
		
		inter i=new inter();
		i.test11();
	}
	int  aa=0;
	static class inter{
		static void  test11() {
			System.out.println(new out().a);
			new out().test12();
		}
	}
}

class out{
	
	static int  a=0;
	static void test12() {
		System.out.println("out:test12");
	}
}