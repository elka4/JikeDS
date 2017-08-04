package _1_Array.Ugly_Number;
import java.util.*;

/*
LeetCode – Find K Pairs with Smallest Sums (Java)

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.


Example:

Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Java Solution

This problem is similar to Super Ugly Number. The basic idea is using an array to track the index of the next element in the other array.

The best way to understand this solution is using an example such as nums1={1,3,11} and nums2={2,4,8}.
 */


public class Find_K_Pairs_with_Smallest_Sums {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<int[]>();

        k = Math.min(k, nums1.length*nums2.length);

        if(k==0)
            return result;

        int[] idx = new int[nums1.length];

        while(k>0){
            int min = Integer.MAX_VALUE;
            int t=0;
            for(int i=0; i<nums1.length; i++){
                if(idx[i]<nums2.length && nums1[i]+nums2[idx[i]]<min){
                    t=i;
                    min = nums1[i]+nums2[idx[i]];
                }
            }

            int[] arr = {nums1[t], nums2[idx[t]]};
            result.add(arr);

            idx[t]++;

            k--;
        }

        return result;
    }
/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////
}
