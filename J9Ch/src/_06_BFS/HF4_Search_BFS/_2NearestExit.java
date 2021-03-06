package _06_BFS.HF4_Search_BFS;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
能力维度:
2. 代码基础功力
3. 基础数据结构/算法
4. 逻辑思维/算法优化能力
 */

//Nearest Exit
public class _2NearestExit {

    // version: 高频题班
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
                if (0 <= nx && nx < n && 0 <= ny && ny < m && rooms[nx][ny] == INF) {
                    qx.offer(nx);
                    qy.offer(ny);
                    rooms[nx][ny] = rooms[cx][cy] + 1;
                }
            }
        }
    }



    int[][] rooms;

    @Test
    public void test01(){
        rooms = new int[4][4];
        rooms[0][0] = INF;
        rooms[0][1] = -1;
        rooms[0][2] = 0;
        rooms[0][3] = INF;

        rooms[1][0] = INF;
        rooms[1][1] = INF;
        rooms[1][2] = INF;
        rooms[1][3] = -1;

        rooms[2][0] = INF;
        rooms[2][1] = -1;
        rooms[2][2] = INF;
        rooms[2][3] = -1;

        rooms[3][0] = 0;
        rooms[3][1] = -1;
        rooms[3][2] = INF;
        rooms[3][3] = INF;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(rooms[i][j] + " ");
            }
            System.out.println();
        }

        wallsAndGates(rooms);

        System.out.println();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(rooms[i][j] + " ");
            }
            System.out.println();
        }

    }

//-------------------------------------------------------------------------------

}
/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example
Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
return the result:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */