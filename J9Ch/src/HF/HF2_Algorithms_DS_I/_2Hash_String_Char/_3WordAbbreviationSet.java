package HF.HF2_Algorithms_DS_I._2Hash_String_Char;

import java.util.*;

public class _3WordAbbreviationSet {

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

    /**
     * Your ValidWordAbbr object will be instantiated and called as such:
     * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
     * boolean param = obj.isUnique(word);
     */



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
}
