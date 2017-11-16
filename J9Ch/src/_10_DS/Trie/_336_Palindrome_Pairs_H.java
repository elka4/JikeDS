package _10_DS.Trie;
import java.util.*;

//  336. Palindrome Pairs
//  https://leetcode.com/problems/palindrome-pairs/description/
//  Hash Table,  String,  Trie
public class _336_Palindrome_Pairs_H {
/////////////////////////////////////////////////////////////////////////
//150 ms 45 lines JAVA solution
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        if (words == null || words.length < 2) return ret;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i=0; i<words.length; i++) map.put(words[i], i);
        for (int i=0; i<words.length; i++) {
            // System.out.println(words[i]);
            for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(map.get(str2rvs));
                        list.add(i);
                        ret.add(list);
                        // System.out.printf("isPal(str1): %s\n", list.toString());
                    }
                }
                if (isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    // check "str.length() != 0" to avoid duplicates
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length()!=0) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(map.get(str1rvs));
                        ret.add(list);
                        // System.out.printf("isPal(str2): %s\n", list.toString());
                    }
                }
            }
        }
        return ret;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) !=  str.charAt(right--)) return false;
        }
        return true;
    }
//    The <= in for (int j=0; j<=words[i].length(); j++) is aimed to handle empty string in the input. Consider the test case of ["a", ""];
//
//    Since we now use <= in for (int j=0; j<=words[i].length(); j++) instead of <. There may be duplicates in the output (consider test case ["abcd", "dcba"]). Therefore I put a str2.length()!=0 to avoid duplicates.
//
//    Another way to avoid duplicates is to use Set<List<Integer>> ret = new HashSet<>(); and return new ArrayList<>(ret);
/////////////////////////////////////////////////////////////////////////
/*The Easy-to-unserstand JAVA Solution
    There are several cases to be considered that isPalindrome(s1 + s2):

    Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.

            Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.

            Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.

            Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.

    To make the search faster, build a HashMap to store the String-idx pairs.

    My code:*/

    public class Solution2 {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if(words == null || words.length == 0){
                return res;
            }
            //build the map save the key-val pairs: String - idx
            HashMap<String, Integer> map = new HashMap<>();
            for(int i = 0; i < words.length; i++){
                map.put(words[i], i);
            }

            //special cases: "" can be combine with any palindrome string
            if(map.containsKey("")){
                int blankIdx = map.get("");
                for(int i = 0; i < words.length; i++){
                    if(isPalindrome(words[i])){
                        if(i == blankIdx) continue;
                        res.add(Arrays.asList(blankIdx, i));
                        res.add(Arrays.asList(i, blankIdx));
                    }
                }
            }

            //find all string and reverse string pairs
            for(int i = 0; i < words.length; i++){
                String cur_r = reverseStr(words[i]);
                if(map.containsKey(cur_r)){
                    int found = map.get(cur_r);
                    if(found == i) continue;
                    res.add(Arrays.asList(i, found));
                }
            }

            //find the pair s1, s2 that
            //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
            //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
            for(int i = 0; i < words.length; i++){
                String cur = words[i];
                for(int cut = 1; cut < cur.length(); cut++){
                    if(isPalindrome(cur.substring(0, cut))){
                        String cut_r = reverseStr(cur.substring(cut));
                        if(map.containsKey(cut_r)){
                            int found = map.get(cut_r);
                            if(found == i) continue;
                            res.add(Arrays.asList(found, i));
                        }
                    }
                    if(isPalindrome(cur.substring(cut))){
                        String cut_r = reverseStr(cur.substring(0, cut));
                        if(map.containsKey(cut_r)){
                            int found = map.get(cut_r);
                            if(found == i) continue;
                            res.add(Arrays.asList(i, found));
                        }
                    }
                }
            }

            return res;
        }

        public String reverseStr(String str){
            StringBuilder sb= new StringBuilder(str);
            return sb.reverse().toString();
        }

        public boolean isPalindrome(String s){
            int i = 0;
            int j = s.length() - 1;
            while(i <= j){
                if(s.charAt(i) != s.charAt(j)){
                    return false;
                }
                i++;
                j--;
            }
            return true;
        }
    }
/////////////////////////////////////////////////////////////////////////
//3
/*
The Easy-to-unserstand JAVA Solution
    There are several cases to be considered that isPalindrome(s1 + s2):

    Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.

            Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.

            Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.

            Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.

    To make the search faster, build a HashMap to store the String-idx pairs.

    My code:
*/

    public class Solution3 {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if(words == null || words.length == 0){
                return res;
            }
            //build the map save the key-val pairs: String - idx
            HashMap<String, Integer> map = new HashMap<>();
            for(int i = 0; i < words.length; i++){
                map.put(words[i], i);
            }

            //special cases: "" can be combine with any palindrome string
            if(map.containsKey("")){
                int blankIdx = map.get("");
                for(int i = 0; i < words.length; i++){
                    if(isPalindrome(words[i])){
                        if(i == blankIdx) continue;
                        res.add(Arrays.asList(blankIdx, i));
                        res.add(Arrays.asList(i, blankIdx));
                    }
                }
            }

            //find all string and reverse string pairs
            for(int i = 0; i < words.length; i++){
                String cur_r = reverseStr(words[i]);
                if(map.containsKey(cur_r)){
                    int found = map.get(cur_r);
                    if(found == i) continue;
                    res.add(Arrays.asList(i, found));
                }
            }

            //find the pair s1, s2 that
            //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
            //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
            for(int i = 0; i < words.length; i++){
                String cur = words[i];
                for(int cut = 1; cut < cur.length(); cut++){
                    if(isPalindrome(cur.substring(0, cut))){
                        String cut_r = reverseStr(cur.substring(cut));
                        if(map.containsKey(cut_r)){
                            int found = map.get(cut_r);
                            if(found == i) continue;
                            res.add(Arrays.asList(found, i));
                        }
                    }
                    if(isPalindrome(cur.substring(cut))){
                        String cut_r = reverseStr(cur.substring(0, cut));
                        if(map.containsKey(cut_r)){
                            int found = map.get(cut_r);
                            if(found == i) continue;
                            res.add(Arrays.asList(i, found));
                        }
                    }
                }
            }

            return res;
        }

        public String reverseStr(String str){
            StringBuilder sb= new StringBuilder(str);
            return sb.reverse().toString();
        }

        public boolean isPalindrome(String s){
            int i = 0;
            int j = s.length() - 1;
            while(i <= j){
                if(s.charAt(i) != s.charAt(j)){
                    return false;
                }
                i++;
                j--;
            }
            return true;
        }
    }
