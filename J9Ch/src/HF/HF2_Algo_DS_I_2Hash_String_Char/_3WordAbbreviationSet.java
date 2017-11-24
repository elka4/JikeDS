package HF.HF2_Algo_DS_I_2Hash_String_Char;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
• • • •
• • • • •
a) it --> it (no abbreviation) b) d|o|g --> d1g
c) I | nternationalizatio | n --> i18n
d) l | ocalizatio | n --> l10n
Given dictionary = [ "deer", "door", "cake", "card" ] isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
 */

/*
• Company Tags: Google
考点:
• 理解题目的规则 • Hash的应用
为什么考:
• 包装了一下的hash，不简单的裸考
 */

/*
能力维度:
1. 理解问题
2. 代码基础功力
3. 基础数据结构/算法
5. 细节处理(corner case) 7. debug能力
 */

/*
思路:
• 不考虑o(n)的时间复杂度要求，从小到大排序，然后从左到右扫一遍。 • 这样的方法有多少同学会想到?
• 有什么可以改进的?
 */

/*
• Company Tags:Google Facebook 考点:
• 是否可以跳出排序后扫描的思维定式，以每个元素作为突破点
 */

/*
能力维度:
3. 基础数据结构/算法
4. 逻辑思维/算法优化能力
6. 算法分析(时间/空间复杂度)
 */

// Word Abbreviation Set
public class _3WordAbbreviationSet {
    // 9Ch
    public class ValidWordAbbr {

        private Map<String,Set<String>> d;

        // @param dictionary a list of word
        public ValidWordAbbr(String[] dictionary) {
            // Write your code here
            d = new HashMap<String, Set<String>>();
            for (int i = 0;i < dictionary.length; i++) {
                String w = dictionary[i];
                String abbr = getAbbr(w);

                if (d.containsKey(abbr)) {
                    Set<String> s = d.get(abbr);
                    if (!s.contains(w)) s.add(w);
                } else {
                    Set<String> s = new HashSet<String>();
                    s.add(w);
                    d.put(abbr,s);
                }
            }
        }

        /**
         * @param word a string
         * @return true if its abbreviation is unique or false
         */
        public boolean isUnique(String word) {
            // Write your code here
            String a = getAbbr(word);
            if (d.containsKey(a)) {
                Set<String> s = d.get(a);
                if (s.size() == 1 && s.contains(word)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }

        private String getAbbr(String w){
            int len = w.length() - 2;
            if (len <= 0) {
                return w;
            }
            return "" + w.charAt(0) + len + w.charAt(w.length() - 1);
        }
    }
    private String getAbbr(String w){
        int len = w.length() - 2;
        if (len <= 0) {
            return w;
        }
        return "" + w.charAt(0) + len + w.charAt(w.length() - 1);
    }

    @Test
    public void test1(){
        String[] dictionary = {"deer", "door", "cake", "card" };
        ValidWordAbbr vwa = new ValidWordAbbr(dictionary);

        System.out.println(vwa.isUnique("dear"));
        System.out.println(vwa.isUnique("cart"));
        System.out.println(vwa.isUnique("cane"));
        System.out.println(vwa.isUnique("make"));
    }

    @Test
    public void test11(){
        System.out.println(getAbbr("deer"));
        System.out.println(getAbbr("door"));
        System.out.println(getAbbr("cake"));
        System.out.println(getAbbr("card" ));
        System.out.println(getAbbr("internationalization" ));
//        System.out.println(getAbbr("card" ));
    }

    /**
     * Your ValidWordAbbr object will be instantiated and called as such:
     * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
     * boolean param = obj.isUnique(word);
     */

//------------------------------------------------------------------------------///

    // version: 高频题班
    public class ValidWordAbbr2 {
        Map<String, Integer> dict = new HashMap<>();
        Map<String, Integer> abbr = new HashMap<>();

        // @param dictionary a list of word
        public ValidWordAbbr2(String[] dictionary) {
            // Write your code here
            for (String d : dictionary) {
                if (!dict.containsKey(d)) {
                    dict.put(d, 1);
                } else {
                    dict.put(d, dict.get(d) + 1);
                }

                String a = getAbbr(d);
                if (!abbr.containsKey(a)) {
                    abbr.put(a, 1);
                } else {
                    abbr.put(a, abbr.get(a) + 1);
                }
            }
        }
        /**
         * @param word a string
         * @return true if its abbreviation is unique or false
         */
        public boolean isUnique(String word) {
            // Write your code here
            String a = getAbbr(word);
            return dict.get(word) == abbr.get(a);
        }

        String getAbbr(String str) {
            if (str.length() <= 2) {
                return str;
            }
            return "" + str.charAt(0) + (str.length() - 2) + str.charAt(str.length() - 1);
        }
    }

    @Test
    public void test2(){
        String[] dictionary = {"deer", "door", "cake", "card" };
        ValidWordAbbr2 vwa = new ValidWordAbbr2(dictionary);

        System.out.println(vwa.isUnique("dear"));
        System.out.println(vwa.isUnique("cart"));
        System.out.println(vwa.isUnique("cane"));
        System.out.println(vwa.isUnique("make"));
    }
//------------------------------------------------------------------------------///


}
/*
An abbreviation of a word follows the form . Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Have you met this question in a real interview? Yes
Example
Given dictionary = [ "deer", "door", "cake", "card" ]
isUnique("dear") // return false
isUnique("cart") // return true
isUnique("cane") // return false
isUnique("make") // return true
 */
