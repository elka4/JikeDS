package j_5_DP2;

import java.util.Set;
//9CH summer 2016
//这个超时\
/*
* ￼￼Word Break
• state: f[i]表示“前i”个字符能否被完美切分（最后剩下一个空串）true／false
• function: f[i] = OR{f[j]} 其中 j < i && j+1~i is a word 
	• OR 运算的意思
	• 假如j=0,1,3,5时满足j<i && j+1~i is a word 
	• 那么 f[i] = f[0] || f[1] || f[3] || f[5]
• initialize: f[0] = true  （目的就是要把string s成功切成一个空串）
• answer: f[n]

• 注意:切分位置的枚举->单词长度枚举 O(NL^2) 
	• N: 字符串长度
	• L: 最长的单词的长度
*/
@SuppressWarnings("all")

public class _01Word_Break_2016 {
public boolean wordBreak(String s, Set<String> dict){
	int n = s.length();
	boolean[] f = new boolean[n + 1];
	
	//empty string is valid
	f[0] = true;
	
	//s = "abcde";
	//from "a" "ab" . to .. "abcde"
	//长的前缀依赖短的前缀，所以从最短的前缀开始，自顶向下
	
	for(int i = 1; i <= n; i++){
		//f[i] is false by default。所以不需要单独提前做initialize
		for(int j = 0; j < i; j++){
			if(f[j] == false){
				continue;
			}
			/*
			能走到这里，说面前面的部分(一直到j)可以完美切分
			which is: f[j] = true;
			*/

			String sub = s.substring(j, i);
		//那么如果切下来的部分可以构成一个单词，到i为止的就都能完美切分
		//which is j to i - 1 is also true which makes f[i] true
			if(dict.contains(sub)){
				f[i] = true;
				break;
			}
		}
	}
	return f[n];
}
}