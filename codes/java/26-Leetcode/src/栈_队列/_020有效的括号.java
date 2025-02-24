package 栈_队列;

import tools.Asserts;

import java.util.*;

public class _020有效的括号 {
    /**
     * 栈是一种先进后出的数据结构，处理括号问题的时候尤其有用。
     *
     */
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else {
                if (st.isEmpty()) {
                    return false;
                }
                char leftC = st.pop();
                if ((leftC == '(' && c == ')') || (leftC == '{' && c == '}') || (leftC == '[' && c == ']')) {
                    // do nothing
                } else {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }

    public boolean isValid2(String s) {
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            // 碰到左括号，就把相应的右括号入栈
            if (ch == '(') {
                deque.push(')');
            } else if (ch == '{') {
                deque.push('}');
            } else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            } else {// 如果是右括号判断是否和栈顶元素匹配
                deque.pop();
            }
        }
        // 最后判断栈中元素是否匹配
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        _020有效的括号 obj = new _020有效的括号();

        Asserts.test(obj.isValid("()") == true);
        Asserts.test(obj.isValid("()[]{}") == true);
        Asserts.test(obj.isValid("(]") == false);
        Asserts.test(obj.isValid("([)]") == false);
        Asserts.test(obj.isValid("({})]") == false);
        Asserts.test(obj.isValid("({})[(]") == false);

        Asserts.test(obj.isValid2("()") == true);
        Asserts.test(obj.isValid2("()[]{}") == true);
        Asserts.test(obj.isValid2("(]") == false);
        Asserts.test(obj.isValid2("([)]") == false);
        Asserts.test(obj.isValid2("({})]") == false);
        Asserts.test(obj.isValid2("({})[(]") == false);
    }
}
