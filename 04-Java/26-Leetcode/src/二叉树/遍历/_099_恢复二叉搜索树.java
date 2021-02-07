package 二叉树.遍历;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 99. 恢复二叉搜索树
 * 困难
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 * 示例 1：
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 *
 *
 * 示例 2：
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 *  
 *
 * 提示：
 * 树上节点的数目在范围 [2, 1000] 内
 * -231 <= Node.val <= 231 - 1
 */

/**
 * 参考：https://leetcode-cn.com/problems/recover-binary-search-tree/solution/san-chong-jie-fa-xiang-xi-tu-jie-99-hui-fu-er-cha-/
 */
public class _099_恢复二叉搜索树 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        node2.left = node3;

        _099_恢复二叉搜索树 bstCover = new _099_恢复二叉搜索树();
        // bstCover.recoverTree1(root);
        // bstCover.recoverTree2(root);
        bstCover.recoverTree3(root);
        System.out.println(root);
    }

    public void recoverTree1(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        TreeNode x = null;
        TreeNode y = null;

        // 找出可能存在错误交换的节点 x 和 y
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).val > list.get(i + 1).val) {
                y = list.get(i + 1);
                if (x == null) {
                    x = list.get(i);
                }
            }
        }

        // 如果 x y 不为空，则交换这两个节点值，恢复二叉搜索树
        if (x != null && y != null) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }

    private void dfs(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }

    private TreeNode x2 = null;
    private TreeNode y2 = null;
    private TreeNode pre = null;

    private void recoverTree2(TreeNode root) {
        dfs2(root);
        if (x2 != null && y2 != null) {
            int tmp = x2.val;
            x2.val = y2.val;
            y2.val = tmp;
        }
    }

    private void dfs2(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs2(node.left);
        if (pre == null) {
            pre = node;
        } else {
            if (pre.val > node.val) {
                y2 = node;
                if (x2 == null) {
                    x2 = pre;
                }
            }
            pre = node;
        }

        dfs2(node.right);
    }

    /**
     * 莫里斯遍历
     * S = O(1)
     */
    private void recoverTree3(TreeNode root) {
        if (root == null) {
            return;
        }

        // 记录错误的两个值
        TreeNode x = null, y = null;
        // 记录中序遍历当前节点的前驱
        TreeNode pre = null;
        // 用来完成Morris连接的寻找前驱的指针
        TreeNode predecessor = null;
        while (root != null) {
            // 左子树不为空，1、链接root节点的前驱，他的前驱还没访问，所以root不能现在访问,继续访问root左子树  2、root节点访问,并且断开root节点的前驱连接，然后访问root的右子树
            if (root.left != null) {
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // predecessor.right ！= root,说明了还没执行过1
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明了1已经执行过了，我们执行2
                // predecessor.right == root
                else {
                    // 访问
                    if (pre != null && pre.val > root.val) {
                        y = root;
                        if (x == null) {
                            x = pre;
                        }
                    }
                    // 更新前驱,为下一个节点做准备
                    pre = root;
                    // 断开前驱连接
                    predecessor.right = null;
                    // 访问root右子树
                    root = root.right;
                }
            }
            else { // root.left == null，root不需要链接节点的前驱（他的前驱其实就是pre(第一个节点pre为null)，且已经被访问过了），那么直接访问root
                // 访问
                if (pre != null && pre.val > root.val) {
                    y = root;
                    if (x == null) {
                        x = pre;
                    }
                }
                // 更新前驱,为下一个节点做准备
                pre = root;
                // 访问root的右子树
                root = root.right;
            }
        }

        if (x != null && y != null) {
            int val = x.val;
            x.val = y.val;
            y.val = val;
        }
    }
}
