package _String._String;
import java.util.*;
import org.junit.Test;

//  14. Longest Common Prefix
//  https://leetcode.com/problems/longest-common-prefix/description/
//  http://www.lintcode.com/problem/longest-common-prefix/
//
//  给k个字符串，求出他们的最长公共前缀(LCP)


public class _014_String_Longest_Common_Prefix_E {
//------------------------------------------------------------------------------
//https://leetcode.com/articles/longest-common-prefix/
//------------------------------------------------------------------------------
    //Approach #1 (Horizontal scanning)
    class Solution1{
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) return "";
            String prefix = strs[0];
            for (int i = 1; i < strs.length; i++)
                while (strs[i].indexOf(prefix) != 0) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.isEmpty()) return "";
                }
            return prefix;
        }
    }



//------------------------------------------------------------------------------
    //Approach #2 (Vertical scanning)
    class Solution2{
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";
            for (int i = 0; i < strs[0].length() ; i++){
                char c = strs[0].charAt(i);
                for (int j = 1; j < strs.length; j ++) {
                    if (i == strs[j].length() || strs[j].charAt(i) != c)
                        return strs[0].substring(0, i);
                }
            }
            return strs[0];
        }
    }


//------------------------------------------------------------------------------
    //Approach #3 (Divide and conquer)
    class Solution3{
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";
            return longestCommonPrefix(strs, 0 , strs.length - 1);
        }

        private String longestCommonPrefix(String[] strs, int l, int r) {
            if (l == r) {
                return strs[l];
            }
            else {
                int mid = (l + r)/2;
                String lcpLeft =   longestCommonPrefix(strs, l , mid);
                String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
                return commonPrefix(lcpLeft, lcpRight);
            }
        }

        String commonPrefix(String left,String right) {
            int min = Math.min(left.length(), right.length());
            for (int i = 0; i < min; i++) {
                if ( left.charAt(i) != right.charAt(i) )
                    return left.substring(0, i);
            }
            return left.substring(0, min);
        }
    }

//------------------------------------------------------------------------------
    //Approach #4 (Binary search)
    class Solution4{
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0)
                return "";
            int minLen = Integer.MAX_VALUE;
            for (String str : strs)
                minLen = Math.min(minLen, str.length());
            int low = 1;
            int high = minLen;
            while (low <= high) {
                int middle = (low + high) / 2;
                if (isCommonPrefix(strs, middle))
                    low = middle + 1;
                else
                    high = middle - 1;
            }
            return strs[0].substring(0, (low + high) / 2);
        }

        private boolean isCommonPrefix(String[] strs, int len){
            String str1 = strs[0].substring(0,len);
            for (int i = 1; i < strs.length; i++)
                if (!strs[i].startsWith(str1))
                    return false;
            return true;
        }

    }


//------------------------------------------------------------------------------
    //Further Thoughts / Follow up
    public String longestCommonPrefix(String q, String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        Trie trie = new Trie();
        for (int i = 1; i < strs.length ; i++) {
            trie.insert(strs[i]);
        }
        return trie.searchLongestPrefix(q);
    }
//------------------------------------------------------------------------------




    class TrieNode {

        // R links to node children
        private TrieNode[] links;

        private final int R = 26;

        private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    public TrieNode get(char ch) {
        return links[ch -'a'];
    }
//    public void put(char ch, TrieNode node) {
//        links[ch -'a'] = node;
//    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
        // number of children non null links
        private int size;
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
            size++;
        }

        public int getLinks() {
            return size;
        }
        //assume methods containsKey, isEnd, get, put are implemented as it is described
        //in  https://leetcode.com/articles/implement-trie-prefix-tree/)
    }
//------------------------------------------------------------------------------

    class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        //assume methods insert, search, searchPrefix are implemented as it is described
//in  https://leetcode.com/articles/implement-trie-prefix-tree/)
        private String searchLongestPrefix(String word) {
            TrieNode node = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (node.containsKey(curLetter) && (node.getLinks() == 1) && (!node.isEnd())) {
                    prefix.append(curLetter);
                    node = node.get(curLetter);
                }
                else
                    return prefix.toString();

            }
            return prefix.toString();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (!node.containsKey(currentChar)) {
                    node.put(currentChar, new TrieNode());
                }
                node = node.get(currentChar);
            }
            node.setEnd();
        }

        // search a prefix or whole key in trie and
        // returns the node where search ends
        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (node.containsKey(curLetter)) {
                    node = node.get(curLetter);
                } else {
                    return null;
                }
            }
            return node;
        }


        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }
    }


//------------------------------------------------------------------------------
    //9Ch
    public class Jiuzhang {

        // 1. Method 1, start from the first one, compare prefix with next string, until end;
        // 2. Method 2, start from the first char, compare it with all string, and then the second char
        // I am using method 1 here
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            String prefix = strs[0];
            for(int i = 1; i < strs.length; i++) {
                int j = 0;
                while( j < strs[i].length() && j < prefix.length() && strs[i].charAt(j) == prefix.charAt(j)) {
                    j++;
                }
                if( j == 0) {
                    return "";
                }
                prefix = prefix.substring(0, j);
            }
            return prefix;
        }

    }

//------------------------------------------------------------------------------
}
/*

 */