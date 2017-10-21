package _01_Array.Heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.*;

/*
LeetCode – Meeting Rooms II (Java)

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.

 */


// Heap Greedy Sort

// 253. Meeting Rooms II
public class Meeting_Rooms_II {

    public int minMeetingRooms(Interval[] intervals) {
        if(intervals==null||intervals.length==0)
            return 0;

        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start-i2.start;
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        int count=1;
        queue.offer(intervals[0].end);

        for(int i=1; i<intervals.length; i++){
            if(intervals[i].start<queue.peek()){
                count++;

            }else{
                queue.poll();
            }

            queue.offer(intervals[i].end);
        }

        return count;
    }


    @Test
    public void test01(){
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0,30);
        intervals[1] = new Interval(5,10);
        intervals[2] = new Interval(15,20);
        System.out.println(minMeetingRooms(intervals));
    }


/////////////////////////////////////////////////////////////////////////////////////

    // jiuzhang
    class Point{
        int time;
        int flag;

        Point(){
        }

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

    public int minMeetingRooms2(Interval[] intervals) {
        List<Point> list = new ArrayList<>(intervals.length*2);
        for(Interval i : intervals){
            list.add(new Point(i.start, 1));
            list.add(new Point(i.end, 0));
        }

        Collections.sort(list,new Point().PointComparator );
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


    @Test
    public void test02(){
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0,30);
        intervals[1] = new Interval(5,10);
        intervals[2] = new Interval(15,20);
        System.out.println(minMeetingRooms2(intervals));
    }



/////////////////////////////////////////////////////////////////////////////////////

    //leetcode
    //AC Java solution using min heap
    //Just want to share another idea that uses min heap, average time complexity is O(nlogn).

    public int minMeetingRooms3(Interval[] intervals) {
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


    @Test
    public void test03(){
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0,30);
        intervals[1] = new Interval(5,10);
        intervals[2] = new Interval(15,20);
        System.out.println(minMeetingRooms3(intervals));
    }


/////////////////////////////////////////////////////////////////////////////////////

    //Explanation of "Super Easy Java Solution Beats 98.8%" from pinkfloyda
    public int minMeetingRooms4(Interval[] intervals) {
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
    /*
    Given [[0, 30],[5, 10],[15, 20]],
return 2.
     */

    @Test
    public void test04(){
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0,30);
        intervals[1] = new Interval(5,10);
        intervals[2] = new Interval(15,20);
        System.out.println(minMeetingRooms4(intervals));
    }


/////////////////////////////////////////////////////////////////////////////////////





}
