package HF.HF2_Algorithms_DS_I._1Interval;

import java.util.*;

public class _2MergeIntervals {

    public class Solution {
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

    }


    // version: 高频题班
    class Solution2 {
        /**
         * @param intervals, a collection of intervals
         * @return: A new sorted interval list.
         */
        public List<Interval> merge(List<Interval> intervals) {
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
    }
}
