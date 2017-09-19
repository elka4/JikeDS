package HF.HF2_Algorithms_DS_I._1Interval;

import java.util.*;

/*
Example:
• Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 思路:
• 做了merge interval 这一题就很简单了
• 先插入，然后直接套用merge interval
• 特殊输入?
时间复杂度:O(n)
 */

/*

能力维度:
2. 代码基础功力
3. 基础数据结构/算法
7. debug能力
 */

//Insert Interval
public class _3InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null || intervals == null) {
            return intervals;
        }

        List<Interval> results = new ArrayList<Interval>();
        int insertPos = 0;

        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                results.add(interval);
                insertPos++;
            } else if (interval.start > newInterval.end) {
                results.add(interval);
            } else {
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }

        results.add(insertPos, newInterval);

        return results;
    }

//////////////////////////////////////////////////////////////////////


    // version: 高频题班
    /**
     * Insert newInterval into intervals.
     *
     * @param intervals:   Sorted interval list.
     * @param newInterval: A new interval.
     * @return: A new sorted interval list.
     */
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        // write your code here
        List<Interval> ans = new ArrayList<>();

        int idx = 0;
        while (idx < intervals.size() && intervals.get(idx).start < newInterval.start) {
            idx++;
        }
        intervals.add(idx, newInterval);

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
//////////////////////////////////////////////////////////////////////

}
/*
Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

Have you met this question in a real interview? Yes
Example
Insert [2, 5] into [[1,2], [5,9]], we get [[1,9]].

Insert [3, 4] into [[1,2], [5,9]], we get [[1,2], [3,4], [5,9]].
 */