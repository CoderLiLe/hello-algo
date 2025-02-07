package 二叉树.字典树;

/**
 * 创建一棵 26 叉树，一开始只有一个根节点 root。
 * 26 叉树的每个节点包含一个长为 26 的儿子节点列表 son，以及一个字符串s，表示查找到的字符串
 */
public class TrieNode2 {
    TrieNode2[] son = new TrieNode2[26];
    String s;
}
