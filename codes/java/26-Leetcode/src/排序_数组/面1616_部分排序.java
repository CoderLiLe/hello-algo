package 排序_数组;

public class 面1616_部分排序 {
	public int[] subSort(int[] array) {
		if (array.length == 0) return new int[] {-1, -1};
		
		// 从左扫描到右寻找逆序对（正序：逐渐变大）
		int max = array[0];
		// 用来记录最右的那个逆序对位置
		int r = -1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] >= max) {
				max = array[i];
			} else {
				r = i;
			}
		}
		
		// 提前结束
		if ( r == -1) return new int[] {-1, -1};
		
		// 从右扫描到左寻找逆序对（正序：逐渐变小）
		int min = array[array.length - 1];
		// 用来记录最左的那个逆序对位置
		int l = -1;
		for (int i = array.length - 2; i >= 0; i--) {
			if (array[i] <= min) {
				min = array[i];
			} else {
				l = i;
			}
		}
				
		return new int[] {l, r};
	}
}
