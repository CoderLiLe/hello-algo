package common;

import print.BTPrinter;

import java.util.Random;

public class TestMorris {
    // 二叉树的莫里斯(Morris)遍历
    private static class MorrisTree extends BinarySearchTree<Integer> {
        public void preorder() {
            Node<Integer> node = root;
            while (node != null) {
                if (node.left != null) {
                    Node<Integer> pred = node.left;
                    while (pred.right != null && pred.right != node) {
                        pred = pred.right;
                    }

                    if (pred.right == null) { // 第一次到达左子树的最右端
                        System.out.print(node.element + " ");
                        pred.right = node;
                        node = node.left;
                    } else { // 第二次到达左子树的最右端
                        pred.right = null;
                        node = node.right;
                    }
                } else {
                    System.out.print(node.element + " ");
                    node = node.right;
                }
            }
        }

        public void inorder() {
            Node<Integer> node = root;
            while (node != null) {
                if (node.left != null) {
                    Node<Integer> pred = node.left;
                    while (pred.right != null && pred.right != node) {
                        pred = pred.right;
                    }

                    if (pred.right == null) {
                        pred.right = node;
                        node = node.left;
                    } else {
                        System.out.print(node.element + " ");
                        pred.right = null;
                        node = node.right;
                    }
                } else {
                    System.out.print(node.element + " ");
                    node = node.right;
                }
            }
        }

        public void postorder() {
            Node<Integer> node = root;
            while (node != null) {
                if (node.left != null) {
                    Node<Integer> pred = node.left;
                    while (pred.right != null && pred.right != node) {
                        pred = pred.right;
                    }

                    if (pred.right == null) {
                        pred.right = node;
                        node = node.left;
                    } else {
                        pred.right = null;
                        postorderPrint(node.left);
                        node = node.right;
                    }
                } else {
                    node = node.right;
                }
            }
            postorderPrint(root);
        }

        private void postorderPrint(Node<Integer> node) {
            Node tail = reverseNode(node);
            Node cur = tail;
            while (cur != null) {
                System.out.print(cur.element + " ");
                cur = cur.right;
            }
            reverseNode(tail);
        }

        private Node<Integer> reverseNode(Node<Integer> node) {
            Node pre = null;
            Node next = null;
            while (node != null) {
                next = node.right;
                node.right = pre;
                pre = node;
                node = next;
            }
            return pre;
        }
    }

    public static void main(String[] args) {
        MorrisTree tree = new MorrisTree();
        for (int i = 0; i < 10; i++) {
            tree.add(new Random().nextInt(100));
        }
        BTPrinter.println(tree);
        System.out.println("--------- 前序遍历 ---------");
        tree.preorder();
        System.out.println("\n--------- 前序遍历 ---------");
        System.out.println("--------- 中序遍历 ---------");
        tree.inorder();
        System.out.println("\n--------- 中序遍历 ---------");
        System.out.println("--------- 后序遍历 ---------");
        tree.postorder();
        System.out.println("\n--------- 后序遍历 ---------");
        BTPrinter.println(tree);
    }
}
