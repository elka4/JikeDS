package DP.DP4;
import org.junit.Test;
import java.util.*;

//• 划分型动态规划

/*
• 设f[i]为S前i个字符S[0..i-1]最少可以划分成几个回文串

f[i] = minj=0,...,i-1{f[j] + 1 | S[j..i-1]是回文串}


f[i] S前i个字符最少可以 划分成几个回文串
f[j] S前j个字符最少可以 划分成几个回文串
S[j..i-1] 最后一段回文串


• 设f[i]为S前i个字符S[0..i-1]最少可以划分成几个回文串
• f[i] = minj=0,...,i-1{f[j] + 1| S[j..i-1]是回文串}
• 初始条件:空串可以被分成0个回文串 – f[0] = 0

• 计算f[0], f[1], ..., f[N]
• 答案是f[N]

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

 */


//  Palindrome Partitioning II
//  https://leetcode.com/problems/palindrome-partitioning-ii/description/
//  http://www.lintcode.com/zh-cn/problem/palindrome-partitioning-ii/
public class _2PalindromePartitioning_II {
/*    Easiest Java DP Solution (97.36%)
    This can be solved by two points:

    cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
    If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
    The 2nd point reminds us of using dp (caching).

    a   b   a   |   c  c
    j  i
    j-1  |  [j, i] is palindrome
    cut(j-1) +  1
    Hope it helps!*/

    public int minCut01(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n];
        boolean[][] pal = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = 0; j <= i; j++) {
                if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }



//------------------------------------------------------------------------------//////


