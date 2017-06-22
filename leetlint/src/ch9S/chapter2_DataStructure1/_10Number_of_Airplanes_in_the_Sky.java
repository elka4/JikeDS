package ch9S.chapter2_DataStructure1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _10Number_of_Airplanes_in_the_Sky {

}
	  

class Point_2{
	int time;
	int flag;
	
	Point_2(int t, int s){
		this.time = t;
		this.flag = s;
	}
	public static Comparator<Point_2> Point_2Comparator  = new Comparator<Point_2>(){
		public int compare(Point_2 p1, Point_2 p2){
			if(p1.time == p2.time) return p1.flag - p2.flag;
			else return p1.time - p2.time;
		}
	};
}
  
class Solution {
	
class Interval {
	int start, end;
	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
  public int countOfAirplanes(List<Interval> airplanes) { 
    List<Point_2> list = new ArrayList<>(airplanes.size()*2);
    for(Interval i : airplanes){
      list.add(new Point_2(i.start, 1));
      list.add(new Point_2(i.end, 0));
    }
    //sort with time
    Collections.sort(list,Point_2.Point_2Comparator );
    int count = 0, ans = 0;
    for(Point_2 p : list){
      if(p.flag == 1) count++;
      else count--;
      ans = Math.max(ans, count);
    }

    return ans;
  }
    
}

/*Given an interval list which are flying and landing time of 
 * the flight. How many airplanes are on the sky at most?
 

 Notice

If landing and flying happens at the same time, we consider 
landing should happen at first.

Have you met this question in a real interview? Yes
Example
For interval list

[
  [1,10],
  [2,3],
  [5,8],
  [4,7]
]
Return 3

Tags 
LintCode Copyright Array Interval
Related Problems 
Easy Merge Intervals 20 %*/