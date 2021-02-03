package 排序_数组;

public class _088_合并两个有序数组 {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i1 = m - 1;
		int i2 = n - 1;
		int cur = nums1.length - 1;
		
		while (i2 >= 0) {
			if (i1 >= 0 && nums1[i1] > nums2[i2]) {
				nums1[cur--] = nums1[i1--];
			} else {
				nums1[cur--] = nums2[i2--];
			}
		}
    }

	public void merge2(int[] nums1, int m, int[] nums2, int n) {
		for (int i = 0; i < n; i++) {
			nums1[m+i] = nums2[i];
		}

		mergeSort(nums1, 0, m, m+n);
	}

	private void mergeSort(int[] array, int begin, int mid, int end) {
		int li = 0, le = mid - begin;
		int ri = mid, re = end;
		int ai = begin;

		int[] leftArray = new int[mid];
		// 备份左边数组
		for (int i = li; i < le; i++) {
			leftArray[i] = array[begin + i];
		}

		// 如果左边还没有结束
		while (li < le) {
			if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
				array[ai++] = array[ri++];
			} else {
				array[ai++] = leftArray[li++];
			}
		}
	}

	private int cmp(Integer v1, Integer v2) {
		return v1.compareTo(v2);
	}
}
