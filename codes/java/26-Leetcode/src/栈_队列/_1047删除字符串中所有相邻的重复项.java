package 栈_队列;

import tools.Asserts;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1047删除字符串中所有相邻的重复项 {
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : chars) {
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    /**
     * 拿字符串直接作为栈，省去了栈还要转为字符串的操作
     */
    public String removeDuplicates2(String s) {
        // 将 res 当做栈
         StringBuilder res = new StringBuilder();
        // top为 res 的长度
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 当 top >= 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if (top >= 0 && res.charAt(top) == c) {
                res.deleteCharAt(top);
                top--;
                // 否则，将该字符 入栈，同时top++
            } else {
                res.append(c);
                top++;
            }
        }
        return res.toString();
    }

    /**
     * 双指针
     */
    public String removeDuplicates3(String s) {
        char[] chars = s.toCharArray();
        int fast = 0;
        int slow = 0;
        while (fast < chars.length) {
            // 直接用fast指针覆盖slow指针的值
            chars[slow] = chars[fast];

            // 遇到前后相同值的，就跳过，即slow指针后退一步，下次循环就可以直接被覆盖掉了
            if (slow > 0 && chars[slow] == chars[slow - 1]) {
                slow--;
            } else {
                slow++;
            }
            fast++;
        }
        return new String(chars, 0, slow);
    }

    public static void main(String[] args) {
        _1047删除字符串中所有相邻的重复项 obj = new _1047删除字符串中所有相邻的重复项();
        String s = "abbaca";
        String res = "ca";
        Asserts.test(obj.removeDuplicates(s).equals(res));
        Asserts.test(obj.removeDuplicates2(s).equals(res));
        Asserts.test(obj.removeDuplicates3(s).equals(res));
    }
}
