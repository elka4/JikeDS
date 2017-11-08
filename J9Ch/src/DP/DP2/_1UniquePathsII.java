package DP.DP2;

//• 坐标型动态规划

/*
-----------------------------------------------------------------------------------------------
LintCode 115: Unique Paths II


-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

f[i][j] = f[i-1][j] + f[i][j-1]

f[i][j]:
f[i-1][j]:
f[i][j-1]:
-----------------------------------------------------------------------------------------------
• 状态f[i][j]  表示从左上角有多少种方式走到格子 (i, j)

• 坐标型动态规划： 数组下标[i][j]即坐标(i, j)
-----------------------------------------------------------------------------------------------
• f[i][j] =  机器人有多少种方式从左上角（0，0）走到(i,j)
• 如果左上角(0, 0)格或者右下角(m-1, n-1)格有障碍，直接输出0
• 如果(i, j)格有障碍，f[i][j] = 0，表示机器人不能到达次格（0种方式）
• 初始条件： f[0][0] = 1
-----------------------------------------------------------------------------------------------

f[i][j] = :
0,                      如果(i, j)格有障碍
1,                      i=0 && j=0
f[i-1][j]，             如果j=1，即第一列
f[i][j-1]，             如果i=1，即第一行
f[i-1][j] + f[i][j-1]， 其他
-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */

//  Unique PathsII
public class _1UniquePathsII {

    // 这个是目前看最好的解法， 从下面复制来
    public int uniquePathsWithObstaclesXX(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] s = new int[2][n];
        //s[0][0] = obstacleGrid[0][0]==0 ? 1:0;
        // if(s[0][0] == 0) return 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(obstacleGrid[i][j] == 1){        //要先对obstacle做处理，适合任何情况，除了起始点。但是起始点在下面处理了。
                    s[i%2][j] = 0;
                } else if (i == 0 && j == 0) {      //起始点检查和初始化
                    if (obstacleGrid[0][0] == 0){
                        s[i][j] = 1;
                    }
                }
                else if(i==0){                      //初始化首行
                     s[i%2][j] = s[i%2][j-1];//j>0排除了起始点 //j>0 is always true
                }
                else if(j==0){                      //初始化首列
                     s[i%2][j] = s[(i-1)%2][j];//i>0排除了起始点   //i>0 is always true
                }
                else s[i%2][j] = s[(i-1)%2][j] + s[i%2][j-1];
            }
        }
        return s[(m-1)%2][n-1];
    }

    //讲以上算法转为now， old
    public int uniquePathsWithObstaclesXXX(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] s = new int[2][n];

        int old = 0;int now = 0;
        for(int i=0;i<m;i++){

            old = now;
            now = 1 - old;
            for(int j=0;j<n;j++){
                // s[now][j] = 0;                   //不需要。那为什么下面的算法需要？
                if(obstacleGrid[i][j] == 1){        //要先对obstacle做处理，适合任何情况，除了起始点。但是起始点在下面处理了。
                    s[now][j] = 0;
                } else if (i == 0 && j == 0) {      //起始点检查和初始化
                    if (obstacleGrid[0][0] == 0){
                        s[now][j] = 1;
                    }
                }
                else if(i==0){                      //初始化首行
                    s[now][j] = s[now][j-1];//j>0排除了起始点
                }
                else if(j==0){                      //初始化首列
                    s[now][j] = s[old][j];//i>0排除了起始点
                }
                else s[now][j] = s[old][j] + s[now][j-1];
            }
        }
        return s[now][n-1];
    }

    //从下面copy来的
    public int uniquePathsWithObstacles222(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] paths = new int[2][n];
        int old;                        //最简单做法这两个都初始化为0
        int now = 0;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }


        for (int i = 0; i < m; i++) {
            old = now;                  //这里是重点
            now = 1 - now;
            for (int j = 0; j < n; j++) {
                paths[now][j] = 0;              //这里不能省略！否则[[0,0],[1,1],[0,0]]会错报2, why????!!!
                if (obstacleGrid[i][j] == 1) { //一定要先判断obstacle
                    paths[now][j] = 0;
                }
                else {
                    if (i == 0 && j == 0) {
//                        paths[now][j] = 1;
                        if (obstacleGrid[i][i] == 0) paths[now][j] = 1;
                    }else{
                        if (i > 0) {
                            paths[now][j] += paths[old][j]; //加左边
                        }
                        if (j > 0) {
                            paths[now][j] += paths[now][j-1];//加右边
                        }
                    }
                }
            }
        }

        return paths[now][n - 1];
    }

//////////////////////////////////////////////////////////////////////////////////////////////////
    //最标准写法
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] paths = new int[n][m];

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0] != 1) {
                paths[i][0] = 1;
            } else {
                break;//一旦遭遇obstacle，后面的点全都不能到达，为0。默认值为0。所以可以直接break
            }
        }

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[0][i] != 1) {
                paths[0][i] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] != 1) {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                } else {
                    paths[i][j] = 0;
                }
            }
        }

        return paths[n - 1][m - 1];
    }

