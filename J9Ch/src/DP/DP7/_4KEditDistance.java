package DP.DP7;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
-----------------------------------------------------------------------------------------------
LintCode 623 K Edit Distance

• 题意:
• 给定n个字符串，以及目标字符串Target
• 问哪些字符串和Target的编辑距离不大于K
• 一次编辑包括插入一个字符或删除一个字符或修改一个字符
• 例子: • 输入:
    – A = ["abc", "abd", "abcd", "adc"]
    – Target = "ac”
    – K=1
• 输出: ["abc", "adc"]
-----------------------------------------------------------------------------------------------
题目分析

• 这题和Edit Distance非常类似，只是要求多个字符串和Target的最小编 辑距离
• 可以依次求每个字符串s和Target的最小编辑距离
– 设f[i][j]为s前i个字符s[0..i-1]和Target前j个字符Target[0..j-1]的最小编辑距离
• 存在重复计算
– 如果给定的字符串是"abca", "abcb", "abcc"
– 三个字符串的前3个字符都一样
– "abcda"前0~3个字符和Target前0~n个字符的最小编辑距离
– "abcdb"前0~3个字符和Target前0~n个字符的最小编辑距离
– "abcdc"前0~3个字符和Target前0~n个字符的最小编辑距离
重复计算了3次

• 如何避免重复计算
• 如果几个字符串共享一段前缀，他们对应的f[i][j]可以共享，即只计算一次
• 如何知道哪些字符串共享前缀?如何共享f[i][j]?
• 数据结构Trie:字母树
-----------------------------------------------------------------------------------------------
字母树
一个词的所有前缀就是从根到这个词的节点路径上形成的所有的字符串
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 在Edit Distance一题中，状态是f[i][j]为A前i个字符A[0..i-1]和Target前j个 字符Target[0..j-1]的最小编辑距离
    – 设Target长度是n
    – A每个前缀和Target所有前缀的最小编辑距离是:
        • f[0][0]~f[0][n]
        • f[1][0]~f[1][n]
        • f[2][0]~f[2][n]
        •...
• 现在，因为有多个字符串A1, A2, ...，我们可以将用f[前缀][j]表示一个前 缀和Target前j个字符的最小编辑距离



-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• 设f[sP][j]为前缀sP(即节点P对应的字符串)和Target前j个字符Target[0..j-1] 的最小编辑距离
• 设P的父亲是Q
f[sP][j] = min{f[sP][j-1]+1, f[sQ][j-1]+1, f[sQ][j]+1, f[sQ][j-1]|sP[last]=Target[j-1]}

f[sP][j-1]+1 情况一:SP在最后插 入Target[j-1]
f[sQ][j-1]+1 情况二: SP最后一个字符 替换成Target[j-1]
f[sQ][j]+1   情况三: SP删掉最后一 个字符
f[sQ][j-1]   情况四:SP和Target最后 一个字符相等
-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 设f[sP][j]为前缀sP(即节点P对应的字符串)和Target前j个字符Target[0..j-1] 的最小编辑距离
• 初始条件:一个空串和一个长度为L的串的最小编辑距离是L
    – f[sroot][j] = f[""][j] = j (j = 0, 1, 2, ..., n)
    – f[sp][0] = length(sp)
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• 初始化f[sroot][0]~f[sroot][n]
• 按照字母树深度优先搜索顺序计算每个f[sP][0]~f[sP][n]
• 答案是满足f[sP][n]<=K且sP为一个给定的单词的节点P的个数
• 时间复杂度(计算步数)O(前缀个数*N)，空间复杂度(数组大小) O(前缀个数*N)
-----------------------------------------------------------------------------------------------
序列+哈希表

• 在序列+状态型动态规划中，如果状态数过多，直接开数组会空间过大
• 在实际操作中，可以用哈希表来存储可能达到的状态
• 节省空间

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
 */

// K Edit Distance
public class _4KEditDistance {

//------------------------------------------------------------------------------
    // 9Ch DP
    char[] target = null;
    int K = 0;
    int[] f = null;
    int n = 0;
    List<String> res = null;

