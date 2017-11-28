package _String._Hash;
import java.util.*;
import org.junit.Test;

//  159. Longest Substring with At Most Two Distinct Characters
//
//  Hash Table, Two Pointers, String
//  3. Longest Substring Without Repeating Characters - Hash Table, Two Pointers, String
//  239. Sliding Window Maximum - heap
//  340. Longest Substring with At Most K Distinct Characters - Hash Table String
//  9:
//
public class _159_String_Longest_Substring_with_At_Most_Two_Distinct_Characters_H {
//------------------------------------------------------------------------------
    //https://discuss.leetcode.com/topic/71662/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
//------------------------------------------------------------------------------
    //1
/*Simple O(n) java solution - easily extend to k characters
    The main idea is to maintain a sliding window with 2 unique characters. The key is to store the last occurrence of each character as the value in the hashmap. This way, whenever the size of the hashmap exceeds 2, we can traverse through the map to find the character with the left most index, and remove 1 character from our map. Since the range of characters is constrained, we should be able to find the left most index in constant time.*/

    public class Solution1 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if(s.length() < 1) return 0;
            HashMap<Character,Integer> index = new HashMap<Character,Integer>();
            int lo = 0;
            int hi = 0;
            int maxLength = 0;

            while(hi < s.length()) {
                if(index.size() <= 2) {
                    char c = s.charAt(hi);
                    index.put(c, hi);
                    hi++;
                }
                if(index.size() > 2) {
                    int leftMost = s.length();
                    for(int i : index.values()) {
                        leftMost = Math.min(leftMost,i);
                    }
                    char c = s.charAt(leftMost);
                    index.remove(c);
                    lo = leftMost+1;
                }
                maxLength = Math.max(maxLength, hi-lo);
            }
            return maxLength;
        }
    }
//------------------------------------------------------------------------------

    //2
    //Clean 11 lines AC answer, O(1) space, O(n) time.
    //A good solution beating more than 80%! I reimplemented it using Java:
    class Solution2{
        public int lengthOfLongestSubstringTwoDistinct(String str) {
            int i = 0, j = -1;
            int maxLen = 0;
            char[] s = str.toCharArray();
            for (int k = 1; k < s.length; k++) {
                if (s[k] == s[k-1]) continue;
                if (j > -1 && s[k] != s[j]) {
                    maxLen = Math.max(maxLen, k - i);
                    i = j + 1;
                }
                j = k - 1;
            }
            return maxLen > (s.length - i) ? maxLen : s.length - i;
        }
    }

//------------------------------------------------------------------------------
    //https://discuss.leetcode.com/topic/71662/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
    //Sliding Window algorithm template to solve all the Leetcode substring search problem.
//------------------------------------------------------------------------------
    //3
//4ms Java Two pointers solution beat 100%
    class Solution3{
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if (s.isEmpty()) return 0;
            int max = 1;
            int p1 = 0, p2 = 0;
            int last = 1;
            char[] chars = s.toCharArray();
            for (int i = 1; i < chars.length; i++) {
                if (p1 != p2 && chars[i] != chars[p1] && chars[i] != chars[p2]) {
                    if (last > max) max = last;

                    last = i - p1;
                    p1 = p2;
                    p2 = i;
                } else {
                    if (chars[i] == chars[p1]) {
                        p1 = p1 == p2 ? i : p2;
                    }
                    last++;
                    p2 = i;
                }
            }

            if (last > max) max = last;
            return max;
        }
    }
