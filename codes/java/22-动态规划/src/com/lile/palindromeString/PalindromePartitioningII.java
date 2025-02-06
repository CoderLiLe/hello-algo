package com.lile.palindromeString;

/**
 * 【132. Palindrome Partitioning II】
 * Difficult
 * https://leetcode-cn.com/problems/palindrome-partitioning-ii/
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 */


import com.lile.tools.Times;

/**
 * 动规五部曲
 * 1、确定 dp 数组（dp table）以及下标的含义
 *  dp[i]：范围是[0, i]的回文子串，最少分割次数是 dp[i]
 *
 * 2、确定递推公式
 * 如果长度为 [0, i] 的子串分割点为 j，那么分割后，区间 [j + 1, i] 是回文子串，则 dp[i] = dp[j] + 1
 * 本题要找到最少分割次数，所以遍历 j 的时候要取最小的 dp[i]
 * 所以递推公式为：dp[i] = min(dp[i], dp[j] + 1)
 *
 * 3、dp 数组如何初始化
 * 长度为 1 的字符串最小分割次数为0，故 dp[0] = 0
 * 由递推公式知非零下标的 dp[i] 应该初始化为一个最大数，这样递推公式在计算结果的时候才不会被初始值覆盖！
 * 也可以这样初始化，更具dp[i]的定义，dp[i]的最大值其实就是i，也就是把每个字符分割出来
 *
 * 4、确定遍历顺序
 * 遍历 i 的 for 循环一定在外层，遍历 j 的 for 循环在内层才能通过计算 dp[j] 数值推导出 dp[i]
 *
 * 5、举例推导 dp 数组
 */

