package _String._Math;
import java.util.*;
import org.junit.Test;

//  553. Optimal Division
//  https://leetcode.com/problems/optimal-division/
//  Math String
//  7:
//
public class _553_String_Optimal_Division_M {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/optimal-division/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Brute Force [Accepted]
    public class Solution1 {
        public String optimalDivision(int[] nums) {
            T t = optimal(nums, 0, nums.length - 1, "");
            return t.max_str;
        }
        class T {
            float max_val, min_val;
            String min_str, max_str;
        }
        public T optimal(int[] nums, int start, int end, String res) {
            T t = new T();
            if (start == end) {
                t.max_val = nums[start];
                t.min_val = nums[start];
                t.min_str = "" + nums[start];
                t.max_str = "" + nums[start];
                return t;
            }
            t.min_val = Float.MAX_VALUE;
            t.max_val = Float.MIN_VALUE;
            t.min_str = t.max_str = "";
            for (int i = start; i < end; i++) {
                T left = optimal(nums, start, i, "");
                T right = optimal(nums, i + 1, end, "");
                if (t.min_val > left.min_val / right.max_val) {
                    t.min_val = left.min_val / right.max_val;
                    t.min_str = left.min_str + "/" + (i + 1 != end ? "(" : "") + right.max_str + (i + 1 != end ? ")" : "");
                }
                if (t.max_val < left.max_val / right.min_val) {
                    t.max_val = left.max_val / right.min_val;
                    t.max_str = left.max_str + "/" + (i + 1 != end ? "(" : "") + right.min_str + (i + 1 != end ? ")" : "");
                }
            }
            return t;
        }
    }


//------------------------------------------------------------------------------
    //2
    //Approach #2 Using Memorization [Accepted]
    public class Solution2 {
        class T {
            float max_val, min_val;
            String min_str, max_str;
        }
        public String optimalDivision(int[] nums) {
            T[][] memo = new T[nums.length][nums.length];
            T t = optimal(nums, 0, nums.length - 1, "", memo);
            return t.max_str;
        }
        public T optimal(int[] nums, int start, int end, String res, T[][] memo) {
            if (memo[start][end] != null)
                return memo[start][end];
            T t = new T();
            if (start == end) {
                t.max_val = nums[start];
                t.min_val = nums[start];
                t.min_str = "" + nums[start];
                t.max_str = "" + nums[start];
                memo[start][end] = t;
                return t;
            }
            t.min_val = Float.MAX_VALUE;
            t.max_val = Float.MIN_VALUE;
            t.min_str = t.max_str = "";
            for (int i = start; i < end; i++) {
                T left = optimal(nums, start, i, "", memo);
                T right = optimal(nums, i + 1, end, "", memo);
                if (t.min_val > left.min_val / right.max_val) {
                    t.min_val = left.min_val / right.max_val;
                    t.min_str = left.min_str + "/" + (i + 1 != end ? "(" : "") + right.max_str + (i + 1 != end ? ")" : "");
                }
                if (t.max_val < left.max_val / right.min_val) {
                    t.max_val = left.max_val / right.min_val;
                    t.max_str = left.max_str + "/" + (i + 1 != end ? "(" : "") + right.min_str + (i + 1 != end ? ")" : "");
                }
            }
            memo[start][end] = t;
            return t;
        }
    }

//------------------------------------------------------------------------------
    //3
    //Approach #3 Using some Math [Accepted]
    public class Solution3 {
        public String optimalDivision(int[] nums) {
            if (nums.length == 1)
                return nums[0] + "";
            if (nums.length == 2)
                return nums[0] + "/" + nums[1];
            StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
            for (int i = 2; i < nums.length; i++) {
                res.append("/" + nums[i]);
            }
            res.append(")");
            return res.toString();
        }
    }


//------------------------------------------------------------------------------
    //4
    //Java Solution, Backtracking
    public class Solution4 {
        class Result {
            String str;
            double val;
        }

