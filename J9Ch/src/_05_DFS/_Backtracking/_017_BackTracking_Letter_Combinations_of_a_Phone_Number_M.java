package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;

//  17. Letter Combinations of a Phone Number
//  https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
//  http://www.lintcode.com/zh-cn/problem/letter-combinations-of-a-phone-number/
public class _017_BackTracking_Letter_Combinations_of_a_Phone_Number_M {
    //My java solution with FIFO queue
    //iterative
    public List<String> letterCombinations01(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits == null || digits.equals("")) {
            return ans;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};

        ans.add("");
        for(int i = 0; i < digits.length(); i++){
            int x = Character.getNumericValue(digits.charAt(i));//记住这个方法
            while(ans.peek().length() == i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }
    @Test
    public void test01(){
        System.out.println(letterCombinations01("23"));
    }
    //[ad, ae, af, bd, be, bf, cd, ce, cf]
    @Test
    public void test011(){
        System.out.println(letterCombinations01("").size());
    }//0
    /*
        Input:""
        Output:[""]
        Expected:[]
     */


    /*
    return  the numeric value of the character, as a nonnegative {@code int}
     *           value; -2 if the character has a numeric value that is not a
     *          nonnegative integer; -1 if the character has no numeric value.
     */
    @Test
    public void testChar(){
        System.out.println(Character.getNumericValue('7'));//7
        System.out.println(Character.getNumericValue('a'));//10
        System.out.println(Character.getNumericValue('A'));//10
        System.out.println(Character.getNumericValue('\n'));//-1
//        System.out.println(Character.getNumericValue(null));
    }


////////////////////////////////////////////////////////////////////

    private final String[] KEYS = { "", "", "abc", "def",
            "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations02(String digits) {
        List<String> ret = new LinkedList<String>();
        if (digits == null || digits.equals("")) {
            return ret;
        }
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
    @Test
    public void test02(){
        System.out.println(letterCombinations02("23"));
    }
    @Test
    public void test022(){
        System.out.println(letterCombinations02("").size());
    }//0
    /*
        Input:""
        Output:[""]
        Expected:[]
     */
////////////////////////////////////////////////////////////////////
    // 9Ch
    public List<String> letterCombinations_J1(String digits) {
        List<String> result = new ArrayList<String>();

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
                        StringBuilder sb, List<String> result) {
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
    @Test
    public void test03(){
        System.out.println(letterCombinations_J1("23"));
    }
    @Test
    public void test033(){
        System.out.println(letterCombinations_J1("").size());
    }//0
////////////////////////////////////////////////////////////////////
    // 9Ch
    // version: 高频题班
    //  方法1 计状态
    List<String> ans = new ArrayList<>();

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

    public List<String> letterCombinations_J2(String digits) {
        // Write your code here
        String phone[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        if (digits.length() == 0) {
            return ans;
        }
        dfs(0, digits.length(), "", digits, phone);
        return ans;
    }
    @Test
    public void test044(){
        System.out.println(letterCombinations_J2("").size());
    }//0
////////////////////////////////////////////////////////////////////
    // 9Ch
    // 方法2 计状态
    /**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    List<String> answer = new ArrayList<>();

    int l;
    String digits;
    final String phone[] = {"", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"};

    void dfs(int x, String str) {
        if (x == l) {
            answer.add(str);
            return;
        }
        int d = digits.charAt(x) - '0';
        for (char c : phone[d].toCharArray()) {
            dfs(x + 1, str + c);
        }
    }

    public List<String> letterCombinations_J3(String digits) {
        // Write your code here
        this.l = digits.length();
        this.digits = digits;

        if (digits.length() == 0) {
            return answer;
        }
        dfs(0, "");
        return answer;
    }
    @Test
    public void test055(){
        System.out.println(letterCombinations_J3("").size());
    }//0
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

标签
字符串处理 回溯法 递归
 */

/* Leetcode
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
 */