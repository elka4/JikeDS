package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;


//        17. Letter Combinations of a Phone Number
public class _017_BackTracking_Letter_Combinations_of_a_Phone_Number_M {
    //My java solution with FIFO queue
    class Solution{
        public List<String> letterCombinations(String digits) {
            LinkedList<String> ans = new LinkedList<String>();
            String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
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
    }

//////////////////////////////////
public class Solution2 {
    private final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
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
}
//////////////////////////////////
    // jiuzhang
public class jiuzhang1 {
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
}

// version: 高频题班

    //  方法1 计状态
    public class jiuzhang2 {
        /**
         * @param digits A digital string
         * @return all posible letter combinations
         */
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

        public ArrayList<String> letterCombinations(String digits) {
            // Write your code here
            String phone[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

            if (digits.length() == 0) {
                return ans;
            }
            dfs(0, digits.length(), "", digits, phone);
            return ans;
        }
    }


    // 方法2 计状态
    public class jiuzhang3 {
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

        public ArrayList<String> letterCombinations(String digits) {
            // Write your code here
            this.l = digits.length();
            this.digits = digits;

            if (digits.length() == 0) {
                return ans;
            }
            dfs(0, "");
            return ans;
        }
    }

///////////////////////////////////////////////////////////////////
}
/*
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */