package Classification.Array_95;

/**
 * Created by tianhuizhu on 6/21/17.
 */

/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */



public class a_283_Move_Zeroes {

    public void moveZeroes(int[] nums) {
        int m=-1;

        for(int i=0; i<nums.length; i++){
            if(nums[i]==0){
                if(m==-1 || nums[m]!=0){
                    m=i;
                }
            }else{
                if(m!=-1){
                    int temp = nums[i];
                    nums[i]=nums[m];
                    nums[m]=temp;
                    m++;
                }
            }
        }
    }


//---------------------------------//////////

    //Java Solution 2 - Simple

    public void moveZeroes2(int[] nums) {
        int i=0;
        int j=0;

        while(j<nums.length){
            if(nums[j]==0){
                j++;
            }else{
                nums[i]=nums[j];
                i++;
                j++;
            }
        }

        while(i<nums.length){
            nums[i]=0;
            i++;
        }
    }

//---------------------------------

    //Simple O(N) Java Solution Using Insert Index

    // Shift non-zero values as far forward as possible
// Fill remaining space with zeros

    public void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }


//---------------------------------

    //1ms Java solution

    public void moveZeroes4(int[] nums) {

        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }


//---------------------------------



//---------------------------------




}
