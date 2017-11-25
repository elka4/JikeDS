package DP.DP2;

//• 坐标型动态规划


import org.junit.Test;

/*
Up[i][j]表示（i，j）格放一个炸弹向上能炸死的敌人数
Up[i][j] = Up[i - 1][j], 如果（i，j）格是空地
Up[i][j] = Up[i - 1][j] + 1, 如果（i，j）格是敌人
Up[i][j] = 0, 如果（i，j）格是墙

初始条件：第0行的Up值和格子内容有关
Up[i][j] = 0, 如果（0， j）格不是敌人
Up[i][j] = 1, 如果 (0， j）格是敌人

-----------------------------------------------------------------------------------------------

 */
//Bomb Enemy
/*
首先，这个题的关键在于熟悉对于2D Array的操作，就是对于2D Array index x, y的操作。

其次，用两个 2D Array。一个做计算，一个记录最终结果。

四个方向从

m-1
4
3
2
1
0 1 2 3 4 n-1
 */

//  361. Bomb Enemy
//  https://leetcode.com/problems/bomb-enemy/description/
//
public class _6BombEnemy {
    //Simple DP solution in Java  这个和maxKilledEnemies2是一样的，不过是分离出来row和col的method

    //    only need to store one killed enemies value for a row and an array of each column;

    //if current grid value is W, this means previous stored value becomes invalid, you need to recalculate.

    //_5MinimumPathSum里单行DP也是类似思想
    public int maxKilledEnemies(char[][] grid) {
        if(grid == null || grid.length == 0 ||  grid[0].length == 0) return 0;
        int max = 0;
        int row = 0;
        int[] col = new int[grid[0].length];

        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length;j++){

                if(grid[i][j] == 'W') continue;

                if(j == 0 || grid[i][j-1] == 'W'){
                    row = killedEnemiesRow(grid, i, j);
                }

                if(i == 0 || grid[i-1][j] == 'W'){
                    col[j] = killedEnemiesCol(grid,i,j);
                }

                if(grid[i][j] == '0'){
                    max = (row + col[j] > max) ? row + col[j] : max;
                }
            }
        }
        return max;
    }

    //calculate killed enemies for row i from column j
    private int killedEnemiesRow(char[][] grid, int i, int j){
        int num = 0;
        while(j <= grid[0].length-1 && grid[i][j] != 'W'){
            if(grid[i][j] == 'E') num++;
            j++;
        }
        return num;
    }
    //calculate killed enemies for  column j from row i
    private int killedEnemiesCol(char[][] grid, int i, int j){
        int num = 0;
        while(i <= grid.length -1 && grid[i][j] != 'W'){
            if(grid[i][j] == 'E') num++;
            i++;
        }
        return num;
    }
//------------------------------------------------------------------------------
    //这个性能极好
    public int maxKilledEnemies33(char[][] grid) {
        // Write your code here
        int m = grid.length;
        int n = m > 0 ? grid[0].length : 0;

        int result = 0, rows = 0;
        int[] cols = new int[n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j == 0 || grid[i][j-1] == 'W') {//行
                    rows = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; ++k){
                        if (grid[i][k] == 'E')
                            rows += 1;
                    }
                }

                if (i == 0 || grid[i-1][j] == 'W') {//列
                    cols[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; ++k){
                        if (grid[k][j] == 'E')
                            cols[j] += 1;
                    }
                }

                if (grid[i][j] == '0' && rows + cols[j] > result){
                    result = rows + cols[j];
                }
            }
        }
        return result;
    }


    public int maxKilledEnemies3(char[][] grid) {
        // Write your code here
        int m = grid.length;
        int n = m > 0 ? grid[0].length : 0;

        int result = 0, rows = 0;
        int[] cols = new int[n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j == 0 || grid[i][j-1] == 'W') {
                    rows = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; ++k)
                        if (grid[i][k] == 'E')
                            rows += 1;
                }
                if (i == 0 || grid[i-1][j] == 'W') {
                    cols[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; ++k)
                        if (grid[k][j] == 'E')
                            cols[j] += 1;
                }

                if (grid[i][j] == '0' && rows + cols[j] > result)
                    result = rows + cols[j];
            }
        }
        return result;
    }

