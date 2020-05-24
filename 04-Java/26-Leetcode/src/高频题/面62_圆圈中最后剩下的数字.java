package 高频题;

public class 面62_圆圈中最后剩下的数字 {
    // f(n, m) = (f(n - 1, m) + m) % n
    public int lastRemaining(int n, int m) {
        if (n == 1) return 0;
        return (lastRemaining(n - 1, m) + m) % n;
    }

    public int lastRemaining2(int n, int m) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }
        return res;
    }

    public static void main(String[] args) {
        面62_圆圈中最后剩下的数字 obj = new 面62_圆圈中最后剩下的数字();
        System.out.println(obj.lastRemaining(10, 17));
        System.out.println(obj.lastRemaining2(10, 17));
    }
}
