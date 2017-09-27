package J_7_2Pointers.all;

/** 625 Partition Array II


 * Created by tianhuizhu on 6/28/17.
 */
public class _625_Partition_Array_II {

    // 参考程序1

    public void partition1(int[] nums, int low, int high) {
        // Write your code here
        if (nums == null || nums.length <= 1) {
            return;
        }

        int pl = 0, pr = nums.length - 1;
        int i = 0;
        while (i <= pr) {
            if (nums[i] < low) {
                swap(nums, pl, i);
                pl++;
                i++;
            } else if (nums[i] > high) {
                swap(nums, pr, i);
                pr--;
            } else {
                i ++;
            }
        }
    }

    public void sortColors(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int pl = 0;
        int pr = a.length - 1;
        int i = 0;
        while (i <= pr) {
            if (a[i] == 0) {
                swap(a, pl, i);
                pl++;
                i++;
            } else if(a[i] == 1) {
                i++;
            } else {
                swap(a, pr, i);
                pr--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }



////////////////////////////////////////////////////////////////////////

    // 参考程序2

    public void partition2(int[] nums, int low, int high) {
        // Write your code here
        int left = 0;
        int right = nums.length - 1;

        // 首先把区间分为 < low 和 >= low 的两个部分
        while(left <= right) {
            while(left <= right && nums[left] < low) {
                left ++;
            }
            while(left <= right && nums[right] >= low) {
                right --;
            }

            if(left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left ++;
                right --;
            }
        }

        // 然后从 >= low 的部分里分出 <= high 和 > high 的两个部分
        right = nums.length - 1;
        while(left <= right) {
            while(left <= right && nums[left] <= high) {
                left ++;
            }
            while(left <= right && nums[right] > high) {
                right --;
            }
            if(left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left ++;
                right --;
            }
        }
    }

/*
Partition an unsorted integer array into three parts:

The front part < low
The middle part >= low & <= high
The tail part > high
Return any of the possible solutions.

 Notice

low <= high in all testcases.

Have you met this question in a real interview? Yes
Example
Given [4,3,4,1,2,3,1,2], and low = 2 and high = 3.

Change to [1,1,2,3,2,3,4,4].

([1,1,2,2,3,3,4,4] is also a correct answer, but [1,2,1,2,3,3,4,4] is not)
 */

}