//------------------------------------------------------------------------------
    //4
    //My java AC solution
    /*
    Basic idea:

Maintain a HashMap with counter of each entry (CountMap) to keep the count of each distinct characters in the current substring (from star to i).

Scan the string sequentially, add each character into the CountMap, if the count map has less than 2 entries (i.e. distinct characters), keep growing the substring; else, remove entries from the substring (from the star position) till the CountMap contains less than 2 entries so that a new entry can be added.

Note: this solution is also good for the general problem of find longest substring with at most K distinct characters.
Complexity: O(N) time -- scan string twice (star++, i++), O(K) space where K is the number distinct characters maintained in the CounterMap.
     */

    public class Solution4 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int rst = 0;
            CountMap cM = new CountMap();
            int star = 0;

            for (int i=0; i<s.length(); i++){
                char x = s.charAt(i);
                if(cM.containsKey(x)){
                    cM.put(x);
                }else{
                    while(cM.size()==2){
                        cM.remove(s.charAt(star));
                        star++;
                    }
                    cM.put(x);
                }
                rst = Math.max(rst, i-star+1);
            }
            return rst;
        }

        class CountMap {
            Map<Character, Integer> m = new HashMap<Character, Integer>();

            public boolean containsKey(char x){
                return m.containsKey(x);
            }

            public int size(){
                return m.size();
            }

            public void put(char x){
                if (!m.containsKey(x)){
                    m.put(x, 1);
                }else{
                    m.put(x, m.get(x)+1);
                }
            }

            public void remove(char x){
                if (!m.containsKey(x)){
                    return;
                }else if (m.get(x)==1){
                    m.remove(x);
                }else{
                    m.put(x, m.get(x)-1);
                }
            }
        }//CountMap
    }


//------------------------------------------------------------------------------
    //5
    //O(n) time and O(1) space solution without using HashMap
//The basic idea is to store the two characters and keep track of last indices of them. When third character comes, we set the start_point to 1 + smaller index, in that way we can always throw away one character. And the length is given by current_index - start_point.

    public class Solution5 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if(s == null || s.length() == 0){
                return 0;
            }
            char charOne = s.charAt(0);
            int charOneIndex = 0;
            while(charOneIndex+1 < s.length() && s.charAt(charOneIndex+1) == charOne){ // in case of "aaa"
                charOneIndex++;
            }
            if(charOneIndex == s.length()-1){
                return s.length();
            }
            char charTwo = s.charAt(charOneIndex+1);
            int charTwoIndex = charOneIndex+1;
            int startIndex = 0;
            int maxLen = charTwoIndex+1;
            for(int i=charTwoIndex+1; i<s.length(); i++){
                char c = s.charAt(i);
                if(c != charOne && c != charTwo){ //new character comes, update index and char
                    startIndex = Math.min(charOneIndex, charTwoIndex)+1;
                    charOneIndex = Math.max(charOneIndex, charTwoIndex);
                    charOne = charOneIndex == charTwoIndex ? charTwo : charOne;
                    charTwoIndex = i;
                    charTwo = c;
                }
                else{ //same character comes, update max length
                    if(c == charOne){
                        charOneIndex = i;
                    }
                    else{
                        charTwoIndex = i;
                    }
                    if(i - startIndex + 1 > maxLen){
                        maxLen = i - startIndex + 1;
                    }
                }
            }
            return maxLen;
        }
    }

//------------------------------------------------------------------------------
    //6
    //Java Two Pointer Solution 6ms Beats 96% submissions
    public class Solution6 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if(s==null||s.length()==0) return 0;
            char first = s.charAt(0), second = first;
            int lenMax = 1, currLen = 1, secLen = 1;
            for(int i=1; i<s.length(); i++){
                char curr = s.charAt(i);
                if(curr!=second&&curr!=first&&first!=second){
                    lenMax = lenMax<currLen?currLen:lenMax;
                    currLen = 1+secLen;
                    secLen = 1;
                    first = second;
                    second = curr;
                }else if(curr!=second){
                    currLen++;
                    secLen = 1;
                    first = second;
                    second = curr;
                }else{
                    currLen++;
                    secLen++;
                }
            }
            return Math.max(lenMax,currLen);
        }
    }

//------------------------------------------------------------------------------
    //7
    //Easy Understand AC Solution Without HashMap
//Use array and a variable count instead of HashMap, which makes it a little faster. By the way, it is also acceptable to K Distinct Character, we just need to change count > 2 to count > k. Really easy, right?

    public class Solution7 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int[] cnt = new int[256];// record the emerge times for this substring
            int begin = 0, end = 0, distance = Integer.MIN_VALUE, count = 0;
            while(end < s.length()){
                if(cnt[s.charAt(end++)]++ == 0)     count++;
                while(count > 2){
                    if(--cnt[s.charAt(begin++)] == 0)   count--;
                }
                distance = Math.max(distance, end - begin);
            }
            return distance == Integer.MIN_VALUE? 0 : distance;
        }
    }

