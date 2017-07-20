package J_6_Linked_List_Array.Array;

/** 41 Maximum Subarray
 * Easy
 Find the contiguous subarray within an array (containing at least one number)
 which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.

 click to show more practice.

 More practice:
 If you have figured out the O(n) solution, try coding another solution using
 the divide and conquer approach, which is more subtle.
 * Created by tianhuizhu on 6/28/17.
 */

@SuppressWarnings({"unused", "all"})


public class _41_Maximum_Subarray {

    // Version 1: Greedy
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }

        int max = Integer.MIN_VALUE, sum = 0;

        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum);

            //将sum修正为非负，也就是如果这个sum为负，之前到subarray就不要了
            sum = Math.max(sum, 0);
        }

        return max;
    }

/////////////////////////////////////////////////////////////////////////////////

    // Version 2: Prefix Sum
    public int maxSubArray2(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }

        int max = Integer.MIN_VALUE, sum = 0, minSum = 0;

        for (int i = 0; i < A.length; i++) {

            sum += A[i]; //sum是到i为止到current的presum

            // sum - minSum 是以current为终点的最大的subarray
            //max是从0到current范围内最大subarray（终点不固定为current）
            max = Math.max(max, sum - minSum);

            minSum = Math.min(minSum, sum);//simSum是到i为止到最小到presum
        }

        return max;
    }

/////////////////////////////////////////////////////////////////////////////////

    public int maxSubArray3(int[] nums) {
        // write your code
        if(nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int[] global = new int[2];
        int[] local = new int[2];

        global[0] = nums[0];
        local[0] = nums[0];

        for(int i = 1; i < n; i ++) {
            local[i % 2] = Math.max(nums[i], local[(i - 1) % 2] + nums[i]);
            global[i % 2] = Math.max(local[i % 2], global[(i - 1) % 2]);
        }
        return global[(n-1) % 2];
    }

    //1. Dynamic Programming Solution
//The changing condition for dynamic programming is "We should ignore the
// sum of the previous n-1 elements if nth element is greater than the sum."


    public int maxSubArray_DP(int[] A) {
        int max = A[0];
        int[] sum = new int[A.length];
        sum[0] = A[0];

        for (int i = 1; i < A.length; i++) {
            sum[i] = Math.max(A[i], sum[i - 1] + A[i]);
            max = Math.max(max, sum[i]);
        }

        return max;
    }



    //1. Simple Solution
    public int maxSubArray4(int[] A) {
        int newsum=A[0];
        int max=A[0];
        for(int i=1;i<A.length;i++){
            newsum=Math.max(newsum+A[i],A[i]);
            max= Math.max(max, newsum);
        }
        return max;
    }


    //you can also do it with O(1) space complexity instead of O(n):


    public int maxSubArray5(int[] A) {
        int newsum = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            newsum = Math.max(newsum + A[i], A[i]);
            max = Math.max(max, newsum);
        }
        return max;
    }

    //Based on the second method(DP style), I write this for printing the sub
    // array in details. I tested the example in the question and it works.

    public int maxSubArray6(int[] A) {
        int max = A[0];
        int[] sum = new int[A.length];
        sum[0] = A[0];

        int begin = 0;
        int end = 0;

        for (int i = 1; i < A.length; i++) {

            int newBegin = begin;
            int newEnd = end;

            if (A[i] > sum[i - 1] + A[i]) {
                sum[i] = A[i];
                newBegin = i;
                newEnd = i;
            } else {
                sum[i] = sum[i - 1] + A[i];
                newEnd = i;
            }

            if (sum[i] > max) {
                max = sum[i];
                begin = newBegin;
                end = newEnd;
            }
        }
        return max;
    }

//Native Solution is wrong, but you can change some row, and it will be ok!

        public static int maxSubArray7(int[] A) {
            int sum = 0;
            int maxSum = Integer.MIN_VALUE;

            for (int i = 0; i < A.length; i++) {
                sum += A[i];

                if (sum < 0)
                    sum = A[i]; // change
                maxSum = Math.max(maxSum, sum); // change
            }

            return maxSum;
        }


//////////////////////////////////////////////////////////////////

    public int maxSubArray8(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];

        for(int i = 1; i < n; i++){
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }


//////////////////////////////////////////////////////////////////


    //To calculate sum(0,i), you have 2 choices: either adding sum(0,i-1) to a[i],
    // or not. If sum(0,i-1) is negative, adding it to a[i] will only make a smaller
    // sum, so we add only if it's non-negative.

    //We can then use O(1) space to keep track of the max sum(0, i) so far.

    public int maxSubArray9(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }
        int max = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) { sum = nums[i]; }
            else {sum += nums[i]; }
            max = Math.max(max, sum);
        }
        return max;
    }


/////////////////////////////////////////////////////////////////////
    //Accepted O(n) solution in java

