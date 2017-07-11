package J_8_Data_Structure.Required_10;
import java.util.*;import lib.ListNode;
/** 612 K Closest Points
 *
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _612_K_Closest_Points {
    public class Point {
        public int x, y;
        public Point() { x = 0; y = 0; }
        public Point(int a, int b) { x = a; y = b; }
    }

    public class Solution {
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
                if (pq.size() > k)
                    pq.poll();
            }

            k = pq.size();
            Point[] ret = new Point[k];
            while (!pq.isEmpty())
                ret[--k] = pq.poll();
            return ret;
        }

        private int getDistance(Point a, Point b) {
            return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
        }
    }
}