//------------------------------------------------------------------------------
    // 9Ch DP
    public int maxKilledEnemies2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int [][] f  = new int[m][n];
        int [][] res = new int[m][n];
//        int i, j;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // how many enemies can be killed by a bomb put at grid(i, j)
                res[i][j] = 0;
            }
        }

        // Up 也就是单方向上看连续为空格的数量
        //i从小向大变化，所以大的是从更小的来：f[i][j] += f[i - 1][j];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') {//和PathSumII一样，就是遇到obstacle的情况，先处理
                    f[i][j]  = 0;
                } else {
                    f[i][j] = 0;            //初始化， 而不可以不要？肯定不行。
                    if (grid[i][j] == 'E') {//两步里的第一步，遇敌就杀1个
                        f[i][j] = 1;
                    }
                    if (i > 0) {            //如果不是边缘状况，就加上下方的那个
                        f[i][j] += f[i - 1][j];
                    }
                }
                res[i][j] += f[i][j];

            }
        }

        // Down
        //i从最大向最小变化，所以小的是从更大的来：f[i][j] += f[i + 1][j];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'W') {
                    f[i][j]  = 0;
                } else {
                    f[i][j] = 0;
                    if (grid[i][j] == 'E') {
                        f[i][j] = 1;
                    }
                    if (i + 1 < m) {
                        f[i][j] += f[i + 1][j];
                    }
                }
                res[i][j] += f[i][j];
            }
        }

        // left
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'W') {
                    f[i][j]  = 0;
                } else {
                    f[i][j] = 0;
                    if (grid[i][j] == 'E') {
                        f[i][j] = 1;
                    }
                    if (j - 1 >= 0) {
                        f[i][j] += f[i][j - 1];
                    }
                }
                res[i][j] += f[i][j];

            }
        }

        // right
        for (int i = 0; i < m; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 'W') {
                    f[i][j]  = 0;
                } else {
                    f[i][j] = 0;
                    if (grid[i][j] == 'E') {
                        f[i][j] = 1;
                    }
                    if (j + 1 < n) {
                        f[i][j] += f[i][j + 1];
                    }
                }
                res[i][j] += f[i][j];

            }
        }

        int result = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '0') {
                    if (res[i][j] > result) {
                        result = res[i][j];
                    }
                }
            }
        }

        return result;
    }

    @Test
    public void test01(){
        char[][] grid = {{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}};
        System.out.println(maxKilledEnemies(grid));
    }


//------------------------------------------------------------------------------
    // 方法二
    /**
     * @param grid Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies4(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] up = new int[m][n];
        int[][] down = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        int i, j, t;

        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                up[i][j] = 0;
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        up[i][j] = 1;
                    }

                    if (i - 1 >= 0) {
                        up[i][j] += up[i-1][j];
                    }
                }
            }
        }

        for (i = m - 1; i >= 0; --i) {
            for (j = 0; j < n; ++j) {
                down[i][j] = 0;
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        down[i][j] = 1;
                    }

                    if (i + 1 < m) {
                        down[i][j] += down[i+1][j];
                    }
                }
            }
        }

        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                left[i][j] = 0;
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        left[i][j] = 1;
                    }

                    if (j - 1 >= 0) {
                        left[i][j] += left[i][j-1];
                    }
                }
            }
        }

        for (i = 0; i < m; ++i) {
            for (j = n - 1; j >= 0; --j) {
                right[i][j] = 0;
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        right[i][j] = 1;
                    }

                    if (j + 1 < n) {
                        right[i][j] += right[i][j+1];
                    }
                }
            }
        }

        int res = 0;
        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                if (grid[i][j] == '0') {
                    t = up[i][j] + down[i][j] + left[i][j] + right[i][j];
                    if (t > res) {
                        res = t;
                    }
                }
            }
        }

        return res;
    }
//------------------------------------------------------------------------------

}
/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)

 */