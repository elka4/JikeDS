package _05_DFS._DFS_Matrix;
import java.util.*;

//
//
public class _499_DFS_The_Maze_III_H {

    //Clear Java Accepted DFS Solution with Explanation
    public class Solution {
        int min; //min distance to hole
        String minS; //min distance's path string
        int[] hole;
        int[][] maze;
        int[][] map; //shortest distant traveling from ball to this point
        int[][] dirs = {{0,1},{-1,0},{1,0},{0,-1}}; //r, u, d, l
        public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
            this.min = Integer.MAX_VALUE;
            this.minS = null;
            this.hole = hole;
            this.maze = maze;
            this.map = new int[maze.length][maze[0].length];
            for(int i = 0; i<map.length; i++) Arrays.fill(map[i], Integer.MAX_VALUE);

            move(ball[0], ball[1], 0, "", -1);
            return (minS==null) ? "impossible" : minS;
        }

        private void move(int r, int c, int cnt, String path, int dir){//dir is a index of dirs
            if(cnt > min || cnt > map[r][c]) return; //not a shortest route for sure
            if(dir!=-1){//if not from start point
                //add path
                if(dir==0) path+='r';
                else if(dir==1) path+='u';
                else if(dir==2) path+='d';
                else path+='l';

                //roll along dir
                while(r>=0 && r<maze.length && c>=0 && c<maze[0].length && maze[r][c]==0){
                    map[r][c] = Math.min(map[r][c], cnt);
                    if(r==hole[0] && c==hole[1]){//check hole
                        if(cnt==min && path.compareTo(minS)<0){
                            minS=path;
                        }else if(cnt<min){
                            min = cnt;
                            minS = path;
                        }
                        return;
                    }
                    r += dirs[dir][0];
                    c += dirs[dir][1];
                    cnt++;
                }
                r -= dirs[dir][0];//[r,c] is wall, need to walk back 1 step
                c -= dirs[dir][1];
                cnt--;
            }

            //hit wall (or start) -> try to turn
            for(int i = 0; i<dirs.length; i++){
                if(dir == i) continue;//dont keep going
                if(dir == (3-i)) continue;//dont go back
                int newR = r + dirs[i][0];
                int newC = c + dirs[i][1];
                if(newR>=0 && newR<maze.length && newC>=0 && newC<maze[0].length && maze[newR][newC]==0){//can go
                    move(r, c, cnt, path, i);
                }
            }
        }
    }



//////////////////////////////////////////////////////////////////////////////////////
    public class Solution2 {
        int minStep;
        int m, n;
        String res;
        int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        String[] dirc = {"d", "r", "l", "u"}; // 0123
        public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
            this.m = maze.length;
            this.n = maze[0].length;
            this.minStep = Integer.MAX_VALUE;
            this.res = null;
            boolean[][] vis = new boolean[m][n];
            vis[ball[0]][ball[1]] = true;

            dfs(ball[0], ball[1], maze, hole, vis, "", 0);

            return res == null ? "impossible" : res;
        }

        private void dfs(int i, int j, int[][] maze, int[] hole, boolean[][] vis, String route, int step) {
            if (step > minStep) return;
            if (i == hole[0] && j == hole[1]) {
                if (step == minStep && route.compareTo(res) < 0) {
                    res = route;
                } else if (step < minStep) {
                    minStep = step;
                    res = route;
                }
                vis[i][j] = false;
                return;
            }

            for (int d = 0; d < 4; d++) {
                // roll to the wall
                int x = i, y = j;
                while (x + dirs[d][0] >= 0 && x + dirs[d][0] < m && y + dirs[d][1] >= 0 && y + dirs[d][1] < n
                        && maze[x + dirs[d][0]][y + dirs[d][1]] != 1) {
                    x += dirs[d][0];
                    y += dirs[d][1];
                    if (x == hole[0] && y == hole[1] || vis[x][y])  break;
                }
                if (!vis[x][y] && maze[x][y] == 0) {
                    vis[x][y] = true;
                    dfs(x, y, maze, hole, vis, route + dirc[d], step + Math.abs(x - i) + Math.abs(y - j));
                    vis[x][y] = false;
                }
            }
        }
    }

//////////////////////////////////////////////////////////////////////////////////////
}
/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