/*    DP solution; & some thoughts
    Some thoughts:

            return the mininum cut of the partition s => optimization => DP
try to divide & conqure =>
    public int minCutRecur(String s){
        int n = s.length;

        //base case
        if(s < 2 || isPalindr(s)) return 0;
        int min = n - 1;
        for(int i = 1; i <= n - 1; i++){
            if(isPalindr(s)){
                min = Math.min(min, 1 + minCutRecur(s.substring(i)));
            }
        }

        return min;
    }

    However, sub problem overlapped (are not independent with each other).

    Use DP to build the solution from bottom up.*/

    public int minCut02(String s) {
        int n = s.length();

        //isPalindr[i][j] = true means s[i:j) is a valid palindrome
        boolean[][] isPalindr = new boolean[n + 1][n + 1];

        //dp[i] means the minCut for s[0:i) to be partitioned
        int[] dp = new int[n + 1];

        //initialize the value for each dp state.
        for(int i = 0; i <= n; i++)
            dp[i] = i - 1;

        for(int i = 2; i <= n; i++){
            for(int j = i - 1; j >= 0; j--){
                //if(isPalindr[j][i]){
                if(s.charAt(i - 1) == s.charAt(j) &&
                        (i - 1 - j < 2 || isPalindr[j + 1][i - 1])){
                    isPalindr[j][i] = true;
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }

/*    Several optimizations include:

    No need to check if a string is a palindrome or not inside the loop by adjusting the order of getting of solution of the sub problems.
    assign dp[0] to be -1 so that when s[0:i) is a palindrome by itself, dp[i] is 0. This is for the consistency of the code.
    The time complexity and the space complexity are both O(n ^ 2).*/
//------------------------------------------------------------------------------//////

//    My accepted Java solution
//    I've solved the problem some time ago (with DP, of course), and I thought I would share it:

    public class Solution03 {
        int[] cost;
        public int minCut(String s) {
            if (s == null || s.length() < 2) return 0;
            int N = s.length();
            cost = new int[N];
            for (int i = 0; i < N; i++)
                cost[i] = Integer.MAX_VALUE;
            cut(s);
            return cost[N-1];
        }

        private void cut (String s) {
            if (s.length() > 0) cost[0] = 0;
            if (s.length() > 1) cost[1] = s.charAt(1) == s.charAt(0) ? 0 : 1;
            int k = 0, l = 0, ni = 0;
            for (int i = 2; i < s.length(); i++) {
                if (cost[i] == Integer.MAX_VALUE) cost[i] = cost[i-1]+1;
                for (int j = 1; j <= 2; j++) {
                    for (k = i-j, l = i;
                         k >= 0 && l <= s.length()-1 && s.charAt(k) == s.charAt(l);
                         k--, l++) {
                        int c = k == 0 ? 0 : cost[k-1]+1;
                        if (cost[l] > c) cost[l] = c;
                    }
                }
            }
        }
    }
   /* Explanation
    So my thought process was go through the string, char by char starting from the left. The current character would be the center of the palindrome and expand as much as I can. After it ceases to be a palindrome (e.g no longer matching end chars or reached the boundary of the string), the cost would be
"1 + the cost of the substring I already processed to the left of the current palindrome I am on". I compare this cost under the index of the last char of my current palindrome. If it is less than what was previously recorded, the I record this new cost.

    For example, let's take 'abb':

    Start with 'a'. Obviously a cost of 0 (base case)
    Now we go to 'b' (index 1). Since we haven't visited 'b', let's assign an initial cost to it, which is 1 + cost[0]. So cost[1] = 0+1 = 1. This makes sense since you only need a 1 cut to get two palindromes: 'a' and 'b'
    Let's try to expand 'b'
            'ab': It's not a palindrome so do nothing
            'abb': Not a palindrome either
    After expansion, cost[1] remains 1. - Now we go to 'b' (index 2). Initial cost[2] = cost[1]+1 = 2. Expanding...
            'bb': Hey, a palindrome! So I will compute a cost of cost[0]+1 = 1 (remember "1 + cost of substring to the left...). Is 1 less than what I initially have in cost[2]? Yup, it is. So I record it: cost[2] = 1
    After expansion, cost[2] was changed from 2 to 1, and rightfully so because that is the minimum cut we can do to get all substrings as palindromes: 'a' and 'bb'.
    Another example but we'll go faster this time: 'xccx'

            'x': cost[0] = 0. Base case.
            'c' (index 1): Init cost[1] = 1. Expanding...
            'xc': Nope
'xcc': Nope
- 'c': Init cost[2] = 2. Expanding...
            'cc->xccx': Yes. Since we reached start of string, this is a base case. So new cost = 0. Is 0 > cost[3] = Integer.MAX_VALUE? Yes, so cost[3] = 0
            'ccx': Nope
- 'x': Since cost[3] was previously entered a cost, we don't need to init. Expanding...
            'cx': Nope
- Cost to cut entire string: cost[3] = 0
    So there you go. With this method, you are assured that remaining substring (or 'previous state') to the left of the current palindrome is the minimum at that point because you already computed it earlier. You just need to +1 to the cost and see if you have a new minimum cost for the state you are currently in (the last character of the current palindrome).

    Sorry for the lengthy explanation :|*/
//------------------------------------------------------------------------------//////

//    JAVA DP recursive 5ms solution
    public class Solution04 {
        int[] cut;
        public int minCut(String s) {
            int len=s.length();
            char[] tmp=s.toCharArray();
            cut=new int[s.length()];
            Arrays.fill(cut,Integer.MAX_VALUE);
            for(int i=0;i<len;i++)
            {
                re(tmp,i,i);
                re(tmp,i-1,i);
            }
            return cut[len-1];
        }
        public void re(char[] s,int start,int end)
        {
            if(start<0||end>=s.length)return;
            if(s[start]==s[end])
            {
                re(s,start-1,end+1);
                int tmp=start==0?0:cut[start-1]+1;
                cut[end]=Math.min(tmp,cut[end]);
            }
        }
    }
//------------------------------------------------------------------------------//////
    // 9Ch DP
    public int minCut(String ss) {
        char[] s = ss.toCharArray();
        int n = s.length;
        if (n == 0) {
            return 0;
        }
        
        boolean[][] isPalin = new boolean[n][n];
        int i, j, t;
        for (i = 0; i < n; i++) {
            for (j = i; j < n; j++) {
                isPalin[i][j] = false;
            }
        }

        //generate palindrome
        // t 是中心点，枚举中心点t
        for (t = 0; t < n; t++) {
            // odd length
            i = j = t;
            while (i >= 0 && j < n && s[i] == s[j]) {
                isPalin[i][j] = true;
                --i;
                ++j;
            }

            // even length
            i = t;
            j = t + 1;
            while (i >= 0 && j < n && s[i] == s[j]) {
                isPalin[i][j] = true;
                --i;
                ++j;
            }
        }

        int[] f = new int[n + 1];
        f[0] = 0;
        for (i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (j = 0; j < i; j++) {
                if (isPalin[j][i - 1]) {
                    f[i] = Math.min(f[j] + 1, f[i]);
                }
            }
        }
        return f[n] - 1;
    }

    @Test
    public void test01(){
        String ss = "aab";
        System.out.println(minCut("aab"));
        System.out.println(minCut("aabcd"));
    }
//------------------------------------------------------------------------------//////
    // 9Ch
    // version 1
    // f[i] 表示前i个字母，最少可以被分割为多少个回文串
    // 最后return f[n] - 1
    private boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        //对角线，单一字符
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        //两个字符
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                        = isPalindrome[start + 1][start + length - 1]
                        && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }

    public int minCut1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // preparation
        boolean[][] isPalindrome = getIsPalindrome(s);

        // initialize
        int[] f = new int[s.length() + 1];
        f[0] = 0;

        // main
        for (int i = 1; i <= s.length(); i++) {
            f[i] = Integer.MAX_VALUE; // or f[i] = i
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }

        return f[s.length()] - 1;
    }

    @Test
    public void testpl(){
        int[][] arr = new int[2][2];
        Arrays.fill(arr, new int[]{1,2});
        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }


//------------------------------------------------------------------------------//////
    // 9Ch
    // version 2
    // f[i] 表示前i个字母，最少被切割几次可以切割为都是回文串。
    // 最后return f[n]
    public class Solution2 {
        private boolean isPalindrome(String s, int start, int end) {
            for (int i = start, j = end; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        }

        private boolean[][] getIsPalindrome(String s) {
            boolean[][] isPalindrome = new boolean[s.length()][s.length()];

            for (int i = 0; i < s.length(); i++) {
                isPalindrome[i][i] = true;
            }
            for (int i = 0; i < s.length() - 1; i++) {
                isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            }

            for (int length = 2; length < s.length(); length++) {
                for (int start = 0; start + length < s.length(); start++) {
                    isPalindrome[start][start + length]
                            = isPalindrome[start + 1][start + length - 1]
                            && s.charAt(start) == s.charAt(start + length);
                }
            }

            return isPalindrome;
        }

        public int minCut(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            // preparation
            boolean[][] isPalindrome = getIsPalindrome(s);

            // initialize
            int[] f = new int[s.length() + 1];
            for (int i = 0; i <= s.length(); i++) {//初始化切割成单一字符的次数
                f[i] = i - 1;
            }

            // main
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j][i - 1]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }

            return f[s.length()];
        }
    }

//------------------------------------------------------------------------------//////

}

/*
给定一个字符串s，将s分割成一些子串，使每个子串都是回文。

返回s符合要求的的最少分割次数。

您在真实的面试中是否遇到过这个题？ Yes
样例
比如，给出字符串s = "aab"，

返回 1， 因为进行一次分割可以将字符串s分割成["aa","b"]这样两个回文子串
 */

/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */




/*
Given a string s, cut s into some substrings such that every substring is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Have you met this question in a real interview? Yes
Example
Given s = "aab",

Return 1 since the palindrome partitioning ["aa", "b"] could be produced using 1 cut.
 */

