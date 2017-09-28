package Bit106.L06;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Bob on 4/22/17.
 *
 * Given a 2d grid map of '1's (land) and '0's (water),
 * count the number of islands. An island is surrounded
 * by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four
 * edges of the grid are all surrounded by water.
 *
 * Example 1:
 *      11110
 *      11010
 *      11000
 *      00000
 *      Answer: 1
 *
 * Example 2:
 *      11000
 *      11000
 *      00100
 *      00011
 *      Answer: 3
 */
public class BFSDemo {
    private boolean[][] visited;
    private int numRow;
    private int numCol;

    public int numIslands(char[][] grid) {
        // Validate input.
        if (grid == null || grid.length < 1
                || grid[0].length < 1) {
            return 0;
        }

        // Initiation.
        int total = 0;
        numRow = grid.length;
        numCol = grid[0].length;
        visited = new boolean[numRow][numCol];

        // Search for entry point.
        for (int i = 0; i < numRow; ++i) {
            for (int j = 0; j < numCol; ++j) {
                if (isValid(grid, i, j)) {
                    ++total;
                    // Start BFS.
                    bfs(grid, i, j);
                }
            }
        }

        return total;
    }

    class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private boolean isValid(char[][] grid, int row, int col) {
        return !visited[row][col] && grid[row][col] == '1';
    }

    private void bfs(char[][] grid, int row, int col) {
        visited[row][col] = true;

        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(row, col));
        Pair curIdx;

        while (!queue.isEmpty()) {
            curIdx = queue.poll();
            int i = curIdx.row;
            int j = curIdx.col;
            if (i > 0 && isValid(grid, i - 1, j)) {
                queue.offer(new Pair(i - 1, j));
                visited[i - 1][j] = true;
            }
            if (i < numRow - 1 && isValid(grid, i + 1, j)) {
                queue.offer(new Pair(i + 1, j));
                visited[i + 1][j] = true;
            }
            if (j > 0 && isValid(grid, i, j - 1)) {
                queue.offer(new Pair(i, j - 1));
                visited[i][j - 1] = true;
            }
            if (j < numCol - 1 && isValid(grid, i, j + 1)) {
                queue.offer(new Pair(i, j + 1));
                visited[i][j + 1] = true;
            }
        }
    }
}










