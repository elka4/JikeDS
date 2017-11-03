package _TwoPointer;
import java.util.*;
import org.junit.Test;

//  360. Sort Transformed Array
//  https://leetcode.com/problems/sort-transformed-array/description/

public class _360_TwoPointer_Sort_Transformed_Array_M {
//Java O(n) incredibly short yet easy to understand AC solution

/*    the problem seems to have many cases a>0, a=0,a<0, (when a=0, b>0, b<0). However, they can be combined into just 2 cases: a>0 or a<0

            1.a>0, two ends in original array are bigger than center if you learned middle school math before.

            2.a<0, center is bigger than two ends.

    so use two pointers i, j and do a merge-sort like process. depending on sign of a, you may want to start from the beginning or end of the transformed array. For a==0 case, it does not matter what b's sign is.
    The function is monotonically increasing or decreasing. you can start with either beginning or end.*/

    public class Solution1 {
        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            int n = nums.length;
            int[] sorted = new int[n];
            int i = 0, j = n - 1;
            int index = a >= 0 ? n - 1 : 0;
            while (i <= j) {
                if (a >= 0) {
                    sorted[index--] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[i++], a, b, c) : quad(nums[j--], a, b, c);
                } else {
                    sorted[index++] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[j--], a, b, c) : quad(nums[i++], a, b, c);
                }
            }
            return sorted;
        }

        private int quad(int x, int a, int b, int c) {
            return a * x * x + b * x + c;
        }
    }

//    Same idea as you. Below I share a shorter 8-lines implementation:

    public class Solution2 {
        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            double piv = -b / 2.0 / a;
            int n = nums.length;
            boolean direction = a < 0 || (a == 0 && b > 0);
            int[] ans = new int[n];
            for (int k = 0, i = 0, j = n - 1; k < n; k++)
                ans[direction ? k : n - k - 1] =
                        cal(nums[(Math.abs(nums[j] - piv) > Math.abs(nums[i] - piv) ? j-- : i++)], a, b, c);
            return ans;
        }

        private int cal(int x, int a, int b, int c) {
            return a * x * x + b * x + c;
        }
    }


    //My easy to understand Java AC solution using Two pointers
/*    The idea is simple:
    For a parabola,
            if a >= 0, the min value is at its vertex. So our our sorting should goes from two end points towards the vertex, high to low.
if a < 0, the max value is at its vertex. So our sort goes the opposite way.*/

    public class Solution3 {
        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            int[] res = new int[nums.length];
            int start = 0;
            int end = nums.length - 1;
            int i = a >= 0 ? nums.length - 1 : 0;
            while(start <= end) {
                int startNum = getNum(nums[start], a, b, c);
                int endNum = getNum(nums[end], a, b, c);
                if(a >= 0) {
                    if(startNum >= endNum) {
                        res[i--] = startNum;
                        start++;
                    }
                    else{
                        res[i--] = endNum;
                        end--;
                    }
                }
                else{
                    if(startNum <= endNum) {
                        res[i++] = startNum;
                        start++;
                    }
                    else{
                        res[i++] = endNum;
                        end--;
                    }
                }
            }
            return res;
        }
        public int getNum(int x, int a, int b, int c) {
            return a * x * x + b * x + c;
        }
    }

//O(n) Solution using JAVA
//It is parabolic relation. After convert nums to a*x^2 + bx + c. If a > 0, there will be bottom in the arr; if a < 0, there it top in the arr. Find the bottom or top then go left and right.

    public class Solution4 {
        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            int n = nums.length;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = a * nums[i] * nums[i] + b * nums[i] + c;
            if (n <= 1) return arr;
            if (a == 0) {
                if (b >= 0) return arr;
                else return reverse(arr);
            }
            double bottom = (double)-b / (2 * a);
            int lo = 0, hi = n - 1; //binary search to find low/top point
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] <= bottom) lo = mid + 1;
                else hi = mid - 1;
            }
            if (hi == n - 1) return a > 0? reverse(arr) : arr;
            if (hi == -1) return a > 0? arr : reverse(arr);
            int i = hi, j = lo;
            int[] res = new int[n];
            if (a > 0) {
                int k = 0;
                while (i >= 0 || j < n) {
                    if (i < 0) res[k++] = arr[j++];
                    else if (j >= n) res[k++] = arr[i--];
                    else if (arr[i] < arr[j]) res[k++] = arr[i--];
                    else res[k++] = arr[j++];
                }
            }
            else {
                int k = n - 1;
                while (i >= 0 || j < n) {
                    if (i < 0) res[k--] = arr[j++];
                    else if (j >= n) res[k--] = arr[i--];
                    else if (arr[i] > arr[j]) res[k--] = arr[i--];
                    else res[k--] = arr[j++];
                }
            }
            return res;
        }

        private int[] reverse(int[] nums) {
            int i = 0, j = nums.length - 1;
            while (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
            return nums;
        }
    }


    //My solution with some optimization
