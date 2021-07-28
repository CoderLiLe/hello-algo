package DFS;

import java.util.ArrayList;
import java.util.List;

public class _017_电话号码的字母组合 {
    private char[][] lettersArray = {
            {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'},
    };
    private List<String> list;
    private char[] chars;
    /**
     * 用来存储每一层选择的字母
     */
    private char[] string;

    public List<String> letterCombinations(String digits) {
        if (digits == null) return null;
        list = new ArrayList<>();
        chars = digits.toCharArray();
        if (chars.length == 0) return list;
        string = new char[chars.length];
        dfs(0);
        return list;
    }

    private void dfs(int idx) {
        //  已经进入最后一层，不能再往下搜索了
        if (idx == chars.length) {
            // 得到一个正确的解
            list.add(new String(string));
            return;
        }

        // 先枚举这一层可以做的所有选择
        char[] letters = lettersArray[chars[idx] - '2'];
        for (char letter : letters) {
            string[idx] = letter;
            dfs(idx + 1);
        }
    }

    public static void main(String[] args) {
        _017_电话号码的字母组合 obj = new _017_电话号码的字母组合();
        List<String> result = obj.letterCombinations("23");
        System.out.println(result.toString());
    }
}
