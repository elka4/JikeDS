package _09_Math;

//  357. Count Numbers with Unique Digits
//  https://leetcode.com/problems/count-numbers-with-unique-digits/description/
//  DP, Backtracking
//
public class _357_Count_Numbers_with_Unique_Digits {
//-----------------------------------------------------------------------
    //1
    public class Solution1 {
        public int countNumbersWithUniqueDigits(int n) {
            int[] arr = new int[n+1];
            arr[0]=1; // x can be 0

            for(int i=1; i<=n; i++){
                arr[i]=9;
                for(int j=9; j>=9-i+2; j--){
                    arr[i] *= j;
                }
            }

            int result =0;
            for(int i: arr)
                result += i;

            return result;
        }
    }

//-----------------------------------------------------------------------
    //2
    // 9Ch
    //  solution 1
    //DP Solution
    public class Solution2 {
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return 10;
            }
            if (n > 10) {
                n = 10;
            }
            //f[i]表示不含0的i位数中满足条件的数的个数
            //g[i]表示含有0的i位数中满足条件的数的个数
            int[] f = new int[11];
            int[] g = new int[11];
            int ans = 10;
            g[0] = 1;
            g[1] = 9;
            for (int i = 2; i <= n; i++) {
                f[i] = f[i-1] * (11 - i) + g[i-2] * (11 - i);
                g[i] = g[i-1] * (10 - i);
                ans += f[i] + g[i];
            }
            return ans;
        }
    }
//-----------------------------------------------------------------------
    //3
    // solution 2
    //Math Method
    public class Solution3 {
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) {
                return 1;
            }
            if (n > 10) {
                n = 10;
            }
            int ans = 1;
            int multiple = 9;
            for (int i = n - 1; i >= 0; i--) {
                if (i == 0) {
                    ans += multiple;
                } else {
                    ans += (n - i + 1) * multiple;
                }
                multiple = multiple * (10 - n + i - 1);
            }
            return ans;
        }
    }
//-----------------------------------------------------------------------
}
/*
LeetCode – Count Numbers with Unique Digits (Java)

Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])

Credits:
Special thanks to @memoryless for adding this problem and creating all test cases.

 */