package _05_DFS._DFS_Matrix;
import java.util.*;

//  417. Pacific Atlantic Water Flow
//  https://leetcode.com/problems/pacific-atlantic-water-flow/description/
public class _417_DFS_Pacific_Atlantic_Water_Flow_M {

//Java BFS & DFS from Ocean
public class Solution {
    int[][]dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        //One visited map for each ocean
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        for(int i=0; i<n; i++){ //Vertical border
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, m-1});
            pacific[i][0] = true;
            atlantic[i][m-1] = true;
        }
        for(int i=0; i<m; i++){ //Horizontal border
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{n-1, i});
            pacific[0][i] = true;
            atlantic[n-1][i] = true;
        }
        bfs(matrix, pQueue, pacific);
        bfs(matrix, aQueue, atlantic);
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(pacific[i][j] && atlantic[i][j])
                    res.add(new int[]{i,j});
            }
        }
        return res;
    }
    public void bfs(int[][]matrix, Queue<int[]> queue, boolean[][]visited){
        int n = matrix.length, m = matrix[0].length;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int[] d:dir){
                int x = cur[0]+d[0];
                int y = cur[1]+d[1];
                if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]){
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
}

//------------------------------------------------------------------------------
    //    DFS version:
    public class Solution2 {
        public List<int[]> pacificAtlantic(int[][] matrix) {
            List<int[]> res = new LinkedList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
                return res;
            }
            int n = matrix.length, m = matrix[0].length;
            boolean[][]pacific = new boolean[n][m];
            boolean[][]atlantic = new boolean[n][m];
            for(int i=0; i<n; i++){
                dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
                dfs(matrix, atlantic, Integer.MIN_VALUE, i, m-1);
            }
            for(int i=0; i<m; i++){
                dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
                dfs(matrix, atlantic, Integer.MIN_VALUE, n-1, i);
            }
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    if (pacific[i][j] && atlantic[i][j])
                        res.add(new int[] {i, j});
            return res;
        }

        int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        public void dfs(int[][]matrix, boolean[][]visited, int height, int x, int y){
            int n = matrix.length, m = matrix[0].length;
            if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < height)
                return;
            visited[x][y] = true;
            for(int[]d:dir){
                dfs(matrix, visited, matrix[x][y], x+d[0], y+d[1]);
            }
        }
    }

//------------------------------------------------------------------------------
public class Solution3 {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<int[]>();
        if(matrix.length == 0 || matrix[0].length == 0) return result;
        boolean[][] pacific = new boolean[matrix.length][matrix[0].length];  // the pacific boolean table
        boolean[][] atlantic = new boolean[matrix.length][matrix[0].length]; // the atlantic booean table
        //initially, all the top and left cells are flooded with pacific water
        //and all the right and bottom cells are flooded with atlantic water
        for(int i = 0; i < matrix.length; i++){
            pacific[i][0] = true;
            atlantic[i][matrix[0].length-1] = true;
        }
        for(int i = 0; i < matrix[0].length; i++){
            pacific[0][i] = true;
            atlantic[matrix.length-1][i] = true;
        }
        //we go around the matrix and try to flood the matrix from 4 side.
        for(int i = 0; i < matrix.length; i++){
            boolean[][] pacificVisited = new boolean[matrix.length][matrix[0].length];
            boolean[][] atlanticVisited = new boolean[matrix.length][matrix[0].length];
            water(pacific, pacificVisited, matrix, i,0);
            water(atlantic, atlanticVisited, matrix, i, matrix[0].length - 1);
        }
        for(int i = 0; i < matrix[0].length; i++){
            boolean[][] pacificVisited = new boolean[matrix.length][matrix[0].length];
            boolean[][] atlanticVisited = new boolean[matrix.length][matrix[0].length];
            water(pacific, pacificVisited, matrix, 0,i);
            water(atlantic, atlanticVisited, matrix, matrix.length - 1, i);
        }
        //check the shared points among 2 tables
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    int[] element = {i,j};
                    result.add(element);
                }
            }
        }
        return result;
    }
    //the flood function
    private void water(boolean[][] wet, boolean[][] visited, int[][] matrix, int i , int j){
        wet[i][j] = true;
        visited[i][j] = true;
        int[] x = {0,0,1,-1};
        int[] y = {1,-1,0,0};
        for(int k = 0; k < 4; k++){
            if(i+y[k] >= 0 && i+y[k] < matrix.length && j+x[k] >= 0 && j+x[k] < matrix[0].length
                    && !visited[i+y[k]][j+x[k]] && matrix[i+y[k]][j+x[k]] >= matrix[i][j]){
                water(wet, visited, matrix, i+y[k], j+x[k]);
            }
        }
    }
}

//----------------------------------------------------------------------------
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