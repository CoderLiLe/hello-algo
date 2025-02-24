package 字符串;

import tools.Asserts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _068文本左右对齐 {
    /**
     * 字符串大模拟，分情况讨论即可：
     * （1）如果当前行只有一个单词，特殊处理为左对齐；
     * （2）如果当前行为最后一行，特殊处理为左对齐；
     * （3）其余为一般情况，分别计算「当前行单词总长度」、「当前行空格总长度」和「往下取整后的单位空格长度」，然后依次进行拼接。
     * 当空格无法均分时，每次往靠左的间隙多添加一个空格，直到剩余的空格能够被后面的间隙所均分。
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; ) {
            // list 装载当前行的所有 word
            list.clear();
            list.add(words[i]);
            int cur = words[i++].length();
            while (i < n && cur + 1 + words[i].length() <= maxWidth) {
                cur += 1 + words[i].length();
                list.add(words[i++]);
            }

            // 当前行为最后一行，特殊处理为左对齐
            if (i == n) {
                StringBuilder sb = new StringBuilder(list.get(0));
                for (int k = 1; k < list.size(); k++) {
                    sb.append(" ").append(list.get(k));
                }
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                ans.add(sb.toString());
                break;
            }

            // 如果当前行只有一个 word，特殊处理为左对齐
            int cnt = list.size();
            if (cnt == 1) {
                String str = list.get(0);
                while (str.length() != maxWidth) {
                    str += " ";
                }
                ans.add(str);
                continue;
            }

            /**
             * 其余为一般情况
             * wordWidth : 当前行单词总长度;
             * spaceWidth : 当前行空格总长度;
             * spaceItem : 往下取整后的单位空格长度
             */
            int wordWidth = cur - (cnt - 1);
            int spaceWidth = maxWidth - wordWidth;
            int spaceItemWidth = spaceWidth / (cnt - 1);
            String spaceItem = "";
            for (int k = 0; k < spaceItemWidth; k++) {
                spaceItem += " ";
            }
            StringBuilder sb = new StringBuilder();
            for (int k = 0, sum = 0; k < cnt; k++) {
                String item = list.get(k);
                sb.append(item);
                if (k == cnt - 1) {
                    break;
                }
                sb.append(spaceItem);
                sum += spaceItemWidth;
                // 剩余的间隙数量（可填入空格的次数）
                int remain = cnt - k - 1 - 1;
                // 剩余间隙数量 * 最小单位空格长度 + 当前空格长度 < 单词总长度，则在当前间隙多补充一个空格
                if (remain * spaceItemWidth + sum < spaceWidth) {
                    sb.append(" ");
                    sum++;
                }
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    public static void main(String[] args) {
        _068文本左右对齐 obj = new _068文本左右对齐();

        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> res = new ArrayList<>(Arrays.asList("This    is    an", "example  of text", "justification.  "));
        Asserts.test(obj.fullJustify(words, 16).equals(res));
    }
}
