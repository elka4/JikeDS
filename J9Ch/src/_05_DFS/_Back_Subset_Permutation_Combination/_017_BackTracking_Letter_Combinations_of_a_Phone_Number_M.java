package _05_DFS._Back_Subset_Permutation_Combination;
import java.util.*;


//  17. Letter Combinations of a Phone Number
//  https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
//  http://www.lintcode.com/zh-cn/problem/letter-combinations-of-a-phone-number/
public class _017_BackTracking_Letter_Combinations_of_a_Phone_Number_M {
    //My java solution with FIFO queue
    public List<String> letterCombinations01(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};

        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }

////////////////////////////////////////////////////////////////////
    //My recursive solution using Java
    private final String[] KEYS = { "", "", "abc", "def", "ghi",
        "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations02(String digits) {
        List<String> ret = new LinkedList<String>();
        combination("", digits, 0, ret);
        return ret;
    }

    private void combination(String prefix, String digits, int offset, List<String> ret) {
        if (offset >= digits.length()) {
            ret.add(prefix);
            return;
        }
        String letters = KEYS[(digits.charAt(offset) - '0')];

        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, offset + 1, ret);
        }
    }

////////////////////////////////////////////////////////////////////
    // jiuzhang
    public ArrayList<String> letterCombinations_J1(String digits) {
        ArrayList<String> result = new ArrayList<String>();

        if (digits == null || digits.equals("")) {
            return result;
        }

        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v'});
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        StringBuilder sb = new StringBuilder();
        helper(map, digits, sb, result);

        return result;
    }

    private void helper(Map<Character, char[]> map, String digits,
                        StringBuilder sb, ArrayList<String> result) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            helper(map, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

////////////////////////////////////////////////////////////////////
    // 9Ch
    // version: 高频题班

    //  方法1 计状态
    ArrayList<String> ans = new ArrayList<>();

    void dfs(int x, int l, String str, String digits, String phone[]) {
        if (x == l) {
            ans.add(str);
            return;
        }
        int d = digits.charAt(x) - '0';
        for (char c : phone[d].toCharArray()) {
            dfs(x + 1, l, str + c, digits, phone);
        }
    }

    public ArrayList<String> letterCombinations_J2(String digits) {
        // Write your code here
        String phone[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        if (digits.length() == 0) {
            return ans;
        }
        dfs(0, digits.length(), "", digits, phone);
        return ans;
    }

////////////////////////////////////////////////////////////////////
    // 9Ch
    // 方法2 计状态
    ArrayList<String> ans_J3 = new ArrayList<>();

    int l;
    String digits;
    final String phone[] = {"", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"};

    void dfs_J3(int x, String str) {
        if (x == l) {
            ans_J3.add(str);
            return;
        }
        int d = digits.charAt(x) - '0';
        for (char c : phone[d].toCharArray()) {
            dfs_J3(x + 1, str + c);
        }
    }

    public ArrayList<String> letterCombinations_J3(String digits) {
        // Write your code here
        this.l = digits.length();
        this.digits = digits;

        if (digits.length() == 0) {
            return ans_J3;
        }
        dfs_J3(0, "");
        return ans_J3;
    }

///////////////////////////////////////////////////////////////////
}
/*
电话号码的字母组合

Given a digit string excluded 01, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Cellphone

 注意事项

以上的答案是按照词典编撰顺序进行输出的，不过，在做本题时，你也可以任意选择你喜欢的输出顺序。

样例
给定 "23"

返回 ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 */

/*
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */