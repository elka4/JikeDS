package _05_DFS._Recursion;

//625. Minimum Factorization
//https://leetcode.com/problems/minimum-factorization/description/
public class _625_Minimum_Factorization_M {

    //https://leetcode.com/problems/minimum-factorization/solution/
//Approach #2 Better Brute Force [Time Limit Exceeded]

    public class Solution2 {
        long ans;
        public int smallestFactorization(int a) {
            if(a < 2)
                return a;
            int[] dig=new int[]{9, 8, 7, 6, 5, 4, 3, 2};
            if (search(dig, 0, a, 1, "") && ans <= Integer.MAX_VALUE)
                return (int)ans;
            return 0;
        }
        public boolean search(int[] dig, int i, int a, long mul, String res) {
            if (mul > a || i == dig.length )
                return false;
            if (mul == a) {
                ans = Long.parseLong(res);
                return true;
            }
            return search(dig, i, a, mul * dig[i], dig[i] + res) || search(dig, i + 1, a, mul, res);
        }
    }
//----------------------------------------------------------------------------
//

//----------------------------------------------------------------------------
}
/*
Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.

If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1
Input:

48
Output:
68
Example 2
Input:

15
Output:
35
 */