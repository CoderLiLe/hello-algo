package 排序_数组;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 描述
 题目标题：

 将两个整型数组按照升序合并，并且过滤掉重复数组元素。
 输出时相邻两数之间没有空格。



 输入描述：
 输入说明，按下列顺序输入：
 1 输入第一个数组的个数
 2 输入第一个数组的数值
 3 输入第二个数组的个数
 4 输入第二个数组的数值

 输出描述：
 输出合并之后的数组

 示例1
 输入：
 3
 1 2 5
 4
 -1 0 3 2

 输出：
 -101235
 */
public class HJ80整型数组合并 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Set<Integer> treeSet = new TreeSet<>();

        int lengthOne = sc.nextInt();
        for (int i = 0; i < lengthOne; i++) {
            treeSet.add(sc.nextInt());
        }

        int lengthTwo = sc.nextInt();
        for (int i = 0; i < lengthTwo; i++) {
            treeSet.add(sc.nextInt());
        }

        for (Integer integer : treeSet) {
            System.out.print(integer);
        }
    }

}
