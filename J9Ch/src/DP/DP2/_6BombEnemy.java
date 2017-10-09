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


 */
//Bomb Enemy
public class _6BombEnemy {

    // 9Ch DP
    public int maxKilledEnemies(char[][] grid) {
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

        // Up
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') {
                    f[i][j]  = 0;
                } else {
                    f[i][j] = 0;
                    if (grid[i][j] == 'E') {
                        f[i][j] = 1;
                    }
                    if (i > 0) {
                        f[i][j] += f[i - 1][j];
                    }
                }
                res[i][j] += f[i][j];

            }
        }

        // Down
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

////////////////////////////////////////////////////////////////////////
    /**
     * @param grid Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies1(char[][] grid) {
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

////////////////////////////////////////////////////////////////////////////


    // 方法二
    /**
     * @param grid Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies2(char[][] grid) {
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
////////////////////////////////////////////////////////////////////////////

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