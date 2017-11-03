package _TwoPointer;
import java.util.*;
import org.junit.Test;

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
                                    str.compareTo(max_str) < 0)) max_str = str;
            }
            return max_str;
        }
        public void generate(String s, String str, int i,  List < String > l) {
            if (i == s.length())
                l.add(str);
            else {
                generate(s, str + s.charAt(i), i + 1, l); generate(s, str, i + 1, l);
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
        public boolean isSubsequence(String x, String y) {
            int j = 0;
            for (int i = 0; i < y.length() && j < x.length(); i++)
                if (x.charAt(j) == y.charAt(i))
                    j++;
            return j == x.length();
        }
        public String findLongestWord(String s, List < String > d) {
            Collections.sort(d, new Comparator<String>() {
                public int compare(String sl, String s2) {
                    return s2.length() != sl.length() ? s2 .length() - sl.length() :sl.compareTo(s2);
                }
            });
            for (String str : d) {
                if (isSubsequence(str, s)) return str;
            }
            return "";
        }
    }

        // Approach #4 Without Sorting [Accepted]:
    public class Solution44 {
        public boolean isSubsequence(String x, String y) {
            int j = 0;
            for (int i = 0; i < y.length() && j < x.length(); i++)
                if (x.charAt(j) == y.charAt(i))
                    j++;
            return j == x.length();
        }
        public String findLongestWord(String s, List < String > d) {
            String max_str = "";
            for (String str : d) {
                if (isSubsequence(str, s)) {
                    if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
                        max_str = str;
                }
            }
            return max_str;
        }
    }


//////////////////////////////////////////////////////////////////////////////
    //  Easy Java Solution, isSubSequence
//Idea is sort the dictionary d first by length DESC then lexicographical ASC and test if p is SubSequence of s. The first match is the answer.

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