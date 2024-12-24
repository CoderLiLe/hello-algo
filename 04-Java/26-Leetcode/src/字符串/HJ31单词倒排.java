package 字符串;

import java.util.Scanner;

public class HJ31单词倒排 {
    public static String reverse(String str) {
        // 匹配非字母的字符进行分割
        String[] words = str.split("[^A-Za-z]");
        StringBuilder result = new StringBuilder();

        // 逆序添加分割完的单词
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]).append(" ");
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            String res = reverse(str);
            System.out.println(res);
        }
    }
}
