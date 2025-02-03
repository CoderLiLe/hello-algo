package 栈_队列;

import tools.Asserts;

import java.util.Stack;

public class _150逆波兰表达式 {
    public int evalRPN(String[] tokens) {
        Stack<String> st = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                // 取出栈顶的两个操作数运算，然后将操作数入栈
                Integer num2 = Integer.valueOf(st.pop());
                Integer num1 = Integer.valueOf(st.pop());
                Integer res = 0;
                switch (s) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    default:
                        res = num1 / num2;
                        break;
                }
                st.push(String.valueOf(res));
            } else {
                // 将操作数入栈
                st.push(s);
            }
        }
        return Integer.valueOf(st.pop());
    }

    public static void main(String[] args) {
        _150逆波兰表达式 obj = new _150逆波兰表达式();

        String[] tokens = {"4", "13", "5", "/", "+"};
        Asserts.test(obj.evalRPN(tokens) == 6);
    }
}