        public String optimalDivision(int[] nums) {
            int len = nums.length;
            return getMax(nums, 0, len - 1).str;
        }

        private Result getMax(int[] nums, int start, int end) {
            Result r = new Result();
            r.val = -1.0;

            if (start == end) {
                r.str = nums[start] + "";
                r.val = (double)nums[start];
            }
            else if (start + 1 == end) {
                r.str = nums[start] + "/" + nums[end];
                r.val = (double)nums[start] / (double)nums[end];
            }
            else {
                for (int i = start; i < end; i++) {
                    Result r1 = getMax(nums, start, i);
                    Result r2 = getMin(nums, i + 1, end);
                    if (r1.val / r2.val > r.val) {
                        r.str = r1.str + "/" + (end - i >= 2 ? "(" + r2.str + ")" : r2.str);
                        r.val = r1.val / r2.val;
                    }
                }
            }

            //System.out.println("getMax " + start + " " + end + "->" + r.str + ":" + r.val);
            return r;
        }

        private Result getMin(int[] nums, int start, int end) {
            Result r = new Result();
            r.val = Double.MAX_VALUE;

            if (start == end) {
                r.str = nums[start] + "";
                r.val = (double)nums[start];
            }
            else if (start + 1 == end) {
                r.str = nums[start] + "/" + nums[end];
                r.val = (double)nums[start] / (double)nums[end];
            }
            else {
                for (int i = start; i < end; i++) {
                    Result r1 = getMin(nums, start, i);
                    Result r2 = getMax(nums, i + 1, end);
                    if (r1.val / r2.val < r.val) {
                        r.str = r1.str + "/" + (end - i >= 2 ? "(" + r2.str + ")" : r2.str);
                        r.val = r1.val / r2.val;
                    }
                }
            }

            //System.out.println("getMin " + start + " " + end + "->" + r.str + ":" + r.val);
            return r;
        }
    }

//------------------------------------------------------------------------------
    //5
    //O(n) very easy Java solution.
    public class Solution5 {
        public String optimalDivision(int[] nums) {
            StringBuilder builder = new StringBuilder();
            builder.append(nums[0]);
            for (int i = 1; i < nums.length; i++) {
                if (i == 1 && nums.length > 2) {
                    builder.append("/(").append(nums[i]);
                } else {
                    builder.append("/").append(nums[i]);
                }
            }

            return nums.length > 2 ? builder.append(")").toString() : builder.toString();
        }
    }

//------------------------------------------------------------------------------
    //6
    //Simple Java Solution
    public class Solution6 {
        public String optimalDivision(int[] nums) {
            if (nums.length == 1)
                return nums[0] + "";
            if (nums.length == 2)
                return nums[0] + "/" + nums[1];
            String res = nums[0] + "/(" + nums[1];
            for (int i = 2; i < nums.length; i++) {
                res += "/" + nums[i];
            }
            return res + ")";
        }
    }
//------------------------------------------------------------------------------
    //7
    //[C++] [Java] Clean Code
    public class Solution7 {
        public String optimalDivision(int[] nums) {
            int n = nums.length;
            String expr = "";
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    expr += "/";
                }
                if (i == 1 && n > 2) {
                    expr += "(";
                }
                expr += nums[i];
                if (i == n - 1 && n > 2) {
                    expr += ")";
                }
            }
            return expr;
        }
    }


//------------------------------------------------------------------------------
}
/*
Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.

However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.

Example:
Input: [1000,100,10,2]
Output: "1000/(100/10/2)"
Explanation:
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
since they don't influence the operation priority. So you should return "1000/(100/10/2)".

Other cases:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
Note:

The length of the input array is [1, 10].
Elements in the given array will be in range [2, 1000].
There is only one optimal division for each test case.
Seen this question in a real interview before?   Yes  No

Companies
Amazon

Related Topics
Math String
 */