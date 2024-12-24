package 动态规划;

import java.util.Scanner;

public class HJ37统计每个月兔子的总数 {
    /**
     * 描述
     * 有一种兔子，从出生后第三个月起，每个月都会生一只兔子，生出来的兔子同理。假设兔子都不死，求解第n 个月时的兔子总数。
     */

    public static void main(String[] args) {
        test1();
        test2();
    }

    // 方法一：斐波那契数列
    private static void test1() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(f(n));
        }
    }

    private static int f(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    // 方法二：动态规划
    private static void test2() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(dp(n));
        }
    }

    public static int dp(int n) {
        int num[] = new int[n + 1];
        num[1] = 1;
        num[2] = 1;
        for (int i = 3; i <= n; i++) {
            num[i] = num[i - 1] + num[i - 2];
        }
        return num[n];
    }
}
