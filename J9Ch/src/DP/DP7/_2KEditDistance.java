package DP.DP7;

import java.util.ArrayList;
import java.util.List;

/*
• 设f[sP][j]为前缀sP(即节点P对应的字符串)和Target前j个字符Target[0..j-1] 的最小编辑距离
• 设P的父亲是Q
f[sP][j] = min{f[sP][j-1]+1, f[sQ][j-1]+1, f[sQ][j]+1, f[sQ][j-1]|sP[last]=Target[j-1]}

f[sP][j-1]+1 情况一:SP在最后插 入Target[j-1]
f[sQ][j-1]+1 情况二: SP最后一个字符 替换成Target[j-1]
f[sQ][j]+1   情况三: SP删掉最后一 个字符
f[sQ][j-1]   情况四:SP和Target最后 一个字符相等

• 设f[sP][j]为前缀sP(即节点P对应的字符串)和Target前j个字符Target[0..j-1] 的最小编辑距离
• 初始条件:一个空串和一个长度为L的串的最小编辑距离是L – f[sroot][j] = f[""][j] = j (j = 0, 1, 2, ..., n)
– f[sp][0] = length(sp)
 */

//K Edit Distance
public class _2KEditDistance {

///////////////////////////////////////////////////////////////////////////
    /**
     * @param words a set of stirngs
     * @param target a target string
     * @param k an integer
     * @return output all the strings that meet the requirements
     */
    public List<String> kDistance(String[] words, String target, int k) {
        // Write your code here
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++)
            TrieNode.addWord(root, words[i]);

        List<String> result = new ArrayList<String>();

        int n = target.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; ++i)
            dp[i] = i;

        find(root, result, k, target, dp);
        return result;
    }

    public void find(TrieNode node, List<String> result, int k, String target, int[] dp) {
        int n = target.length();
        // dp[i] 表示从Trie的root节点走到当前node节点，形成的Prefix
        // 和 target的前i个字符的最小编辑距离
        if (node.hasWord && dp[n] <= k) {
            result.add(node.str);
        }
        int[] next = new int[n + 1];
        for (int i = 0; i <= n; ++i)
            next[i] = 0;

        for (int i = 0; i < 26; ++i)
            if (node.children[i] != null) {
                next[0] = dp[0] + 1;
                for (int j = 1; j <= n; j++) {
                    if (target.charAt(j - 1) - 'a' == i) {
                        next[j] = Math.min(dp[j - 1], Math.min(next[j - 1] + 1, dp[j] + 1));
                    } else {
                        next[j] = Math.min(dp[j - 1] + 1, Math.min(next[j - 1] + 1, dp[j] + 1));
                    }
                }
                find(node.children[i], result, k, target, next);
            }
    }

///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

}
/*
Given a set of strings which just has lower case letters and a target string, output all the strings for each the edit distance with the target no greater than k.
You have the following 3 operations permitted on a word:
Insert a character
Delete a character
Replace a character
 */
