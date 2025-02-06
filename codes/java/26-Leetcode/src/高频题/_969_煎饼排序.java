package 高频题;

import java.util.LinkedList;
import java.util.List;

public class _969_煎饼排序 {
    private List<Integer> res = new LinkedList<>();

    public List<Integer> pancakeSort(int[] arr) {
        sort(arr, arr.length);
        return res;
    }

    private void sort(int[] arr, int n) {
        // base case
        if (1 == n) return;

        // 寻找最大饼的索引
        int maxCake = 0;
        int maxCakeIndex = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > maxCake) {
                maxCake = arr[i];
                maxCakeIndex = i;
            }
        }

        // 第一次翻转，将最大饼翻到最上面
        reverse(arr, 0, maxCakeIndex);
        res.add(maxCakeIndex + 1);

        // 第二次翻转，将最大饼翻到最下面
        reverse(arr, 0, n - 1);
        res.add(n);

        sort(arr, n - 1);
    }

    private void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            ++i;
            --j;
        }
    }

    public static void main(String[] args) {
        _969_煎饼排序 obj = new _969_煎饼排序();
        int[] arr = {3,2,4,1};
        List<Integer> res = obj.pancakeSort(arr);
        for (Integer re : res) {
            System.out.println(re);
        }
    }
}
