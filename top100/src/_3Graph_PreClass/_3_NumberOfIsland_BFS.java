package _3Graph_PreClass;

import java.util.Deque;
import java.util.LinkedList;

public class _3_NumberOfIsland_BFS {

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        if(grid[0] == null || grid[0].length == 0)
            return 0;

        int count = 0;
        int row = grid.length;
        int col = grid[0].length;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {
                    removeBFS(grid, i, j, row, col);
                    count++;
                }
            }
        }
        return count;
    }

    private void removeBFS (char[][] grid, int x, int y, int m, int n) {

        Deque<Integer> queue = new LinkedList<Integer>();
        queue.offer(x * n + y);
        grid[x][y] = '0';

        while (!queue.isEmpty()) {

            int cur = queue.poll();
            int curX = cur / n;
            int curY = cur % n;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if (isValid(i, j, curX, curY, m, n)
                            && grid[curX + i][curY + j] == '1') {

                        queue.offer((curX + i) * n + (curY + j));
                        grid[curX + i][curY + j] = '0';
                    }
                }
            }
        }
    }

    private boolean isValid(int di, int dj, int i, int j, int row, int col) {
        return Math.abs(di) != Math.abs(dj) && i + di >= 0 &&
                i + di < row && j + dj >= 0 && j + dj < col;
    }

}
