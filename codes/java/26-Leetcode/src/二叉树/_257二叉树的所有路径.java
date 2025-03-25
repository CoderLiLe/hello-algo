package 二叉树;

import common.TreeNode;

import java.util.*;

public class _257二叉树的所有路径 {
    /**
     * 深度优先搜索
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        backtrack(root, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(TreeNode root, List<Integer> path, List<String> res) {
        // 前序遍历
        path.add(root.val);
        // 遇到叶子结点
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            res.add(sb.toString());
            return;
        }

        // 递归和回溯是同时进行，所以要放在同一个花括号里
        if (root.left != null) {
            backtrack(root.left, path, res);
            path.removeLast();
        }
        if (root.right != null) {
            backtrack(root.right, path, res);
            path.removeLast();
        }
    }

    /**
     * 广度搜索-层序遍历
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();
        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();
            if (node.left == null && node.right == null) {
                paths.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    pathQueue.offer(path + "->" + node.left.val);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    pathQueue.offer(path + "->" + node.right.val);
                }
            }
        }
        return paths;
    }

    /**
     * 深度优先搜索（非递归写法）
     * @param root
     * @return
     */
    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        // 栈中节点和路径都是成对出现的，路径表示的是从根节点到当前
        // 节点的路径，如果到达根节点，说明找到了一条完整的路径
        Stack<Object> stack = new Stack<>();
        // 当前节点和路径同时入栈
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            // 节点和路径同时出栈
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            // 如果是根节点，说明找到了一条完整路径，把它加入到集合中
            if (node.left == null && node.right == null) {
                res.add(path);
            }
            //右 子节点不为空就把右子节点和路径压栈
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
            // 左子节点不为空就把左子节点和路径压栈
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }
        return res;
    }
}
