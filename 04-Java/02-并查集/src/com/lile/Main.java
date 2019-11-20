package com.lile;

import com.lile.tools.Times;
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

}