/*    This is a kind of long solution, but the idea is to boost the running time by some optimization. There are 4 cases:

    a, b, c are all zero, return an array with zeros.
    a, b are zero, return an array with c.
    a is zero, apply linear equation on input.
    a is not zero, apply quadratic equation on input.
    By dealing with each case separately, we can get rid of the unnecessary multiplication operations given the input. Another minor 2 optimizations are:

    when using the 2 pointer approach to fill in the output array, calculate f(x) only once for each x;
    use f(x) = x * (a * x + b) + c, instead of f(x) = a * x * x + b * x + c; you can reduce one multiplication operation per function call.
            Thanks.*/

    public class Solution5 {
        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            int n = nums.length;
            int [] ret = new int[n];

            if (a == 0 && b == 0 && c == 0) {
                Arrays.fill(ret, 0);
            } else if (a == 0 && b == 0) {
                Arrays.fill(ret, c);
            } else if (a == 0) {
                for (int i = 0; i < n; i++) {
                    int fx = b * nums[i] + c;
                    if (b > 0) {
                        ret[i] = fx;
                    } else {
                        ret[n - i - 1] = fx;
                    }
                }
            } else {
                int l = 0;
                int r = n - 1;
                int valL = f(nums[l], a, b, c);
                int valR = f(nums[r], a, b, c);

                if (a > 0) {
                    int index = n - 1;
                    while (l < r) {
                        if (valL < valR) {
                            ret[index--] = valR;
                            r--;
                            valR = f(nums[r], a, b, c);
                        } else {
                            ret[index--] = valL;
                            l++;
                            valL = f(nums[l], a, b, c);
                        }
                    }
                    ret[index] = f(nums[l], a, b, c);
                } else {
                    int index = 0;
                    while (l < r) {
                        if (valL < valR) {
                            ret[index++] = valL;
                            l++;
                            valL = f(nums[l], a, b, c);
                        } else {
                            ret[index++] = valR;
                            r--;
                            valR = f(nums[r], a, b, c);
                        }
                    }
                    ret[index] = f(nums[l], a, b, c);
                }
            }
            return ret;
        }

        private int f(int x, int a, int b, int c) {
            return x * (a * x + b) + c;
        }
    }


    //Accepted Java solution, two pointer

//    so easy to make mistakes...

    public class Solution6 {
        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            int[] result = new int[nums.length];

            if(nums.length == 0) return result;

            double center = a != 0 ? -0.5 * b / a : (b > 0 ? nums[0] : nums[nums.length - 1]);
            int left = -1, right = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < center) {
                    left = i;
                } else {
                    break;
                }
            }
            right = left + 1;

            int r = a > 0 || a == 0 && b > 0 ? 0 : nums.length - 1;
            while (left >= 0 || right < nums.length) {
                if (left < 0 || right < nums.length && Math.abs(nums[left] - center) > Math.abs(nums[right] - center)) {
                    result[r] = convert(a, b, c, nums[right++]);
                } else {
                    result[r] = convert(a, b, c, nums[left--]);
                }
                r += a > 0 || a == 0 && b > 0 ? 1 : -1;
            }

            return result;
        }

        private int convert(int a, int b, int c, int x) {
            return a * x * x + b * x + c;
        }
    }



}
/*
Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]

 */

/*

 */