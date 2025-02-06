package 高频题;

import java.util.Stack;

public class _772_基本计算器III {
    private String s;
    private Integer nextI;

    public int calculate(String s) {
        this.s = s;
        return helper(0);
    }

    private int helper(int begin) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;

        for (int i = begin; i < s.length(); i++) {
            char c = s.charAt(i);

            // 如果是数字，连续读取到 num
            if (Character.isDigit(c)) {
                // 字符串转整数
                num = 10 * num + (c - '0');
            }

            // 括号包含的算式，我们直接视为一个数字就行了
            // 遇到(开始递归，遇到)结束递归
            // 遇到左括号开始递归计算 num
            if (c == '(') {
                num = helper(i + 1);
                i = nextI;
            }

            // 如果不是数字，就是遇到了下一个符号，
            // 之前的数字和符号就要存进栈中
            if ((!Character.isDigit(c) && c != ' ') || s.length() -1 == i) {
                Integer pre;
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre / num);
                        break;
                }

                // 更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }

            // 遇到右括号返回递归结果
            if (c == ')') {
                nextI = i;
                break;
            }
        }

        // 将栈中所有结果求和就是答案
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }

    public static void main(String[] args) {
        _772_基本计算器III obj = new _772_基本计算器III();
        System.out.println(obj.calculate("1+1")); // 2
        System.out.println(obj.calculate("6-4/2")); // 4
        System.out.println(obj.calculate("2*(5+5*2)/3+(6/2+8)")); // 21
        System.out.println(obj.calculate("(2+6*3+5-(3*14/7+2)*5)+3")); // -12
        System.out.println(obj.calculate("0")); // 0
    }
}
