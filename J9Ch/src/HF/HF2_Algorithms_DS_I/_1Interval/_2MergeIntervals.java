package HF.HF2_Algorithms_DS_I._1Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Example:
• Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18]
  思路一:
• 类似桶排序 mark数组，出现在线段内就标true • 可是时间复杂度?
 */

/*
Example:
• Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18]
  思路二:
• 直接合并，比如[1,3] [2,6] 合并成[1,6] ，不断合并，直到不能合并为止
 */

/*
• Company Tags: LinkedIn Google Facebook
考点:
• 是否想到直接合并
• 是否可以想到排序来简化问题
 */

/*
能力维度:
2. 代码基础功力
3. 基础数据结构/算法
7. debug能力
 */


//Merge Intervals
public class _2MergeIntervals {
    //jiuzhang
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        Collections.sort(intervals, new IntervalComparator());

        List<Interval> result = new ArrayList<Interval>();
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end ){
                last.end = Math.max(last.end, curt.end);
            }else{
                result.add(last);
                last = curt;
            }
        }

        result.add(last);
        return result;
    }


    private class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }


////////////////////////////////////////////////////////////////


    // version: 高频题班
    /**
     * @param intervals, a collection of intervals
     * @return: A new sorted interval list.
     */
    public List<Interval> merge2(List<Interval> intervals) {
        // write your code here
        List<Interval> ans = new ArrayList<>();

        //intervals.sort(Comparator.comparing(i -> i.start));
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval obj0, Interval obj1) {
                return obj0.start - obj1.start;
            }
        });

        Interval last = null;
        for (Interval item : intervals) {
            if (last == null || last.end < item.start) {
                ans.add(item);
                last = item;
            } else {
                last.end = Math.max(last.end, item.end); // Modify the element already in list
            }
        }
        return ans;
    }

////////////////////////////////////////////////////////////////

}

/*
Given a collection of intervals, merge all overlapping intervals.

Have you met this question in a real interview? Yes
Example
Given intervals => merged intervals:

[                     [
  [1, 3],               [1, 6],
  [2, 6],      =>       [8, 10],
  [8, 10],              [15, 18]
  [15, 18]            ]
]
Challenge
Tags
LinkedIn Sort Array Google
 */
