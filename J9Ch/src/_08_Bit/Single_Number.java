package _08_Bit;

/*
Leetcode – Single Number (Java)

The problem:

Given an array of integers, every element appears twice except for one. Find that single one.
 */
public class Single_Number {
   /* The key to solve this problem is bit manipulation. XOR will return 1 only on two different bits. So if two numbers are the same, XOR will return 0. Finally only one number left.*/

    public int singleNumber(int[] A) {
        int x = 0;
        for (int a : A) {
            x = x ^ a;
        }
        return x;
    }
}
