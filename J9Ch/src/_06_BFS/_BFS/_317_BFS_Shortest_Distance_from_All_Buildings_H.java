package _06_BFS._BFS;
import java.util.*;import lib.*;
import org.junit.Test;


public class _317_BFS_Shortest_Distance_from_All_Buildings_H {

/*    Java solution with explanation and time complexity analysis
    Inspired by previous solution.
    The main idea is the following:

    Traverse the matrix. For each building, use BFS to compute the shortest distance from each '0' to
this building. After we do this for all the buildings, we can get the sum of shortest distance
    from every '0' to all reachable buildings. This value is stored
    in 'distance[][]'. For example, if grid[2][2] == 0, distance[2][2] is the sum of shortest distance from this block to all reachable buildings.
    Time complexity: O(number of 1)O(number of 0) ~ O(m^2n^2)

    We also count how many building each '0' can be reached. It is stored in reach[][]. This can be done during the BFS. We also need to count how many total buildings are there in the matrix, which is stored in 'buildingNum'.

    Finally, we can traverse the distance[][] matrix to get the point having shortest distance to all buildings. O(m*n)

    The total time complexity will be O(m^2*n^2), which is quite high!. Please let me know if I did the analysis wrong or you have better solution.*/

    public class Solution {
        public int shortestDistance(int[][] grid) {
            if (grid == null || grid[0].length == 0) return 0;
            final int[] shift = new int[]{0, 1, 0, -1, 0};

            int row = grid.length, col = grid[0].length;
            int[][] distance = new int[row][col];
            int[][] reach = new int[row][col];
            int buildingNum = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1) {
                        buildingNum++;
                        Queue<int[]> myQueue = new LinkedList<int[]>();
                        myQueue.offer(new int[]{i, j});

                        boolean[][] isVisited = new boolean[row][col];
                        int level = 1;

                        while (!myQueue.isEmpty()) {
                            int qSize = myQueue.size();
                            for (int q = 0; q < qSize; q++) {
                                int[] curr = myQueue.poll();

                                for (int k = 0; k < 4; k++) {
                                    int nextRow = curr[0] + shift[k];
                                    int nextCol = curr[1] + shift[k + 1];

                                    if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                                            && grid[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                                        //The shortest distance from [nextRow][nextCol] to thic building
                                        // is 'level'.
                                        distance[nextRow][nextCol] += level;
                                        reach[nextRow][nextCol]++;

                                        isVisited[nextRow][nextCol] = true;
                                        myQueue.offer(new int[]{nextRow, nextCol});
                                    }
                                }
                            }
                            level++;
                        }
                    }
                }
            }

            int shortest = Integer.MAX_VALUE;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
                        shortest = Math.min(shortest, distance[i][j]);
                    }
                }
            }

            return shortest == Integer.MAX_VALUE ? -1 : shortest;


        }
    }

//------------------------------------------------------------------------------
/*Share a Java implement
    Short version: BFS from every building, calculate the distances and find the minimum distance in the end.

    Key optimization : we do not go into a land, if it is not accessible by at least one of previous buildings.

    For a long explanation see here in my blog.

    It runs in 13 ms.

    It is the same idea as stefan's c++ solution. I didn't see it until now. I did this myself.

    Also one may want to make a copy of grid if it is not suppose to be modified.*/

    class Solution2 {


        int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

        public int shortestDistance(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] dist = new int[m][n];
            // Initialize building list and accessibility matrix `grid`
            List<Tuple> buildings = new ArrayList<>();
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 1)
                        buildings.add(new Tuple(i, j, 0));
                    grid[i][j] = -grid[i][j];
                }
            // BFS from every building
            for (int k = 0; k < buildings.size(); ++k)
                bfs(buildings.get(k), k, dist, grid, m, n);
            // Find the minimum distance
            int ans = -1;
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j)
                    if (grid[i][j] == buildings.size() && (ans < 0 || dist[i][j] < ans))
                        ans = dist[i][j];
            return ans;
        }

        public void bfs(Tuple root, int k, int[][] dist, int[][] grid, int m, int n) {
            Queue<Tuple> q = new ArrayDeque<>();
            q.add(root);
            while (!q.isEmpty()) {
                Tuple b = q.poll();
                dist[b.y][b.x] += b.dist;
                for (int i = 0; i < 4; ++i) {
                    int x = b.x + dx[i], y = b.y + dy[i];
                    if (y >= 0 && x >= 0 && y < m && x < n && grid[y][x] == k) {
                        grid[y][x] = k + 1;
                        q.add(new Tuple(y, x, b.dist + 1));
                    }
                }
            }
        }

        class Tuple {
            public int y;
            public int x;
            public int dist;

            public Tuple(int y, int x, int dist) {
                this.y = y;
                this.x = x;
                this.dist = dist;
            }
        }
    }

    //------------------------------------------------------------------------------
//    My BFS Java Solution

    public class Solution4 {
        public int shortestDistance(int[][] grid) {
            int row = grid.length;
            if (row == 0) {
                return -1;
            }
            int col = grid[0].length;
            int[][] record1 = new int[row][col]; // visited num
            int[][] record2 = new int[row][col]; // distance
            int num1 = 0;
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (grid[r][c] == 1) {
                        num1++;
                        boolean[][] visited = new boolean[row][col];
                        Queue<int[]> queue = new LinkedList<int[]>();
                        queue.offer(new int[]{r, c});
                        int dist = 0;
                        while (!queue.isEmpty()) {
                            int size = queue.size();
                            for (int i = 0; i < size; i++) {
                                int[] node = queue.poll();
                                int x = node[0];
                                int y = node[1];
                                record2[x][y] += dist;
                                record1[x][y]++;
                                if (x > 0 && grid[x - 1][y] == 0 && !visited[x - 1][y]) {
                                    queue.offer(new int[]{x - 1, y});
                                    visited[x - 1][y] = true;
                                }
                                if (x + 1 < row && grid[x + 1][y] == 0 && !visited[x + 1][y]) {
                                    queue.offer(new int[]{x + 1, y});
                                    visited[x + 1][y] = true;
                                }
                                if (y > 0 && grid[x][y - 1] == 0 && !visited[x][y - 1]) {
                                    queue.offer(new int[]{x, y - 1});
                                    visited[x][y - 1] = true;
                                }
                                if (y + 1 < col && grid[x][y + 1] == 0 && !visited[x][y + 1]) {
                                    queue.offer(new int[]{x, y + 1});
                                    visited[x][y + 1] = true;
                                }
                            }
                            dist++;
                        }
                    }
                }
            }
            int result = Integer.MAX_VALUE;
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (grid[r][c] == 0 && record1[r][c] == num1 && record2[r][c] < result) {
                        result = record2[r][c];
                    }
                }
            }
            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }
}
//------------------------------------------------------------------------------

/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.


 */