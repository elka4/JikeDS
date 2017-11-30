package _TwoPointer.Sort;
import org.junit.Test;

//  75. Sort Colors
//  https://leetcode.com/problems/sort-colors/description/
//  8:
//  Array, Two Pointers, Sort
//  _148_List_Sort_List_M
//  _280_Wiggle_Sort
// _324_Wiggle_Sort_II
public class _075_Sort_Colors_M {
//-------------------------------------------------------------------------
    //1
//    My java version is more readable, the basic idea is to use two pointer low and high and an iterator i
//
//    every elem left low pointer is 0, elem right high pointer is 2

    public void sortColors1(int[] nums) {
        if(nums==null || nums.length<2) return;
        int low = 0;
        int high = nums.length-1;
        for(int i = low; i<=high;) {
            if(nums[i]==0) {
                // swap nums[i] and nums[low] and i,low both ++
                int temp = nums[i];
                nums[i] = nums[low];
                nums[low]=temp;
                i++;low++;
            }else if(nums[i]==2) {
                //swap nums[i] and nums[high] and high--;
                int temp = nums[i];
                nums[i] = nums[high];
                nums[high]=temp;
                high--;
            }else {
                i++;
            }
        }
    }

    @Test
    public void test01(){
        int[] nums = {1, 0, 1, 2};
        sortColors1(nums);
        for (int i:nums) {
            System.out.print(i + ", ");
        }
    }//0, 1, 1, 2,


//-------------------------------------------------------------------------
    //2
    //  Four different solutions
    // n = nums.length
    // two pass O(m+n) space
    void sortColors2(int nums[], int n) {
/*        if (nums == null || nums.length <= 1) {
            return;
        }*/
        int num0 = 0, num1 = 0, num2 = 0;

        for(int i = 0; i < n; i++) {
            if (nums[i] == 0) ++num0;
            else if (nums[i] == 1) ++num1;
            else if (nums[i] == 2) ++num2;
        }

        for(int i = 0; i < num0; ++i) nums[i] = 0;
        for(int i = 0; i < num1; ++i) nums[num0+i] = 1;
        for(int i = 0; i < num2; ++i) nums[num0+num1+i] = 2;
    }

    @Test
    public void test02(){
        int[] nums = {1, 0};
        sortColors2(nums, 2);
        for (int i:nums) {
            System.out.print(i + ", ");
        }
    }//0, 1, 1, 2,

//-------------------------------------------------------------------------
    //3
    // one pass in place solution
    void sortColors3(int nums[], int n) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0)
            {
                nums[++n2] = 2; nums[++n1] = 1; nums[++n0] = 0;
            }
            else if (nums[i] == 1)
            {
                nums[++n2] = 2; nums[++n1] = 1;
            }
            else if (nums[i] == 2)
            {
                nums[++n2] = 2;
            }
        }
    }
    @Test
    public void test03(){
        int[] nums = {1, 0};
        sortColors3(nums, nums.length);
        for (int i:nums) {
            System.out.print(i + ", ");
        }
    }//0, 1, 1, 2,
//-------------------------------------------------------------------------
    //4
    // one pass in place solution
    void sortColors4(int nums[], int n) {
        int j = 0, k = n - 1;
        for (int i = 0; i <= k; ++i){
            if (nums[i] == 0 && i != j){
                swap(nums, i, j);
                i--;j++;
            }
            else if (nums[i] == 2 && i != k){
                swap(nums, i, k);
                i--;k--;
            }
        }
    }
    @Test
    public void test04(){
        int[] nums = {1, 0, 1, 2};
        sortColors4(nums, nums.length);
        for (int i:nums) {
            System.out.print(i + ", ");
        }
    }//0, 1, 1, 2,
//-------------------------------------------------------------------------
    //5
    // one pass in place solution
    void sortColors5(int nums[], int n) {
        int j = 0, k = n-1;
        for (int i=0; i <= k; i++) {
            if (nums[i] == 0){
                swap(nums, i, j);
                j++;
            }else if (nums[i] == 2){
//                swap2(nums[i--], nums[k--]);
                swap(nums, i, k);
                i--;k--;
            }
        }
    }
    @Test
    public void test05(){
        int[] nums = {1, 0};
        sortColors5(nums, 3);
        for (int i:nums) {
            System.out.print(i + ", ");
        }
    }//0, 1, 1, 2,
    private void swap2(int i, int j){
        int temp = i;
        i = j;
        j = temp;
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    @Test
    public void test055(){
        int[] nums = {1, 0, 1, 2};
        sortColors5(nums, 3);
        for (int i:nums) {
            System.out.print(i + ", ");
        }
    }//0, 1, 1, 2,
//-------------------------------------------------------------------------
    //6
//Nice summary! For method 1 bucket sort, instead of hard coding 1/2/3 and listing for,for,for,if,else,if,else I suggest write something as follows:

    private static final int MnumsX = 3;

    public void sortColors6(int[] nums) {
        int[] buckets = new int[MnumsX];
        for(int num : nums) buckets[num]++;
        for(int p = 0, val = 0; val < MnumsX; val++) {
            for(int count = 0; count < buckets[val]; count++) {
                nums[p++] = val;
            }
        }
    }
//-------------------------------------------------------------------------
    //7
//    numsnother O(n) solution like your last one, bounded by one pass:)

    public void sortColors7(int[] nums) {
        int runner = 0, second = nums.length - 1, zero = 0;
        while (runner <= second) {
            if (nums[runner] == 2) swap(nums, second--, runner);
            else if (nums[runner] == 0) swap(nums, zero++, runner++);
            else runner++;
        }
    }
//-------------------------------------------------------------------------
    //8
    // 9Ch
    /*
    三指针，左，右，i
     */
    class Jiuzhang {
        public void sortColors(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return;
            }

            int pl = 0;
            int pr = nums.length - 1;
            int i = 0;
            while (i <= pr) {
                if (nums[i] == 0) {
                    swap(nums, pl, i);
                    pl++;
                    i++;
                } else if(nums[i] == 1) {
                    i++;
                } else if(nums[i] == 2) {
                    swap(nums, pr, i);
                    pr--;
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

//-------------------------------------------------------------------------
}

/*
给定一个包含红，白，蓝且长度为 n 的数组，将数组元素进行分类使相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序。

我们可以使用整数 0，1 和 2 分别代表红，白，蓝。

 注意事项

不能使用代码库中的排序函数来解决这个问题。
排序需要在原数组中进行。

样例
给你数组 [1, 0, 1, 2], 需要将该数组原地排序为 [0, 1, 1, 2]。

挑战
一个相当直接的解决方案是使用计数排序扫描2遍的算法。

首先，迭代数组计算 0,1,2 出现的次数，然后依次用 0,1,2 出现的次数去覆盖数组。

你否能想出一个仅使用常数级额外空间复杂度且只扫描遍历一遍数组的算法？
//-------------------------------------------------------------------------
 */

/*
//-------------------------------------------------------------------------
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
nums rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
//-------------------------------------------------------------------------
 */
