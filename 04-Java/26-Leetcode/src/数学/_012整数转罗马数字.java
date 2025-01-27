package 数学;

import tools.Asserts;

public class _012整数转罗马数字 {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * 模拟/贪心
     * 贪心法则：我们每次尽量使用最大的数来表示
     * 时间复杂度：O(1)。由于 valueSymbols 长度是固定的，且这 13 字符中的每个字符的出现次数均不会超过 3，因此循环次数有一个确定的上限。
     * 对于本题给出的数据范围，循环次数不会超过 15 次。
     * 空间复杂度：O(1)。
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) {
        _012整数转罗马数字 obj = new _012整数转罗马数字();
        Asserts.test(obj.intToRoman(3) == "III");
        Asserts.test(obj.intToRoman(4) == "IV");
        Asserts.test(obj.intToRoman(9) == "IX");
        Asserts.test(obj.intToRoman(58) == "LVIII");
        Asserts.test(obj.intToRoman(1994) == "MCMXCIV");
        Asserts.test(obj.intToRoman(3999) == "MMMCMXCIX");
    }
}
