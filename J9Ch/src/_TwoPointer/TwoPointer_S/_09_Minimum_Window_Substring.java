package _TwoPointer.TwoPointer_S;


//  76. Minimum Window Substring
//  https://leetcode.com/problems/minimum-window-substring/description/
//  http://www.lintcode.com/zh-cn/problem/minimum-window-substring/
public class _09_Minimum_Window_Substring {

//------------------------------------------------------------------------------////
	//方法一:
    //判断source hash是否包含target hash
    boolean valid(int []sourcehash, int []targethash) {
        
        for(int i = 0; i < 256; i++) {
            if(targethash[i] > sourcehash[i])    
                return false;
        }
        return true;
    }
    public String minWindow1(String Source, String Target) {
        // queueing the position that matches the char in T
        int ans = Integer.MAX_VALUE;
        String minStr = "";
        
        int[] sourcehash = new int[256];
        int[] targethash = new int[256];
        
        initTargetHash(targethash, Target);
        int j = 0, i = 0;
        for(i = 0; i < Source.length(); i++) {
            
            while( !valid(sourcehash, targethash) && j < Source.length()  ) {
                sourcehash[Source.charAt(j)]++;
                if(j < Source.length() )
                    j++;
                else 
                    break;
            }
            //如果满足条件，则更新当前答案
            if(valid(sourcehash, targethash) ){
                if(ans > j - i ) {
                    ans = Math.min(ans, j - i );
                    minStr = Source.substring(i, j );
                }
            }
            sourcehash[Source.charAt(i)]--;
        }
        return minStr;
    }

//---------------------------------/////
//方法二:
    int initTargetHash(int []targethash, String Target) {
        int targetnum =0 ;
        for (char ch : Target.toCharArray()) {
            targetnum++;
            targethash[ch]++;
        }
        return targetnum;
    }
    public String minWindow2(String Source, String Target) {
        // queueing the position that matches the char in T
        int ans = Integer.MAX_VALUE;
        String minStr = "";

        int[] targethash = new int[256];

        int targetnum = initTargetHash(targethash, Target);
        int sourcenum = 0;
        int j = 0, i = 0;
        for(i = 0; i < Source.length(); i++) {
            if(targethash[Source.charAt(i)] > 0)
                sourcenum++;

            targethash[Source.charAt(i)]--;
            while(sourcenum>=targetnum) {
                if(ans > i - j + 1) {
                    ans = Math.min(ans, i - j + 1);
                    minStr = Source.substring(j, i + 1);
                }
                targethash[Source.charAt(j)]++;
                if(targethash[Source.charAt(j)] > 0)
                    sourcenum--;
                j ++;
            }
        }
        return minStr;
    }
//------------------------------------------------------------------------------////
}
/*
最小子串覆盖

 描述
 笔记
 数据
 评测
给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串。

 注意事项

如果在source中没有这样的子串，返回""，如果有多个这样的子串，返回起始位置最小的子串。

说明
在答案的子串中的字母在目标字符串中是否需要具有相同的顺序？

——不需要。

样例
给出source = "ADOBECODEBANC"，target = "ABC" 满足要求的解  "BANC"

挑战
要求时间复杂度为O(n)


 */

/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */