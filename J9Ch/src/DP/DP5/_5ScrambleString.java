package DP.DP5;
import java.util.*;

//• 区间型动态规划

/*
• 设f[i][j][k]表示S1能否通过变换成为T1
– S1为S从字符i开始的长度为k的子串
– T1为T从字符j开始的长度为k的子串

f[i][j][k] = OR1<=w<=k-1{f[i][j][w] AND f[i+w][j+w][k-w]}
OR
OR1<=w<=k-1{f[i][j+k-w][w] AND f[i+w][j][k-w]}


-----------------------------------------------------------------------------------------------
 */

//  87. Scramble String
//  https://leetcode.com/problems/scramble-string/description/
//  http://www.lintcode.com/zh-cn/problem/scramble-string/
public class _5ScrambleString {

//    Accepted Java solution
    public class Solution01 {
        public boolean isScramble(String s1, String s2) {
            if (s1.equals(s2)) return true;

            int[] letters = new int[26];
            for (int i=0; i<s1.length(); i++) {
                letters[s1.charAt(i)-'a']++;
                letters[s2.charAt(i)-'a']--;
            }
            for (int i=0; i<26; i++) if (letters[i]!=0) return false;

            for (int i=1; i<s1.length(); i++) {
                if (isScramble(s1.substring(0,i), s2.substring(0,i))
                        && isScramble(s1.substring(i), s2.substring(i))) return true;
                if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i))
                        && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) return true;
            }
            return false;
        }
    }
//------------------------------------------------------------------------------
/*
Simple iterative DP Java solution with explanation
    Explanation in code itself. The iterative version of the idea is considerably slower than the recursive simply because here we consider all possible states, while the recursive will only compute required states as it founds them. Time complexity of both is, in any case, the same.
*/

    public class Solution02 {
        public boolean isScramble(String s1, String s2) {
            if (s1.length() != s2.length()) return false;
            int len = s1.length();
            /**
             * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
             * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
             * Let q be the length of a cut (hence, q < k), then we are in the following situation:
             *
             * S1 [   x1    |         x2         ]
             *    i         i + q                i + k - 1
             *
             * here we have two possibilities:
             *
             * S2 [   y1    |         y2         ]
             *    j         j + q                j + k - 1
             *
             * or
             *
             * S2 [       y1        |     y2     ]
             *    j                 j + k - q    j + k - 1
             *
             * which in terms of F means:
             *
             * F(i, j, k) = for some 1 <= q < k we have:
             *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
             *
             * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal
             * */
            boolean [][][] F = new boolean[len][len][len + 1];
            for (int k = 1; k <= len; ++k)
                for (int i = 0; i + k <= len; ++i)
                    for (int j = 0; j + k <= len; ++j)
                        if (k == 1)
                            F[i][j][k] = s1.charAt(i) == s2.charAt(j);
                        else for (int q = 1; q < k && !F[i][j][k]; ++q) {
                            F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) || (F[i][j + k - q][q] && F[i + q][j][k - q]);
                        }
            return F[0][0][len];
        }
    }
//------------------------------------------------------------------------------
/*Java fast DP iteration solution and recursion solution

4
    L liji94188
    Reputation:  476
    Iterative version:*/

    public class Solution03 {
        public boolean isScramble(String s1, String s2) {
            int len = s1.length();
            if(len!=s2.length()) return false;
            if(len==0) return true;
            boolean[][][] isScr = new boolean[len][len][len];
            for(int i = 0; i < len; i++) { //length of current substring, 0 means length==1
                for(int j = 0; j + i < len; j++) { //start point of current substring at s1.
                    for(int k = 0; k + i < len; k++) { //start point of current substring at s2.
                        if(i==0) isScr[i][j][k] = s1.charAt(j)==s2.charAt(k);
                        for(int m = 0; m < i; m++) {
                            if(isScr[m][j][k] && isScr[i-(m+1)][j+m+1][k+m+1]) isScr[i][j][k] = true;
                            else if(isScr[m][j][k+i-m] && isScr[i-(m+1)][j+m+1][k]) isScr[i][j][k] = true;
                        }
                    }
                }
            }
            return isScr[len-1][0][0];
        }
    }



    //    Recursive version: with some pruning check at the beginning, finally get rid of TLE...
    public class Solution04 {
        public boolean isScramble(String s1, String s2) {
            int len= s1.length();
            if(s2.length()!=len) return false;
            if(s1.equals(s2)) return true;
            Map<Character,Integer> checkPermutation = new HashMap<Character,Integer>();
            for(int i = 0; i < len; i++) {
                char a = s1.charAt(i), b = s2.charAt(i);
                if(checkPermutation.containsKey(a))
                    checkPermutation.put(a,checkPermutation.get(a)+1);
                else checkPermutation.put(a,1);
                if(checkPermutation.containsKey(b))
                    checkPermutation.put(b,checkPermutation.get(b)-1);
                else checkPermutation.put(b,-1);
            }
            for(char c : checkPermutation.keySet()) if(checkPermutation.get(c)!=0) return false;

            for(int i = 1; i < s1.length(); i++) {
                if(isScramble(s1.substring(0,i),s2.substring(0,i)) &&
                        isScramble(s1.substring(i,len),s2.substring(i,len)))
                    return true;
                else if(isScramble(s1.substring(0,i),s2.substring(len-i,len)) &&
                        isScramble(s1.substring(i,len),s2.substring(0,len-i))) return true;
            }
            return false;
        }
    }

