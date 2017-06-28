/** 611 Knight Shortest Path
 * Medium*/


/**
 * Definition for a point.
 * struct Point {
 *     int x, y;
 *     Point() : x(0), y(0) {}
 *     Point(int a, int b) : x(a), y(b) {}
 * };
 */
class Solution {
public:
    /**
     * @param grid a chessboard included 0 (false) and 1 (true)
     * @param source, destination a point
     * @return the shortest path
     */
    int shortestPath(vector<vector<bool>>& grid, Point& source, Point& destination) {
        // Write your code here
        int n = grid.size();
        int m = grid[0].size();

        vector<vector<int>> record(n, vector<int>(m, INT_MAX));

        record[source.x][source.y] = 0;

        vector<vector<int>> d = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};

        queue<Point> Q;
        Q.push(source);
        while (!Q.empty()) {
            Point head = Q.front(); Q.pop();
            for (int k = 0; k < 8; ++k) {
                int x = head.x + d[k][0];
                int y = head.y + d[k][1];
                if (x >=0 && x < n && y >= 0 && y < m && !grid[x][y] &&
                    record[head.x][head.y] + 1 < record[x][y]) {
                    record[x][y] = record[head.x][head.y] + 1;
                    Q.push(Point(x, y));
                }
            }
        }
        if (record[destination.x][destination.y] == INT_MAX)
            return -1;
        return record[destination.x][destination.y];
    }
};