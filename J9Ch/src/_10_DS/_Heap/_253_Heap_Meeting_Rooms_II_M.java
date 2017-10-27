package _10_DS._Heap;
import java.util.*;
import org.junit.Test;

//253. Meeting Rooms II

public class _253_Heap_Meeting_Rooms_II_M {
    class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
/////////////////////////////////////////////////////////////

//AC Java solution using min heap
    class Solution1{
//    Just want to share another idea that uses min heap, average time complexity is O(nlogn).

        public int minMeetingRooms(Interval[] intervals) {
            if (intervals == null || intervals.length == 0)
                return 0;

            // Sort the intervals by start time
            Arrays.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval a, Interval b) { return a.start - b.start; }
            });

            // Use a min heap to track the minimum end time of merged intervals
            PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
                public int compare(Interval a, Interval b) { return a.end - b.end; }
            });

            // start with the first meeting, put it to a meeting room
            heap.offer(intervals[0]);

            for (int i = 1; i < intervals.length; i++) {
                // get the meeting room that finishes earliest
                Interval interval = heap.poll();

                if (intervals[i].start >= interval.end) {
                    // if the current meeting starts right after
                    // there's no need for a new room, merge the interval
                    interval.end = intervals[i].end;
                } else {
                    // otherwise, this meeting needs a new room
                    heap.offer(intervals[i]);
                }

                // don't forget to put the meeting room back
                heap.offer(interval);
            }

            return heap.size();
        }
    }

//Explanation of "Super Easy Java Solution Beats 98.8%" from @pinkfloyda
//  https://leetcode.com/problems/meeting-rooms-ii/discuss/
    /*
To understand why it works, first let’s define two events:
Meeting Starts
Meeting Ends

Next, we acknowledge three facts:
The numbers of the intervals give chronological orders
When an ending event occurs, there must be a starting event has happened before that, where “happen before” is defined by the chronological orders given by the intervals
Meetings that started which haven’t ended yet have to be put into different meeting rooms, and the number of rooms needed is the number of such meetings

So, what this algorithm works as follows:

for example, we have meetings that span along time as follows:

|_____|
      |______|
|________|
        |_______|
Then, the start time array and end time array after sorting appear like follows:

||    ||
     |   |   |  |
Initially, endsItr points to the first end event, and we move i which is the start event pointer. As we examine the start events, we’ll find the first two start events happen before the end event that endsItr points to, so we need two rooms (we magically created two rooms), as shown by the variable rooms. Then, as i points to the third start event, we’ll find that this event happens after the end event pointed by endsItr, then we increment endsItr so that it points to the next end event. What happens here can be thought of as one of the two previous meetings ended, and we moved the newly started meeting into that vacant room, thus we don’t need to increment rooms at this time and move both of the pointers forward.
Next, because endsItr moves to the next end event, we’ll find that the start event pointed by i happens before the end event pointed by endsItr. Thus, now we have 4 meetings started but only one ended, so we need one more room. And it goes on as this.
     */
public class Solution2 {
    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }
}

//    Super Easy Java Solution Beats 98.8%
    public class Solution3 {
        public int minMeetingRooms(Interval[] intervals) {
            int[] starts = new int[intervals.length];
            int[] ends = new int[intervals.length];
            for(int i=0; i<intervals.length; i++) {
                starts[i] = intervals[i].start;
                ends[i] = intervals[i].end;
            }
            Arrays.sort(starts);
            Arrays.sort(ends);
            int rooms = 0;
            int endsItr = 0;
            for(int i=0; i<starts.length; i++) {
                if(starts[i]<ends[endsItr])
                    rooms++;
                else
                    endsItr++;
            }
            return rooms;
        }
    }
/////////////////////////////////////////////////////////////
    //jiuzhang
    class Point{
        int time;
        int flag;

        Point(int t, int s){
            this.time = t;
            this.flag = s;
        }
        public Comparator<Point> PointComparator  = new Comparator<Point>(){
            public int compare(Point p1, Point p2){
                if(p1.time == p2.time) return p1.flag - p2.flag;
                else return p1.time - p2.time;
            }
        };
    }
    public class Solution {
        public int minMeetingRooms(Interval[] intervals) {
            List<Point> list = new ArrayList<>(intervals.length*2);
            for(Interval i : intervals){
                list.add(new Point(i.start, 1));
                list.add(new Point(i.end, 0));
            }

            Collections.sort(list,new Point(1,2).PointComparator );
            int count = 0, ans = 0;
            for(Point p : list){
                if(p.flag == 1) {
                    count++;
                }
                else {
                    count--;
                }
                ans = Math.max(ans, count);
            }

            return ans;
        }
    }
}
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
 */