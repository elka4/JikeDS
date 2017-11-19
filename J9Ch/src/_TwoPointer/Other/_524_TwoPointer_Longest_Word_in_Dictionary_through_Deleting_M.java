package _TwoPointer.Other;
import java.util.*;

//  524. Longest Word in Dictionary through Deleting
//  https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/

public class _524_TwoPointer_Longest_Word_in_Dictionary_through_Deleting_M {

    //https://leetcode.com/articles/longest-word-in-dictionary-through-deletion/
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution11 {
        public String findLongestWord(String s, List < String > d) {
            HashSet < String > set = new HashSet < > (d);
            List < String > l = new ArrayList < > ();
            generate(s, "", 0, l);
            String max_str = ""; for (String str: l) {
                if (set.contains(str))
                    if (str.length() > max_str.length() ||
                            (str.length() == max_str.length() &&
                                    str.compareTo(max_str) < 0))
                        max_str = str;
            }
            return max_str;
        }
        public void generate(String s, String str, int i,  List < String > l) {
            if (i == s.length())
                l.add(str);
            else {
                generate(s, str + s.charAt(i), i + 1, l);
                generate(s, str, i + 1, l);
            }
        }
    }

    //Approach #2 Iterative Brute Force [Time Limit Exceeded]
    public class Solution22 {
        public String findLongestWord(String s, List < String > d) {
            HashSet < String > set = new HashSet <> (d);
            List < String > l = new ArrayList <> ();
            for	(int	i	=	0;	i	<	(1	<<	s.length()); i++) {
                String t = "";
                for (int j = 0; j < s.length(); j++) {
                    if (((i >> j) & 1) != 0) t += s.charAt(j);
                }
                l.add(t);
            }
            String max_str = "";
            for	(String	str:	l)	{
                if (set.contains(str))
                        if (str.length() > max_str.length() ||
                                (str.length() == max_str.length() &&
                                        str.compareTo(max_str) < 0)) max_str = str;
            }
            return max_str;
        }
    }

    //Approach #3 Sorting and checking Subsequence [Accepted]
    public class Solution33 {

        public String findLongestWord(String s, List < String > d) {
            Collections.sort(d, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return s2.length() != s1.length() ?
                            s2 .length() - s1.length() :s1.compareTo(s2);
                }
            });
            for (String str : d) {
                if (isSubsequence(str, s)) return str;
            }
            return "";
        }

        public boolean isSubsequence(String x, String y) {
            int j = 0;
            for (int i = 0; i < y.length() && j < x.length(); i++)
                if (x.charAt(j) == y.charAt(i))
                    j++;
            return j == x.length();
        }
    }

        // Approach #4 Without Sorting [Accepted]:
    public class Solution44 {

        public String findLongestWord(String s, List < String > d) {
            String max_str = "";
            for (String str : d) {
                if (isSubsequence(str, s)) {
                    if (str.length() > max_str.length() ||
                        (str.length() == max_str.length() && str.compareTo(max_str) < 0))
                        max_str = str;
                }
            }
            return max_str;
        }

        public boolean isSubsequence(String x, String y) {
            int j = 0;
            for (int i = 0; i < y.length() && j < x.length(); i++)
                if (x.charAt(j) == y.charAt(i))
                    j++;
            return j == x.length();
        }
    }


//-------------------------------------------------------------------------////////////////
    //  Easy Java Solution, isSubSequence
//Idea is sort the dictionary d first by length DESC then lexicographical
// ASC and test if p is SubSequence of s. The first match is the answer.

    public class Solution1 {
        public String findLongestWord(String s, List<String> d) {
            if (s.length() == 0 || d.size() == 0) return "";

            Collections.sort(d, (a, b) -> {
                if (a.length() != b.length()) return b.length() - a.length();
                return a.compareTo(b);
            });

            for (String p : d) {
                if (s.length() < p.length()) continue;
                if (isSubSeq(s, p)) return p;
            }

            return "";
        }

        private boolean isSubSeq(String s, String p) {
            int i = 0, j = 0;
            while (i < s.length() && j < p.length()) {
                if (s.charAt(i) == p.charAt(j)) {
                    i++; j++;
                }
                else {
                    i++;
                }
            }
            return j == p.length();
        }
    }


