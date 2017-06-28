/** 573. Build Post Office II
 * Hard*/


class node {
public:

    node(){}
    node(int xx, int yy, int dist) {
        x = xx;
        y = yy;
        dis = dist;
    }
    int x, y, dis;
};

class Solution {
public:
    /**
     * @param grid a 2D grid
     * @return an integer
     */
    int dx[4] = {1,0,-1,0};
    int dy[4] = {0,1,0,-1};

    bool valid(int nx, int ny, int n, int m, vector<vector<int>>& grid, vector<vector<bool>>& flag) {
        if(0 <= nx && nx < n && 0 <= ny && ny < m) {
            if(grid[nx][ny] == 0 && flag[nx][ny] == false) {
                return  true;
            }
        }
        return false;
    }

    void bfs(node now, vector<vector<int>>& grid, vector<vector<int>>& dis, vector<vector<int>>& visit_num, int n, int m) {
        queue<node> q;
        q.push(now);
        vector<vector<bool> > flag(n, vector<bool>(m)) ;


        while(!q.empty()) {
            now = q.front();
            q.pop();
            visit_num[now.x][now.y]++;

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (valid(nx, ny, n, m, grid, flag)) {
                    dis[nx][ny] = dis[nx][ny] + now.dis + 1;
                    flag[nx][ny] = true;
                    q.push(node(nx, ny, now.dis+1));
                }
            }
        }
    }
    int shortestDistance(vector<vector<int>>& grid) {
        // Write your code here
        if(grid.size() == 0)
            return 0;
        int n = grid.size();
        int m = grid[0].size();
        vector<vector<int>> dis(n, vector<int>(m, 0));
        vector<vector<int> > visit_num(n, vector<int>(m)) ;

        int house_num = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid[i].size(); j++)
            if (grid[i][j] == 1) {
                house_num++;
                bfs(node(i,j,0), grid, dis, visit_num, n, m);
            }
        }

        int ans = INT_MAX;
        for (int i = 0; i < grid.size(); i++) {
            for(int j = 0; j < grid[i].size(); j++)
            if (grid[i][j] == 0 && visit_num[i][j] == house_num){
                ans = min(ans, dis[i][j]);
            }
        }

        return ans == INT_MAX ? -1 : ans;
    }
};