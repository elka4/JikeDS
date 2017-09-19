package chapter5_DP2;

//9Ch summer 2016
//大区间依赖于小区间
class _02Palindrome_Partitioning_II_2016{

/*
 * ￼￼Palindrome Partitioning II
• state: f[i]表示前i个字符组成的子串能被分割为最少多少个回文串 
• function: f[i] = MIN{f[j]+1}, j < i && j+1 ~ i这一段是一个回文串
• initialize: f[i] = i (f[0] = 0)Í
• answer: f[n] – 1
为什么-1? 分为x个串需要x-1刀
￼￼￼￼￼￼￼￼￼￼￼
 * */

//version 1
//f[i] 表示前i个字母，最少可以被分割为多少个回文串
//最后return f[n] - 1


	
	/*
	 * 1.f[i] 表示前i个字符组成的前缀，最少切割几次，能切成全部是回文串
	 * “aab”
	 * f[3] = f["aab"] = 1
	 * f[2] = f["aa"] = 0
	 * f[1] = f["a"] = 0
	 * f[0] = f[""] = -1
	 * return f[n]
	 * 
	 * 2.f[i]表示前i个字符，组成的前缀，最少能分割为几个回文串
	 * f[3] = f["aab"] = 2
	 * f[2] = f["aa"] = 1
	 * f[1] = f["a"] = 1
	 * f[0] = f[""] = 0
	 * 
	 * return f[n] - 1
	 *
	 *
	 *           下面在解释区间型动态规划，划分区间的方法。
	 *           下面在解释区间型动态规划，划分区间的方法。
	public int minCut(String s){

		//i依赖比较大的，j依赖比较小的，所以循环i从大往小（也就是从后往前），循环j从小往大（也就是从前往后）
		for(int i = n - 1; i >= 0; i--){
			for(int j = i + 1; j < n; j++){
				f[i][j] = f[i + 1][j - 1] && s[i] == s[j];
			}
		}
	
		//f[i][j] <- f[i + 1][j - 1]; 这个是更小的推出更大的


		//另外一个写法
		//delta = j - i  区间长度
		//(0, n - 1)
		for(int delta = 1; delta < n; delta++){
			for(int i = 0; i < n; i++){
				int j = i + delta;
				f[i][j] = f[i + 1][j - 1] && s[i] == s[j];
			}
		}
	}
	*/
	


/*
 * Given a string s, cut s into some substrings such that every 
 * substring is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Have you met this question in a real interview? Yes
Example
Given s = "aab",

Return 1 since the palindrome partitioning ["aa", "b"] could be
 produced using 1 cut.

Tags 
Dynamic Programming
Related Problems 
Medium Wiggle Sort II 24 %
Medium Palindrome Partitioning 23 %
Medium Longest Palindromic Substring
 */
}
