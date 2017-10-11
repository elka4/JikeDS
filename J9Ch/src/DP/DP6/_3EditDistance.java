package DP.DP6;

//双序列型动态规划


/*
• 要求A[0..m-1]和B[0..n-2]的最小编辑距离，A[0..m-2]和B[0..n-1]的最小
编辑距离和A[0..m-2]和B[0..n-2]的最小编辑距离
• 原来是求A[0..m-1]和B[0..n-1]的最小编辑距离
• 子问题
• 状态:设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]的最小编辑距离

• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]的最小编辑距离
• 要求f[m][n]
f[i][j] = min{f[i][j-1]+1, f[i-1][j-1]+1, f[i-1][j]+1, f[i-1][j-1]|A[i-1]=B[j-1]}
f[i][j-1]+1   情况一:A在最后插入 B[j-1]
f[i-1][j-1]+1 情况二:A最后一个字符 替换成B[j-1]
f[i-1][j]+1   情况三:A删掉最后一个 字符
f[i-1][j-1]   情况四:A和B最后一个 字符相等


• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]的最小编辑距离
• 转移方程: f[i][j] = min{f[i][j-1]+1, f[i-1][j-1]+1, f[i-1][j]+1, f[i-1][j-1]|A[i- 1]=B[j-1]}
• 初始条件:一个空串和一个长度为L的串的最小编辑距离是L – f[0][j] = j (j = 0, 1, 2, ..., n)
– f[i][0] = i (i = 0, 1, 2, ..., m)



 */
//  Edit Distance
public class _3EditDistance {
    // 9Ch DP 动态规划版本去掉注释
    public int minDistance(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int i, j;
        int m = s1.length;
        int n = s2.length;

        int[][] f = new int[m + 1][n + 1];

        for (i = 0; i <= m; ++i) {
            for (j = 0; j <= n; ++j) {
                if (i == 0) {
                    f[i][j] = j;
                    continue;
                }

                if (j == 0) {
                    f[i][j] = i;
                    continue;
                }

                // i > 0, j > 0
                // +1, important!
                //                       delete        insert         replace
                f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;

                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                }
            }
        }

        return f[m][n];
    }


//////////////////////////////////////////////////////////////
// 9Ch DP 不能通过
public int minDistance00(String word1, String word2) {
    char[] c1 = word1.toCharArray();
    char[] c2 = word1.toCharArray();
    int m = c1.length;
    int n = c2.length;

    int i, j;
    int[][] f = new int[2][n + 1];
    int old, now = 0;

    for (i = 0; i <= m; i++) {
        old = now;
        now = 1 - now;
        for (j = 0; j <= n; j++) {
            if (i == 0) {
                f[now][j] = j;
                continue;
            }

            if (j == 0) {
                f[now][j] = i;
                continue;
            }
            f[now][j] = Math.min(Math.min(f[old][j], f[now][j - 1]), f[old][j - 1]) + 1;


            // same
            if (c1[i - 1] == c2[j - 1]) {
                f[now][j] = Math.min(f[now][j], f[old][j - 1]);
            }

        }
    }
    return f[now][n];
}

//////////////////////////////////////////////////////////////
    public int minDistance0(String word1, String word2) {
        // write your code here
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];
        for(int i=0; i< m+1; i++){
            dp[0][i] = i; //一次delete
        }
        for(int i=0; i<n+1; i++){
            dp[i][0] = i;//一次insert
        }


        for(int i = 1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 +
                            Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[n][m];
    }
//////////////////////////////////////////////////////////////

    public int minDistance1(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];
        for(int i=0; i< m+1; i++){
            dp[0][i] = i;
        }
        for(int i=0; i<n+1; i++){
            dp[i][0] = i;
        }


        for(int i = 1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[n][m];
    }

//////////////////////////////////////////////////////////////

    // 动态规划班版本
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance2(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int i, j;
        int m = s1.length;
        int n = s2.length;

        int[][] f = new int[m + 1][n + 1];

        // commented part is for outputting solution
        // 'I', 'D', 'R', 'S'
        //char[][] pi = new char[m + 1][n + 1];
        for (i = 0; i <= m; ++i) {
            for (j = 0; j <= n; ++j) {
                if (i == 0) {
                    f[i][j] = j;
                    continue;
                }

                if (j == 0) {
                    f[i][j] = i;
                    continue;
                }

                // i > 0, j > 0

                // +1, important!
                //                       delete        insert         replace
                f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;
                /*if (f[i][j] == f[i - 1][j] + 1) {
                    pi[i][j] = 'D';
                }
                else {
                    if (f[i][j] == f[i][j - 1] + 1) {
                        pi[i][j] = 'I';
                    }
                    else {
                        pi[i][j] = 'R';
                    }
                }*/

                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                    //  pi[i][j] = 'S';
                }
            }
        }

        /*i = m;
        j = n;

        while (i > 0 || j > 0) {
            if (pi[i][j] == 'D') {
                System.out.println("Delete A's " + i + "-th letter from A");
                --i;
                continue;
            }

            if (pi[i][j] == 'I') {
                System.out.println("Insert B's " + j + "-th letter of B to A");
                --j;
                continue;
            }

            if (pi[i][j] == 'R') {
                System.out.println("Replace the A's " + i + "-th letter to B's " + j + "-th letter");
                --i;
                --j;
                continue;
            }

            if (pi[i][j] == 'S') {
                --i;
                --j;
                continue;
            }
        }*/

        return f[m][n];
    }
//////////////////////////////////////////////////////////////
    // leet
    //Java DP solution - O(nm)

    public int minDistance3(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    cost[i + 1][j + 1]++;
                }
            }
        }
        return cost[m][n];
    }
//////////////////////////////////////////////////////////////
    // My Accepted Java Solution

    public int minDistance4(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.abs(word1.length() - word2.length());
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////

}
/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Have you met this question in a real interview? Yes
Example
Given word1 = "mart" and word2 = "karma", return 3.
 */
