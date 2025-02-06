package 字符串;

import tools.Asserts;

public class _058最后一个单词的长度 {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        String[] strings = s.split(" ");
        return strings[strings.length - 1].length();
    }

    public int lengthOfLastWord2(String s) {
        int end = s.length() - 1;
        while(end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if(end < 0) {
            return 0;
        }
        int start = end;
        while(start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

    public static void main(String[] args) {
        _058最后一个单词的长度 obj = new _058最后一个单词的长度();
        Asserts.test(obj.lengthOfLastWord("Hello World") == 5);
        Asserts.test(obj.lengthOfLastWord2("Hello World") == 5);
    }
}
