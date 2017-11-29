package HF.HF0_OA9;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

//  Heap
//  K Closest Points
//  http://www.lintcode.com/en/problem/k-closest-points/
//
public class _2KClosestPoints {
//----------------------------------------------------------------------------

    class Point {
        public int x, y;
        public Point() { x = 0; y = 0; }
        public Point(int a, int b) { x = a; y = b; }

        @Override
        public String toString() {
            return "[" + x + " " + y + "]";
        }
    }

//----------------------------------------------------------------------------

    /**
     * @param points a list of points
     * @param origin a point
     * @param k an integer
     * @return the k closest points
     */
    private Point global_origin = null;

    public Point[] kClosest(Point[] points, Point origin, int k) {
        // Write your code here
        global_origin = origin;
        PriorityQueue<Point> pq = new PriorityQueue<Point> (k, new Comparator<Point> () {
            @Override
            public int compare(Point a, Point b) {
                int diff = getDistance(b, global_origin) - getDistance(a, global_origin);
                if (diff == 0)
                    diff = b.x - a.x;
                if (diff == 0)
                    diff = b.y - a.y;
                return diff;
            }
        });

        for (int i = 0; i < points.length; i++) {
            pq.offer(points[i]);
            if (pq.size() > k){             //用这一步来维护PQ的size
                pq.poll();
            }
        }

        k = pq.size();
        Point[] ret = new Point[k];

        while (!pq.isEmpty()) {
            ret[--k] = pq.poll();
        }

        return ret;
    }

    private int getDistance(Point a, Point b) {

        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

//------------------------------------------------------------------------------

    Point[] points;
    Point origin;
    int k;
    @Before
    public void bef(){
        points = new Point[5];
        points[0] = new Point(4,6);
        points[1] = new Point(4,7);
        points[2] = new Point(4,4);
        points[3] = new Point(2,5);
        points[4] = new Point(1,1);

        origin = new Point(0,0);
        k = 3;
    }

    @Test
    public void test01(){
        Point[] result = kClosest(points, origin, k);
        for ( Point p:result) {
            System.out.print(p);
        }
    }

//----------------------------------------------------------------------------

}
/*
Given some points and a point origin in two dimensional space, find k points out of the some points which are nearest to origin.
Return these points sorted by distance, if they are same with distance, sorted by x-axis, otherwise sorted by y-axis.

Have you met this question in a real interview? Yes
Example
Given points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
return [[1,1],[2,5],[4,4]]
 */


/*
K个最近的点

给定一些 points 和一个 origin，从 points 中找到 k 个离 origin 最近的点。
按照距离由小到大返回。如果两个点有相同距离，则按照x值来排序；若x值也相同，就再按照y值排序。

Have you met this question in a real interview? Yes
Example
给出 points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
返回 [[1,1],[2,5],[4,4]]
 */