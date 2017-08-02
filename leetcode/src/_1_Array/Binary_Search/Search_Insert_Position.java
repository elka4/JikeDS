package _1_Array.Binary_Search;

public class Search_Insert_Position {
    //This is a binary search problem. The complexity should be O(log(n)).

    public int searchInsert(int[] nums, int target) {
        if(nums==null)
            return -1;
        if(target>nums[nums.length-1]){
            return nums.length;
        }

        int i=0;
        int j=nums.length;

        while(i<j){
            int m=(i+j)/2;
            if(target>nums[m]){
                i=m+1;
            }else{
                j=m;
            }
        }

        return j;
    }
    //Or similarly, we can write the solution like the following:

    public int searchInsert2(int[] nums, int target) {
        int i=0;
        int j=nums.length-1;

        while(i<=j){
            int mid = (i+j)/2;

            if(target > nums[mid]){
                i=mid+1;
            }else if(target < nums[mid]){
                j=mid-1;
            }else{
                return mid;
            }
        }

        return i;
    }

}
