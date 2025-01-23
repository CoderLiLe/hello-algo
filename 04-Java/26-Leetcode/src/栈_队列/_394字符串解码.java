package 栈_队列;

import tools.Asserts;

import java.util.Stack;

public class _394字符串解码 {
    /**
     * 本题难点在于括号内嵌套括号，需要从内向外生成与拼接字符串，这与栈的先入后出特性对应
     *
     * 时间复杂度 O(N)，一次遍历 s；
     * 空间复杂度 O(N)，辅助栈在极端情况下需要线性空间，例如 2[2[2[a]]]。
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        char[] sChars = s.toCharArray();
        if (sChars.length == 0) {
            return "";
        }
        int multi = 0;
        StringBuilder str = new StringBuilder();
        Stack<Integer> multiStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        for (char c : sChars) {
            if (c >= '0' && c <= '9') {
                multi = multi * 10 + c - '0';
            } else if (c == '[') {
                multiStack.push(multi);
                strStack.push(str.toString());
                multi = 0;
                str = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                Integer curMulti = multiStack.pop();
                for (int i = 0; i < curMulti; i++) {
                    tmp.append(str);
                }
                str = new StringBuilder(strStack.pop() + tmp);
            } else {
                str.append(c);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        _394字符串解码 obj = new _394字符串解码();
        Asserts.test(obj.decodeString("3[a]2[bc]").equals("aaabcbc"));
        Asserts.test(obj.decodeString("3[a2[c]]").equals("accaccacc"));
        Asserts.test(obj.decodeString("2[abc]3[cd]ef").equals("abcabccdcdcdef"));
        Asserts.test(obj.decodeString("abc3[cd]xyz").equals("abccdcdcdxyz"));
        Asserts.test(obj.decodeString("3[a]2[b4[F]c]").equals("aaabFFFFcbFFFFc"));
        Asserts.test(obj.decodeString("2[3[a]b]").equals("aaabaaab"));
        Asserts.test(obj.decodeString("2[3[a]b]").equals("aaabaaab"));
        Asserts.test(obj.decodeString("2[3[a]b]").equals("aaabaaab"));
        Asserts.test(obj.decodeString("2[3[a]b]").equals("aaabaaab"));
        Asserts.test(obj.decodeString("2[3[a]b]").equals("aaabaaab"));
    }
}
