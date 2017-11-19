package _05_DFS._Back_String;
import org.junit.Test;
import java.util.*;

/*
1.  Generate Parentheses
2.  Restore IP Addresses
3.  Palindrome Partitioning
4.  Palindrome Permutation II
 */

public class Back_String {

//////Generate Parentheses, Generate Parentheses, Generate Parentheses, Generate Parentheses ////////
//  22. Generate Parentheses
//  https://leetcode.com/problems/generate-parentheses/description/
//  http://www.lintcode.com/zh-cn/problem/generate-parentheses/

//给定 n 对括号，请写一个函数以将其生成新的括号组合，并返回所有组合结果。
//给定 n = 3, 可生成的组合如下:"((()))", "(()())", "(())()", "()(())", "()()()"


    // 9
    //Zhu
    //这个方法是DFS，但是并不是backtracking，因为每次传递到下层函数的是通过 + 重新创建的一个String
    public List<String> generateParenthesis_Zhu(int n) {
        List<String> result = new ArrayList<>();
        if(n <= 0) return result;
        dfs("", n, n, result);
        return result;
    }

    private void dfs(String paren, int left, int right, List<String> result){
        if (left > right) return;
        if (left == 0 && right == 0) result.add(paren);
        if (left > 0) dfs(paren + "(", left - 1, right, result);
        if(right > 0 )dfs(paren + ")", left, right - 1, result);
    }

    @Test
    public void test09(){
        System.out.println(generateParenthesis_Zhu(3));
    }
    //[((())), (()()), (())(), ()(()), ()()()]

//---------------------------------------------------------------------------------------------
    // 10
    //Zhu
    /*
    现在这方法就是正宗的backtracking，使用了StringBuilder，每次传到下层的都是同一个Object
    因此每次在返回上一层之后，需要删除上一层的操作
     */
    public List<String> generateParenthesis_Zhu10(int n) {
        List<String> result = new ArrayList<>();
        if(n <= 0)return result;
        dfs10(new StringBuilder(), n, n, result);
        return result;
    }
    //left right指要多少个左括号，多少个右括号
    private void dfs10(StringBuilder paren, int left, int right, List<String> result){
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            result.add(paren.toString());
        }
        if (left > 0) {
            dfs10(paren.append('('), left - 1, right, result);
            paren.deleteCharAt(paren.length() - 1);
        }
        if(right > 0 ){
            dfs10(paren.append(')'), left, right - 1, result);
            paren.deleteCharAt(paren.length() - 1);
        }
    }
    @Test
    public void test10(){
        System.out.println(generateParenthesis_Zhu(3));
    }//[((())), (()()), (())(), ()(()), ()()()]
    @Test
    public void test11(){
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();

        dfs10(sb, 1,3, result);
        System.out.println(result);
    }//[())), )()), ))()]

//---------------------------------------------------------------------------------------------
    //最好的是这个我自己改写的
    class Solution3 {
        public List<String> generateParenthesis3(int n) {

            List<String> result = new ArrayList<>();
            if(n <= 0)return result;
            dfs10_3(new StringBuilder(), n, n, result);
            return result;
        }
        //left right指要多少个左括号，多少个右括号
        private void dfs10_3(StringBuilder paren, int left, int right, List<String> result){
            if (left > right || left < 0 || right < 0) {
                return;
            }
            if (left == 0 && right == 0) {
                result.add(paren.toString());
            }

            dfs10_3(paren.append('('), left - 1, right, result);
            paren.deleteCharAt(paren.length() - 1);

            dfs10_3(paren.append(')'), left, right - 1, result);
            paren.deleteCharAt(paren.length() - 1);
        }
    }
//////Generate Parentheses, Generate Parentheses, Generate Parentheses, Generate Parentheses ////////

////////Restore IP Addresses, Restore IP Addresses, Restore IP Addresses, Restore IP Addresses///////////

