package 高频题;

import tools.Asserts;

public class _007_整数反转 {
    // 1234 = ((1 * 10 + 2) * 10 + 3) * 10 + 4
    // 4321 = ((4 * 10 + 3) * 10 + 2) * 10 + 1
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            if (res > Integer.MAX_VALUE) return 0;
            if (res < Integer.MIN_VALUE) return 0;
            x /= 10;
        }
        return (int) res;
    }

    public int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            int preRes = res;
            int mod = x % 10;
            res = preRes * 10 + mod;
            if ((res - mod) / 10 != preRes) return 0;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        _007_整数反转 obj = new _007_整数反转();
        Asserts.test(obj.reverse2(1234) == 4321);
        Asserts.test(obj.reverse2(1200) == 21);
        Asserts.test(obj.reverse2(-1234) == -4321);
        Asserts.test(obj.reverse2(Integer.MAX_VALUE) == 0);
    }
}
