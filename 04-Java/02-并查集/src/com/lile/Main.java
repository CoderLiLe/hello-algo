package com.lile;

import com.lile.tools.Times;
import com.lile.union.GenericUnionFind;
import com.lile.union.UnionFind;
import com.lile.union.UnionFind_QF;
import com.lile.union.UnionFind_QU;
import com.lile.union.UnionFind_QU_R;
import com.lile.union.UnionFind_QU_R_PC;
import com.lile.union.UnionFind_QU_R_PH;
import com.lile.union.UnionFind_QU_R_PS;
import com.lile.union.UnionFind_QU_S;

public class Main {
	static final int count = 5000000;
	
	public static void main(String[] args) {
		
		testTime(new UnionFind_QF(count));
		testTime(new UnionFind_QU(count));
		testTime(new UnionFind_QU_S(count));
		testTime(new UnionFind_QU_R(count));
		testTime(new UnionFind_QU_R_PC(count));
		testTime(new UnionFind_QU_R_PS(count));
		testTime(new UnionFind_QU_R_PH(count));
		testTime(new GenericUnionFind<Integer>());
		
//		GenericUnionFind<Student> uf = new GenericUnionFind<>();
//		Student stu1 = new Student(1, "jack");
//		Student stu2 = new Student(2, "rose");
//		Student stu3 = new Student(3, "jack");
//		Student stu4 = new Student(4, "rose");
//		uf.makeSet(stu1);
//		uf.makeSet(stu2);
//		uf.makeSet(stu3);
//		uf.makeSet(stu4);
//		
//		uf.union(stu1, stu2);
//		uf.union(stu3, stu4);
//		uf.union(stu1, stu4);
//		
//		System.out.println(uf.isSame(stu1, stu2));
//		System.out.println(uf.isSame(stu3, stu4));
//		System.out.println(uf.isSame(stu1, stu3));
	}
	
	static void testTime(UnionFind uf)  {
		Times.test(uf.getClass().getSimpleName(), () -> {
			for (int i = 0; i < count; i++) {
				uf.union((int)Math.random() * count, (int)Math.random() * count);
			}
			
			for (int i = 0; i < count; i++) {
				uf.isSame((int)Math.random() * count, (int)Math.random() * count);
			}
		});
	}
	
	static void testTime(GenericUnionFind<Integer> uf)  {
		Times.test(uf.getClass().getSimpleName(), () -> {
			for (int i = 0; i < count; i++) {
				uf.union((int)Math.random() * count, (int)Math.random() * count);
			}
			
			for (int i = 0; i < count; i++) {
				uf.isSame((int)Math.random() * count, (int)Math.random() * count);
			}
		});
	}

}
