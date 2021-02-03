package 排序_数组;

import java.util.ArrayList;
import java.util.List;

public class _315_计算右侧小于当前元素的个数 {
    // T(n) = O(n^2)，暴力法不行，会超时
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>();
        if (nums == null || nums.length == 0) return counts;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) count++;
            }
            counts.add(count);
        }

        return counts;
    }

    /*
    我们只要把输入数组反过来插入一个有序数组（降序）中，插入的位置就是在原数组中位于它右侧的元素的个数。

    不好理解的话，我们看例子。

    初始状态：
    原数组为：[5,2,6,1]
    排序数组：[]
    结果数组：[]

    第一轮：
    原数组为：[5,2,6]
    排序数组：[1]
    插入的下标为 0，记入结果数组：[0]

    第二轮：
    原数组为：[5,2]
    排序数组：[1,6]
    插入的下标为 1，记入结果数组：[0,1]

    第三轮：
    原数组为：[5]
    排序数组：[1,2,6]
    插入的下标为 1，记入结果数组：[0,1,1]

    第四轮：
    原数组为：[]
    排序数组：[1,2,5,6]
    插入的下标为 2，记入结果数组：[0,1,1,2]

    最后我们把结果数组逆序一下，就得到了最终的结果。
    算法的复杂度的上界为：nlogn
    * */
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> counts = new ArrayList<>();
        if (nums == null || nums.length == 0) return counts;

        List<Integer> sortNums = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            Integer num = nums[i];

            if (sortNums.size() == 0) {
                sortNums.add(num);
                res.add(0);
                System.out.println("sortNums 0 = " + sortNums);
                System.out.println("res 0 = " + res);
                continue;
            }

            for (int j = 0; j < sortNums.size(); j++) {
                if (sortNums.get(j) < num) {
                    if (j + 1 == sortNums.size()) {
                        sortNums.add(num);
                        res.add(j + 1);
                        System.out.println("sortNums 1 = " + sortNums);
                        System.out.println("res 1 = " + res);
                        break;
                    }
                    continue;
                };

                sortNums.add(j, num);
                res.add(j);
                System.out.println("sortNums 3 = " + sortNums);
                System.out.println("res 3 = " + res);
                break;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = res.size() - 1; i >= 0; i--) {
            list.add(res.get(i));
        }

        return list;
    }

    public static void main(String[] args) {
        _315_计算右侧小于当前元素的个数 obj = new _315_计算右侧小于当前元素的个数();
//        int[] nums = new int[] {5, 2, 6, 1};
        int[] nums = new int[] {1, 0, 2};
        System.out.println(obj.countSmaller2(nums));
    }
}
