package com.lile;

import com.lile.file.FileInfo;
import com.lile.file.Files;
import com.lile.map.Map;
import com.lile.map.Map.Visitor;
import com.lile.map.TreeMap;
import com.lile.set.Set;
import com.lile.set.TreeSet;

public class Main {

	public static void main(String[] args) {
		test3();
	}
	
	static void test1() {
		Map<String, Integer> map = new TreeMap<>();
		map.put("c", 2);
		map.put("a", 5);
		map.put("b", 6);
		map.put("a", 8);
		
		map.traversal(new Visitor<String, Integer>() {
			public boolean visit(String key, Integer value) {
				System.out.println(key + "_" + value);
				return false;
			}
		});
	}
	
	static void test2() {
//		FileInfo fileInfo = Files.read("/Users/icourt1/Desktop/Algo/DataStructureAlgorithm/04-Java/09-集合/src/com/lile/set/TreeSet.java");
//		FileInfo fileInfo = Files.read("/Users/icourt1/Desktop/Algo/DataStructureAlgorithm/04-Java/09-集合/src/com/lile/set", new String[] {"java"});
		FileInfo fileInfo = Files.read("/Users/icourt1/Desktop/Algo/DataStructureAlgorithm/04-Java/", new String[] {"java"});

		System.out.println("文件数量：" + fileInfo.getFiles());
		System.out.println("代码行数：" + fileInfo.getLines());
		String[] words = fileInfo.words();
		System.out.println("单词数量：" + words.length);
		
		Map<String, Integer> map = new TreeMap<>();
		for (int i = 0; i < words.length; i++) {
			Integer count = map.get(words[i]);
			count = (count == null) ? 1 : (count + 1);
			map.put(words[i], count);
		}
		
		map.traversal(new Visitor<String, Integer>() {
			public boolean visit(String key, Integer value) {
				System.out.println(key + "_" + value);
				return false;
			}
		});
	}

	static void test3() {
		Set<String> set = new TreeSet<>();
		set.add("c");
		set.add("b");
		set.add("c");
		set.add("c");
		set.add("a");
		
		set.traversal(new Set.Visitor<String>() {
			public boolean visit(String element) {
				System.out.println(element);
				return false;
			}
		});
	}
}
