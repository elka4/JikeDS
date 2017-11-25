package _01_Array.Heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
/*
LeetCode – Meeting Rooms (Java)

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings. For example, Given [[0, 30],[5, 10],[15, 20]], return false. Java Solution

If a person can attend all meetings, there must not be any overlaps between any meetings. After sorting the intervals, we can compare the current end and next start.
 */

// Tag: sort

// Meeting Rooms
public class Meeting_Rooms {

    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start-b.start;
            }
        });

        for(int i=0; i<intervals.length-1; i++){
            if(intervals[i].end>intervals[i+1].start){
                return false;
            }
        }

        return true;
    }

//------------------------------------------------------------------------------

    // jiuzhang
    public boolean canAttendMeetings2(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        int end = intervals[0].end;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].start < end) {
                return false;
            }
            end = Math.max(end, intervals[i].end);
        }
        return true;
    }

    @Test
    public void test01(){
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0,30);
        intervals[1] = new Interval(5,10);
        intervals[2] = new Interval(15,20);
        System.out.println(canAttendMeetings2(intervals));
    }
    @Test
    public void test02(){
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0,30);
        intervals[1] = new Interval(5,10);
        intervals[2] = new Interval(15,20);
        System.out.println(canAttendMeetings(intervals));
    }

//------------------------------------------------------------------------------
    //https://leetcode.com/articles/meeting-rooms/
    //Approach #1 (Brute Force) [Accepted]

    public boolean canAttendMeetings3(Interval[] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (overlap(intervals[i], intervals[j])) return false;
            }
        }
        return true;
    }

    private boolean overlap(Interval i1, Interval i2) {
        return ((i1.start >= i2.start && i1.start < i2.end)
                || (i2.start >= i1.start && i2.start < i1.end));
    }

    private boolean overlap2(Interval i1, Interval i2) {
        return (Math.min(i1.end, i2.end) >
                Math.max(i1.start, i2.start));
    }
//------------------------------------------------------------------------------
    //Approach #2 (Sorting) [Accepted]

    public boolean canAttendMeetings4(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) return false;
        }
        return true;
    }


//------------------------------------------------------------------------------

}
