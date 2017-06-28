/** 612 K Closest Points
 *
 * Medium*/


/**
 * Definition for a point.
 * struct Point {
 *     int x;
 *     int y;
 *     Point() : x(0), y(0) {}
 *     Point(int a, int b) : x(a), y(b) {}
 * };
 */
Point global_origin;
long long getDistance(Point a, Point b) {
    return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
}

struct compare {
    bool operator()(const Point &a, const Point &b) const {
        int diff = getDistance(a, global_origin) - getDistance(b, global_origin);
        if (diff == 0)
            diff = a.x - b.x;
        if (diff == 0)
            diff = a.y - b.y;
        return diff < 0;
    }
};

class Solution {
public:
    /**
     * @param points a list of points
     * @param origin a point
     * @param k an integer
     * @return the k closest points
     */
    vector<Point> kClosest(vector<Point>& points, Point& origin, int k) {
        // Write your code here
        global_origin = Point(origin.x, origin.y);
        priority_queue<Point, vector<Point>, compare> pq;

        int n = points.size();
        for (int i = 0; i < n  ; i++) {
            Point p = points[i];
            pq.push(p);
            if (pq.size() > k)
                pq.pop();
        }

        vector<Point> ret;
        while (!pq.empty()) {
            Point p = pq.top();
            ret.push_back(p);
            pq.pop();
        }

        reverse(ret.begin(), ret.end());
        return ret;
    }
};