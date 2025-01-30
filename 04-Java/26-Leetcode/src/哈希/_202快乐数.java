package 哈希;

import tools.Asserts;

import java.util.HashSet;
import java.util.Set;

public class _202快乐数 {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }
            set.add(n);

            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }
            n = sum;
        }

        return true;
    }

    public static void main(String[] args) {
        _202快乐数 obj = new _202快乐数();
        Asserts.test(obj.isHappy(19));
//        Asserts.test(!obj.isHappy(2));
//        Asserts.test(!obj.isHappy(3));
    }
}
