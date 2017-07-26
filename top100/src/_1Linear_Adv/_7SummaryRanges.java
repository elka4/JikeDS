package _1Linear_Adv;
import org.junit.Test;

import java.util.*;
//summary ranges
//类似于two pointer： pre, cur, gap
//双前向型指针
public class _7SummaryRanges {
    //editorial
    //https://leetcode.com/articles/summary-ranges/

    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i = 0, j = 0; j < nums.length; ++j) {
            // check if j + 1 extends the range [nums[i], nums[j]]
            if (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
                continue;
            // put the range [nums[i], nums[j]] into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);
            i = j + 1;
        }
        return summary;
    }

    @Test
    public void test01(){
        System.out.println(summaryRanges(new int[]{0,1,2,4,5,7}));
    }

/////////////////////////////////////////////////////////////////////

    public List<String> summaryRanges2(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i, j = 0; j < nums.length; ++j){
            i = j;
            // try to extend the range [nums[i], nums[j]]
            while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
                ++j;
            // put the range into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);
        }
        return summary;
    }

/////////////////////////////////////////////////////////////////////

}
/*
Time complexity : O(n)O(n). Each element is visited constant times:
 either in comparison with neighbor or put in the result list.

Space complexity : O(1)O(1). All the auxiliary space we need is the two
indices, if we don't count the return list.
 */