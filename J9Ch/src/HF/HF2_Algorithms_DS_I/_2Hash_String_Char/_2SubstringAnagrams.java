package HF.HF2_Algorithms_DS_I._2Hash_String_Char;

import java.util.*;

public class _2SubstringAnagrams {

    public class Solution {
        /**
         * @param s a string
         * @param p a non-empty string
         * @return a list of index
         */
        public List<Integer> findAnagrams(String s, String p) {
            // Write your code here
            List<Integer> ans = new ArrayList <Integer>();
            int[] sum = new int[30];

            int plength = p.length(), slength = s.length();
            for(char c : p.toCharArray()){
                sum[c - 'a'] ++;
            }

            int start = 0, end = 0, matched = 0;
            while(end < slength){
                if(sum[s.charAt(end) - 'a'] >= 1){
                    matched ++;
                }
                sum[s.charAt(end) - 'a'] --;
                end ++;
                if(matched == plength) {
                    ans.add(start);
                }
                if(end - start == plength){
                    if(sum[s.charAt(start) - 'a'] >= 0){
                        matched --;
                    }
                    sum[s.charAt(start) - 'a'] ++;
                    start ++;
                }
            }
            return ans;
        }
    }

    // version: 高频题班
    public class Solution2 {
        /**
         * @param s a string
         * @param p a non-empty string
         * @return a list of index
         */
        public List<Integer> findAnagrams(String s, String p) {
            // Write your code here
            List<Integer> ans = new ArrayList<>();
            if (s.length() < p.length()) {
                return ans;
            }
            char[] sc = s.toCharArray();
            char[] pc = p.toCharArray();

            int[] cntP = new int[256];
            int[] cntS = new int[256];
            int[] det = new int[256];

            for (int i = 0; i < p.length(); i++) {
                cntP[pc[i]]++;
                cntS[sc[i]]++;

                det[pc[i]]--;
                det[sc[i]]++;
            }

            int absSum = 0;
            for (int item : det) {
                absSum += Math.abs(item);
            }
            if (absSum == 0) {
                ans.add(0);
            }

            for (int i = p.length(); i < s.length(); i++) {
                int r = sc[i];
                int l = sc[i - p.length()];
                cntS[r]++;
                cntS[l]--;

                absSum = absSum - Math.abs(det[r]) - Math.abs(det[l]);

                det[r]++;
                det[l]--;

                absSum = absSum + Math.abs(det[r]) + Math.abs(det[l]);

                if (absSum == 0) {
                    ans.add(i - p.length() + 1);
                }
            }
            return ans;
        }
    }

/*
//clean version

public class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        // Write your code here
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) {
            return ans;
        }
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();

        int[] det = new int[256];

        for (int i = 0; i < p.length(); i++) {
            det[pc[i]]--;
            det[sc[i]]++;
        }

        int absSum = 0;
        for (int item : det) {
            absSum += Math.abs(item);
        }
        if (absSum == 0) {
            ans.add(0);
        }

        for (int i = p.length(); i < s.length(); i++) {
            int r = sc[i];
            int l = sc[i - p.length()];
            absSum = absSum - Math.abs(det[r]) - Math.abs(det[l]);

            det[r]++;
            det[l]--;

            absSum = absSum + Math.abs(det[r]) + Math.abs(det[l]);
            if (absSum == 0) {
                ans.add(i - p.length() + 1);
            }
        }
        return ans;
    }
}
*/


}
