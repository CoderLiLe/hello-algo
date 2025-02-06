package 数学;

import tools.Asserts;

import java.util.Arrays;

public class _066加一 {
    /**
     * 对一个表示整数的数组进行加一操作
     * 数组的每个元素存储整数的一个位，最高位在数组的首部
     *
     * @param digits 表示整数的数组
     * @return 加一操作后的数组
     */
    public int[] plusOne(int[] digits) {
        // 获取数组的长度
        int len = digits.length;
        // 初始化进位为0
        int remainder = 0;
        // 初始化结果数组，长度为原数组长度加一，以处理可能的进位
        int[] result = new int[len + 1];

        // 从数组末尾开始向前遍历
        for (int i = len - 1; i >= 0; i--) {
            // 获取当前位的数字
            int num = digits[i];
            // 如果是最后一位，需要加上1
            if (i == len - 1) {
                num += 1;
            }
            // 处理进位
            if (num + remainder > 9) {
                // 如果加进位后大于9，则当前位为加进位后的数对10取模的结果，并将进位设为1
                result[i] = (num + remainder) % 10;
                remainder = 1;
            } else {
                // 如果加进位后不大于9，则当前位为加进位后的数，并将进位设为0
                result[i] = num + remainder;
                remainder = 0;
            }
        }
        // 最后检查是否有进位
        if (remainder == 1) {
            // 如果有进位，结果数组的第一位设为1，其余部分已填充
            result[0] = 1;
            return result;
        } else {
            // 如果没有进位，返回结果数组的次高位到末尾的部分，忽略最前的0
            return Arrays.copyOfRange(result, 0, len);
        }
    }

    /**
     * 对一个表示整数的数组进行加一操作，数组每个元素代表整数的一位
     * 如果加一操作导致最高位进位，则需要返回一个新的数组来表示这个新的整数
     *
     * @param digits 一个非空的整数数组，每个元素是整数的一位，最高位在数组的首位
     * @return 如果没有最高位进位，返回原数组；如果有最高位进位，返回一个新的数组表示加一后的结果
     */
    public int[] plusOne2(int[] digits) {
        // 从数组的末尾开始向前遍历，即从个位开始向前做加一操作
        for (int i = digits.length - 1; i >= 0; i--) {
            // 当前位加一
            digits[i]++;
            // 如果当前位加一后等于10，需要进位，因此取模10得到当前位的值
            digits[i] = digits[i] % 10;
            // 如果当前位加一后不等于0，说明没有进位，直接返回原数组
            if (digits[i] != 0) return digits;
        }
        // 如果遍历完数组后，仍未返回，说明最高位有进位，需要创建一个新的数组
        digits = new int[digits.length + 1];
        // 新数组的最高位设为1，表示进位
        digits[0] = 1;
        // 返回新数组
        return digits;
    }

    public static void main(String[] args) {
        _066加一 obj = new _066加一();

        int[] nums = {1, 2, 3};
        Asserts.test(Arrays.equals(obj.plusOne(nums), new int[]{1, 2, 4}));

        int[] nums2 = {4, 3, 2, 9};
        Asserts.test(Arrays.equals(obj.plusOne(nums2), new int[]{4, 3, 3, 0}));

        int[] nums3 = {9, 9};
        Asserts.test(Arrays.equals(obj.plusOne(nums3), new int[]{1, 0, 0}));
    }
}
