# [28.找出字符串中第一个匹配项的下标](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/)


给你两个字符串 `haystack` 和 `needle` ，请你在 `haystack` 字符串中找出 `needle` 字符串的第一个匹配项的下标（下标从 `0` 开始）。如果 `needle` 不是 `haystack` 的一部分，则返回  `-1` 。

 

示例 1：
```
输入：haystack = "sadbutsad", needle = "sad"
输出：0
解释："sad" 在下标 0 和 6 处匹配。
第一个匹配项的下标是 0 ，所以返回 0 。
```

示例 2：
```
输入：haystack = "leetcode", needle = "leeto"
输出：-1
解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
```

提示：

- 1 <= haystack.length, needle.length <= 104
- haystack 和 needle 仅由小写英文字符组成

## 朴素解法

直观的解法的是：枚举原串 ss 中的每个字符作为「发起点」，每次从原串的「发起点」和匹配串的「首位」开始尝试匹配：

- 匹配成功：返回本次匹配的原串「发起点」。
- 匹配失败：枚举原串的下一个「发起点」，重新尝试匹配。

```java
class Solution {
    public int strStr(String ss, String pp) {
        int n = ss.length(), m = pp.length();
        char[] s = ss.toCharArray(), p = pp.toCharArray();
        // 枚举原串的「发起点」
        for (int i = 0; i <= n - m; i++) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int a = i, b = 0;
            while (b < m && s[a] == p[b]) {
                a++;
                b++;
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (b == m) return i;
        }
        return -1;
    }
}
```

- 时间复杂度：n 为原串的长度，m 为匹配串的长度。其中枚举的复杂度为 O(n−m)，构造和比较字符串的复杂度为 O(m)。整体复杂度为 O((n−m)∗m)。
- 空间复杂度：O(1)。

## KMP 解法

KMP 算法是一个快速查找匹配串的算法，它的作用其实就是本题问题：**如何快速在「原字符串」中找到「匹配字符串」**。

上述的朴素解法，不考虑剪枝的话复杂度是 O(m∗n) 的，而 KMP 算法的复杂度为 O(m+n)。

**KMP 之所以能够在 O(m+n) 复杂度内完成查找，是因为其能在「非完全匹配」的过程中提取到有效信息进行复用，以减少「重复匹配」的消耗**。

你可能不太理解，没关系，我们可以通过举 🌰 来理解 KMP。

### 1. 匹配过程

在模拟 KMP 匹配过程之前，我们先建立两个概念：

前缀：对于字符串 `abcxxxxefg`，我们称 `abc` 属于 `abcxxxxefg` 的某个前缀。
后缀：对于字符串 `abcxxxxefg`，我们称 `efg` 属于 `abcxxxxefg` 的某个后缀。
然后我们假设原串为 `abeababeabf`，匹配串为 `abeabf`：



