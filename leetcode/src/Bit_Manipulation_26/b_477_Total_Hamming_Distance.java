package Bit_Manipulation_26;
import java.util.*;

public class b_477_Total_Hamming_Distance {


    //https://leetcode.com/problems/total-hamming-distance/tabs/solution


    //////////////////////////////////////////////////////////////////

//    Java O(n) time O(1) Space
//    For each bit position 1-32 in a 32-bit integer, we count the number of integers in the array which have that bit set. Then, if there are n integers in the array and k of them have a particular bit set and (n-k) do not, then that bit contributes k*(n-k) hamming distance to the total.

    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j=0;j<32;j++) {
            int bitCount = 0;
            for (int i=0;i<n;i++)
                bitCount += (nums[i] >> j) & 1;
            total += bitCount*(n - bitCount);
        }
        return total;
    }

    /////////////////////////////////////////////////////////////////////
//
//    Java Solution with Explanation
//    The first solution came to my mind is brute forcely iterate through each pair, then sum all Integer.bitCount(x ^ y) like what I mentioned here https://discuss.leetcode.com/topic/72093/java-1-line-solution-d But as you can imagine, it TLE...
//
//    Let's think about this problem another way. We can separate the calculation to do one bit at a time. For example, look at the rightmost bit of all the numbers in nums. Suppose that i numbers have a rightmost 0-bit, and j numbers have a 1-bit. Then out of the pairs, i * j of them will have 1 in the rightmost bit of the XOR. This is because there are i * j ways to choose one number that has a 0-bit and one that has a 1-bit. These bits will therefore contribute i * j towards the total of all the XORs.
//
//    Apply above algorithm to each bit (31 bits in total as specified in the problem), sum them together then we get the answer.
//
//    Reference: http://stackoverflow.com/questions/21388448/sum-of-xor-values-of-all-pairs

    public class Solution {
        public int totalHammingDistance(int[] nums) {
            int n = 31;
            int len = nums.length;
            int[] countOfOnes = new int[n];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < n; j++) {
                    countOfOnes[j] += (nums[i] >> j) & 1;
                }
            }
            int sum = 0;
            for (int count: countOfOnes) {
                sum += count * (len - count);
            }
            return sum;
        }
    }

}
/*
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.
 */