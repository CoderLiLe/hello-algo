package com.lile;

import com.lile.map.HashMap;
import com.lile.map.Map;
import com.lile.map.Map.Visitor;
import com.lile.model.Key;
import com.lile.model.Person;

public class Main {

	public static void main(String[] args) {
		test3();
	}
	
	static void test1() {
		Person p1 = new Person(18, 1.78f, "lile");
		System.out.println(p1.hashCode());
		Person p2 = new Person(18, 1.78f, "lile");
		System.out.println(p2.hashCode());
		
		Map<Object, Object> map = new HashMap<>();
		map.put(p1, "p1");
		map.put(p2, "p2");
		map.put("rose", 1);
		System.out.println("HashMap size = " + map.size());
	}
	
	static void test2() {
		Person p1 = new Person(10, 1.72f, "liming");
		Person p2 = new Person(10, 1.72f, "liming");
		
		Map<Object, Integer> map = new HashMap<>();
		map.put(p1, 1);
		map.put(p2, 2);
		map.put("liming", 3);
		map.put("zhangsan", 4);
		map.put("liming", 5);
		map.put(new Key(6), 6);
		
//		System.out.println(map.size());
//		System.out.println(map.remove("liming"));
//		System.out.println(map.get("liming"));
//		System.out.println(map.size());
		
		System.out.println(map.containsKey(p1));
		System.out.println(map.containsKey(null));
		System.out.println(map.containsValue(6));
		System.out.println(map.containsValue(1));
	}
	
	static void test3() {
		HashMap<Object, Integer> map = new HashMap<>();
		for (int i = 1; i <= 19; i++) {
			map.put(new Key(i), i);
		}
//		map.traversal(new Visitor<Object, Integer>() {
//			
//			@Override
//			public boolean visit(Object key, Integer value) {
//				System.out.println(key + "_" + value);
//				return false;
//			}
//		});
		System.out.println(map.get(new Key(6)));
		map.print();
	}

}
