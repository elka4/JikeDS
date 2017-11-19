package HF.HF2_Algo_DS_I_1Interval;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
Example:
• 区间:[0, 99] 挖去的点:[0, 1, 3, 50, 75]
• Output: ["2", "4->49", "51->74", "76->99"]
思路:
• 简单的模拟题
– 两端点和一头一尾形成的区间 + for循环扫描中间形成的区间 – 利用函数让自己的代码更简洁(见代码)
  • 特殊输入?
– 实现时可能出现中间值超过int范围 – 去掉的点为空
时间复杂度:O(n)
 */

/*
• Company Tags:Google
考点:
• 快速实现简单问题 • 特殊情况的处理
 */

/*
能力维度:
2. 代码基础功力
5. 细节处理(corner case)
 */

//Missing Interval
public class _1MissingInterval {
    /**
     * @param nums a sorted integer array
     * @param lower an integer
     * @param upper an integer
     * @return a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // Write your code here
        List<String> results = new ArrayList<String>();

        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == Integer.MIN_VALUE) {
                lower = nums[i] + 1;
                continue;
            }
            if (lower == nums[i] - 1) {
                results.add(lower + "");
            } else if (lower < nums[i] - 1) {
                results.add(lower + "->" + (nums[i] - 1));
            }
            if (nums[i] == Integer.MAX_VALUE) {
                return results;
            }
            lower = nums[i] + 1;
        }
        if (lower == upper) {
            results.add(lower + "");
        } else if (lower < upper) {
            results.add(lower + "->" + upper);
        }
        return results;
    }

    @Test
    public void test01(){
        int[] nums = {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;
        System.out.println(findMissingRanges(nums, lower, upper));
    }

//-------------------------------------------------------------------------/

    //jiuzhang
    /**
     * @param nums a sorted integer array
     * @param lower an integer
     * @param upper an integer
     * @return a list of its missing ranges
     */
    public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        // Write your code here
        List<String> results = new ArrayList<String>();

        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == Integer.MIN_VALUE) {
                lower = nums[i] + 1;
                continue;
            }
            if (lower == nums[i] - 1) {
                results.add(lower + "");
            } else if (lower < nums[i] - 1) {
                results.add(lower + "->" + (nums[i] - 1));
            }
            if (nums[i] == Integer.MAX_VALUE) {
                return results;
            }
            lower = nums[i] + 1;
        }
        if (lower == upper) {
            results.add(lower + "");
        } else if (lower < upper) {
            results.add(lower + "->" + upper);
        }
        return results;
    }

    @Test
    public void test02(){
        int[] nums = {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;
        System.out.println(findMissingRanges2(nums, lower, upper));
    }

//-------------------------------------------------------------------------/


    // version: 高频题班
    /**
     * @param nums  a sorted integer array
     * @param lower an integer
     * @param upper an integer
     * @return a list of its missing ranges
     */

    /*
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // Write your code here
        List<String> ans = new ArrayList<>();

        addRange(ans, lower, nums[0] - 1);

        for (int i = 1; i < nums.length; i++) {
            addRange(ans, nums[i - 1] + 1, nums[i] - 1);
        }
        addRange(ans, nums[nums.length - 1] + 1, upper);

        return ans;
    }

    void addRange(List<String> ans, int st, int ed) {
        if (st > ed) {
            return;
        }
        if (st == ed) {
            ans.add(st + "");
            return;
        }
        ans.add(st + "->" + ed);
    }*/


    public List<String> findMissingRanges3(int[] nums, int lower, int upper) {
        // Write your code here
        List<String> ans = new ArrayList<>();
        if (nums.length == 0) {
            addRange(ans, lower, upper);
            return ans;
        }

        addRange(ans, lower, (long) nums[0] - 1);

        for (int i = 1; i < nums.length; i++) {
            addRange(ans, (long) nums[i - 1] + 1, (long) nums[i] - 1);
        }
        addRange(ans, (long) nums[nums.length - 1] + 1, upper);

        return ans;
    }

    void addRange(List<String> ans, long st, long ed) {
        if (st > ed) {
            return;
        }
        if (st == ed) {
            ans.add(st + "");
            return;
        }
        ans.add(st + "->" + ed);
    }

    @Test
    public void test03(){
        int[] nums = {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;
        System.out.println(findMissingRanges3(nums, lower, upper));
    }

//-------------------------------------------------------------------------/
}
/*
Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Have you met this question in a real interview? Yes
Example
Given nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
return ["2", "4->49", "51->74", "76->99"].
 */