package _String._Reverse;


//  189. Rotate Array
//  https://leetcode.com/problems/rotate-array/description/
//  http://www.lintcode.com/problem/rotate-array/
//
//  6:
//Rotate List
//Reverse Words in a String II
//
//
public class _189_Rotate_Array {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/rotate-array/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution1 {
        public void rotate(int[] nums, int k) {
            int temp, previous;
            for (int i = 0; i < k; i++) {
                previous = nums[nums.length - 1];
                for (int j = 0; j < nums.length; j++) {
                    temp = nums[j];
                    nums[j] = previous;
                    previous = temp;
                }
            }
        }
    }



//------------------------------------------------------------------------------
    //2
    //Approach #2 Using Extra Array [Accepted]
    public class Solution2 {
        public void rotate(int[] nums, int k) {
            int[] a = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                a[(i + k) % nums.length] = nums[i];
            }
            for (int i = 0; i < nums.length; i++) {
                nums[i] = a[i];
            }
        }
    }



//------------------------------------------------------------------------------
    //3
    //Approach #3 Using Cyclic Replacements [Accepted]
    public class Solution3 {
        public void rotate(int[] nums, int k) {
            k = k % nums.length;
            int count = 0;
            for (int start = 0; count < nums.length; start++) {
                int current = start;
                int prev = nums[start];
                do {
                    int next = (current + k) % nums.length;
                    int temp = nums[next];
                    nums[next] = prev;
                    prev = temp;
                    current = next;
                    count++;
                } while (start != current);
            }
        }
    }



//------------------------------------------------------------------------------
    //4
    //Approach #4 Using Reverse [Accepted]
    public class Solution4 {
        public void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }
        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }



//------------------------------------------------------------------------------
    //5
    /*Easy to read Java solution
    I really don't like those something little line solutions as they are incredibly hard to read. Below is my solution.*/
    class Solution5{
        public void rotate(int[] nums, int k) {
            if(nums == null || nums.length < 2){
                return;
            }
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }
//------------------------------------------------------------------------------
    //6
    //9Ch
    public class Jiuzhang {
        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++; end--;
            }
        }
        public void rotate(int[] nums, int k) {
            if (nums.length == 0) {
                return;
            }

            k = k % nums.length;
            reverse(nums, 0, nums.length - k - 1);
            reverse(nums, nums.length - k, nums.length - 1);
            reverse(nums, 0, nums.length - 1);
        }
    }



//------------------------------------------------------------------------------
}
/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Related problem: Reverse Words in a String II

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

Seen this question in a real interview before?   Yes  No
Companies
Microsoft Amazon Bloomberg

Related Topics
Array

Similar Questions
Rotate List
Reverse Words in a String II
Java
 */