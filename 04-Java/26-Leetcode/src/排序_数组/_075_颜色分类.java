package 排序_数组;

public class _075_颜色分类 {
	/**
	 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
	 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
	 * 
	 * 仅使用常数空间的一趟扫描算法
	 * 
	 * @param nums
	 */
	public void sortColors(int[] nums) {
		int red = 0;
		int white = 0;
		int blue = nums.length - 1;
		
		while (white <= blue) {
			int val = nums[white];
			if (val == 0) { // 遇到0：红白指针交换，然后红白指针++
				swap(nums, red++, white++);
			} else if (val == 1) { // 遇到1：跳过，白色指针++
				white++;
			} else { // 遇到2：白蓝指针交换，然后蓝色指针--，再次对白色指针的值判断
				swap(nums, white, blue--);
			}
		}
    }
	
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