//------------------------------------------------------------------------------
    //8
    //The idea is easy to understand, but the code is really hard to read... I have pasted a more readable version according to your code...
    class Solution8{
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int[] cnt = new int[256];
            int begin = 0, end = 0, distance = Integer.MIN_VALUE, count = 0;

            while(end < s.length())
            {
                if(cnt[s.charAt(end)] == 0) // a new character
                {
                    count++;
                }

                cnt[s.charAt(end)]++; // increase the count
                end++; //move the end pointer

                while(count > 2) // if more than 2 distinct characters
                {
                    cnt[s.charAt(begin)]--; // decrease the count
                    if(cnt[s.charAt(begin)] == 0) // if the character is not in the substring anymore
                    {
                        count--;
                    }
                    begin++; // move the begin pointer
                }
                distance = Math.max(distance, end - begin); // update the length
            }
            return distance == Integer.MIN_VALUE? 0 : distance;
        }
    }


//------------------------------------------------------------------------------
    //9
    //Java Easy to understand sliding window using HashMap
    public class Solution9 {
        public int lengthOfLongestSubstringTwoDistinct(String s)
        {
            if (s == null || s.isEmpty()) { return 0; }

            // sliding window boundary pointers
            int left = 0;
            int right = 0;

            // map stores character and its occurrence
            Map<Character, Integer> map = new HashMap<>();
            int maxLen = 0;
            while (right < s.length())
            {
                if (map.size() <= 2)
                {
                    char c = s.charAt(right);
                    map.put(c, map.getOrDefault(c, 0) + 1);
                    right++;
                }
                else
                {
                    // move left pointer
                    maxLen = Math.max(maxLen, right - left - 1);
                    char c = s.charAt(left);

                    int leftCharFreq = map.get(c);
                    // remove char from map if its occurrence = 0
                    if (leftCharFreq - 1 == 0)
                    {
                        map.remove(c);
                    }
                    else
                    {
                        map.put(c, leftCharFreq - 1);
                    }
                    left++;
                }

            }
            // final check
            if (map.size() > 2)
            {
                maxLen = Math.max(maxLen, right - left - 1);
            }
            else
            {
                maxLen = Math.max(maxLen, right - left);
            }

            return maxLen;
        }
    }

//------------------------------------------------------------------------------
    //10
    //https://gist.github.com/BiruLyu/457924c6ef22c37f9e10fcb3a521ba05
class Solution10 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() < 1) return 0;
        int count = 0, end = 0, start = 0, res = 0;
        int[] vector = new int[256];
        char[] str = s.toCharArray();
        while (end < str.length) {
            if (vector[str[end++]]++ == 0) count++;
            while (count > 2) {
                if (--vector[str[start++]] == 0) count--;
            }
            res = Math.max(res, end - start);
        }
        return res;
    }
}
/*
using hashset store at most 2 unique characters, however it is not easy to extend to k characters
using hashmap to store the last occurrence of each character coped with finding left most the character with the left most index
*/

    public class Solution11 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if (s == null || s.length() == 0) return 0;
            int start = 0;
            int end = 0;
            int res = 0;
            //char[] dict = new char[2];
            //HashSet<Character> dict = new HashSet<Character>();
            HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
            while( end < s.length()){
                if (dict.size() <= 2){
                    dict.put(s.charAt(end), end);
                    end++;
                }
                if (dict.size() > 2) {
                    int leftMost = s.length();
                    for(int i : dict.values()){
                        leftMost = Math.min(leftMost, i);
                    }
                    dict.remove(s.charAt(leftMost));
                    start = leftMost + 1;
                }
                res = Math.max(res, end - start);
            }
            return res;

        }
    }