/////////////////////////////////////////////////////////////////////////
//4
//    Accepted short Java solution using HashMap
    class Solution5{
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> pairs = new LinkedList<>();
            if (words == null) return pairs;
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < words.length; ++ i) map.put(words[i], i);
            for (int i = 0; i < words.length; ++ i) {
                int l = 0, r = 0;
                while (l <= r) {
                    String s = words[i].substring(l, r);
                    Integer j = map.get(new StringBuilder(s).reverse().toString());
                    if (j != null && i != j && isPalindrome(words[i].substring(l == 0 ? r : 0, l == 0 ? words[i].length() : l)))
                        pairs.add(Arrays.asList(l == 0 ? new Integer[]{i, j} : new Integer[]{j, i}));
                    if (r < words[i].length()) ++r;
                    else ++l;
                }
            }
            return pairs;
        }

        private boolean isPalindrome(String s) {
            for (int i = 0; i < s.length()/2; ++ i)
                if (s.charAt(i) != s.charAt(s.length()-1-i))
                    return false;
            return true;
        }
    }

////////Solution_Trie, Solution_Trie, Solution_Trie, Solution_Trie, Solution_Trie, Solution_Trie
    //O(n*k^2) java solution with Trie structure (n: total number of words; k: average length of each word)
    class Solution_Trie1{
        private class TrieNode {
            TrieNode[] next;
            int index;
            List<Integer> list;

            TrieNode() {
                next = new TrieNode[26];
                index = -1;
                list = new ArrayList<>();
            }
        }

        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<>();

            TrieNode root = new TrieNode();
            for (int i = 0; i < words.length; i++) addWord(root, words[i], i);
            for (int i = 0; i < words.length; i++) search(words, i, root, res);

            return res;
        }

        private void addWord(TrieNode root, String word, int index) {
            for (int i = word.length() - 1; i >= 0; i--) {
                int j = word.charAt(i) - 'a';
                if (root.next[j] == null) root.next[j] = new TrieNode();
                if (isPalindrome(word, 0, i)) root.list.add(index);
                root = root.next[j];
            }

            root.list.add(index);
            root.index = index;
        }

        private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
            for (int j = 0; j < words[i].length(); j++) {
                if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                    res.add(Arrays.asList(i, root.index));
                }

                root = root.next[words[i].charAt(j) - 'a'];
                if (root == null) return;
            }

            for (int j : root.list) {
                if (i == j) continue;
                res.add(Arrays.asList(i, j));
            }
        }

        private boolean isPalindrome(String word, int i, int j) {
            while (i < j) {
                if (word.charAt(i++) != word.charAt(j--)) return false;
            }

            return true;
        }
    }

//Note: I have added the solution to the original post above so you can skip this one.
    class Solution_Trie2{
        class TrieNode {
            TrieNode[] next;
            int index;
            List<Integer> list;

            TrieNode() {
                next = new TrieNode[26];
                index = -1;
                list = new ArrayList<>();
            }
        }

        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<>();

            TrieNode root = new TrieNode();
            for (int i = 0; i < words.length; i++) addWord(root, words[i], i);
            for (int i = 0; i < words.length; i++) search(words, i, root, res);

            return res;
        }

        private void addWord(TrieNode root, String word, int index) {
            for (int i = word.length() - 1; i >= 0; i--) {
                int j = word.charAt(i) - 'a';
                if (root.next[j] == null) root.next[j] = new TrieNode();
                if (isPalindrome(word, 0, i)) root.list.add(index);
                root = root.next[j];
            }

            root.list.add(index);
            root.index = index;
        }

        private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
            for (int j = 0; j < words[i].length(); j++) {
                if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                    res.add(Arrays.asList(i, root.index));
                }

                root = root.next[words[i].charAt(j) - 'a'];
                if (root == null) return;
            }

            for (int j : root.list) {
                if (i == j) continue;
                res.add(Arrays.asList(i, j));
            }
        }

        private boolean isPalindrome(String word, int i, int j) {
            while (i < j) {
                if (word.charAt(i++) != word.charAt(j--)) return false;
            }

            return true;
        }
    }
////////Solution_Trie, Solution_Trie, Solution_Trie, Solution_Trie, Solution_Trie, Solution_Trie
/////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////


}
/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */