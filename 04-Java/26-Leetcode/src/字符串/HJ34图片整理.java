package 字符串;

import java.util.Arrays;
import java.util.Scanner;

public class HJ34图片整理 {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();

    }

    private static void test1() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int a[] = new int[128];
            String str = in.next();
            for (int i = 0; i < str.length(); i++) {
                int k = str.charAt(i);//统计出现次数
                a[k]++;
            }
            for (int j = 48; j < a.length; j++) {//从'0'开始输出
                if (a[j] != 0)
                    for (int b = 0; b < a[j]; b++)
                        System.out.print((char) j);
            }
            System.out.println();
        }
    }

    private static void test2() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            int[] hash = new int[128];
            //记录每个字符出现的次数
            for (char c : s.toCharArray()) {
                hash[c]++;
            }
            //记录最终的结果
            StringBuilder sb = new StringBuilder();
            //遍历存入结果中
            for (int i = 0; i < hash.length; i++) {
                while (hash[i]-- > 0) {
                    sb.append((char) i);
                }
            }
            String ans = sb.toString();
            System.out.println(ans);
        }
    }

    private static void test3() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            //转为字符数组
            char[] array = s.toCharArray();
            //使用sort进行拍下
            Arrays.sort(array);
            //记录结果
            String ans = new String(array);
            System.out.println(ans);
        }
    }
}
