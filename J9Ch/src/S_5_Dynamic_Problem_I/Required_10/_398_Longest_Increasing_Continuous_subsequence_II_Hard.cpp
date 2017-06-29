
const int dirx[4] = {0, 0, 1, -1};
const int diry[4] = {1, -1, 0, 0};
class Solution {
public:
    /**
     * @param A an integer matrix
     * @return  an integer
     */
    int n, m;
    int dis[200][200];
    int maxx;
    void dfs(int x,int y,int depth, vector<vector<int>>& a) {
        int nowdepth = depth;
            if ((++nowdepth) > dis[x][y]) {
                dis[x][y] = nowdepth;
                if (dis[x][y] > maxx) maxx = dis[x][y];
        }
        else
            return;
        int nowx,nowy;
        for (int i=0;i < 4; ++i) {
            nowx = x + dirx[i];
            nowy = y + diry[i];
            if (nowx < 0 || nowx >= n || nowy < 0 || nowy >= m) continue;
            if (a[nowx][nowy] <= a[x][y]) continue;
            dfs(nowx, nowy, nowdepth, a);
        }
    }
    int longestIncreasingContinuousSubsequenceII(vector<vector<int>>& A) {
        // Write your code here
        maxx = 0;
        n = A.size();
        if (n == 0)
            return 0;
        m = A[0].size();
        if (m == 0)
            return 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                dis[i][j] =0;

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (dis[i][j] == 0)
                    dfs(i, j , 0, A);
        return maxx;
    }
};