//////////////////////////////////////////////////////////////////////////////////////////////////
    public int uniquePathsWithObstaclesX(int[][] obstacleGrid) {
//        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
//            return 0;
//        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] paths = new int[2][n];
        int old;                        //最简单做法这两个都初始化为0
        int now = 0;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }


        for (int i = 0; i < m; i++) {
            old = now;                  //这里是重点
            now = 1 - now;
            for (int j = 0; j < n; j++) {
                paths[now][j] = 0;              //这里不能省略！否则[[0,0],[1,1],[0,0]]会错报2
                if (obstacleGrid[i][j] == 1) { //一定要先判断obstacle
                    paths[now][j] = 0;
                }
                else {
                    if (i == 0 && j == 0) {
                        paths[now][j] = 1;
                    }else{
                        if (i == 0 && j == 0) {
                            paths[now][j] = 1;
                        }
                        if (i > 0) {
                            paths[now][j] += paths[old][j]; //加左边
                        }
                        if (j > 0) {
                            paths[now][j] += paths[now][j-1];//加右边
                        }
                    }
                }
            }
        }

        return paths[now][n - 1];
    }
//                        paths[now][j] = paths[old][j] + paths[now][j - 1];

//////////////////////////////////////////////////////////////////////////////////////////////////
    //Simple Java DP solution
    public class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] s = new int[m][n];
            s[0][0] = obstacleGrid[0][0]==0 ? 1:0;
            if(s[0][0] == 0) return 0;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(obstacleGrid[i][j] == 1) s[i][j] = 0;
                    else if(i==0){
                        if(j>0) s[i][j] = s[i][j-1];
                    }
                    else if(j==0){
                        if(i>0) s[i][j] = s[i-1][j];
                    }
                    else s[i][j] = s[i-1][j] + s[i][j-1];
                }
            }
            return s[m-1][n-1];
        }


        public int uniquePathsWithObstaclesX(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] s = new int[2][n];
            s[0][0] = obstacleGrid[0][0]==0 ? 1:0;
            if(s[0][0] == 0) return 0;

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(obstacleGrid[i][j] == 1) s[i%2][j] = 0;
                    else if(i==0){
                        if(j>0) s[i%2][j] = s[i%2][j-1];
                    }
                    else if(j==0){
                        if(i>0) s[i%2][j] = s[(i-1)%2][j];
                    }
                    else s[i%2][j] = s[(i-1)%2][j] + s[i%2][j-1];
                }
            }
            return s[(m-1)%2][n-1];
        }

        // 这个是目前看最好的解法
        public int uniquePathsWithObstaclesXX(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] s = new int[2][n];
            //s[0][0] = obstacleGrid[0][0]==0 ? 1:0;
            // if(s[0][0] == 0) return 0;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(obstacleGrid[i][j] == 1) s[i%2][j] = 0;
                    else if (i == 0 && j == 0) {
                        if (obstacleGrid[0][0] == 0){
                            s[i][j] = 1;
                        }else {
                            return 0;
                        }
                    }
                    else if(i==0){
                        if(j>0) s[i%2][j] = s[i%2][j-1];    //j>0 is always true, why?
                    }
                    else if(j==0){
                        if(i>0) s[i%2][j] = s[(i-1)%2][j];  //i>0 is always true, why?
                    }
                    else s[i%2][j] = s[(i-1)%2][j] + s[i%2][j-1];
                }
            }
            return s[(m-1)%2][n-1];
        }
    }

//////////////////////////////////////////////////////////////////////////
    //  Short JAVA solution   这方法会破坏原数组
    public int uniquePathsWithObstacles4(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
//////////////////////////////////////////////////////////////////////////

    // 方法二
    /**
     * @param A: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles2(int[][] A) {
        int m = A.length;
        if (m == 0) {
            return 0;
        }
        int n = A[0].length;
        if (n == 0) {
            return 0;
        }

        if (A[0][0] == 1 || A[m-1][n-1] == 1) {
            return 0;
        }

        int[][] f = new int[2][n];
        int i, j, old, now;
        now = 0;

        for (i = 0; i < m; ++i) {
            old = now;
            now = 1 - now;
            for (j = 0; j < n; ++j) {
                f[now][j] = 0;          //必须清零。没想清楚到底为什么必须清零，和第一个相比。
                if (A[i][j] == 1) {
                    f[now][j] = 0;
                }
                else {
                    if (i == 0 && j == 0) {
                        f[now][j] = 1;
                    }
                    if (i > 0) {
                        f[now][j] += f[old][j];
                    }
                    if (j > 0) {
                        f[now][j] += f[now][j-1];
                    }
                }
            }
        }

        return f[now][n-1];
    }


//////////////////////////////////////////////////////////////////////////

    // 9Ch DP
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if(m == 0){
            return 0;
        }

        int n = obstacleGrid[0].length;
        if (n == 0) {
            return 0;
        }
        int[][] f = new int[m][n];

        int i, j;
        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                //遇到障碍
              if (obstacleGrid[i][j] == 1){
                  f[i][j] = 0;
              }
              else{
                  //初始化：左上角起点（0，0）
//                  if (i == 0) f[i][j] = f[i][j - 1];
//                  if (j == 0) f[i][j] = f[i - 1][j];
                  if (i == 0 && j == 0){
                      f[i][j] = 1;
                  } else {
                      //初始化：每个点都初始化为0                   这行没必要因为默认为0
                      f[i][j] = 0;
                      //排除i == 0的情况， 也就是 第一行的情况
                      if(i - 1 >= 0){
                          f[i][j] += f[i - 1][j];
                      }
                      //排除j == 0的情况， 也就是 第一列的情况
                      if(j - 1 >= 0){
                          f[i][j] += f[i][j - 1];
                      }
                  }
              }

            }
        }
        return f[m - 1][n - 1];
    }
//////////////////////////////////////////////////////////////////////////

}

/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 Notice

m and n will be at most 100.

Have you met this question in a real interview? Yes
Example
For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.
 */