//------------------------------------------------------------------------------
/*    2ms Java Recursive solution (beat 100%)

4
    A antiquity
    Reputation:  18*/
    public class Solution05 {
        int[] p = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};
        public boolean isScramble(String s1, String s2) {
            int l1=s1.length(), l2=s2.length();
            if(l1!=l2) return false;
            if(l1<=1) return s1.equals(s2);
            if(s1.equals(s2)) return true;
            long a=1, b=1, c=1;
            for(int i=0; i<l1; i++){
                if(i>0 && a==b && isScramble(s1.substring(0,i),s2.substring(l2-i)) && isScramble(s1.substring(i),s2.substring(0,l2-i)))
                    return true;
                if(i>0 && a==c && isScramble(s1.substring(0,i),s2.substring(0,i)) && isScramble(s1.substring(i),s2.substring(i)))
                    return true;
                a*=p[s1.charAt(i)-'A'];
                b*=p[s2.charAt(l2-1-i)-'A'];
                c*=p[s2.charAt(i)-'A'];
            }
            return false;
        }
    }
//------------------------------------------------------------------------------
    // 9CH DP
    public boolean isScramble(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        if (s1.length() != s2.length()) {
            return false;
        }

        int n = s1.length();
        boolean[][][] f = new boolean[n][n][n + 1];
        int i, j, k, w;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                f[i][j][1] = c1[i] == c2[j];
            }
        }

        // s t length
        for (k = 2; k <= n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    f[i][j][k] = false;
                    // enumerate partition position (s1's length)
                    for (w = 0; w <= k - 1; w++) {
                        // case 1: no swap
                        // s1 --> t1    s2 -->t2
                        if (f[i][j][w] && f[i + w][j + w][k - w]) {
                            f[i][j][k] = true;
                            break;
                        }
                        // case 2: swap
                        // s1 --> t2     s2 --> t1
                        if (f[i][j + k - w][w] && f[i + w][j][k - w]) {
                            f[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
     }

//------------------------------------------------------------------------------

    // 记忆化搜索
    /**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
    HashMap<String, Boolean> hash = new HashMap<String, Boolean>();

    public boolean isScramble1(String s1, String s2) {
        // Write your code here
        if (s1.length() != s2.length())
            return false;

        if (hash.containsKey(s1 + "#" + s2))
            return hash.get(s1 + "#" + s2);

        int n = s1.length();
        if (n == 1) {
            return s1.charAt(0) == s2.charAt(0);
        }
        for (int k = 1; k < n; ++k) {
            if (isScramble1(s1.substring(0, k), s2.substring(0, k)) &&
                    isScramble1(s1.substring(k, n), s2.substring(k, n)) ||
                    isScramble1(s1.substring(0, k), s2.substring(n - k, n)) &&
                        isScramble1(s1.substring(k, n), s2.substring(0, n - k))
                    ) {
                hash.put(s1 + "#" + s2, true);
                return true;
            }
        }
        hash.put(s1 + "#" + s2, false);
        return false;
    }

//------------------------------------------------------------------------------

    // 递推
    /**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
    public boolean isScramble2(String s1, String s2) {
        // Write your code here
        if (s1.length() != s2.length())
            return false;
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n+1];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);

        for (int len = 2; len <= n; ++len)
            for (int x = 0; x < n && x + len <= n; ++x)
                for (int y = 0; y < n && y + len <=n; ++y)
                    for (int k= 1; k < len; ++k)
                        dp[x][y][len] |= dp[x][y][k] && dp[x + k][y + k][len - k]
                                || dp[x][y + len - k][k] && dp[x + k][y][len - k];

        return dp[0][0][n];
    }

//------------------------------------------------------------------------------

    // 普通搜索
    /**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
    public boolean isScramble3(String s1, String s2) {
        // Write your code here
        if (s1.length() != s2.length()) {
            return false;
        }

        if (s1.length() == 0 || s1.equals(s2)) {
            return true;
        }

        if (!isValid(s1, s2)) {
            return false;
        }// Base Cases


        for (int i = 1; i < s1.length(); i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, s1.length());
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, s2.length());
            String s23 = s2.substring(0, s2.length() - i);
            String s24 = s2.substring(s2.length() - i, s2.length());

            if (isScramble(s11, s21) && isScramble(s12, s22)) return true;
            if (isScramble(s11, s24) && isScramble(s12, s23)) return true;// cut

        }
        return false;
    }


    private boolean isValid(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        if (!(new String(arr1)).equals(new String(arr2))) {
            return false;
        }
        return true;
    }

//------------------------------------------------------------------------------

    // 记忆化搜索
    public class Solution4 {
        /**
         * @param s1 A string
         * @param s2 Another string
         * @return whether s2 is a scrambled string of s1
         */

        private boolean checkScramble(String s1,int start1, String s2, int start2, int k, int [][][]visit) {
            if(visit[start1][start2][k] == 1)
                return true;
            if(visit[start1][start2][k] ==-1)
                return false;


            if (s1.length() != s2.length()) {
                visit[start1][start2][k] = -1;
                return false;
            }

            if (s1.length() == 0 || s1.equals(s2)) {
                visit[start1][start2][k] = 1;
                return true;
            }

            if (!isValid(s1, s2)) {
                visit[start1][start2][k] = -1;
                return false;
            }// Base Cases


            for (int i = 1; i < s1.length(); i++) {
                String s11 = s1.substring(0, i);
                String s12 = s1.substring(i, s1.length());

                String s21 = s2.substring(0, i);
                String s22 = s2.substring(i, s2.length());

                String s23 = s2.substring(0, s2.length() - i);
                String s24 = s2.substring(s2.length() - i, s2.length());

                if (checkScramble(s11,start1, s21, start2, i, visit)
                        && checkScramble(s12, start1+i, s22, start2+i,k-i, visit))  {
                    visit[start1][start2][k] = 1;
                    return true;
                }

                if (checkScramble(s11,start1, s24, start2+k-i, i, visit)
                        && checkScramble(s12, start1+i, s23,start2, k-i, visit))
                {
                    visit[start1][start2][k] = 1;
                    return true;
                }
            }
            visit[start1][start2][k] = -1;
            return false;
        }
        public boolean isScramble(String s1, String s2) {
            int len = s1.length();
            int [][][] visit = new int[len][len][len + 1];
            return checkScramble(s1,0,s2,0, len, visit);
        }


        private boolean isValid(String s1, String s2) {
            char[] arr1 = s1.toCharArray();
            char[] arr2 = s2.toCharArray();
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            if (!(new String(arr1)).equals(new String(arr2))) {
                return false;
            }
            return true;
        }
    }
//------------------------------------------------------------------------------
}

/*  攀爬字符串
给定一个字符串 S1，将其递归地分割成两个非空子字符串,从而将其表示为二叉树。

下面是s1 = "great"的一个可能表达：

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
在攀爬字符串的过程中，我们可以选择其中任意一个非叶节点，然后交换该节点的两个儿子。

例如，我们选择了 "gr" 节点，并将该节点的两个儿子进行交换，从而产生了攀爬字符串 "rgeat"。

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
我们认为， "rgeat" 是 "great" 的一个攀爬字符串.

类似地，如果我们继续将其节点 "eat" 和 "at" 进行交换，就会产生新的攀爬字符串 "rgtae"。

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
同样地，"rgtae" 也是 "great"的一个攀爬字符串。

给定两个相同长度的字符串s1 和 s2，判定 s2 是否为 s1 的攀爬字符串。
 */



/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */