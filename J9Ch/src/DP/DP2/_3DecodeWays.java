package DP.DP2;

public class _3DecodeWays {
    public class Solution {
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int[] nums = new int[s.length() + 1];
            nums[0] = 1;
            nums[1] = s.charAt(0) != '0' ? 1 : 0;
            for (int i = 2; i <= s.length(); i++) {
                if (s.charAt(i - 1) != '0') {
                    nums[i] = nums[i - 1];
                }

                int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
                if (twoDigits >= 10 && twoDigits <= 26) {
                    nums[i] += nums[i - 2];
                }
            }
            return nums[s.length()];
        }
    }

    // version: 高频题班
    public class Solution2 {
        /**
         * @param s a string,  encoded message
         * @return an integer, the number of ways decoding
         */
        public int numDecodings(String s) {
            // Write your code here
            int l = s.length();
            if (l == 0) {
                return 0;   // only for this problem, but the ans should be 1
            }
            int[] f = new int[l + 1];
            f[0] = 1;
            char sc[] = s.toCharArray();

            for (int i = 1; i <= l; i++) {
                if (sc[i - 1] != '0') {
                    f[i] += f[i - 1];
                }
                if (i >= 2) {
                    int val2 = (sc[i - 2] - '0') * 10 + sc[i - 1] - '0';
                    if (10 <= val2 && val2 <= 26) {
                        f[i] += f[i - 2];
                    }
                }
            }
            return f[l];
        }
    }
}
