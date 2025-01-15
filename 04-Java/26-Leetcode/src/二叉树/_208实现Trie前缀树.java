package 二叉树;

import tools.Asserts;

/**
 * 创建一棵 26 叉树，一开始只有一个根节点 root。
 * 26 叉树的每个节点包含一个长为 26 的儿子节点列表 son，以及一个布尔值 end，表示是否为终止节点
 */
class TrieNode {
    TrieNode[] son = new TrieNode[26];
    boolean end;
}

public class _208实现Trie前缀树 {
    private TrieNode root;

    public _208实现Trie前缀树() {
        root = new TrieNode();
    }

    /**
     * 在Trie（前缀树）中插入一个单词
     * 这个方法从根节点开始，逐字符地将单词中的每个字符插入到Trie中
     * 如果 necessary 的节点不存在，它会创建新的节点
     * 最后，标记单词的末尾节点，表示一个单词的结束
     *
     * @param word 要插入的单词，由小写字母组成
     */
    public void insert(String word) {
        // 从根节点开始
        TrieNode cur = root;
        // 遍历单词中的每个字符
        for (char c : word.toCharArray()) {
            // 将字符转换为在子节点数组中的索引（'a' -> 0, 'b' -> 1, ..., 'z' -> 25）
            c -= 'a';
            // 如果当前字符对应的节点不存在，则创建一个新的节点
            if (cur.son[c] == null) {
                cur.son[c] = new TrieNode();
            }
            // 将当前节点移动到下一个字符对应的节点
            cur = cur.son[c];
        }
        // 标记最后一个节点为单词的末尾
        cur.end = true;
    }

    public boolean search(String word) {
        TrieNode trieNode = find(word);
        return trieNode != null && trieNode.end;
    }

    public boolean startsWith(String prefix) {
        TrieNode trieNode = find(prefix);
        return trieNode != null;
    }

    /**
     * 在Trie树中查找一个单词对应的节点
     *
     * @param word 待查找的单词
     * @return 返回找到的TrieNode节点，如果单词不存在则返回null
     */
    private TrieNode find(String word) {
        // 从Trie树的根节点开始查找
        TrieNode cur = root;
        // 遍历单词中的每个字符
        for (char c : word.toCharArray()) {
            // 将字符转换为对应的索引位置（'a'到'z'对应0到25）
            c -= 'a';
            // 如果当前字符对应的子节点不存在，则返回null
            if (cur.son[c] == null) {
                return null;
            }
            // 移动到当前字符对应的子节点
            cur = cur.son[c];
        }
        // 返回最后一个字符对应的节点
        return cur;
    }

    public static void main(String[] args) {
        _208实现Trie前缀树 trie = new _208实现Trie前缀树();
        trie.insert("apple");
        Asserts.test(trie.search("apple"));
        Asserts.test(!trie.search("app"));
        Asserts.test(trie.startsWith("app"));
        trie.insert("app");
        Asserts.test(trie.search("app"));
    }
}