//------------------------------------------------------------------------------
    //12
    //http://blog.csdn.net/fantasiasango/article/details/52714485
    /*
    自己思路：
一开始：
记录一个start位置，记录一个结果变量res，再用一个hashmap保存该char出现的index，再用一个count变量计数。
从左到右一遍，每次更新结果res = Math.max(res, cur - start + 1)：
1. 如果map中有该字符，说明出现过，重写value。
2. 如果map中没有该字符，但是count < 2，加入map，count++。
3 如果map中没有字符并且count为2，说明需要剔除一个字符。要求的是substring，必须是连续的。从cur-2位置开始，到之前的start，如果字符!= cur-1处的字符，则删除break循环。(例如：ccaabbb)

然后就发现其实map是没有意义的，我们关心的是这个元素出现过没有，而不是它在哪出现。于是换成了hashset，这样count变量也可以省去了。
     */
public class Solution12 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) return 0;
        int start = 0;
        int res = Integer.MIN_VALUE;
        int cur = 0;
        char[] ss = s.toCharArray();
        Set<Character> set = new HashSet<Character>();

        while(cur < ss.length){
            if(set.contains(ss[cur])){
                set.add(ss[cur]);
            }else if(set.size() < 2){
                set.add(ss[cur]);
            }else{
                for(int i = cur - 2; i >= start; i--){
                    if(ss[i] != ss[cur - 1] && set.contains(ss[i])){
                        set.remove(ss[i]); start = i + 1; break;
                    }
                }
                set.add(ss[cur]);
            }
            res = Math.max(res, cur - start + 1);
            cur++;

        }
        return res;
    }
}
//别人的解法，参考这里：count == 0 则 map删除该key，确实秒。
public class Solution13 { // 39ms
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int i = 0;
        int maxLen = 0;
        while (i < s.length()) {

            // 根据右指针指的当前字符更新map
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }

            // 移动左指针，直到map中字符数量降至规定数量
            while (map.size() > 2) {
                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar)) {

                    // 注意会有重复元素，所以先减小次数，只有次数降至0，才删除元素
                    map.put(leftChar, map.get(leftChar) - 1);
                    if (map.get(leftChar) == 0) {
                        map.remove(leftChar);
                    }
                }
                left++;
            }
            maxLen = Math.max(maxLen, i - left + 1);
            i++;
        }
        return maxLen;
    }
}

/*
自己思路:
沿用上面的代码。之前2个时候只需要只需要探测cur-1位置之前是否有连续的字符串就行了(aab, aaab, aaaab, b是cur位置）。
现在是k个，结果问题来了。
首先是ab无法i - 2，然后其他各test case也是各种多一少一。
于是决定这样：
清除set，从当前位置开始，往前遍历，加入元素直到k个，这时候更新start和res。当然这样就变成了O(NK)。
 */

    public class Solution14 { // 64 ms
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            if(s == null || s.length() == 0 || k == 0) return 0;

            int start = 0;
            int res = Integer.MIN_VALUE;
            int cur = 0;
            char[] ss = s.toCharArray();
            Set<Character> set = new HashSet<Character>();

            while(cur < ss.length){
                if(set.contains(ss[cur])){
                    set.add(ss[cur]);
                }else if(set.size() < k){
                    set.add(ss[cur]);
                }else{
                    set.clear();
                    int i = cur;
                    while(set.size() < k || set.contains(ss[i])){
                        if(!set.contains(ss[i])) set.add(ss[i]);
                        i--;
                    }
                    start = i + 1;
                }
                res = Math.max(res, cur - start + 1);
                cur++;
            }
            return res;
        }
    }
//别人的思路，把之前的2换成k就行：
public class Solution15 { // 65ms
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int i = 0;
        int maxLen = 0;
        while (i < s.length()) {

            // 根据右指针指的当前字符更新map
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }

            // 移动左指针，直到map中字符数量降至规定数量
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar)) {

                    // 注意会有重复元素，所以先减小次数，只有次数降至0，才删除元素
                    map.put(leftChar, map.get(leftChar) - 1);
                    if (map.get(leftChar) == 0) {
                        map.remove(leftChar);
                    }
                }
                left++;
            }
            maxLen = Math.max(maxLen, i - left + 1);
            i++;
        }
        return maxLen;
    }
}

//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
}
/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.

Companies
Google

Related Topics
Hash Table Two Pointers String

Similar Questions
3. Longest Substring Without Repeating Characters - Hash Table, Two Pointers, String
239. Sliding Window Maximum - heap
340. Longest Substring with At Most K Distinct Characters - Hash Table String
 */