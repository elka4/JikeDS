

/**
 * Definition for a point.
 * struct Point {
 *     int x;
 *     int y;
 *     Point() : x(0), y(0) {}
 *     Point(int a, int b) : x(a), y(b) {}
 * };
 */
class Solution {
public:
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    int find(int x) {
        if (f[x] == x)
            return x;
        return f[x] = find(f[x]);
    }

    bool merge(int x, int y) {
        if (f[x] == -1 || f[y] == -1)
            return false;

        x = find(x);
        y = find(y);
        if (x != y) {
            f[x] = y;
            return true;
        } else
            return false;
    }

    bool inside(int x, int y, int n, int m) {
        return (x >= 0 && y >=0 && x < n && y < m);
    }

    int f[100000];
    vector<int> numIslands2(int n, int m, vector<Point>& operators) {
        // Write your code here
        const int d[4][2] = {0, 1, 0, -1, 1, 0, -1, 0};
        int area = 0;
        int cnt = operators.size();
        vector<int> ret;
        for (int i = 0; i < n * m; ++i) f[i] = -1;
        for (int i = 0; i < cnt; ++i) {
            int point = operators[i].x * m + operators[i].y;
            if (f[point] == -1) {
                area += 1;
                f[point] = point;
            }
            for (int k = 0; k < 4; ++k) {
                int x = operators[i].x + d[k][0];
                int y = operators[i].y + d[k][1];

                if (inside(x, y, n, m)) {
                    if (merge(x * m + y, point))
                        area -= 1;
                }
            }
            ret.push_back(area);
        }
        return ret;
    }
};