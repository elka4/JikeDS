package _TwoPointer.All_TwoPointer;

import java.util.Arrays;


//259. 3Sum Smaller
//  https://leetcode.com/problems/3sum-smaller/description/

public class _259_TwoPointer_3Sum_Smaller_M {
    //Simple and easy-understanding O(n^2) JAVA solution
    public class Solution1 {
        int count;

        public int threeSumSmaller(int[] nums, int target) {
            count = 0;
            Arrays.sort(nums);
            int len = nums.length;

            for(int i=0; i<len-2; i++) {
                int left = i+1, right = len-1;
                while(left < right) {
                    if(nums[i] + nums[left] + nums[right] < target) {
                        count += right-left;
                        left++;
                    } else {
                        right--;
                    }
                }
            }

            return count;
        }
    }

//    great idea. I made the code shorter

    public class Solution2 {
        public int threeSumSmaller(int[] nums, int target) {
            int n = nums.length, cnt = 0;
            Arrays.sort(nums);
            for (int i = 0; i < n - 2 && nums[i] * 3 < target; i++)
                for (int l = i + 1, r = n - 1; l < r;)
                    if (nums[i] + nums[l] + nums[r] < target)
                        cnt += r - l++; //all r in (l, r] will also satisfy the condition
                    else
                        r--;
            return cnt;
        }
    }


    //Accepted and Simple Java O(n^2) solution with detailed explanation
    public class Solution3 {
        public int threeSumSmaller(int[] nums, int target) {
            int result = 0;
            Arrays.sort(nums);
            for(int i = 0; i <= nums.length-3; i++) {
                int lo = i+1;
                int hi = nums.length-1;
                while(lo < hi) {
                    if(nums[i] + nums[lo] + nums[hi] < target) {
                        result += hi - lo;
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
            return result;
        }
    }

    //*Java* straightforward O(n^2) solution with explanations
/*    Similar to 3-sum problem, we sort the array first. Again, similar to 3-sum problem, we use two pointers (lo and hi) to check if the sum satisfies the condition. The only trick here is that if we found out

    nums[i] + nums[lo] + nums[hi] < target
    then for all hi in (lo, hi] satisfy the condition. That's why we have

            count += hi-lo;
            in the code.

            Code in Java:*/

    public class Solution4 {
        public int threeSumSmaller(int[] nums, int target) {
            int L = nums.length;
            Arrays.sort(nums);
            int count = 0;
            for(int i=0; i<L-2; i++) {
                int lo = i+1;
                int hi = L-1;
                while(lo<hi) {
                    if(nums[i] + nums[lo] + nums[hi] < target) {
                        count += hi-lo;
                        lo++;
                    }
                    else
                        hi--;
                }
            }
            return count;
        }
    }

    //JAVA O(n^2) solution beats 95% - easy to understand
    public class Solution5 {

        public int threeSumSmaller(int[] nums, int target) {
            if(nums.length < 3){return 0;}
            int count = 0;
            Arrays.sort(nums);
            for(int i = 0; i < nums.length-2; i++){
                if(nums[i]*3 >= target){break;}
                count += find(nums, target-nums[i], i+1, nums.length-1);
            }
            return count;
        }

        //find number of pair that sum up smaller than target from given part of array
        public int find(int[] nums, int target, int start, int end){
            int count = 0;
            while(start < end){
                if(nums[start] + nums[end] >= target){
                    end--;
                }else{
                    count += end-start;
                    start++;
                }
            }
            return count;
        }
    }

}
/*
Given an array of n integers nums and a target,

find the number of index triplets i, j, k with 0 <= i < j < k < n that
satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?
 */