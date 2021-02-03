package 动态规划.houseRobbery;

import common.TreeNode;
import tools.Asserts;
import tools.Times;

import java.util.HashMap;

/**
 * 【打家劫舍III】
 * https://leetcode-cn.com/problems/house-robber-iii
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，
 * 每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 */
public class HourseRobbery3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.right = node4;

        HourseRobbery3 hr = new HourseRobbery3();

        Times.test("暴力递归", () -> {
            int result = hr.rob1(root);
            Asserts.test(result == 7);
        });

        Times.test("记忆化 - 解决重复子问题", () -> {
            HashMap<TreeNode, Integer> memo = new HashMap<>();
            int result = hr.rob2(root, memo);
            Asserts.test(result == 7);
        });

        Times.test("动态规划", () -> {
            int[] result = hr.rob3(root);
            Asserts.test(Math.max(result[0], result[1]) == 7);
        });
    }

    /**
     * 解法一：暴力递归 - 最优子结构
     * 4 个孙子偷的钱 + 爷爷偷的钱 VS 两个儿子偷的钱，看那个组合钱多，就当作当前节点能偷的最大钱数。
     * 这就是动规中的最优子结构
     * @param root
     * @return
     */
    private int rob1(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) {
            money += (rob1(root.left.left) + rob1(root.left.right));
        }

        if (root.right != null) {
            money += (rob1(root.right.left) + rob1(root.right.right));
        }

        return Math.max(money, rob1(root.left) + rob1(root.right));
    }

    /**
     * 解法二：记忆化 - 解决重复子问题
     * 针对解法一种速度太慢的问题，经过分析其实现，我们发现爷爷在计算自己能偷多少钱的时候，同时计算了 4 个孙子能偷多少钱，
     * 也计算了 2 个儿子能偷多少钱。这样在儿子当爷爷时，就会产生重复计算一遍孙子节点。
     *
     * 于是乎我们发现了一个动态规划的关键优化点
     *
     * 【重复子问题】
     * 针对重复子问题进行优化，在做斐波那契数列时，使用的优化方案是记忆化，之前的问题都是使用数组来解决的，把每次的计算结果存起来，
     * 下次如果再来计算，就从缓存中取，不再计算，这样就保证了每个数字只计算一次。
     * 由于二叉树不适合拿数组来当缓存，使用哈希表来存储结果，TreeNode 当作 key，能偷的钱当作 value
     */
    private int rob2(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);

        int money = root.val;

        if (root.left != null) {
            money += (rob2(root.left.left, memo) + rob2(root.left.right, memo));
        }

        if (root.right != null) {
            money += (rob2(root.right.left, memo) + rob2(root.right.right, memo));
        }
        int result = Math.max(money, rob2(root.left, memo) + rob2(root.right, memo));
        memo.put(root, result);
        return result;
    }

    /**
     * 解法三：动态规划
     * 每个节点可以选择偷与不偷，相邻节点不能一起偷
     * （1）当前节点选择偷时，那么两个孩子节点就不能选择偷了
     * （2）当前节点选择不偷时，那么两个孩子节点只需要拿最多钱出来就行（两个孩子节点偷不偷没关系）
     *
     * int[] dp = new int[2]：0 表示不偷，1表示偷
     *
     * 任何一个节点能偷到的最大钱的状态可以定义为
     * （1）当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
     * （2）当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
     */
    private int[] rob3(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = rob3(root.left);
        int[] right = rob3(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }
}
