package _1_Array.Finding_a_Number;
import java.util.*;

/*
LeetCode – Find the Duplicate Number (Java)

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
 */

public class Find_the_duplicate_number {

    //Java Solution 1 - Wrong
    //This solution is wrong, but the same idea is used in Solution 3.
    public int findDuplicate(int[] arr) {
        for(int i=0; i<arr.length; i++){
            while(arr[i]!=i+1){
                if(arr[i]==arr[arr[i]-1])
                    return arr[i];

                int t = arr[arr[i]-1];
                arr[arr[i]-1]=arr[i];
                arr[i]=t;
            }
        }

        return -1;
    }


    //Java Solution 2 - Binary Search
    public int findDuplicate2(int[] nums) {
        int l=1,r=nums.length-1;
        while(l<r){
            int m=(l+r)/2;
            int c=0;

            for(int i: nums){
                if(i<=m){
                    c++;
                }
            }

            //if c < m,
            if(c>m){
                r=m;
            }else{
                l=m+1;
            }
        }

        return r;
    }

    //
    //Java Solution 3 - Finding Cycle
    //https://leetcode.com/problems/find-the-duplicate-number/discuss/

    public int findDuplicate3(int[] nums) {
        int slow = 0;
        int fast = 0;

        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        int find = 0;

        while(find != slow){
            slow = nums[slow];
            find = nums[find];
        }
        return find;
    }

    public int findDuplicate4(int[] nums) {
        int n = nums.length;
        int slow = n;
        int fast = n;

        do{
            slow = nums[slow-1];
            fast = nums[nums[fast-1]-1];
        }while(slow != fast);

        slow = n;

        while(slow != fast){
            slow = nums[slow-1];
            fast = nums[fast-1];
        }

        return slow;
    }

////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////





}
