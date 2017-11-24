package _TwoPointer._TwoPointer;
import java.util.*;

//  76. Minimum Window Substring

//  https://leetcode.com/problems/minimum-window-substring/description/
//  http://www.lintcode.com/zh-cn/problem/minimum-window-substring/
public class _076_TwoPointer_Minimum_Window_Substring_H {
    public String minWindow(String s, String t) {

        int[] map = new int[128];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        for (char c : t.toCharArray()) map[c]++;
        int counter = t.length(), begin = 0, end = 0, d = Integer.MAX_VALUE, head = 0;
        while (end < sc.length) {
            if (map[sc[end++]]-- > 0) counter--; //in t
            while (counter == 0) { //valid
                if (end - begin < d) d = end - (head = begin);
                if (map[sc[begin++]]++ == 0) counter++;  //make it invalid
            }
        }
        return d == Integer.MAX_VALUE ? "" : s.substring(head + 1, d);
    }
/*    Here comes the template.

    For most substring problem, we are given a string and need to find a substring of it which satisfy some restrictions. A general way is to use a hashmap assisted with two pointers. The template is given below.

    int findSubstring(string s){
        vector<int> map(128,0);
        int counter; // check whether the substring is valid
        int begin=0, end=0; //two pointers, one point to tail and one  head
        int d; //the length of substring

        for() { *//* initialize the hash map here *//* }

        while(end<s.size()){

            if(map[s[end++]]-- ?){  *//* modify counter here *//* }

            while(*//* counter condition *//*){

                 *//* update d here if finding minimum*//*

                //increase begin to make it invalid/valid again

                if(map[s[begin++]]++ ?){ *//*modify counter here*//* }
            }

            *//* update d here if finding maximum*//*
        }
        return d;
    }*/

//    make the template more applicable for Longest Substring Without Repeating Character

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = 0;
        int head = 0, i = 0;
        int[] sTable = new int[256];
        int repeat = 0;
        while (i < s.length()) {
            if (sTable[s.charAt(i++)]++ > 0) repeat++;   //total number of repeat
            while(repeat > 0) {
                if (sTable[s.charAt(head++)]-- > 1) repeat--;
            }
            len = Math.max(len, i - head);
        }
        return len;
    }



    //Java solution. using two pointers + HashMap

    public class Solution {
        public String minWindow(String s, String t) {
            if(s == null || s.length() < t.length() || s.length() == 0){
                return "";
            }
            HashMap<Character,Integer> map = new HashMap<Character,Integer>();
            for(char c : t.toCharArray()){
                if(map.containsKey(c)){
                    map.put(c,map.get(c)+1);
                }else{
                    map.put(c,1);
                }
            }
            int left = 0;
            int minLeft = 0;
            int minLen = s.length()+1;
            int count = 0;
            for(int right = 0; right < s.length(); right++){
                if(map.containsKey(s.charAt(right))){
                    map.put(s.charAt(right),map.get(s.charAt(right))-1);
                    if(map.get(s.charAt(right)) >= 0){
                        count ++;
                    }
                    while(count == t.length()){
                        if(right-left+1 < minLen){
                            minLeft = left;
                            minLen = right-left+1;
                        }
                        if(map.containsKey(s.charAt(left))){
                            map.put(s.charAt(left),map.get(s.charAt(left))+1);
                            if(map.get(s.charAt(left)) > 0){
                                count --;
                            }
                        }
                        left ++ ;
                    }
                }
            }
            if(minLen>s.length())
            {
                return "";
            }

            return s.substring(minLeft,minLeft+minLen);
        }
    }


