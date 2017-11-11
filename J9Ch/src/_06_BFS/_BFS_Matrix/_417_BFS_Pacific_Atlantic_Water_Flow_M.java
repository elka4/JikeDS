package _06_BFS._BFS_Matrix;
import java.util.*;

public class _417_BFS_Pacific_Atlantic_Water_Flow_M {

//Java BFS & DFS from Ocean
/*
Two Queue and add all the Pacific border to one queue; Atlantic border to another queue.
Keep a visited matrix for each queue. In the end, add the cell visited by two queue to the result.
BFS: Water flood from ocean to the cell. Since water can only flow from high/equal cell to low cell, add the neighboor cell with height larger or equal to current cell to the queue and mark as visited.
 */
public class Solution1 {
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

//DFS version:
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
/////////////////////////////////////////////////////////////////////////////////////////////
//Java 28ms BFS solution using one queue
/*I use two bits to save the information of pacific ocean and atlantic ocean.
            00: cannot reach any ocean
01: can reach pacific ocean
10: can reach atlantic ocean
11: can reach two oceans

    Step 1: Update the status of border cells and put them into the queue
    Step 2: Iterate the queue and explore the four directions. We only put a new cell into the queue if :

    row and col index are valid
    the height of the new cell is larger or equals to the height of the current cell
    the new cell can benifit from the current cell (check status)*/
    public class Solution3 {
        public List<int[]> pacificAtlantic(int[][] matrix) {
            List<int[]> res = new ArrayList<>();
            int m = matrix.length;
            if (m == 0) return res;
            int n = matrix[0].length;
            int[][] state = new int[m][n];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                state[i][0] |= 1;
                if (i == m - 1 || n == 1) state[i][0] |= 2;
                if (state[i][0] == 3) res.add(new int[]{i, 0});
                q.add(new int[]{i, 0});
                if (n > 1) {
                    state[i][n - 1] |= 2;
                    if (i == 0) state[i][n - 1] |= 1;
                    if (state[i][n - 1] == 3) res.add(new int[]{i, n - 1});
                    q.add(new int[]{i, n - 1});
                }
            }
            for (int j = 1; j < n - 1; j++) {
                state[0][j] |= 1;
                if (m == 1) state[0][j] |= 2;
                if (state[0][j] == 3) res.add(new int[]{0, j});
                q.add(new int[]{0, j});
                if (m > 1) {
                    state[m - 1][j] |= 2;
                    if (state[m - 1][j] == 3) res.add(new int[]{m - 1, j});
                    q.add(new int[]{m - 1, j});
                }
            }
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            while (!q.isEmpty()) {
                int[] cell = q.poll();
                for (int[] dir : dirs) {
                    int row = cell[0] + dir[0];
                    int col = cell[1] + dir[1];
                    if (row < 0 || col < 0 || row == m || col == n || matrix[row][col] < matrix[cell[0]][cell[1]] || ((state[cell[0]][cell[1]] | state[row][col]) == state[row][col])) continue;
                    state[row][col] |= state[cell[0]][cell[1]];
                    if (state[row][col] == 3) res.add(new int[]{row, col});
                    q.add(new int[]{row, col});
                }
            }
            return res;
        }
    }


/////////////////////////////////////////////////////////////////////////////////////////////
//Simple java dfs solution
class Solution4{
//    Build two sets for Pacific and Atlantic. The result is the intersection of them.

    private int[][] direction = new int[][]{{1, 0},{0, 1},{-1, 0},{0, -1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        Set<Integer> pacific = new HashSet<>();
        Set<Integer> atlantic = new HashSet<>();
        for (int i = 0; i < matrix[0].length; i++) {
            dfs(matrix, 0, i, pacific);
            dfs(matrix, matrix.length - 1, i, atlantic);
        }
        for (int i = 0; i < matrix.length; i++) {
            dfs(matrix, i, 0, pacific);
            dfs(matrix, i, matrix[0].length - 1, atlantic);
        }

        for (int i: pacific) {
            if (atlantic.contains(i)) {
                result.add(decode(i, matrix));
            }
        }
        return result;
    }

    private void dfs(int[][] matrix, int i, int j, Set<Integer> result) {
        if (!result.add(encode(i, j, matrix))) return;
        for (int[] dir: direction) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] >= matrix[i][j]) {
                dfs(matrix, x, y, result);
            }
        }
    }

    private int[] decode(int i, int[][] matrix) {
        return new int[]{i / matrix[0].length, i % matrix[0].length};
    }

    private int encode(int i, int j, int[][] matrix) {
        return i * matrix[0].length + j;
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////
//JAVA 17ms Solution, Simple and Clear, similar to Number of Islands's idea
/*
The idea is as following:

First, we can separate Pacific and Atlantic ocean into two, they share the same idea. The only difference is the starting position.

Second, we think this problem in the opposite way: all the valid positions must have at least one path to connect to the ocean, so we start from the ocean to find out all the paths.

1, 1, 1, 1
1, 0, 0, 0
1, 0, 0, 0
1, 0, 0, 0

Then we create a new boolean[][] matrix like above, all the beaches is marked as True (1) in the beginning, which means they can connect to the ocean, then we explore from the beach to find out all the paths. The idea is the same for Pacific and Atlantic.

The last step is to use && to find positions satisfy both Pacific and Atlantic.

Here comes the solution:
 */

class Solution5{
    int[] dx = {-1,0,0,1};
    int[] dy = {0,1,-1,0};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        boolean[][] pacific = new boolean[matrix.length][matrix[0].length];
        boolean[][] atlantic = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            pacific[i][0] = true;
            atlantic[i][matrix[0].length-1] = true;
        }
        for (int j = 0; j < matrix[0].length; j++){
            pacific[0][j] = true;
            atlantic[matrix.length-1][j] = true;
        }
        for (int i = 0; i < matrix.length; i++){
            explore(pacific, matrix, i, 0);
            explore(atlantic, matrix, i, matrix[0].length-1);
        }
        for (int j = 0; j < matrix[0].length; j++){
            explore(pacific, matrix, 0, j);
            explore(atlantic, matrix, matrix.length-1, j);
        }
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (pacific[i][j] && atlantic[i][j] == true)
                    res.add(new int[]{i,j});
            }
        }
        return res;

    }
    private void explore(boolean[][] grid, int[][] matrix, int i, int j){
        grid[i][j] = true;
        for (int d = 0; d < dx.length; d++){
            if (i+dy[d] < grid.length && i+dy[d] >= 0 &&
                    j + dx[d] < grid[0].length && j + dx[d] >= 0 &&
                    grid[i+dy[d]][j+dx[d]] == false && matrix[i+dy[d]][j+dx[d]] >= matrix[i][j])
                explore(grid, matrix, i+dy[d], j+dx[d]);
        }
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////////



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