package _BinarySearch.Interval;
import java.util.*;

public class _436_BinarySearch_Find_Right_Interval_M {
    class Interval{
        int start;
        int end;
        Interval(int i, int j){
            start = i;
            end = j;
        }
    }
//    Java clear O(n logn) solution based on TreeMap
    public class Solution {
        public int[] findRightInterval(Interval[] intervals) {
            int[] result = new int[intervals.length];
            NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();

            for (int i = 0; i < intervals.length; ++i) {
                intervalMap.put(intervals[i].start, i);
            }

            for (int i = 0; i < intervals.length; ++i) {
                Map.Entry<Integer, Integer> entry = intervalMap.ceilingEntry(intervals[i].end);
                result[i] = (entry != null) ? entry.getValue() : -1;
            }

            return result;
        }
    }


/*    Java Concise Binary Search
    If we are not allowed to use TreeMap:

    Sort starts
    For each end, find leftmost start using binary search
    To get the original index, we need a map*/
    class Solution2{
        public int[] findRightInterval(Interval[] intervals) {
            Map<Integer, Integer> map = new HashMap<>();
            List<Integer> starts = new ArrayList<>();
            for (int i = 0; i < intervals.length; i++) {
                map.put(intervals[i].start, i);
                starts.add(intervals[i].start);
            }

            Collections.sort(starts);
            int[] res = new int[intervals.length];
            for (int i = 0; i < intervals.length; i++) {
                int end = intervals[i].end;
                int start = binarySearch(starts, end);
                if (start < end) {
                    res[i] = -1;
                } else {
                    res[i] = map.get(start);
                }
            }
            return res;
        }

        public int binarySearch(List<Integer> list, int x) {
            int left = 0, right = list.size() - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (list.get(mid) < x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return list.get(left);
        }

    }




/*    Time compexity: n*log(n)
    n*log(n) for sorting
    log(n) for binary search X n times is n*log(n)

    Space complexity: n
    n for auxilliary array

    Algorithm:

    Clone intervals and update end with index.
    Sort clone-intervals by start
    Iterate over each interval and find the right by binary searching the clone-intervals.
    If found, shove the end i.e., the original index of the right interval from clone-intervals into the output array.*/
    public int[] findRightInterval(Interval[] intervals) {

        int n;
        // boundary case
        if (intervals == null || (n = intervals.length) == 0) return new int[]{};

        // output
        int[] res = new int[intervals.length];
        // auxilliary array to store sorted intervals
        Interval[] sintervals = new Interval[n];

        // sintervals don't have any use of 'end', so let's use it for tracking original index
        for (int i = 0; i < n; ++i) {
            sintervals[i] = new Interval(intervals[i].start, i);
        }

        // sort
        Arrays.sort(sintervals, (a, b)->a.start-b.start);

        int i = 0;
        for (; i < n; ++i) {
            int key = intervals[i].end;
            // binary search in sintervals for key
            int l = 0, r = n - 1;
            int right = -1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (sintervals[m].start == key) {
                    right = sintervals[m].end; // original index is stored in end
                    break;
                } else if (sintervals[m].start < key) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            // if we haven't found the key, try looking for 'start' that's just greater
            if ((right == -1) && (l < n) && (sintervals[l].start > key)) {
                right = sintervals[l].end; // original index is stored in end
            }

            res[i] = right;
        }

        return res;
    }
}
/*
Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
Example 3:
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.
 */