package 二叉树.字典树;

public class _211添加与搜索单词数据结构设计 {
    private TrieNode root;

    public _211添加与搜索单词数据结构设计() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        char[] chars = word.toCharArray();
        TrieNode cur = root;
        for (char c : chars) {
            c -= 'a';
            if (cur.son[c] == null) {
                cur.son[c] = new TrieNode();
            }
            cur = cur.son[c];
        }
        cur.end = true;
    }

    public boolean search(String word) {
        return dfs(word, root, 0);
    }

    private boolean dfs(String s, TrieNode node, int sIdx) {
        int len = s.length();
        if (len == sIdx) {
            return node.end;
        }
        char c = s.charAt(sIdx);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.son[i] != null && dfs(s, node.son[i], sIdx + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            c -= 'a';
            if (node.son[c] == null) {
                return false;
            }
            return dfs(s, node.son[c], sIdx + 1);
        }
    }
}
