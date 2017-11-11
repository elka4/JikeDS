package _05_DFS._Back_String;
import java.util.*;

// 267. Palindrome Permutation II

public class _267_BackTracking_Palindrome_Permutation_II_M {

    class sol{
        public List<String> generatePalindromes(String s) {
            int odd = 0;
            String mid = "";
            List<String> res = new ArrayList<>();
            List<Character> list = new ArrayList<>();
            Map<Character, Integer> map = new HashMap<>();

            // step 1. build character count map and count odds
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
                odd += map.get(c) % 2 != 0 ? 1 : -1;
            }

            // cannot form any palindromic string
            if (odd > 1) return res;

            // step 2. add half count of each character to list
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                char key = entry.getKey();
                int val = entry.getValue();

                if (val % 2 != 0) mid += key;

                for (int i = 0; i < val / 2; i++) list.add(key);
            }

            // step 3. generate all the permutations
            getPerm(list, mid, new boolean[list.size()], new StringBuilder(), res);

            return res;
        }

        // generate all unique permutation from list
        void getPerm(List<Character> list, String mid, boolean[] used, StringBuilder sb, List<String> res) {
            if (sb.length() == list.size()) {
                // form the palindromic string
                res.add(sb.toString() + mid + sb.reverse().toString());
                sb.reverse();
                return;
            }

            for (int i = 0; i < list.size(); i++) {
                // avoid duplication
                if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) continue;

                if (!used[i]) {
                    used[i] = true; sb.append(list.get(i));
                    // recursion
                    getPerm(list, mid, used, sb, res);
                    // backtracking
                    used[i] = false; sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }



    class Solution2{
        public List<String> generatePalindromes(String s) {
            int[] map = new int[256];
            for(int i=0;i<s.length();i++){
                map[s.charAt(i)]++;
            }
            int j=0,count=0;
            for(int i=0;i<256;i++){
                if(count== 0 && map[i] %2 == 1){
                    j= i;
                    count++;
                }else if(map[i] % 2==1){
                    return new ArrayList<String>();
                }
            }
            String cur = "";
            if(j != 0){
                cur = ""+ (char)j;
                map[j]--;
            }
            List<String> res = new ArrayList<String>();
            DFS(res,cur,map,s.length());
            return res;
        }
        public void DFS(List<String> res,String cur,int[] map,int len){
            if(cur.length()== len){
                res.add(new String(cur));
            }else {
                for(int i=0;i<map.length;i++){
                    if(map[i] <= 0) continue;
                    map[i] = map[i] - 2;
                    cur = (char)i + cur + (char)i;
                    DFS(res,cur,map,len);
                    cur = cur.substring(1,cur.length()-1);
                    map[i] = map[i]+2;
                }
            }
        }
    }



    class Solution3{
        private List<String> list = new ArrayList<>();

        public List<String> generatePalindromes(String s) {
            int numOdds = 0; // How many characters that have odd number of count
            int[] map = new int[256]; // Map from character to its frequency
            for (char c: s.toCharArray()) {
                map[c]++;
                numOdds = (map[c] & 1) == 1 ? numOdds+1 : numOdds-1;
            }
            if (numOdds > 1)   return list;

            String mid = "";
            int length = 0;
            for (int i = 0; i < 256; i++) {
                if (map[i] > 0) {
                    if ((map[i] & 1) == 1) { // Char with odd count will be in the middle
                        mid = "" + (char)i;
                        map[i]--;
                    }
                    map[i] /= 2; // Cut in half since we only generate half string
                    length += map[i]; // The length of half string
                }
            }
            generatePalindromesHelper(map, length, "", mid);
            return list;
        }
        private void generatePalindromesHelper(int[] map, int length, String s, String mid) {
            if (s.length() == length) {
                StringBuilder reverse = new StringBuilder(s).reverse(); // Second half
                list.add(s + mid + reverse);
                return;
            }
            for (int i = 0; i < 256; i++) { // backtracking just like permutation
                if (map[i] > 0) {
                    map[i]--;
                    generatePalindromesHelper(map, length, s + (char)i, mid);
                    map[i]++;
                }
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////
    //Approach #2 Backtracking [Accepted]
public class leet1 {
    Set < String > set = new HashSet < > ();
    public List < String > generatePalindromes(String s) {
        int[] map = new int[128];
        char[] st = new char[s.length() / 2];
        if (!canPermutePalindrome(s, map))
            return new ArrayList < > ();
        char ch = 0;
        int k = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 == 1)
                ch = (char) i;
            for (int j = 0; j < map[i] / 2; j++) {
                st[k++] = (char) i;
            }
        }
        permute(st, 0, ch);
        return new ArrayList < String > (set);
    }
    public boolean canPermutePalindrome(String s, int[] map) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }
    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    void permute(char[] s, int l, char ch) {
        if (l == s.length) {
            set.add(new String(s) + (ch == 0 ? "" : ch) + new StringBuffer(new String(s)).reverse());
        } else {
            for (int i = l; i < s.length; i++) {
                if (s[l] != s[i] || l == i) {
                    swap(s, l, i);
                    permute(s, l + 1, ch);
                    swap(s, l, i);
                }
            }
        }
    }
}

///////////////////////////////////////////////////////////////////////////////////
// version 1
// f[i] 表示前i个字母，最少可以被分割为多少个回文串
// 最后return f[n] - 1
public class Jiuzhang {
    private boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                        = isPalindrome[start + 1][start + length - 1] &&
                          s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // preparation
        boolean[][] isPalindrome = getIsPalindrome(s);

        // initialize
        int[] f = new int[s.length() + 1];
        f[0] = 0;

        // main
        for (int i = 1; i <= s.length(); i++) {
            f[i] = Integer.MAX_VALUE; // or f[i] = i
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }

        return f[s.length()] - 1;
    }
}

    // version 2
// f[i] 表示前i个字母，最少被切割几次可以切割为都是回文串。
// 最后return f[n]
    public class Jiuzhang2 {
        private boolean isPalindrome(String s, int start, int end) {
            for (int i = start, j = end; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        }

        private boolean[][] getIsPalindrome(String s) {
            boolean[][] isPalindrome = new boolean[s.length()][s.length()];

            for (int i = 0; i < s.length(); i++) {
                isPalindrome[i][i] = true;
            }
            for (int i = 0; i < s.length() - 1; i++) {
                isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            }

            for (int length = 2; length < s.length(); length++) {
                for (int start = 0; start + length < s.length(); start++) {
                    isPalindrome[start][start + length]
                            = isPalindrome[start + 1][start + length - 1] &&
                              s.charAt(start) == s.charAt(start + length);
                }
            }

            return isPalindrome;
        }

        public int minCut(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            // preparation
            boolean[][] isPalindrome = getIsPalindrome(s);

            // initialize
            int[] f = new int[s.length() + 1];
            for (int i = 0; i <= s.length(); i++) {
                f[i] = i - 1;
            }

            // main
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j][i - 1]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }

            return f[s.length()];
        }
    }
///////////////////////////////////////////////////////////////////////////////////


}
/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].


 */


/*
给定一个字符串s，将s分割成一些子串，使每个子串都是回文。

返回s符合要求的的最少分割次数。

您在真实的面试中是否遇到过这个题？ Yes
样例
比如，给出字符串s = "aab"，

返回 1， 因为进行一次分割可以将字符串s分割成["aa","b"]这样两个回文子串
 */