/*
algorithm that operates on arrays: it starts at the left end (element A[1]) and
 scans through to the right end (element A[n]), keeping track of the maximum sum
 subvector seen so far. The maximum is initially A[0]. Suppose we've solved the
  problem for A[1 .. i - 1]; how can we extend that to A[1 .. i]? The maximum
sum in the first I elements is either the maximum sum in the first i - 1 elements
(which we'll call MaxSoFar), or it is that of a subvector that ends in position i
 (which we'll call MaxEndingHere).

MaxEndingHere is either A[i] plus the previous MaxEndingHere, or just A[i], whichever is larger.
 */
    public static int maxSubArray10(int[] A) {
        int maxSoFar=A[0], maxEndingHere=A[0];
        for (int i=1;i<A.length;++i){
            maxEndingHere= Math.max(maxEndingHere+A[i],A[i]);
            maxSoFar=Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
/////////////////////////////////////////////////////////////////////
//O(n) Java solution
//The catch here is that we have to take care of negative value.
//The solution does 1 iteration with constant space and no DP.
    public int maxSubArray11(int[] A) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (sum < 0)
                sum = A[i];
            else
                sum += A[i];
            if (sum > max)
                max = sum;
        }
        return max;
    }

/////////////////////////////////////////////////////////////////////

    //Solution1. DP Solution - O(n) time, O(n) space

    public int maxSubArray12(int[] A) {
        int dp[] = new int[A.length]; int max = A[0]; dp[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            dp[i] = Math.max(dp[i-1] + A[i] ,A[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    //Solution2. Simplified DP Solution - O(n) time, O(1) space - Special
    // thanks for TWiStErRob's smart comment

    //The basic idea is to check previous sum, reset it to 0 if it's less than 0.

    public int maxSubArray13(int[] A) {
        int res = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = Math.max(sum, 0) + A[i];
            res = Math.max(res, sum);
        }
        return res;
    }
    //Solution3. Pre-Sum Array Solution - O(n) time, O(n) space

    //The basic idea is to use pre-sum array, max = Math.max(max, sum[i] - minSum).
    // (minSum is the minimum sum before A[i])

    public int maxSubArray14(int[] A) {
        if (A == null || A.length == 0) return 0;
        int max = A[0], minSum = Integer.MAX_VALUE;
        int sum[] = new int[A.length];
        sum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sum[i] = sum[i-1] + A[i];
            minSum = Math.min(0, Math.min(minSum, sum[i-1]));
            max = Math.max(max, sum[i] - minSum);
        }
        return max;
    }
/////////////////////////////////////////////////////////////////////
//Removed the array, still DP, still reads the same, but O(1) space:

    public int maxSubArray15(int[] A) {
        int max = A[0], dp_i_minus_1 = A[0];
        for (int i = 1; i < A.length; i++) {
            int dp_i = Math.max(dp_i_minus_1 + A[i] ,A[i]);
            max = Math.max(max, dp_i);
            dp_i_minus_1 = dp_i; // prepare for next iteration
        }
        return max;
    }
/////////////////////////////////////////////////////////////////////
public int maxSubArray16(int[] A) {
    int max = A[0], dp = A[0];
    for (int i = 1; i < A.length; i++) {
        dp = Math.max(dp + A[i] ,A[i]);
        max = Math.max(max, dp);
    }
    return max;
}
/////////////////////////////////////////////////////////////////////
//My Divide and Conquer Solution in Java under instruction of CLRS(O(nlogn))



    public int maxSubArray17(int[] nums) {
    return Subarray(nums, 0 ,nums.length -1 );
}
    public int Subarray(int[] A,int left, int right){
        if(left == right){return A[left];}
        int mid = left + (right - left) / 2;
        int leftSum = Subarray(A,left,mid);// left part
        int rightSum = Subarray(A,mid+1,right);//right part
        int crossSum = crossSubarray(A,left,right);// cross part
        if(leftSum >= rightSum && leftSum >= crossSum){// left part is max
            return leftSum;
        }
        if(rightSum >= leftSum && rightSum >= crossSum){// right part is max
            return rightSum;
        }
        return crossSum; // cross part is max
    }
    public int crossSubarray(int[] A,int left,int right){
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;
        int mid = left + (right - left) / 2;
        for(int i = mid; i >= left ; i--){
            sum = sum + A[i];
            if(leftSum < sum){
                leftSum = sum;
            }
        }
        sum = 0;
        for(int j = mid + 1; j <= right; j++){
            sum = sum + A[j];
            if(rightSum < sum){
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }
/////////////////////////////////////////////////////////////////////
//JAVA O(n)time O(1) space 5 lines of code

    public int maxSubArray18(int[] nums) {
        int currMax=nums[0],max=nums[0];
        for(int i=1;i<nums.length;i++){
            currMax=Math.max(currMax+nums[i],nums[i]);
            max=Math.max(max,currMax);
        }
        return max;
    }

/////////////////////////////////////////////////////////////////////

    //I commented on this code and repost it here. Hope I can help someone
    // who has suffering understand this great divide-and-conquer method.

    public class Solution {
        public int maxSubArray(int[] nums) {
    /*
    Divide-and-conquer method.
    The maximum summation of subarray can only exists under following conditions:
    1. the maximum summation of subarray exists in left half.
    2. the maximum summation of subarray exists in right half.
    3. the maximum summation of subarray exists crossing the midpoints to left and right.
    1 and 2 can be reached by using recursive calls to left half and right half of the subarraies.
    Condition 3 can be found starting from the middle point to the left,
    then starting from the middle point to the right. Then adds up these two parts and return.

    T(n) = 2*T(n/2) + O(n)
    this program runs in O(nlogn) time
    */

            int maxsum = subArray(nums, 0, nums.length-1);
            return maxsum;
        }

        private int subArray(int[] A, int left, int right){
            if (left == right){
                //base case
                return A[left];
            }
            int mid = left + (right-left)/2;
            int leftsum = subArray(A, left, mid); //left part of the subarray sum, condition 1
            int rightsum = subArray(A, mid+1, right); //right part of the subarray sum, condition 2
            int middlesum = midSubArray(A, left, mid, right); //cross part of the subarray sum, condition 3
            // System.out.println(leftsum);
            // System.out.println(rightsum);
            // System.out.println(middlesum);


            //following if condition will return middlesum if lost the "=" under the conditon of
            // leftsum = rightsum, which may be problematic if leftsum = rightsum < middlesum.
            //for example: [-1, -1, -2, -2]
            if (leftsum >= rightsum && leftsum >= middlesum){
                return leftsum;
            }
            if (rightsum >= leftsum && rightsum >= middlesum){
                return rightsum;
            }
            return middlesum;
        }

        private int midSubArray(int[] A, int left, int mid, int right){
            int leftsum = Integer.MIN_VALUE;
            int rightsum = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = mid; i >= left; i--){
                sum += A[i];
                if (leftsum < sum){
                    leftsum = sum;
                }
            }

            sum = 0;
            for (int j = mid + 1; j <= right; j++){
                sum += A[j];
                if (rightsum < sum){
                    rightsum = sum;
                }
            }

            return leftsum + rightsum;
        }
    }
////////////////////////////////////////////////////////////
//O(n) time O(1) space DP solution (Java)

/*
Base case: 1 element, return nums[0]

Other cases:

If dp[i-1] < 0, dp[i] = nums[i]

if dp[i-1] >0, dp[i] = nums[i] + dp[i-1]

then pick the max sum.

We only need dp[i-1], so i use prev to record it, the space complexity is reduced to O(1).
 */


    public int maxSubArray19(int[] nums) {
        if (nums.length == 0){
            return 0;
        }

        int prev = nums[0];
        int cur = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++){
            if (prev > 0){
                cur = prev + nums[i];
            }else{
                cur = nums[i];
            }
            max = Math.max(max, cur);
            prev = cur;
        }

        return max;
    }
////////////////////////////////////////////////////////////
//O(n) time O(1) space dynamic programming 8-line java solution with comment
    /*Then, from i --> i+1:
-- if sum[i] >= 0, it gives non-negative contribution, sum[i+1] = sum[i] + a[i+1]
-- if sum[i] < 0, it gives negative contribution, sum[i+1] = a[i+1]*/
    public int maxSubArray20(int[] nums) {
        if (nums==null || nums.length==0) { return 0; }
        int max = nums[0], sum = nums[0];
        for (int i=1; i<nums.length; ++i) {
            if (sum >= 0) { sum += nums[i]; }
            else { sum = nums[i]; }
            max = Math.max(max, sum);
        }
        return max;
    }



////////////////////////////////////////////////////////////


    /*
    To calculate sum(0,i), you have 2 choices: either adding sum(0,i-1) to a[i],
     or not. If sum(0,i-1) is negative, adding it to a[i] will only make a smaller sum,
      so we add only if it's non-negative.

sum(0,i) = a[i] + (sum(0,i-1) < 0 ? 0 : sum(0,i-1))
We can then use O(1) space to keep track of the max sum(0, i) so far.
     */

    public int maxSubArray21(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }
        int max = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) { sum = nums[i]; }
            else {sum += nums[i]; }
            max = Math.max(max, sum);
        }
        return max;
    }

////////////////////////////////////////////////////////////
    public int maxSubArray22(int[] nums) {
        int maxSum = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
////////////////////////////////////////////////////////////

    public int maxSubArray23(int[] nums) {

        int n = nums.length;
        int max = nums[0];
        int sum = nums[0];
        int i = 1;

        while (i < n) {
            sum = Math.max(sum, 0);
            max = Math.max(sum + nums[i], max);
            i++;
        }

        return max;
    }


////////////////////////////////////////////////////////////

    //The solution does 1 iteration with constant space and no DP.


    public int maxSubArray24(int[] A) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (sum < 0)
                sum = A[i];
            else
                sum += A[i];
            if (sum > max)
                max = sum;
        }
        return max;
    }

////////////////////////////////////////////////////////////




}
