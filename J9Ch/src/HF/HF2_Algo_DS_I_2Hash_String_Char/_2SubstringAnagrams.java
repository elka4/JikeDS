package HF.HF2_Algo_DS_I_2Hash_String_Char;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
• Input:
– s: "cbaebabacd" p: “aabc"
• Output: – [5]
  • Anagrams 的充要条件?
– 元素出现的次数一样就好了
 */

/*
思路:
• 基本的想法:
– 假设p串的长度为l , s串长度为n
– 那么就枚举出s中所有长度为l的子串，并用hash统计它们元素出现的个数
• 基本想法的时间复杂度:
– n个子串
• 每次统计子串中元素出现的个数O(l)
• 每次和p对比元素出现次数是否一样O(256)
– 总体O( n * (l + 256)) = O(nl)
 */

/*
• Company Tags:Amazon 考点:
• Sliding window + hash
相关题目:
• Sliding window median
• Sliding window maximum
 */

/*
能力维度:
1. 理解问题
3. 基础数据结构/算法
4. 逻辑思维/算法优化能力
 */

// Substring Anagrams
public class _2SubstringAnagrams {
    // 9Ch
    /**
     * @param s a string
     * @param p a non-empty string
     * @return a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        // Write your code here
        List<Integer> ans = new ArrayList <Integer>();
        int[] sum = new int[30];

        int plength = p.length(), slength = s.length();
        for(char c : p.toCharArray()){
            sum[c - 'a'] ++;
        }

        int start = 0, end = 0, matched = 0;
        while(end < slength){
            if(sum[s.charAt(end) - 'a'] >= 1){
                matched ++;
            }
            sum[s.charAt(end) - 'a'] --;
            end ++;
            if(matched == plength) {
                ans.add(start);
            }
            if(end - start == plength){
                if(sum[s.charAt(start) - 'a'] >= 0){
                    matched --;
                }
                sum[s.charAt(start) - 'a'] ++;
                start ++;
            }
        }
        return ans;
    }

//-------------------------------------------------------------------------/////


    // version: 高频题班
    /**
     * @param s a string
     * @param p a non-empty string
     * @return a list of index
     */
    public List<Integer> findAnagrams2(String s, String p) {
        // Write your code here
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) {
            return ans;
        }
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();

        int[] cntP = new int[256];
        int[] cntS = new int[256];
        int[] det = new int[256];

        for (int i = 0; i < p.length(); i++) {
            cntP[pc[i]]++;
            cntS[sc[i]]++;

            det[pc[i]]--;
            det[sc[i]]++;
        }

        int absSum = 0;
        for (int item : det) {
            absSum += Math.abs(item);
        }
        if (absSum == 0) {
            ans.add(0);
        }

        for (int i = p.length(); i < s.length(); i++) {
            int r = sc[i];
            int l = sc[i - p.length()];
            cntS[r]++;
            cntS[l]--;

            absSum = absSum - Math.abs(det[r]) - Math.abs(det[l]);

            det[r]++;
            det[l]--;

            absSum = absSum + Math.abs(det[r]) + Math.abs(det[l]);

            if (absSum == 0) {
                ans.add(i - p.length() + 1);
            }
        }
        return ans;
    }

//------------------------------------------------------------------------------
//clean version
    public List<Integer> findAnagrams3(String s, String p) {
        // Write your code here
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) {
            return ans;
        }
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();

        int[] det = new int[256];

        for (int i = 0; i < p.length(); i++) {
            det[pc[i]]--;
            det[sc[i]]++;
        }

        int absSum = 0;
        for (int item : det) {
            absSum += Math.abs(item);
        }
        if (absSum == 0) {
            ans.add(0);
        }

        for (int i = p.length(); i < s.length(); i++) {
            int r = sc[i];
            int l = sc[i - p.length()];
            absSum = absSum - Math.abs(det[r]) - Math.abs(det[l]);

            det[r]++;
            det[l]--;

            absSum = absSum + Math.abs(det[r]) + Math.abs(det[l]);
            if (absSum == 0) {
                ans.add(i - p.length() + 1);
            }
        }
        return ans;
    }

    @Test
    public void test03(){
        String s = "cbaebabacd";
        String p = "abc";

        List<Integer> result = findAnagrams3(s, p);

        System.out.println(result);
    }
//-------------------------------------------------------------------------/////
}
/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 40,000.

The order of output does not matter.

Have you met this question in a real interview? Yes
Example
Given s = "cbaebabacd" p = "abc"

return [0, 6]

The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

 */

/*

 */