public class PalindromePartitioningII {
    public static void main(String[] args) {
        String str = "aab";
        String str1 = "aacddfdafdsfdfafdfsfsafesdfasfasfsaaafffkasnfkankasfaknfkaacddfdafdsfdfafdfsfsafesdfasfasfsaaafffkasnfkankasfaknfkfffffffffddddddddddaacddfdafdsfdfafdfsfsafesdfasfasfsaaafffkasnfkankasfaknfk" +
                "fffffffffddddddddddddkklmsldmflsmflsamdlasmdlasdlafsdklfmlsamflsmflglglfslkfmlsfmlkmflsmflasmflamsflasmflglemklmfklsdmfklasmfklasmflklsdnflkdflksmflmsdflmklsamflsakdkflsdfkkdkkkkkkkdkdkdkdkkdkdkdk" +
                "dkkkkfkkffkkfkfnkdfklmfklsmaflksmaflsamnfklsdklmlksdmlksmdflkmflkmklsamdflsdmcksdfsdlknfldsfmlskdmflsamflasmflsamflmalkmldfmlsamdflamsdflamflmdmmsldmflamflamlfmsdklfmskdlgmdlfsmalsmflasfmlascsdfam" +
                "sfllfnkasfnamlgslmfklmfksmflksafmlsamfkkdkkdkdkdkdkkdkkkkkkkkdddddddkkkkllalffcmmccgnkgndlflmflsamflmsalcsdfnsakfnskflsakfmlksamflascndflsamlfmslmflsamlfmsalfmaslngakflkamslfkmsalfmaslmflsafmlsama" +
                "lgmmfmkldfmklmkkkkkkkkkklkllllllllslsssllslfmdkkdkfklsfklsmfklmsfklsmaflksmaflkmsalfkmsalkfmlksamflksmflksamclmsdmlsmflasmflasgmlasmfasmfsangkdnglkdmlkmflksamflsmaflkmsaklfmklsamflsmalcmlsmdflsmald" +
                "gkmlglgnkkfkkkkkkkkkkkkkkkeeeeeeeeeeeeecdsfasnflsamfffdddfdfaasfddddddmkmklmlvvrrmflmaflamfdppppllllloooefnfnfnfnfnkdfnasncncnncnckfkafannvndfnaslfnalncnncdfkfnlanlclacanknfalnflnsaczcakmdlkasfmla" +
                "sfasfassdsafsafsdfddkklmsldmflsmflsamdlasmdlasdlafsdklfmlsamflsmflglglfslkfmlsfmlkmflsmflasmflamsflasmflglemklmfklsdmfklasmfklasmflklsdnflkdflksmflmsdflmklsamflsakdkflsdfkkdkkkkkkkdkdkdkdkkdkdkdkd" +
                "kkkkfkkffkkfkfnkdfklmfklsmaflksmaflsamnfklsdklmlksdmlksmdflkmflkmklsamdflsdmcksdfsdlknfldsfmlskdmflsamflasmflsamflmalkmldfmlsamdflamsdflamflmdmmsldmflamflamlfmsdklfmskdlgmdlfsmalsmflasfmlascsdfams" +
                "fllfnkasfnamlgslmfklmfksmflksafmlsamfkkdkkdkdkdkdkkdkkkkkkkkdddddddkkkkllalffcmmccgnkgndlflmflsamflmsalcsdfnsakfnskflsakfmlksamflascndflsamlfmslmflsamlfmsalfmaslngakflkamslfkmsalfmaslmflsafmlsamalg" +
                "mmfmkldfmklmkkkkkkkkkklkllllllllslsssllslfmdkkdkfklsfklsmfklmsfklsmaflksmaflkmsalfkmsalkfmlksamflksmflksamclmsdmlsmflasmflasgmlasmfasmfsangkdnglkdmlkmflksamflsmaflkmsaklfmklsamflsmalcmlsmdflsmaldgk" +
                "mlglgnkkfkkkkkkkkkkkkkkkeeeeeeeeeeeeecdsfasnflsamfffdddfdfaasfddddddmkmklmlvvrrmflmaflamfdppppllllloooefnfnfnfnfnkdfnasncncnncnckfkafannvndfnaslfnalncnncdfkfnlanlclacanknfalnflnsaczcakmdlkasfmlasfas" +
                "fassdsafsafsdffffffffffddddddddddaacddfdafdsfdfafdfsfsafesdfasfasfsaaafffkasnfkankasfaknfkfffffffffddddddddddddkklmsldmflsmflsamdlasmdlasdlafsdklfmlsamflsmflglglfslkfmlsfmlkmflsmflasmflamsflasmflgle" +
                "mklmfklsdmfklasmfklasmflklsdnflkdflksmflmsdflmklsamflsakdkflsdfkkdkkkkkkkdkdkdkdkkdkdkdkdkkkkfkkffkkfkfnkdfklmfklsmaflksmaflsamnfklsdklmlksdmlksmdflkmflkmklsamdflsdmcksdfsdlknfldsfmlskdmflsamflasmfls" +
                "amflmalkmldfmlsamdflamsdflamflmdmmsldmflamflamlfmsdklfmskdlgmdlfsmalsmflasfmlascsdfamsfllfnkasfnamlgslmfklmfksmflksafmlsamfkkdkkdkdkdkdkkdkkkkkkkkdddddddkkkkllalffcmmccgnkgndlflmflsamflmsalcsdfnsakfn" +
                "skflsakfmlksamflascndflsamlfmslmflsamlfmsalfmaslngakflkamslfkmsalfmaslmflsafmlsamalgmmfmkldfmklmkkkkkkkkkklkllllllllslsssllslfmdkkdkfklsfklsmfklmsfklsmaflksmaflkmsalfkmsalkfmlksamflksmflksamclmsdmlsm" +
                "flasmflasgmlasmfasmfsangkdnglkdmlkmflksamflsmaflkmsaklfmklsamflsmalcmlsmdflsmaldgkmlglgnkkfkkkkkkkkkkkkkkkeeeeeeeeeeeeecdsfasnflsamfffdddfdfaasfddddddmkmklmlvvrrmflmaflamfdppppllllloooefnfnfnfnfnkdfn" +
                "asncncnncnckfkafannvndfnaslfnalncnncdfkfnlanlclacanknfalnflnsaczcakmdlkasfmlasfasfassdsafsafsdfddkklmsldmflsmflsamdlasmdlasdlafsdklfmlsamflsmflglglfslkfmlsfmlkmflsmflasmflamsflasmflglemklmfklsdmfklas" +
                "mfklasmflklsdnflkdflksmflmsdflmklsamflsakdkflsdfkkdkkkkkkkdkdkdkdkkdkdkdkdkkkkfkkffkkfkfnkdfklmfklsmaflksmaflsamnfklsdklmlksdmlksmdflkmflkmklsamdflsdmcksdfsdlknfldsfmlskdmflsamflasmflsamflmalkmldfmls" +
                "amdflamsdflamflmdmmsldmflamflamlfmsdklfmskdlgmdlfsmalsmflasfmlascsdfamsfllfnkasfnamlgslmfklmfksmflksafmlsamfkkdkkdkdkdkdkkdkkkkkkkkdddddddkkkkllalffcmmccgnkgndlflmflsamflmsalcsdfnsakfnskflsakfmlksamfl" +
                "ascndflsamlfmslmflsamlfmsalfmaslngakflkamslfkmsalfmaslmflsafmlsamalgmmfmkldfmklmkkkkkkkkkklkllllllllslsssllslfmdkkdkfklsfklsmfklmsfklsmaflksmaflkmsalfkmsalkfmlksamflksmflksamclmsdmlsmflasmflasgmlasmfa" +
                "smfsangkdnglkdmlkmflksamflsmaflkmsaklfmklsamflsmalcmlsmdflsmaldgkmlglgnkkfkkkkkkkkkkkkkkkeeeeeeeeeeeeecdsfasnflsamfffdddfdfaasfddddddmkmklmlvvrrmflmaflamfdppppllllloooefnfnfnfnfnkdfnasncncnncnckfkafan" +
                "nvndfnaslfnalncnncdfkfnlanlclacanknfalnflnsaczcakmdlkasfmlasfasfassdsafsafsdf";
        String str2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        PalindromePartitioningII pp = new PalindromePartitioningII();
        Times.test("minCut", () -> {
            System.out.println(pp.minCut(str2));
        });
    }

    /**
     * 动态规划：T = O(n^2), S = O(n)
     */
    public int minCut(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        boolean[][] isPalindromic = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (cs[i] == cs[j] && (j - i <= 1 || isPalindromic[i + 1][j - 1])) {
                    isPalindromic[i][j] = true;
                }
            }
        }

        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i] = i;
        }

        for (int i = 1; i < n; i++) {
            if (isPalindromic[0][i]) {
                dp[i] = 0;
                continue;
            }

            for (int j = 0; j < i; j++) {
                if (isPalindromic[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }
}