//  93. Restore IP Addresses
//  https://leetcode.com/problems/restore-ip-addresses/description/
//  http://www.lintcode.com/zh-cn/problem/restore-ip-addresses/
//给一个由数字组成的字符串。求出其可能恢复为的所有IP地址。
//给出字符串 "25525511135"，所有可能的IP地址为：["255.255.11.135","255.255.111.35"]

    //4
    //jiuzhang
    //每个单位里只有数字
    //这个是Backtracking，用list来存储状态，在返回上一层函数之后要删除之前增加的
    public ArrayList<String> restoreIpAddresses_J1(String s) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> list = new ArrayList<String>();

        if(s.length() <4 || s.length() > 12)
            return result;

        helper(result, list, s , 0);
        return result;
    }

    private void helper(ArrayList<String> result, ArrayList<String> list, String s, int start){
        if(list.size() == 4){
            //这个很重要，如果只是list.size() == 4，可能s还没取完
            //根据题意，必须是全部s都取完了，而且是分成4个部分
            if(start != s.length()) return;

            StringBuilder sb = new StringBuilder();
            for(String tmp: list){
                sb.append(tmp);
                sb.append('.');
            }
            sb.deleteCharAt(sb.length() - 1);//记住这个方法
            result.add(sb.toString());
            return;
        }

        for(int i = start; i < s.length() && i < start + 3; i++){
            String tmp = s.substring(start, i + 1);
            if(isvalid(tmp)){
                list.add(tmp);
                helper(result, list, s, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isvalid(String s){
        // to eliminate cases like "00", "10"
        if(s.charAt(0) == '0') return s.equals("0");
        int digit = Integer.valueOf(s);//记住这个方法
        return digit >= 0 && digit <= 255;
    }


    @Test
    public void test02(){
        System.out.println(restoreIpAddresses_J1("25525511135"));
    }
    //[255.255.11.135, 255.255.111.35]

////////Restore IP Addresses, Restore IP Addresses, Restore IP Addresses, Restore IP Addresses///////////

///////Palindrome Partitioning, Palindrome Partitioning, Palindrome Partitioning, Palindrome Partitioning, ////
//  131     Palindrome Partitioning
//  132. Palindrome Partitioning II  is DP
//  https://leetcode.com/problems/palindrome-partitioning/description/
//  http://www.lintcode.com/zh-cn/problem/palindrome-partitioning/

//给定一个字符串s，将s分割成一些子串，使每个子串都是回文串。返回s所有可能的回文串分割方案。
//给出 s = "aab"，返回[["aa", "b"], ["a", "a", "b"]]

    //1
    //传到下一层的是string和新一轮的start
    //Backtracking, 用tempList来存储状态
    public List<List<String>> partition01(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }

    public void backtrack(List<List<String>> result, List<String> tempList, String s, int start){
        if(start == s.length()){
            result.add(new ArrayList<>(tempList));
            return;
        }

        for(int i = start; i < s.length(); i++){
            if(isValid(s, start, i)){
                tempList.add(s.substring(start, i + 1));
                backtrack(result, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public boolean isValid(String s, int low, int high){
        while(low < high)
            if(s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    @Test
    public void test03(){
        System.out.println(partition01("aab"));
    }//[[a, a, b], [aa, b]]



///////Palindrome Partitioning, Palindrome Partitioning, Palindrome Partitioning, Palindrome Partitioning, ////

///////Palindrome Permutation II, Palindrome Permutation II, Palindrome Permutation II, Palindrome Permutation II//////
//  267. Palindrome Permutation II
//  https://leetcode.com/problems/palindrome-permutation-ii/description/
//  http://www.lintcode.com/zh-cn/problem/palindrome-partitioning-ii/


/*
Given a string s, return all the palindromic permutations (without duplicates) of it.
Return an empty list if no palindromic permutation could be form.

For example:
Given s = "aabb", return ["abba", "baab"].
Given s = "abc", return [].
 */

    //3

    public List<String> generatePalindromes3(String s) {
        int odd = 0;
        String mid = "";
        List<String> result = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();

        // step 1. build character count map and count odds
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
            odd += map.get(c) % 2 != 0 ? 1 : -1;
        }

        // cannot form any palindromic string
        if (odd > 1) return result;

        // step 2. add half count of each character to list
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();

            if (val % 2 != 0) mid += key;

            for (int i = 0; i < val / 2; i++) list.add(key);
        }

        // step 3. generate all the permutations
        getPerm(list, mid, new boolean[list.size()], new StringBuilder(), result);

        return result;
    }

    // generate all unique permutation from list
    void getPerm(List<Character> list, String mid, boolean[] used, StringBuilder sb, List<String> result) {
        if (sb.length() == list.size()) {
            // form the palindromic string
            result.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            // avoid duplication
            if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) continue;

            if (!used[i]) {
                used[i] = true; sb.append(list.get(i));
                // recursion
                getPerm(list, mid, used, sb, result);
                // backtracking
                used[i] = false; sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    @Test
    public void test04(){
        System.out.println(generatePalindromes3("abc"));//  []
        System.out.println(generatePalindromes3("aabb"));// [abba, baab]
    }


///////Palindrome Permutation II, Palindrome Permutation II, Palindrome Permutation II, Palindrome Permutation II//////

//-------------------------------------------------------------------------/////////


//-------------------------------------------------------------------------/////////



//-------------------------------------------------------------------------/////////


//-------------------------------------------------------------------------/////////



}
