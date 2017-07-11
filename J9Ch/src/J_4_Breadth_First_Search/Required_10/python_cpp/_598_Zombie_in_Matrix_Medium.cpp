/** 598. Zombie in Matrix
 * Medium*/


// version 1
class Position {
public:
    int x, y;
    Position(int _x, int _y):x(_x), y(_y) {}
};

class Solution {
public:
    /**
     * @param grid  a 2D integer grid
     * @return an integer
     */
    int zombie(vector<vector<int>>& grid) {
        // Write your code here
        queue<Position> q;
        int n = grid.size();
        if (n == 0)
            return 0;
        int m = grid[0].size();
        if (m == 0)
            return 0;

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (grid[i][j] == 1)
                    q.push(Position(i, j));

        int d[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int days = 0;
        while (!q.empty()) {
            days ++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Position head = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = head.x + d[k][0];
                    int y = head.y + d[k][1];
                    if (x >= 0 && x < n && y >=0 && y < m && grid[x][y] == 0) {
                        grid[x][y] = 1;
                        q.push(Position(x, y));
                    }
                }
            }
        }

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (grid[i][j] == 0)
                    return -1;

        return days - 1;
    }
};

// version 2
class Solution {
public:
    /**
     * @param grid  a 2D integer grid
     * @return an integer
     */

    struct tnode {
        int x, y;
        int step;
    };
    int dx[4] = {-1, 1, 0, 0};
    int dy[4] = {0, 0, -1, 1};

    int zombie(vector<vector<int> >& grid) {
        // Write your code here

        int sum_zombie = 0, sum_wall = 0;
        int n = grid.size(), m = grid[0].size();
        queue<tnode> qzombie;
        while(!qzombie.empty()) {
            qzombie.pop();
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    tnode p = {i, j, 0};
                    qzombie.push(p);
                    sum_zombie++;
                }
                if(grid[i][j] == 2) {
                    sum_wall++;
                }
            }
        }

        int step;
        while(!qzombie.empty()) {
            tnode p = qzombie.front();
            qzombie.pop();

            for (int i = 0; i < 4; ++i) {
                int x = p.x + dx[i];
                if (x < 0 || x >= n) {
                    continue;
                }

                int y = p.y + dy[i];
                if (y < 0 || y >= m) {
                    continue;
                }

                if (grid[x][y] == 0) {
                    grid[x][y] = 1;
                    tnode new_zombie = {x, y, p.step+1};
                    sum_zombie++;
                    qzombie.push(new_zombie);
                }
            }

            if(qzombie.empty()) {
                step = p.step;
            }
        }

        if(sum_wall + sum_zombie != n *m) {
            return -1;
        }
        else {
            return step;
        }
    }
};