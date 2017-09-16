package HF.HF1_SimulationAlgorithms_StringManipulationSkills._2_system;

import java.util.*;

public class _2StringSerialization {
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