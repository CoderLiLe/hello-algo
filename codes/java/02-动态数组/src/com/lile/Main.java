package com.lile;

public class Main {

	public static void main(String[] args) {
//		test1();
		test2();
	}
	
	static void test1() {
		// int -> Integer
		// new 是向堆空间申请内存
		ArrayList<Person> persons = new ArrayList<>();
		persons.add(new Person(10, "Jack"));
		persons.add(new Person(12, "james"));
		persons.add(new Person(15, "Rose"));
		persons.clear();
		persons.add(new Person(22, "abc"));
		
		System.out.println(persons);
		
		ArrayList<Integer> ins = new ArrayList<>();
		ins.add(1);
		ins.add(2);
		ins.add(3);
		System.out.println(ins);
	}
	
	static void test2() {
		ArrayList<Object> list = new ArrayList<>();
		list.add(10);
		list.add(new Person(10, "Jack"));
		list.add(22);
		
		int index = list.indexOf(new Person(10, "Jack"));
		System.out.println(index);
		
		System.out.println(list);
	}

}