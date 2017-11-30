package _String._DFS;
import java.util.*;
import org.junit.Test;

//  17. Letter Combinations of a Phone Number
//  https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
//  http://www.lintcode.com/zh-cn/problem/letter-combinations-of-a-phone-number/
//  backtracking, string
//  6：5
//  _022_String_Generate_Parentheses_M
public class _017_Letter_Combinations_of_a_Phone_Number_M {
//-------------------------------------------------------------------------------
    //5
    //改写 version: 高频题班 方法1 计状态
    //不需要l，digits.length()就可以了
    //每次传到下层的有新的长度x+1，和新的string str+c
    class Solution_Jiuzhang2{
        ArrayList<String> ans = new ArrayList<>();

        public ArrayList<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return ans;
            }
            String phone[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            dfs(0, "", digits, phone);
            return ans;
        }

        //x就是offset
        void dfs(int x, String str, String digits, String phone[]) {
            if (x == digits.length()) {
                ans.add(str);
                return;
            }
            int d = digits.charAt(x) - '0';

            for (char c : phone[d].toCharArray()) {
                dfs(x + 1, str + c, digits, phone);
            }
        }
    }

//-------------------------------------------------------------------------------
    //1
    //My java solution with FIFO queue
    //
    public List<String> letterCombinations01(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //这里应该做null和size为0的判断

        ans.add("");
        //i的意义：输入的数字中从左到右数的第几位
        for(int i = 0; i < digits.length(); i++){
            //把输入的String中数字转为int
            //getNumericValue这个方法从字母也能操作得到数字，所以是否最佳？
            int x = Character.getNumericValue(digits.charAt(i));
            //peak: Retrieves, but does not remove, the head (first element) of this list.
            //如果ans里第一个元素的长度==i，就是需要对首元素加一个char
            //index 0对应长度0，只差一位，刚好就是给下面for循环加一个char的
            while(ans.peek().length() == i){
                //remove: Retrieves and removes the head (first element) of this list.
                String t = ans.remove();//这就是前面为什么要在for之前ans.add("");
                for(char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }

    @Test
    public void test01(){
        System.out.println(letterCombinations01("").size());
    }//1

//-------------------------------------------------------------------------------
    //2
    //My recursive solution using Java
    private final String[] KEYS = { "", "", "abc", "def", "ghi",
        "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations02(String digits) {
        List<String> ret = new LinkedList<String>();
        if (digits == null || digits.equals("")) {
            return ret;
        }
        combination("", digits, 0, ret);
        return ret;
    }

    private void combination(String prefix, String digits, int offset, List<String> ret) {
        if (offset == digits.length()) {//原来为>=
            ret.add(prefix);
            return;
        }
        //对于一个按键的所有字母进行操作
        String letters = KEYS[(digits.charAt(offset) - '0')];

        //用prefix String来记录状态。每次进入recursion都是重新建了一个String。
        //所以不用使用经典的add，remove包围
        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, offset + 1, ret);
        }
    }
    @Test
    public void test02(){
        System.out.println(letterCombinations02("").size());
    }//1

//-------------------------------------------------------------------------------
    //3
    // jiuzhang
    public ArrayList<String> letterCombinations_J1(String digits) {
        ArrayList<String> result = new ArrayList<String>();

        if (digits == null || digits.equals("")) {
            return result;
        }
        //这个有点烦
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
        //这个是经典的add，remove
        //StringBuilder记录状态
        //对于从短到长，从左往右输入的数字对应的按键的每个字母进行操作
        //sb.length()为0对应index为0的第一个输入数字，以此类推
        for (char c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            helper(map, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

//-------------------------------------------------------------------------------
    //4
    // 9Ch
    // version: 高频题班

    //  方法1 计状态
    class Solution_Jiuzhang1{
        ArrayList<String> ans = new ArrayList<>();
        public ArrayList<String> letterCombinations_J2(String digits) {
            String phone[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

            if (digits.length() == 0) {
                return ans;
            }
            dfs(0, digits.length(), "", digits, phone);
            return ans;
        }
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
    }

//-------------------------------------------------------------------------------
    //6
    // 9Ch
    // 方法2 计状态。仅仅是展示把能放到global的全放到global什么样子。可以让dfs（）参数很少。
    class Solution_Jiuzhang3{
        ArrayList<String> result = new ArrayList<>();
        int l;
        String digits;
        final String phone[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public ArrayList<String> letterCombinations(String digits) {

            this.l = digits.length();
            this.digits = digits;

            if (digits.length() == 0) {
                return result;
            }
            dfs(0, "");
            return result;
        }

        void dfs(int x, String str) {
            if (x == l) {
                result.add(str);
                return;
            }
            int d = digits.charAt(x) - '0';
            for (char c : phone[d].toCharArray()) {
                dfs(x + 1, str + c);
            }
        }
    }



//--------------------------------------------------------------------------------
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
//--------------------------------------------------------------------------------
 */

/*
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//--------------------------------------------------------------------------------
 */

/*
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.

Seen this question in a real interview before?   Yes  No
Companies
Google Facebook Amazon Uber Dropbox

Related Topics
String Backtracking

Similar Questions
_022_String_Generate_Parentheses_M
Combination Sum
Binary Watch
Java
//--------------------------------------------------------------------------------
 */