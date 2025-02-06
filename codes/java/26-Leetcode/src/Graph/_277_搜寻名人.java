package Graph;

import java.util.LinkedList;

public class _277_搜寻名人 {
    /**
     * 优化二
     * T = O(n), S = O(1)
     */
    public int findCelebrity(int n) {
        // 先假设 cand 是名人
        int cand = 0;
        for (int other = 1; other < n; other++) {
            if (knows(cand, other) || !knows(other, cand)) {
                // cand 不可能是名人，排除
                cand = other;
            } else {
                // other 不可能是名人，排除
                // 什么都不用做，继续假设 cand 是名人
            }
        }

        // 现在的 cand 是排除的最后结果，但不保证一定是名人
        for (int other = 0; other < n; other++) {
            if (other == cand) continue;

            // 保证其他人都认识 cand，且 cand 不认识任何其他人
            if (knows(cand, other) || !knows(other, cand)) {
                return -1;
            }
        }

        return cand;
    }

    /**
     * 优化一：不断从候选人中选两个出来，然后排除掉一个，直到最后只剩下一个候选人，这时候再使用一个 for 循环判断这个候选人是否是货真价实的「名人」
     * T = O(n), S = O(n)
     */
    public int findCelebrity2(int n) {
        if (1 == n) return -1;
        // 将所有候选人装进队列
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            q.addLast(i);
        }

        // 一直排除，直到只剩下一个候选人停止循环
        while (q.size() > 1) {
            int cand = q.removeFirst();
            int other = q.removeFirst();
            if (knows(cand, other) || !knows(other, cand)) {
                // cand 不可能是名人，排除，让 other 归队
                q.addFirst(other);
            } else {
                // other 不可能是名人，排除，让 cand 归队
                q.addFirst(cand);
            }
        }

        // 现在排除得只剩下一个人了，判断他是否是真的名人
        int cand = q.removeFirst();
        for (int other = 0; other < n; other++) {
            if (other == cand) continue;

            // 保证其他人都认识 cand，且 cand 不认识任何其他人
            if (knows(cand, other) || !knows(other, cand)) {
                return -1;
            }
        }

        return cand;
    }

    /**
     * 暴力法
     * T = O(n^2)
     */
    public int findCelebrity3(int n) {
        for (int cand = 0; cand < n; cand++) {
            int other;
            for (other = 0; other < n; other++) {
                if (cand == other) continue;

                // 保证其他人都认识 cand，且 cand 不认识其他人
                if (knows(cand, other) || !knows(other, cand)) {
                    break;
                }
            }

            // 找到了名人
            if (n == other) {
                return cand;
            }
        }
        return -1;
    }

    private boolean knows(int i, int j) {
        return false;
    }
}