    // at node p, prefix Sp
    // f now means f[Sp][0~n]
    // todo: Sp-->(Sp, 'a')...,(Sp, 'z')

    private void dfs(Trienode2 p, int[] f) {
        int[] nf = new int[n + 1];
        // whether p has word
        if (p.hasWord) {
            if (f[n] <= K) {
                res.add(p.word);
            }
        }

        int i, j, c;
        // next char is i
        for (i = 0; i < 26; i++) {
            if (p.sons[i] == null) {
                continue;
            }

            // f[i][0] = i
            // f[0] = f[Sp][0] = |Sp|
            nf[0] = f[0] + 1;
            for (j = 1; j <= n; j++) {
                // f[i][j] = min(f[i-1][j]+1, f[i-1][j-1]+1, f[i][j-1]+1)
                nf[j] = Math.min(Math.min(nf[j - 1] + 1, f[j] + 1), f[j - 1] + 1);
                c = target[j - 1] - 'a'; // - 'a'
                if (c == i) {
                    // f[i][j] = min(f[i][j], f[i-1][j-1]) | A[i-1]==target[j-1]
                    nf[j] = Math.min(nf[j], f[j - 1]);
                }
            }
            dfs(p.sons[i], nf);
        }
    }

    public List<String> kDistance1(String[] words, String targetStr, int k) {
        target = targetStr.toCharArray();
        n = target.length;
        K = k;
        res = new ArrayList<String>();

        // 1 init trie
        // root
        Trienode2 root = new Trienode2();
        for (int i = 0; i < words.length; i++) {
            Trienode2.Insert(root, words[i]);
        }

        // 2 init f
        // f[""][0 ~ n]
        f = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            f[i] = i;
        }

        // 3 dfs
        dfs(root, f);

        // 4 return
        return res;
    }

    /*
    {"abc", "abd", "abcd", "adc"}
target = "ac"  k = 1
return"abc" "adc"
     */

    @Test
    public void  test01() {
        String[] words = {"abc", "abd", "abcd", "adc"};
        String targetStr = "ac" ;
        int k = 1;
        System.out.println(kDistance1(words, targetStr, 1));
    }

//------------------------------------------------------------------------------
    /**
     * @param words a set of stirngs
     * @param target a target string
     * @param k an integer
     * @return output all the strings that meet the requirements
     */
    public List<String> kDistance2(String[] words, String target, int k) {
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

//------------------------------------------------------------------------------
    // Top
    //Edit Distance I， recursion
    private int match(String word1, String word2, int i, int j, int[][] count) {
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        //记忆化搜索
        if (count[i][j] != 0) {
            return count[i][j];
        }
        int res;
        if (word1.charAt(i) == word2.charAt(j)) {
            res = match(word1, word2, i + 1, j + 1, count);
        } else {
            //Case 1: insert
            int insert = match(word1, word2, i, j + 1, count);
            //Case 2: delete
            int delete = match(word1, word2, i + 1, j, count);
            //Case 3: replace
            int replace = match(word1, word2, i + 1, j + 1, count);
            res = Math.min(Math.min(insert, delete), replace) + 1;
        }
        count[i][j] = res;
        return res;
    }

    //Edit Distance II， iteration
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        if (word1.length() == 0 || word2.length() == 0) {
            return word1.length() == 0 ? word2.length() : word1.length();
        }

        int[][] match = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            match[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            match[0][j] = j;
        }

        for (int i =  0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j))
                    match[i + 1][j + 1] = match[i][j];
                else {
                    match[i + 1][j + 1] = Math.min(Math.min(match[i][j], match[i][j + 1]), match[i + 1][j]) + 1;
                }
            }
        }
        return match[word1.length()][word2.length()];
    }
//------------------------------------------------------------------------------

}
/*
Given a set of strings which just has lower case letters and a target string, output all the strings for each the edit distance with the target no greater than k.
You have the following 3 operations permitted on a word:
Insert a character
Delete a character
Replace a character

{"abc", "abd", "abcd", "adc"}
target = "ac"  k = 1
return"abc" "adc"
 */
