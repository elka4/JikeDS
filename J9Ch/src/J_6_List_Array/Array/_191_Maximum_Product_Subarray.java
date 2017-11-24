package J_6_List_Array.Array;

import java.util.List;

/** 191 Maximum Product Subarray
 * Created by tianhuizhu on 6/28/17.
 */

/*
Find the contiguous subarray within an array (containing at least one number)
 which has the largest product.

Have you met this question in a real interview? Yes
Example
For example, given the array [2,3,-2,4], the contiguous subarray [2,3]
has the largest product = 6.
 */

@SuppressWarnings("all")

public class _191_Maximum_Product_Subarray {

    // LeetCode version:
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(List<Integer> nums) {
        int[] max = new int[nums.size()];
        int[] min = new int[nums.size()];

        min[0] = max[0] = nums.get(0);
        int result = nums.get(0);

        for (int i = 1; i < nums.size(); i++) {

            min[i] = max[i] = nums.get(i);

            if (nums.get(i) > 0) {
                max[i] = Math.max(max[i], max[i - 1] * nums.get(i));
                min[i] = Math.min(min[i], min[i - 1] * nums.get(i));
            } else if (nums.get(i) < 0) {
                max[i] = Math.max(max[i], min[i - 1] * nums.get(i));
                min[i] = Math.min(min[i], max[i - 1] * nums.get(i));
            }

            result = Math.max(result, max[i]);
        }

        return result;
    }




//------------------------------------------------------------------------------///////

    // LintCode Version 1:
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct2(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        min[0] = max[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = max[i] = nums[i];
            if (nums[i] > 0) {
                max[i] = Math.max(max[i], max[i - 1] * nums[i]);
                min[i] = Math.min(min[i], min[i - 1] * nums[i]);
            } else if (nums[i] < 0) {
                max[i] = Math.max(max[i], min[i - 1] * nums[i]);
                min[i] = Math.min(min[i], max[i - 1] * nums[i]);
            }

            result = Math.max(result, max[i]);
        }

        return result;
    }


//------------------------------------------------------------------------------//////

    //LintCode version2: O(1) Space Complexity
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct3(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int minPre = nums[0], maxPre = nums[0];
        int max = nums[0], min = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            max = Math.max(nums[i], Math.max(maxPre * nums[i], minPre * nums[i]));
            min = Math.min(nums[i], Math.min(maxPre * nums[i], minPre * nums[i]));
            res = Math.max(res, max);
            maxPre = max;
            minPre = min;
        }
        return res;
    }


//------------------------------------------------------------------------------

    //Java Solution - Dynamic Programming
    public int maxProduct4(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        max[0] = min[0] = nums[0];
        int result = nums[0];

        for(int i=1; i<nums.length; i++){
            if(nums[i]>0){
                max[i]=Math.max(nums[i], max[i-1]*nums[i]);
                min[i]=Math.min(nums[i], min[i-1]*nums[i]);
            }else{
                max[i]=Math.max(nums[i], min[i-1]*nums[i]);
                min[i]=Math.min(nums[i], max[i-1]*nums[i]);
            }

            result = Math.max(result, max[i]);
        }

        return result;
    }

//-------------------------------------------------------------------------///
// leetcode

//Possibly simplest solution with O(n) time complexity
int maxProduct(int A[], int n) {
    // store the result that is the max we have found so far
    int r = A[0];

    // imax/imin stores the max/min product of
    // subarray that ends with the current number A[i]
    for (int i = 1, imax = r, imin = r; i < n; i++) {
        // multiplied by a negative makes big number smaller, small number bigger
        // so we redefine the extremums by swapping them
        if (A[i] < 0)
            swap(A, imax, imin);

        // max/min product for the current number is either the current number itself
        // or the max/min by the previous number times the current one
        imax = Math.max(A[i], imax * A[i]);
        imin = Math.min(A[i], imin * A[i]);

        // the newly computed max value is a candidate for our global result
        r = Math.max(r, imax);
    }
    return r;
}
void swap(int[] A, int imax, int imin){
        int temp = A[imax];
        A[imax] = A[imin];
        A[imin] = temp;
}


//-------------------------------------------------------------------------///

//Sharing my solution: O(1) space, O(n) running time
//There's no need to use O(n) space, as all that you need is a minhere and maxhere.
// (local max and local min), then you can get maxsofar (which is global max) from them.

    public int maxProduct(int[] A) {
        if (A.length == 0) {
            return 0;
        }

        int maxherepre = A[0];
        int minherepre = A[0];
        int maxsofar = A[0];
        int maxhere, minhere;

        for (int i = 1; i < A.length; i++) {
            maxhere = Math.max(Math.max(maxherepre * A[i], minherepre * A[i]), A[i]);
            minhere = Math.min(Math.min(maxherepre * A[i], minherepre * A[i]), A[i]);
            maxsofar = Math.max(maxhere, maxsofar);
            maxherepre = maxhere;
            minherepre = minhere;
        }
        return maxsofar;
    }

//-------------------------------------------------------------------------///
/*
Loop through the array, each time remember the max and min value for the
previous product, the most important thing is to update the max and min
value: we have to compare among max * A[i], min * A[i] as well as A[i],
since this is product, a negative * negative could be positive.

 */

    public int maxProduct6(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }

//-------------------------------------------------------------------------///
//    f[i] means maximum product that can be achieved ending with i
//
//    g[i] means minimum product that can be achieved ending with i

    public int maxProduct7(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] f = new int[A.length];
        int[] g = new int[A.length];
        f[0] = A[0];
        g[0] = A[0];
        int res = A[0];
        for (int i = 1; i < A.length; i++) {
            f[i] = Math.max(Math.max(f[i - 1] * A[i], g[i - 1] * A[i]), A[i]);
            g[i] = Math.min(Math.min(f[i - 1] * A[i], g[i - 1] * A[i]), A[i]);
            res = Math.max(res, f[i]);
        }
        return res;
    }

//-------------------------------------------------------------------------///
    public int maxProduct8(int[] a) {
        if (a == null || a.length == 0)
            return 0;

        int ans = a[0], min = ans, max = ans;

        for (int i = 1; i < a.length; i++) {
            if (a[i] >= 0) {
                max = Math.max(a[i], max * a[i]);
                min = Math.min(a[i], min * a[i]);
            } else {
                int tmp = max;
                max = Math.max(a[i], min * a[i]);
                min = Math.min(a[i], tmp * a[i]);
            }

            ans = Math.max(ans, max);
        }

        return ans;
    }

//-------------------------------------------------------------------------///

    //2 Passes scan, beats 99%
//it's really about odd negative numbers or even negative numbers,
// if it's odd, either the left end one or the right end one should be counted,
// so it will be revealed by scanning from left and from right in 2 passes.

//0 is a kind of delimiter, product accumulation will be reset to 1

    public int maxProduct9(int[] nums) {
        int max = Integer.MIN_VALUE, product = 1;
        int len = nums.length;

        for(int i = 0; i < len; i++) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        product = 1;
        for(int i = len - 1; i >= 0; i--) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        return max;
    }




}