/*    There is no need to sort the dictionary. We could compare the string with current result during the iteration of the dictionary.

    If sorting the dictionary, let N be the size of dictionary, L be the length of each string in the dictionary, Ls be the length of string s, then the time complexity is
    O(NlgN * L + N * Ls) = O(N(lgN*L + Ls)). (each string comparison will cost O(L) time)
    If without sorting, the time complexity is:
    O(N*L + N * Ls) = O(N(L + Ls))

    The following is my code:*/

    public class Solution2 {
        public String findLongestWord(String s, List<String> d) {
            String res = "";
            if (s.length() == 0 || d.size() == 0) {
                return res;
            }
            for (String str : d) {
                int resLen = res.length();
                int strLen = str.length();
                if (isSequence(s, str) &&
                        (strLen > resLen || (strLen == resLen && str.compareTo(res) < 0))) {
                    res = str;
                }
            }
            return res;
        }

        private boolean isSequence(String s, String d) {
            int i = 0;
            int j = 0;
            while (i < s.length() && j < d.length()) {
                while (i < s.length() && s.charAt(i) != d.charAt(j)) {
                    i ++;
                }
                if (i < s.length()) {
                    i ++;
                    j ++;
                }
            }
            return j == d.length();
        }
    }


/*    @shawngao Nice solution. Here's my very clean solution without sorting. Sorting incurs of runtime complexity of at least klog(k), where k is the number of words in the dictionary. My algorithm has a runtime complexity of nk, where n is the number of characters in s.*/

    public class Solution3 {
        public String findLongestWord(String s, List<String> d) {
            if (s == null || s.length() == 0 || d == null || d.size() == 0) {
                return "";
            }

            String longest = "";

            for (String word : d) {
                if (containsSubsequence(s, word) && word.length() >= longest.length()) {
                    if (word.length() > longest.length() || word.compareTo(longest) < 0) {
                        longest = word;
                    }
                }
            }

            return longest;
        }

        private boolean containsSubsequence(String s, String word) {
            int i = 0;
            for (char c : s.toCharArray()) {
                if (c == word.charAt(i)) {
                    i ++;
                    if (i == word.length()) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


//    Easy Java Solution, isSubSequence
//    Idea is sort the dictionary d first by length DESC then lexicographical ASC and test if p is SubSequence of s. The first match is the answer.

    public class Solution4 {
        public String findLongestWord(String s, List<String> d) {
            if (s.length() == 0 || d.size() == 0) return "";

            Collections.sort(d, (a, b) -> {
                if (a.length() != b.length()) return b.length() - a.length();
                return a.compareTo(b);
            });

            for (String p : d) {
                if (s.length() < p.length()) continue;
                if (isSubSeq(s, p)) return p;
            }

            return "";
        }

        private boolean isSubSeq(String s, String p) {
            int i = 0, j = 0;
            while (i < s.length() && j < p.length()) {
                if (s.charAt(i) == p.charAt(j)) {
                    i++; j++;
                }
                else {
                    i++;
                }
            }
            return j == p.length();
        }
    }

    //Java Two Point Solution after Sorting the Dictionary
    class Solution5{
        private class MyCompare implements Comparator<String> {
            public int compare(String s1, String s2) {
                return s2.length() == s1.length() ? s1.compareTo(s2)
                        : s2.length() - s1.length();
            }
        }

        public String findLongestWord(String s, List<String> d) {
            Collections.sort(d, new MyCompare());
            for (String str : d) {
                if (check(s, str))
                    return str;
            }
            return "";
        }

        private boolean check(String s1, String s2) {
            char[] cs1 = s1.toCharArray();
            char[] cs2 = s2.toCharArray();
            int i = 0, j = 0;
            while (i < cs1.length && j < cs2.length) {
                if (cs1[i] == cs2[j])
                    j++;
                i++;
            }
            if(j == cs2.length){
                return true;
            }
            return false;
        }
    }

    //  My 13ms Java Solution beats 99% (using indexOf)
    class Solution6{
        public String findLongestWord(String s, List<String> d) {
            int max = 0;
            String ret = "";
            for (String t: d) {
                int index = -1;
                int len = t.length();
                if (len < max)
                    continue;
                for (char c: t.toCharArray()) {
                    index = s.indexOf(c, index + 1);
                    if (index == -1)
                        break;
                }
                if (index != -1 && len >= max) {
                    if (len == max)
                        ret = t.compareTo(ret) < 0 ? t : ret;
                    else
                        ret = t;
                    max = len;
                }
            }
            return ret;
        }
    }

    //Share my java solution (without sorting)
    class Solution7{
        public String findLongestWord(String s, List<String> d) {
            String res = "";
            if (s.length() == 0 || d.size() == 0)
                return "";
            int maxLen = 0;
            for (String tmp : d) {
                if (checkWord(s, tmp)) {
                    int len = tmp.length();
                    if (len > maxLen) {
                        maxLen = len;
                        res = tmp;
                    } else if (len == maxLen)
                        if (tmp.compareTo(res) < 0)
                            res = tmp;
                }
            }
            return res;
        }

        private boolean checkWord(String s, String d) {
            int sP = 0;
            int dP = 0;
            while (sP < s.length() && dP < d.length()) {
                if (s.charAt(sP) == d.charAt(dP)) {
                    sP++;
                    dP++;
                } else
                    sP++;
            }
            if (dP == d.length())
                return true;
            else
                return false;
        }
    }


    //Java Solution with HashMap and TreeSet
    public class Solution8 {
        private boolean canFormat(Map<Character, TreeSet<Integer>> map, String s){
            int ind = 0;
            for(char ch : s.toCharArray()){
                TreeSet<Integer> set = map.get(ch);
                if(set==null) return false;
                Integer pos = set.ceiling(ind);
                if(pos==null) return false;
                ind = pos + 1;
            }
            return true;
        }
        public String findLongestWord(String s, List<String> d) {
            Map<Character, TreeSet<Integer>> map = new HashMap<>();
            for(int i=0;i<s.length();i++){
                char ch = s.charAt(i);
                TreeSet<Integer> set = map.getOrDefault(ch, new TreeSet<>());
                set.add(i);
                map.put(ch, set);
            }
            String res = "";
            for(String str : d){
                if((res.equals("") || str.length()>res.length() ||
                        (str.length()==res.length() && str.compareTo(res)<0)) && canFormat(map, str)){
                    res = str;
                }
            }
            return res;
        }
    }

    //Java O(nlogn) solution using TreeSet
    public class Solution9 {
        public String findLongestWord(String s, List<String> d) {
            Collections.sort(d);
            TreeSet<String> res = new TreeSet<String>();
            for(String t:d)
            {
                if(isSubseq(t,s))
                {
                    if(res.size() > 0 && res.first().length() < t.length())
                    {
                        res = new TreeSet<>();
                        res.add(t);
                    }
                    else
                    {
                        res.add(t);
                    }
                }
            }
            if(res.size() > 0) return res.first();
            return "";
        }
        private boolean isSubseq(String t, String s)
        {
            if(t.length() > s.length()) return false;
            char cur;
            int sPtr = 0;
            for(int i = 0; i < t.length(); ++i)
            {
                cur = t.charAt(i);
                while(sPtr < s.length())
                {
                    if(s.charAt(sPtr) == cur) break;
                    ++sPtr;
                }
                if(sPtr == s.length()) return false;
                ++sPtr;
            }
            return true;
        }
    }

    //Java simple and fast solution
    public class Solution10 {
        public String findLongestWord(String s, List<String> dictionary) {
            String longest = "";
            for (String word : dictionary) {
                if (word.length() < longest.length() ||
                        (word.length() == longest.length() && word.compareTo(longest) > 0)) {
                    continue;
                }
                int i = 0;
                int j = 0;
                while (i < s.length() && j < word.length() &&
                        s.length() - i >= word.length() - j) { // last condition short circuit the loop
                    if (s.charAt(i) == word.charAt(j)) {
                        j++;
                    }
                    i++;
                }
                if (j == word.length()) {
                    longest = word;
                }
            }
            return longest;
        }
    }

}
/*
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"

Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"

Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
 */