/*    create a hashmap for each character in t and count their frequency in t as the value of hashmap.
    Find the first window in S that contains T. But how? there the author uses the count.
    Checking from the leftmost index of the window and to see if it belongs to t. The reason we do so is that we want to shrink the size of the window.
            3-1) If the character at leftmost index does not belong to t, we can directly remove this leftmost value and update our window(its minLeft and minLen value)
3-2) If the character indeed exists in t, we still remove it, but in the next step, we will increase the right pointer and expect the removed character. If find so, repeat step 3.*/
    public String minWindow3(String s, String t) {
        HashMap<Character, Integer> map = new HashMap();
        for(char c : t.toCharArray()){
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }
            else{
                map.put(c, 1);
            }
        }
        int left = 0, minLeft=0, minLen =s.length()+1, count = 0;
        for(int right = 0; right<s.length(); right++){
            char r = s.charAt(right);
            if(map.containsKey(r)){//the goal of this part is to get the first window that contains whole t
                map.put(r, map.get(r)-1);
                if(map.get(r)>=0) count++;//identify if the first window is found by counting frequency of the characters of t showing up in S
            }
            while(count == t.length()){//if the count is equal to the length of t, then we find such window
                if(right-left+1 < minLen){//jsut update the minleft and minlen value
                    minLeft = left;
                    minLen = right-left+1;
                }
                char l = s.charAt(left);
                if(map.containsKey(l)){//starting from the leftmost index of the window, we want to check if s[left] is in t. If so, we will remove it from the window, and increase 1 time on its counter in hashmap which means we will expect the same character later by shifting right index. At the same time, we need to reduce the size of the window due to the removal.
                    map.put(l, map.get(l)+1);
                    if(map.get(l)>0) count--;
                }
                left++;//if it doesn't exist in t, it is not supposed to be in the window, left++. If it does exist in t, the reason is stated as above. left++.
            }
        }
        return minLen==s.length()+1?"":s.substring(minLeft, minLeft+minLen);
    }


    //Share my neat java solution

    public String minWindow4(String S, String T) {
        if(S==null||S.isEmpty()||T==null||T.isEmpty()) return "";
        int i=0, j=0;
        int[] Tmap=new int[256];
        int[] Smap=new int[256];
        for(int k=0; k< T.length(); k++){
            Tmap[T.charAt(k)]++;
        }
        int found=0;
        int length=Integer.MAX_VALUE;
        String res="";
        while(j<S.length()){
            if(found<T.length()){
                if(Tmap[S.charAt(j)]>0){
                    Smap[S.charAt(j)]++;
                    if(Smap[S.charAt(j)]<=Tmap[S.charAt(j)]){
                        found++;
                    }
                }
                j++;
            }
            while(found==T.length()){
                if(j-i<length){
                    length=j-i; res=S.substring(i,j);
                }
                if(Tmap[S.charAt(i)]>0){
                    Smap[S.charAt(i)]--;
                    if(Smap[S.charAt(i)]<Tmap[S.charAt(i)]){
                        found--;
                    }
                }
                i++;
            }
        }
        return res;
    }

    //Sharing my straightforward O(n) solution with explanation
    public class Solution5 {
        public String minWindow(String S, String T) {
            HashMap<Character, Integer> mapt = new HashMap<Character, Integer>();
            HashMap<Character, Integer> window = new HashMap<Character, Integer>();
            for(int i = 0; i < T.length(); i++ ){
                char c= T.charAt(i);
                if(!mapt.containsKey(c)){
                    mapt.put(c, 1);
                    window.put(c, 0);
                }else {
                    mapt.put(c, mapt.get(c)+1);
                }
            }

            int start = 0;
            int count = 0;
            int minLength = Integer.MAX_VALUE;
            int minStart = 0;

            for(int end = 0; end < S.length(); end++){
                char c = S.charAt(end);
                if(mapt.containsKey(c)) {
                    if(window.get(c) < mapt.get(c)){
                        count ++;
                    }
                    window.put(c, window.get(c) + 1);
                }
                if(count >= T.length()){

                    while((!mapt.containsKey(S.charAt(start))) ||
                            window.get(S.charAt(start)) > mapt.get(S.charAt(start))){
                        if(window.containsKey(S.charAt(start)))
                            window.put(S.charAt(start), window.get(S.charAt(start))-1);
                        start++;
                    }
                    if(end-start+1 < minLength){
                        minStart = start;
                        minLength = end-start+1;
                    }

                    count--;
                    window.put(S.charAt(start), window.get(S.charAt(start))-1);
                    start++;
                }
            }

            if(minLength == Integer.MAX_VALUE) return "";
            return S.substring(minStart, minStart+minLength);
        }
    }


//-------------------------------------------------------------------------

    //jiuzhang
     class Jiuzhang {
        //方法一:
        int initTargetHash(int []targethash, String Target) {
            int targetnum =0 ;
            for (char ch : Target.toCharArray()) {
                targetnum++;
                targethash[ch]++;
            }
            return targetnum;
        }
        boolean valid(int []sourcehash, int []targethash) {

            for(int i = 0; i < 256; i++) {
                if(targethash[i] > sourcehash[i])
                    return false;
            }
            return true;
        }
        public String minWindow(String Source, String Target) {
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
                    j++;
                }
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
    }
}
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

//给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串。

