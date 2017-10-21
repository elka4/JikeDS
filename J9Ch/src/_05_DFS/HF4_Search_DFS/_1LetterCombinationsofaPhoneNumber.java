package _05_DFS.HF4_Search_DFS;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Letter Combinations of a Phone Number
public class _1LetterCombinationsofaPhoneNumber {
    public ArrayList<String> letterCombinations(String digits) {
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



///////////////////////////////////////////////////////////

// version: 高频题班

    //  方法1 计状态
    /**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    ArrayList<String> ans2 = new ArrayList<>();

    void dfs(int x, int l, String str, String digits, String phone[]) {
        if (x == l) {
            ans2.add(str);
            return;
        }
        int d = digits.charAt(x) - '0';
        for (char c : phone[d].toCharArray()) {
            dfs(x + 1, l, str + c, digits, phone);
        }
    }

    public ArrayList<String> letterCombinations2(String digits) {
        // Write your code here
        String phone[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        if (digits.length() == 0) {
            return ans2;
        }
        dfs(0, digits.length(), "", digits, phone);
        return ans2;
    }


    @Test
    public void test03() {
        String digits = "23";
        System.out.println(letterCombinations2(digits));
    }
/////////////////////////////////////////////////////////


    // 方法2 计状态
    /**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    ArrayList<String> ans = new ArrayList<>();

    int l;
    String digits;
    final String phone[] = {"", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"};

    void dfs(int x, String str) {
        if (x == l) {
            ans.add(str);
            return;
        }
        int d = digits.charAt(x) - '0';
        for (char c : phone[d].toCharArray()) {
            dfs(x + 1, str + c);
        }
    }

    public ArrayList<String> letterCombinations3(String digits) {
        // Write your code here
        this.l = digits.length();
        this.digits = digits;

        if (digits.length() == 0) {
            return ans;
        }
        dfs(0, "");
        return ans;
    }

/////////////////////////////////////////////////////////
}
/*
Given a digit string excluded 01, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
 */