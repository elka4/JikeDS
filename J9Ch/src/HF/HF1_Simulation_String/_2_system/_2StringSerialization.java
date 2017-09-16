package HF.HF1_Simulation_String._2_system;

import java.util.*;

/*
思路:
• 简单的想法，用‘;’(或者‘+’等)将字符串连起来
• 如果字符串中本身就有‘;’呢?
• 考虑\n \\ 这一类转译字符的原理
• 用‘:’表示转译，那么连接符就是’:;’ 表示‘:’本身就是‘::’
• abc def -> abc:;def:;
• ab:c def -> ab::c:;def:; • ab:;c def -> ab::;c:;def:;
 */

/*
能力维度:
4. 逻辑思维/算法优化能力
 */

public class _2StringSerialization {
    //in class
    /**
     * @param strs a list of strings
     * @return encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        // Write your code here
        String ans = "";
        for (String s : strs) {
            for (char c : s.toCharArray()) {
                if (c == ':') {         // : itself
                    ans += "::";
                } else {                //ordinary character
                    ans += c;
                }
            }
            ans += ":;";                // ; connector
        }
        return ans;
    }

    /**
     * @param str a string
     * @return dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        // Write your code here
        List<String> ans = new ArrayList<>();
        char[] sc = str.toCharArray();
        String item = "";
        int i = 0;
        while (i < str.length()) {
            if (sc[i] == ';') {             //escape
                if (sc[i + 1] == ';') {     // ; connector
                    ans.add(item);
                    item = "";
                    i += 2;
                } else {                    // : itself
                    item += sc[i + 1];
                    i += 2;
                }
            } else {                        // ordinary character
                item += sc[i];
                i += 1;
            }
        }
        return ans;
    }

////////////////////////////////////////////////////////////////////

    //jiuzhang

    public class Solution1 {
        /**
         * @param strs a list of strings
         * @return encodes a list of strings to a single string.
         */
        public String encode(List<String> strs) {
            // Write your code here
            StringBuilder result = new StringBuilder();
            for(String str : strs){
                result.append(String.valueOf(str.length()) + "$");
                result.append(str);
            }
            return result.toString();
        }

        /**
         * @param str a string
         * @return dcodes a single string to a list of strings
         */
        public List<String> decode(String str) {
            // Write your code here
            List<String> result = new LinkedList<String>();
            int start = 0;
            while(start < str.length()){
                int idx = str.indexOf('$', start);
                int size = Integer.parseInt(str.substring(start, idx));
                result.add(str.substring(idx + 1, idx + size + 1));
                start = idx + size + 1;
            }
            return result;
        }
    }

    // version: 高频题班
    public class Solution2 {
        /**
         * @param strs a list of strings
         * @return encodes a list of strings to a single string.
         */
        public String encode(List<String> strs) {
            // Write your code here
            String ans = "";
            for (String s : strs) {
                for (char c : s.toCharArray()) {
                    if (c == ':') {            // : itself
                        ans += "::";
                    } else {                   //ordinary character
                        ans += c;
                    }
                }
                ans += ":;";                   // ; connector
            }
            return ans;
        }

        /**
         * @param str a string
         * @return dcodes a single string to a list of strings
         */
        public List<String> decode(String str) {
            // Write your code here
            List<String> ans = new ArrayList<>();
            char[] sc = str.toCharArray();
            String item = "";
            int i = 0;
            while (i < str.length()) {
                if (sc[i] == ':') {            //escape
                    if (sc[i + 1] == ';') {    // ; connector
                        ans.add(item);
                        item = "";
                        i += 2;
                    } else {                   // : itself
                        item += sc[i + 1];
                        i += 2;
                    }
                } else {                       //ordinary character
                    item += sc[i];
                    i += 1;
                }
            }
            return ans;
        }
    }


////////////////////////////////////////////////////////////////////


}
/*
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Please implement encode and decode

Have you met this question in a real interview? Yes
Example
Given strs = ["lint","code","love","you"]
string encoded_string = encode(strs)

return `["lint","code","love","you"]｀ when you call decode(encoded_string)
 */