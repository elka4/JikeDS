package _08_Bit;
import java.util.*;
import org.junit.Test;

//  477. Total Hamming Distance

//  https://leetcode.com/problems/total-hamming-distance/description/
//
public class _477_Bit_Total_Hamming_Distance_M {

//Java Easy solution with explanation
/*Hi there! Here the strightforward idea is to calculate haming distances between each pair and then sum them up. But that algorithm runs for O(31 * n^2). We have to make it faster.
    Actually we don't need to consider each pair. Let's simplify the problem, such that each number consists of single bit. How could we solve then? This case we know, that haming distance between two numbers can be either 1 or 0. Well, 1 if bits are different and 0 otherwise. It means, the answer is the number of pairs with different bits. The latter is equals to the product of # of zero bits and # of set bits.
    Our problem is the same. You can prove by yourselves that sum of haming distances is equals to the sum of pairs with different bits for each bit position from 1 to 31. Thus we come up with solution that works for O(1) space and O(31*n) time.*/

    public class Solution1 {
        public int totalHammingDistance(int[] nums) {
            int ans= 0 ;
            int bit = 1;
            for(int i = 0;i<31;i++){
                int zero = 0, one = 0;
                for(int j =  0;j<nums.length;j++){
                    if((nums[j]&bit) == 0){
                        zero++;
                    } else {
                        one++;
                    }
                }
                ans+=zero*one;
                bit<<=1;
            }
            return ans;
        }
    }

//-------------------------------------------------------------------------///////////
//8-lines DP solution by one pass with explanation
/*    It's really a straight forward method. To sum the distances of every pair, you can make it with element one by one. For example, you check first element and second one, then check the coming third one with first and second element as: (1,2), (1,3), (2,3)...So the only thing you need to do is check how many more distances come with a new element nums[k] with passed elements nums[0],nums[1],...,nums[k-1].

    I used a matrix(O(1) space) to store total amounts of 0's and 1's at every bit of already passed numbers.
            (x>>i)&1 gets the ith bit of current number.
            ((x>>i)&1)^1 gets the opposite of ith bit of current number, which makes the total Hamming distances between current number and passed numbers.*/

    public int totalHammingDistance2(int[] nums) {
        int[][] dp = new int[31][2];
        int res = 0;
        for (int x : nums)
            for (int i=0; i<31; ++i) {
                ++dp[i][(x>>i)&1];
                res += dp[i][((x>>i)&1)^1];
            }
        return res;
    }
//-------------------------------------------------------------------------///////////
//Java Solution with Explanation


/*    The first solution came to my mind is brute forcely iterate through each pair, then sum all Integer.bitCount(x ^ y) like what I mentioned here https://discuss.leetcode.com/topic/72093/java-1-line-solution-d But as you can imagine, it TLE...

    Let's think about this problem another way. We can separate the calculation to do one bit at a time. For example, look at the rightmost bit of all the numbers in nums. Suppose that i numbers have a rightmost 0-bit, and j numbers have a 1-bit. Then out of the pairs, i * j of them will have 1 in the rightmost bit of the XOR. This is because there are i * j ways to choose one number that has a 0-bit and one that has a 1-bit. These bits will therefore contribute i * j towards the total of all the XORs.

    Apply above algorithm to each bit (31 bits in total as specified in the problem), sum them together then we get the answer.

    Reference: http://stackoverflow.com/questions/21388448/sum-of-xor-values-of-all-pairs*/

    public class Solution3 {
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
//-------------------------------------------------------------------------///////////
/*Java O(n) time O(1) Space

96
    compton_scatter
    Reputation:  1,301
    For each bit position 1-32 in a 32-bit integer, we count the number of integers in the array which have that bit set. Then, if there are n integers in the array and k of them have a particular bit set and (n-k) do not, then that bit contributes k*(n-k) hamming distance to the total.*/

    public int totalHammingDistance4(int[] nums) {
        int total = 0, n = nums.length;
        for (int j=0;j<32;j++) {
            int bitCount = 0;
            for (int i=0;i<n;i++)
                bitCount += (nums[i] >> j) & 1;
            total += bitCount*(n - bitCount);
        }
        return total;
    }

//    Similar Idea but different Implementation.

    public int totalHammingDistance5(int[] nums) {
        int total = 0;
        int [][] store = new int [32][2];
        for (int num : nums) {
            for (int idx = 31; idx >= 0; idx --) {
                int bit = (num >> idx & 1);
                store [idx][bit] ++;
                total += store [idx][bit ^ 1];
            }
        }
        return total;
    }
//-------------------------------------------------------------------------///////////
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

/*

 */