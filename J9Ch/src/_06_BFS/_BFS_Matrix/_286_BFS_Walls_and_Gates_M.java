package _06_BFS._BFS_Matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//
public class _286_BFS_Walls_and_Gates_M {
/*    Approach #2 (Breadth-first Search) [Accepted]

    Instead of searching from an empty room to the gates, how about searching the other way round? In other words, we initiate breadth-first search (BFS) from all gates at the same time. Since BFS guarantees that we search all rooms of distance d before searching rooms of distance d + 1, the distance to an empty room must be the shortest.*/

    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final List<int[]> DIRECTIONS = Arrays.asList(
            new int[] { 1,  0},
            new int[] {-1,  0},
            new int[] { 0,  1},
            new int[] { 0, -1}
    );

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) {
                    q.add(new int[] { row, col });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int[] direction : DIRECTIONS) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                q.add(new int[] { r, c });
            }
        }
    }
/*    Complexity analysis

    Time complexity : O(mn)O(mn).

    If you are having difficulty to derive the time complexity, start simple.

    Let us start with the case with only one gate. The breadth-first search takes at most m \times nm×n steps to reach all rooms, therefore the time complexity is O(mn)O(mn). But what if you are doing breadth-first search from kk gates?

    Once we set a room's distance, we are basically marking it as visited, which means each room is visited at most once. Therefore, the time complexity does not depend on the number of gates and is O(mn)O(mn).

    Space complexity : O(mn)O(mn). The space complexity depends on the queue's size. We insert at most m \times nm×n points into the queue.*/


    //-------------------------------------------------------------------------------
    // 9Ch
    // version: 高频题班
    public class Jiuzhang {
        /**
         * @param rooms m x n 2D grid
         * @return nothing
         */
        static final int INF = 2147483647;
        int n, m;

        public void wallsAndGates(int[][] rooms) {
            // Write your code here
            n = rooms.length;
            if (n == 0) {
                return;
            }
            m = rooms[0].length;

            int dx[] = {0, 1, 0, -1};
            int dy[] = {1, 0, -1, 0};

            Queue<Integer> qx = new LinkedList<>();
            Queue<Integer> qy = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (rooms[i][j] == 0) {
                        qx.offer(i);
                        qy.offer(j);
                    }
                }
            }

            while (!qx.isEmpty()) {
                int cx = qx.poll();
                int cy = qy.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    if (0 <= nx && nx < n && 0 <= ny && ny < m
                            && rooms[nx][ny] == INF) {
                        qx.offer(nx);
                        qy.offer(ny);
                        rooms[nx][ny] = rooms[cx][cy] + 1;
                    }
                }
            }
        }

    }

}
/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */

/*
您将获得一个使用这三个可能值初始化的 m×n 2D 网格。
-1 - 墙壁或障碍物。
0 - 门。
INF - Infinity是一个空房间。我们使用值 2 ^ 31 - 1 = 2147483647 来表示INF，您可以假设到门的距离小于 2147483647。
在代表每个空房间的网格中填入到距离最近门的距离。如果不可能到达门口，则应填入 INF。

您在真实的面试中是否遇到过这个题？ Yes
样例
给定 2D 网格：

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
返回结果：

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */