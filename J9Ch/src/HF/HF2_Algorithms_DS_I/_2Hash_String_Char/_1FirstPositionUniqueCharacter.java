package HF.HF2_Algorithms_DS_I._2Hash_String_Char;

public class _1FirstPositionUniqueCharacter {
    public class Solution {
        /**
         * @param s a string
         * @return it's index
         */
        public int firstUniqChar(String s) {
            // Write your code here
            int[] alp = new int[256];
            char[] arr =s.toCharArray();

            for(char c : arr ){
                alp[c]++;
            }

            for(int i = 0; i < arr.length; i++){
                if (alp[arr[i]]==1) return i;
            }

            return -1;
        }
    }

    // version: 高频题班
    public class Solution2 {
        /**
         * @param s a string
         * @return it's index
         */
        public int firstUniqChar(String s) {
            // Write your code here
            int[] cnt = new int[256];

            for (char c : s.toCharArray()) {
                cnt[c]++;
            }

            for (int i = 0; i < s.length(); i++) {
                if (cnt[s.charAt(i)] == 1) {
                    return i;
                }
            }
            return -1;
        }
    }


}

/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Have you met this question in a real interview? Yes
Example
Given s = "lintcode", return 0.

Given s = "lovelintcode", return 2.
 */