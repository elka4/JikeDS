package _08_Bit;

/*
LeetCode – Single Number II (Java)

Problem

Given an array of integers, every element appears three times except for one. Find that single one.

Java Solution

This problem is similar to Single Number.
 */
public class Single_Number_II {
    public int singleNumber(int[] A) {
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < A.length; i++) {
            twos |= ones & A[i];
            ones ^= A[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }

//////////////////////////////////////////////////////

    public int singleNumber2(int[] A) {
        int ones = 0, twos = 0;
        for(int i = 0; i < A.length; i++){
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;
    }


//////////////////////////////////////////////////////

//https://leetcode.com/problems/single-number-ii/discuss/



//////////////////////////////////////////////////////

//https://leetcode.com/problems/single-number-ii/discuss/





//////////////////////////////////////////////////////

}
