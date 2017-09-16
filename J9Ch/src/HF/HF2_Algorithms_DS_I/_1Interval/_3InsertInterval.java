package HF.HF2_Algorithms_DS_I._1Interval;

import java.util.*;

public class _3InsertInterval {
    public class Solution {
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
    }

    // version: 高频题班
    class Solution2 {
        /**
         * Insert newInterval into intervals.
         *
         * @param intervals:   Sorted interval list.
         * @param newInterval: A new interval.
         * @return: A new sorted interval list.
         */
        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
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
    }
}
