package _String._String;
import java.util.*;
import org.junit.Test;

//  539. Minimum Time Difference
//  https://leetcode.com/problems/minimum-time-difference/description/
//
//  3:
//
public class _539_String_Minimum_Time_Difference_M {
//------------------------------------------------------------------------------
    //1
    //Verbose Java Solution, Bucket
    //    There is only 24 * 60 = 1440 possible time points. Just create a boolean array, each element stands for if we see that time point or not. Then things become simple...

    public class Solution1 {
        public int findMinDifference(List<String> timePoints) {
            boolean[] mark = new boolean[24 * 60];
            for (String time : timePoints) {
                String[] t = time.split(":");
                int h = Integer.parseInt(t[0]);
                int m = Integer.parseInt(t[1]);
                if (mark[h * 60 + m]) return 0;
                mark[h * 60 + m] = true;
            }

            int prev = 0, min = Integer.MAX_VALUE;
            int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
            for (int i = 0; i < 24 * 60; i++) {
                if (mark[i]) {
                    if (first != Integer.MAX_VALUE) {
                        min = Math.min(min, i - prev);
                    }
                    first = Math.min(first, i);
                    last = Math.max(last, i);
                    prev = i;
                }
            }

            min = Math.min(min, (24 * 60 - last + first));

            return min;
        }
    }
//------------------------------------------------------------------------------
    //2
    //Java sorting with a sentinel node
    public class Solution2 {
        public int findMinDifference(List<String> timePoints) {
            int n = timePoints.size();
            List<Time> times = new ArrayList<>();
            for (String tp : timePoints) {
                String[] strs = tp.split(":");
                times.add(new Time(Integer.parseInt(strs[0]), Integer.parseInt(strs[1])));
            }
            Collections.sort(times);
            Time earlist = times.get(0);
            times.add(new Time(earlist.h + 24, earlist.m));
            int minDiff = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int diff = (int) Math.abs(times.get(i).getDiff(times.get(i + 1)));
                minDiff = Math.min(minDiff, diff);
            }
            return minDiff;
        }

    }

    class Time implements Comparable<Time> {
        int h;
        int m;
        public Time(int h, int m) {
            this.h = h;
            this.m = m;
        }

        public int compareTo(Time other) {
            if (this.h == other.h) {
                return this.m - other.m;
            }
            return this.h - other.h;
        }

        public int getDiff(Time other) {
            return (this.h - other.h) * 60 + (this.m - other.m);
        }
    }

//------------------------------------------------------------------------------
    //3
    //Java 10 liner solution. Simplest so far
    public class Solution3 {
        public int findMinDifference(List<String> timePoints) {
            int mm = Integer.MAX_VALUE;
            List<Integer> time = new ArrayList<>();

            for(int i = 0; i < timePoints.size(); i++){
                Integer h = Integer.valueOf(timePoints.get(i).substring(0, 2));
                time.add(60 * h + Integer.valueOf(timePoints.get(i).substring(3, 5)));
            }

            Collections.sort(time, (Integer a, Integer b) -> a - b);

            for(int i = 1; i < time.size(); i++){
                System.out.println(time.get(i));
                mm = Math.min(mm, time.get(i) - time.get(i-1));
            }

            int corner = time.get(0) + (1440 - time.get(time.size()-1));
            return Math.min(mm, corner);
        }
    }

//------------------------------------------------------------------------------
}
/*
Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.

Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.
Seen this question in a real interview before?   Yes  No
Companies
Palantir
Related Topics
String
 */