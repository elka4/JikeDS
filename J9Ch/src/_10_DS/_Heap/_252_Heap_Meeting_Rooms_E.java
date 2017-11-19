package _10_DS._Heap;
import java.util.*;
import org.junit.Test;
public class _252_Heap_Meeting_Rooms_E {
    class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
//-------------------------------------------------------------------------//
    //AC clean Java solution
    class Solution1{
        public boolean canAttendMeetings(Interval[] intervals) {
            if (intervals == null)
                return false;

            // Sort the intervals by start time
            Arrays.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval a, Interval b) { return a.start - b.start; }
            });

            for (int i = 1; i < intervals.length; i++)
                if (intervals[i].start < intervals[i - 1].end)
                    return false;

            return true;
        }
    }

    //Easy JAVA solution beat 98%
    class Solution2{
        public boolean canAttendMeetings(Interval[] intervals) {
            int len=intervals.length;
            if(len==0){
                return true;
            }
            int[]begin=new int[len];
            int[]stop=new int[len];
            for(int i=0;i<len;i++){
                begin[i]=intervals[i].start;
                stop[i]=intervals[i].end;
            }
            Arrays.sort(begin);
            Arrays.sort(stop);
            int endT=0;
            for(int i=1;i<len;i++){
                if(begin[i]<stop[i-1]){
                    return false;
                }
            }
            return true;
        }
    }

    public class Solution3 {
        public boolean canAttendMeetings(Interval[] l) {
            if (l.length < 2) return true;
            Arrays.sort(l, new Comparator<Interval>(){
                @Override
                public int compare(Interval a, Interval b) {
                    return a.start - b.start;
                }
            });
            for (int max = l[0].end, i = 1; i < l.length; i++) {
                if (l[i].start == l[i - 1].start || l[i].start < max) return false;
                max = Math.max(max, l[i].end);
            }
            return true;
        }
    }

//-------------------------------------------------------------------------//
    //jiuzhang
public class Jiuzhang {
    public boolean canAttendMeetings(Interval[] intervals) {
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
}
}
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.


 */