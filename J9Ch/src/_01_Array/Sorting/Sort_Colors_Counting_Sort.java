package _01_Array.Sorting;


/*
LeetCode – Sort Colors (Java)

Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 */


public class Sort_Colors_Counting_Sort {


/*    Java Solution 1 - Counting Sort

    Check out this animation to understand how counting sort works.*/

    public void sortColors(int[] nums) {
        if(nums==null||nums.length<2){
            return;
        }

        int[] countArray = new int[3];
        for(int i=0; i<nums.length; i++){
            countArray[nums[i]]++;
        }

        for(int i=1; i<=2; i++){
            countArray[i]=countArray[i-1]+countArray[i];
        }

        int[] sorted = new int[nums.length];
        for(int i=0;i<nums.length; i++){
            int index = countArray[nums[i]]-1;
            countArray[nums[i]] = countArray[nums[i]]-1;
            sorted[index]=nums[i];
        }

        System.arraycopy(sorted, 0, nums, 0, nums.length);


    }



   /* Java Solution 2 - Improved Counting Sort

    In solution 1, two arrays are created. One is for counting, and the
    other is for storing the sorted array (space is O(n)). We can improve
    the solution so that it only uses constant space. Since we already get
    the count of each element, we can directly project them to the original
    array, instead of creating a new one.*/

    public void sortColors2(int[] nums) {
        if(nums==null||nums.length<2){
            return;
        }

        int[] countArray = new int[3];
        for(int i=0; i<nums.length; i++){
            countArray[nums[i]]++;
        }

        int j = 0;
        int k = 0;
        while(j<=2){
            if(countArray[j]!=0){
                nums[k++]=j;
                countArray[j] = countArray[j]-1;
            }else{
                j++;
            }
        }
    }




//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






}
