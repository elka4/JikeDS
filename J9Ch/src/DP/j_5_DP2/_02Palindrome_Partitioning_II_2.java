package DP.j_5_DP2;

//version 2
//9CH summer 2016 too
//f[i] 表示前i个字母，最少被切割几次可以切割为都是回文串。
//最后return f[n]
/*
* state:
* 2, f[i] 表示前i个字符组成的前缀，最少切割几次，能切成全部是回文串
* 比如："aab" f[3] = f["aab"] = 1
*            f[2] = f["aa"] = 0
*            f[1] = f["a"] = 0
*            f[0] = f[""] = -1
*            
*            ""|aa
*            f[""] + 1 = f["aa"]
*            f[""] = f["aa"] - 1
*            f[""] = 0 - 1 = -1
*            
* 1, f[i] 表示第i个字符，组成的前缀，最少能分割为几个回文串
* 		      f[3] = f["aab"] = 2
*            f[2] = f["aa"] = 1
*            f[1] = f["a"] = 1
*            f[0] = f[""] = 0
* 	
* 		return f[n] - 1
*/


//state2: f[0] = -1;  f[i] 表示前i个字符组成的前缀，最少切割几次，能切成全部是回文串
public class _02Palindrome_Partitioning_II_2 {

    //O(n)
    @SuppressWarnings("unused")
    private boolean isPalindrome(String s, int start, int end) {
       for (int i = start, j = end; i < j; i++, j--) {
           if (s.charAt(i) != s.charAt(j)) {
               return false;
           }
       }
       return true;
    }

    //类似于区间动态规划的方法， f[i][j]表示i到j的区间，看可行不可行啊，最大最小啊
    //用一个小于n ^ 3的方法，给出一个可以搜索是否回文串的二维数组
    private boolean[][] getIsPalindrome(String s) {
       boolean[][] isPalindrome = new boolean[s.length()][s.length()];
       //单个字母的情况，认为是回文串
       for (int i = 0; i < s.length(); i++) {
           isPalindrome[i][i] = true;
       }
       //两个字母的情况，如果两个字母相等，则认为是回文串
       for (int i = 0; i < s.length() - 1; i++) {
           isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
       }
       //区间型动态规划for循环不能写成i.....s
       //大区间依赖于小区间，所以小区间要先做
       for (int length = 2; length < s.length(); length++) {
           for (int start = 0; start + length < s.length(); start++) {
             //中间的string是回文串，而两端的两个字母相同，则外侧String是回文串
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

       // initialize //f[0] = -1;
       int[] f = new int[s.length() + 1];
       for (int i = 0; i <= s.length(); i++) {
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