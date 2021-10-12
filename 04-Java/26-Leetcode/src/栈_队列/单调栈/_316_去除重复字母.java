package 栈_队列.单调栈;

import java.util.LinkedList;

public class _316_去除重复字母 {

    /**
     * 思路总结：
     * 1、从字典入手，第一个字母靠前可以使整个字符串靠前（贪心）
     * 2、每次贪心要找到一个当前最靠前的字母（单调栈）
     * 3、从限制条件切入，改造贪心条件（有条件限制的单调栈）
     * 4、确定数据结构
     */

    /**
     * 若输入为bcacc
     * 1. b 入栈
     * 2. c 入栈时因为字典序靠后,且栈中没出现过c,直接压栈
     * 3. a 入栈,因为 a 的字典序列靠前且a没有出现过,此时要考虑弹出栈顶元素.
     *    元素 c 因为在之后还有 至少一次 出现次数，所以这里可以弹出.
     *    元素 b 之后不再出现,为了保证至少出现一次这里不能再舍弃了.
     * 4. c 入栈时依然因为字典序靠后且栈中没出现过直接压栈
     * 5. c 入栈时栈中已经出现过c,跳过该元素
     * 输出结果为 bac
     */

    /**
     * T = O(n)
     */
    public String removeDuplicateLetters(String s) {
        // 根据思路首先需要一个栈结构,由于最终输出结果是先进先出,我们使用双向队列来模拟栈的操作
        LinkedList<Character> deque = new LinkedList<>();
        // 弹出栈顶元素时需要知道该字母剩余出现次数，可以用一个哈希表统计字母出现次数,这里元素都是小写字母,可以使用长度26的int数组代替
        int[] count = new int[26];
        // 压栈时还需要一个哈希表统计栈中是否出现过该字母，同上可以用长度26的boolean型数组代替
        boolean[] exists = new boolean[26];

        // 初始化
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        // 遍历 s 并入栈
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            // 该位置字母没有在栈中出现过
            if (!exists[temp - 'a']) {
                // 栈不为空 && 栈顶元素字典序列靠后 && 栈顶元素还有剩余出现次数
                while (!deque.isEmpty() && deque.getLast() > temp && count[deque.getLast() - 'a'] > 0) {
                    // 修改出现状态
                    exists[deque.getLast() - 'a'] = false;
                    // 栈顶元素弹出
                    deque.removeLast();
                }
                // 该位置的字母压栈
                deque.add(temp);
                // 修改出现状态
                exists[temp - 'a'] = true;
            }
            // 遍历过的字母出现次数减一
            count[temp - 'a']--;
        }

        // 返回
        StringBuilder res = new StringBuilder();
        for (Character ch : deque) {
            res.append(ch);
        }
        return res.toString();
    }
}
