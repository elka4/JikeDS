package _1_Array.Interval;
import java.util.*;

/*
LeetCode – Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

Analysis

The key to solve this problem is defining a Comparator first to sort the arraylist of Intevals.
 */
public class Merge_Intervals {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();

        if(intervals==null||intervals.size()==0)
            return result;

        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                if(i1.start!=i2.start)
                    return i1.start-i2.start;
                else
                    return i1.end-i2.end;
            }
        });

        Interval pre = intervals.get(0);
        for(int i=0; i<intervals.size(); i++){
            Interval curr = intervals.get(i);
            if(curr.start>pre.end){
                result.add(pre);
                pre = curr;
            }else{
                Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
                pre = merged;
            }
        }
        result.add(pre);

        return result;
    }
}
