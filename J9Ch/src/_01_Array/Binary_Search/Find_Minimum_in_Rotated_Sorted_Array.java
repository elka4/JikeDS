package _01_Array.Binary_Search;

public class Find_Minimum_in_Rotated_Sorted_Array {
//    Java Solution 1 - Recursion
//
//    Define a helper function, otherwise, we will need to use Arrays.copyOfRange() function, which may be expensive for large arrays.

    public int findMin(int[] num) {
        return findMin(num, 0, num.length - 1);
    }

    public int findMin(int[] num, int left, int right) {
        if (left == right)
            return num[left];
        if ((right - left) == 1)
            return Math.min(num[left], num[right]);

        int middle = left + (right - left) / 2;

        // not rotated
        if (num[left] < num[right]) {
            return num[left];

            // go right side
        } else if (num[middle] > num[left]) {
            return findMin(num, middle, right);

            // go left side
        } else {
            return findMin(num, left, middle);
        }
    }
    //Java Solution 2 - Iteration

    public int findMin2(int[] nums) {
        if(nums==null || nums.length==0)
            return -1;

        if(nums.length==1)
            return nums[0];

        int left=0;
        int right=nums.length-1;

        //not rotated
        if(nums[left]<nums[right])
            return nums[left];

        while(left <= right){
            if(right-left==1){
                return nums[right];
            }

            int m = left + (right-left)/2;

            if(nums[m] > nums[right])
                left = m;
            else
                right = m;
        }

        return nums[left];
    }
    //Or

    /*
    To understand the boundaries, use the following 3 examples:
    [2,1], [2,3,1], [3,1,2]
    */
    public int findMin3(int[] nums) {
        if(nums==null || nums.length==0)
            return -1;

        int i=0;
        int j=nums.length-1;

        while(i<=j){
            if(nums[i]<=nums[j])
                return nums[i];

            int m=(i+j)/2;

            if(nums[m]>=nums[i]){
                i=m+1;
            }else{
                j=m;
            }
        }

        return -1;
    }


}
