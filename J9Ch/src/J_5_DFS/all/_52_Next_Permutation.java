package J_5_DFS.all;
import org.junit.Test;

import java.util.ArrayList;

/**52 Next Permutation
 * Created by tianhuizhu on 6/28/17.
 */
public class _52_Next_Permutation {
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's next permuation
     */
    public void swapItem(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void swapList(int[] nums, int i, int j) {
        while (i < j) {
            swapItem(nums, i, j);
            i ++; j --;
        }
    }
    public int[] nextPermutation(int[] nums) {
        int len = nums.length;
        if ( len <= 1)
            return nums;

        int i = len - 1;

        while (i > 0 && nums[i] <= nums[i - 1])
            i --;

        System.out.println("i : " + i);

        swapList(nums, i, len - 1);





        if (i != 0) {
            int j = i;
            while (nums[j] <= nums[i - 1]) j++;
            swapItem(nums, j, i-1);
        }

        return nums;
    }
    @Test
    public void test05(){
        int[] nums = {1,3,2,3};
        int[] result = nextPermutation(nums);
        System.out.println();
        for (int i : result
                ) {
            System.out.print(i + " ");
        }
    }

    @Test
    public void test06() {
        int[] nums = {2, 3, 1};
        int[] result = nextPermutation(nums);
        System.out.println();
        for (int i : result
                ) {
            System.out.print(i + " ");
        }
    }
//---------------------------------//////////////////

    public void swapItem(ArrayList<Integer> nums, int i, int j) {
        Integer tmp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, tmp);
    }
    public void swapList(ArrayList<Integer> nums, int i, int j) {
        while ( i < j) {
            swapItem(nums, i, j);
            i ++; j --;
        }
    }
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
        int len = nums.size();
        if ( len <= 1){
            return nums;
        }

        int i = len - 1;

        while ( i > 0 && nums.get(i) >= nums.get(i-1) ){
            i --;
        }

        swapList(nums, i, len - 1);

        if ( i != 0) {
            int j = i;
            while ( nums.get(j) >= nums.get(i-1) ) j++;
            swapItem(nums, j, i-1);
        }

        return nums;
    }

/*
For [1,3,2,3], the next permutation is [1,3,3,2]

For [4,3,2,1], the next permutation is [1,2,3,4]
 */

    //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

    @Test
    public void test01(){
        int[] nums = {1,3,2,3};
        int[] result = nextPermutation(nums);
        for (int i : result
             ) {
            System.out.print(i + " ");
        }
    }
    @Test
    public void test02() {
        int[] nums = {2, 3, 1};
        int[] result = nextPermutation(nums);
        for (int i : result
                ) {
            System.out.print(i + " ");
        }
    //1, 2, 3   1 3 2
    //2, 3, 1   3 1 2
    }

//------------------------------------------------------------------------------///////////

    // version 2
    public void reverse(int[] num, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }

    public int[] nextPermutation2(int[] num) {
        // find the last increase index
        int index = -1;
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i] < num[i + 1]) {
                index = i;
                break;
            }
        }
        System.out.println("index :" + index);
        if (index == -1) {
            reverse(num, 0, num.length - 1);
            return num;
        }


        // find the first bigger one
        int biggerIndex = index + 1;
        for (int i = num.length - 1; i > index; i--) {
            if (num[i] > num[index]) {
                biggerIndex = i;
                break;
            }
        }
        System.out.println("biggerIndex :" + biggerIndex);

        // swap them to make the permutation bigger
        int temp = num[index];
        num[index] = num[biggerIndex];
        num[biggerIndex] = temp;

        for (int i : num
             ) {
            System.out.print(" " + i);
        }

        // reverse the last part
        reverse(num, index + 1, num.length - 1);
        return num;
    }

    @Test
    public void test03(){
        int[] nums = {1,3,2,3};
        int[] result = nextPermutation2(nums);
        System.out.println();
        for (int i : result
                ) {
            System.out.print(i + " ");
        }
    }

    @Test
    public void test04() {
        int[] nums = {2, 3, 1};
        int[] result = nextPermutation2(nums);
        System.out.println();
        for (int i : result
                ) {
            System.out.print(i + " ");
        }
    }
}
