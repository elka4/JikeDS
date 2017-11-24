package HF.HF1_Sim_String_3_RomeNum;

import java.util.HashMap;
import java.util.Map;


/*
Example: CDXXI=421
 • 1~9: {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
• 10~90: {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
• 100~900: {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
• 1000~3000: {"M", "MM", "MMM"}.
 */

/*
• 大家在做这题时遇到了什么问题? – 怎样区分IV 和VI ?
思路:
• 从左往右加起来，比如XVII=10+5+1+1=17
• 那么像IV=4 IX=9 XL=40 XC=90 这样的怎么处理呢?
• 没有4 9 40 90 这种的，字母代表的数字从左往右是从大到小的
• 发现左边的如果小于右边的，就把左边的减去，比如CDXXI
 */

/*
• Company Tags: Facebook
能力维度:
1. 理解问题
2. 代码基础功力
 */

//Roman to Integer
public class _1RomantoInteger {

//------------------------------------------------------------------------------//////////
    // 9Ch
    public int romanToInt(String s) {
        if (s == null || s.length()==0) {
            return 0;
        }
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);

        int length = s.length();
        int result = m.get(s.charAt(length - 1));
        for (int i = length - 2; i >= 0; i--) {
            if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i))) {
                result += m.get(s.charAt(i));
            } else {
                result -= m.get(s.charAt(i));
            }
        }
        return result;
    }

//------------------------------------------------------------------------------//////////

    // version: 高频题班
    /**
     * @param s Roman representation
     * @return an integer
     */
    public int romanToInt2(String s) {
        // Write your code here
        int ans;
        char[] sc = s.toCharArray();
        ans = toInt(sc[0]);                        //0 special
        for (int i = 1; i < s.length(); i++) {
            ans += toInt(sc[i]);
            if (toInt(sc[i - 1]) < toInt(sc[i])) {
                ans -= toInt(sc[i - 1]) * 2;
            }
        }
        return ans;
    }

    int toInt(char s) {
        switch(s) {
            case 'I':return 1;
            case 'V':return 5;
            case 'X':return 10;
            case 'L':return 50;
            case 'C':return 100;
            case 'D':return 500;
            case 'M':return 1000;
        }
        return 0;
    }

//------------------------------------------------------------------------------//////////
}
/*
Given a roman numeral, convert it to an integer.

The answer is guaranteed to be within the range from 1 to 3999.

Example
IV -> 4

XII -> 12

XXI -> 21

XCIX -> 99
 */