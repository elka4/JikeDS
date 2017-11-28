package _String._String;
import java.util.*;
import org.junit.Test;

//  555. Split Concatenated Strings
//  https://leetcode.com/problems/split-concatenated-strings/
//
//  5:
//
public class _555_String_Split_Assembled_Strings_H {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/split-concatenated-strings/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Depth First Search [Time Limit Exceeded]
    public class Solution1 {
        String res = "";
        public String splitLoopedString(String[] strs) {
            dfs(strs, "", 0, strs.length);
            return res;
        }
        public void dfs(String[] strs, String s, int i, int n) {
            if (i < n) {
                dfs(strs, s + strs[i], i + 1, n);
                dfs(strs, s + new StringBuffer(strs[i]).reverse().toString(), i + 1, n);
            } else {
                for (int j = 0; j < s.length(); j++) {
                    String t = s.substring(j) + s.substring(0, j);
                    if (t.compareTo(res) > 0)
                        res = t;
                }
            }
        }
    }



//------------------------------------------------------------------------------
    //2
    //Approach #2 Breadth First Search [Memory Limit Exceeded]
    public class Solution2 {

        public String splitLoopedString(String[] strs) {
            Queue < String > queue = new LinkedList < > ();
            String res = "";
            int i = 0, j = 0;
            queue.add("");
            while (i < strs.length) {
                String t = queue.remove();
                queue.add(t + strs[i]);
                queue.add(t + new StringBuffer(strs[i]).reverse().toString());
                j++;
                if (j == 1 << i) {
                    i++;
                    j = 0;
                }
            }
            while (!queue.isEmpty()) {
                String t = queue.remove();
                for (int k = 0; k < t.length(); k++) {
                    String t1 = t.substring(k) + t.substring(0, k);
                    if (t1.compareTo(res) > 0)
                        res = t1;
                }
            }
            return res;
        }
    }

//------------------------------------------------------------------------------
    //3
    //Approach #3 Optimized Solution [Accepted]
    public class Solution3 {
        public String splitLoopedString(String[] strs) {
            for (int i = 0; i < strs.length; i++) {
                String rev = new StringBuilder(strs[i]).reverse().toString();
                if (strs[i].compareTo(rev) < 0)
                    strs[i] = rev;
            }
            String res = "";
            for (int i = 0; i < strs.length; i++) {
                String rev = new StringBuilder(strs[i]).reverse().toString();
                for (String st: new String[] {strs[i], rev}) {
                    for (int k = 0; k < st.length(); k++) {
                        StringBuilder t = new StringBuilder(st.substring(k));
                        for (int j = i + 1; j < strs.length; j++)
                            t.append(strs[j]);
                        for (int j = 0; j < i; j++)
                            t.append(strs[j]);
                        t.append(st.substring(0, k));
                        if (t.toString().compareTo(res) > 0)
                            res = t.toString();
                    }
                }
            }
            return res;
        }
    }


//------------------------------------------------------------------------------
    //4
    //DescriptionHintsSubmissionsDiscussSolution
    //Neat Java Solution
    public class Solution4 {
        public String splitLoopedString(String[] strs) {
            for (int i = 0; i < strs.length; i++) {
                String rev = new StringBuilder(strs[i]).reverse().toString();
                if (strs[i].compareTo(rev) < 0)
                    strs[i] = rev;
            }
            String res = "";
            for (int i = 0; i < strs.length; i++) {
                String rev = new StringBuilder(strs[i]).reverse().toString();
                for (String st: new String[] {strs[i], rev}) {
                    for (int k = 0; k < st.length(); k++) {
                        StringBuilder t = new StringBuilder(st.substring(k));
                        for (int j = i + 1; j < strs.length; j++)
                            t.append(strs[j]);
                        for (int j = 0; j < i; j++)
                            t.append(strs[j]);
                        t.append(st.substring(0, k));
                        if (t.toString().compareTo(res) > 0)
                            res = t.toString();
                    }
                }
            }
            return res;
        }
    }


//------------------------------------------------------------------------------
    //5
    /*Java straight forward method with explanation
for each string other than the one we place the cut we choose the bigger one between itself and its reverse. We use the bigger one in the final concatenation.
for the string which we cut - strs[i], we try each cut position for both strs[i] and its reverese, concatenate the other words after it to find the maximum result.
    we don't need to recaculate the concatenated string completely everytime. When we iterate through the cutting string we just need remove the first string and add last string to the mid part.*/
    public static String splitLoopedString5(String[] strs) {
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            String rev = new StringBuilder(strs[i]).reverse().toString();
            if (strs[i].compareTo(rev) < 0) strs[i] = rev;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n-1; i++) sb.append(strs[i]);
        String mid = sb.toString(), result = mid+strs[n-1];
        for (int i = 0; i < n; i++) {
            String str = strs[i], rev = new StringBuilder(str).reverse().toString();
            mid = mid.substring(str.length())+strs[(i+n-1)%n];
            for (int j = 0; j <= str.length(); j++) {
                String s1 = str.substring(j)+mid+str.substring(0, j), s2 = rev.substring(j)+mid+rev.substring(0, j);
                if (s1.compareTo(s2) >= 0 && s1.compareTo(result) > 0) result = s1;
                else if (s2.compareTo(s1) >= 0 && s2.compareTo(result) > 0) result = s2;
            }
        }
        return result;
    }
//------------------------------------------------------------------------------
}
/*
Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.

Specifically, to find the lexicographically biggest string, you need to experience two phases:

Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.
And your job is to find the lexicographically biggest one among all the possible regular strings.

Example:
Input: "abc", "xyz"
Output: "zyxcba"
Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-",
where '-' represents the looped status.
The answer string came from the fourth looped one,
where you could cut from the middle character 'a' and get "zyxcba".
Note:
The input strings will only contain lowercase letters.
The total length of all the strings will not over 1,000.
Seen this question in a real interview before?   Yes  No
Companies
Alibaba
Related Topics
String
 */