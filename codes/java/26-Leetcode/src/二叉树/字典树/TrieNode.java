package 二叉树.字典树;

/**
 * 创建一棵 26 叉树，一开始只有一个根节点 root。
 * 26 叉树的每个节点包含一个长为 26 的儿子节点列表 son，以及一个布尔值 end，表示是否为终止节点
 */
public class TrieNode {
    TrieNode[] son = new TrieNode[26];
    boolean end;
}
