package _06_BFS._BFS_ALL;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _542_BFS_01_Matrix_M {
/*    Java Solution, BFS
    General idea is BFS. Some small tricks:

    At beginning, set cell value to Integer.MAX_VALUE if it is not 0.
    If newly calculated distance >= current distance, then we don't need to explore that cell again.*/
    public class Solution1 {
        public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
            int m = matrix.size();
            int n = matrix.get(0).size();

            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix.get(i).get(j) == 0) {
                        queue.offer(new int[] {i, j});
                    }
                    else {
                        matrix.get(i).set(j, Integer.MAX_VALUE);
                    }
                }
            }

            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                for (int[] d : dirs) {
                    int r = cell[0] + d[0];
                    int c = cell[1] + d[1];
                    if (r < 0 || r >= m || c < 0 || c >= n ||
                            matrix.get(r).get(c) <= matrix.get(cell[0]).get(cell[1]) + 1) continue;
                    queue.add(new int[] {r, c});
                    matrix.get(r).set(c, matrix.get(cell[0]).get(cell[1]) + 1);
                }
            }

            return matrix;
        }
    }
//    LeetCode has changed the function signature. Updated code:

    public class Solution2 {
        public int[][] updateMatrix(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        queue.offer(new int[] {i, j});
                    }
                    else {
                        matrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                for (int[] d : dirs) {
                    int r = cell[0] + d[0];
                    int c = cell[1] + d[1];
                    if (r < 0 || r >= m || c < 0 || c >= n ||
                            matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) continue;
                    queue.add(new int[] {r, c});
                    matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
                }
            }

            return matrix;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////
/*    Java 33ms solution with two sweeps in O(n)
    In the first sweep, we visit each entry in natural order and answer[i][j] = min(Integer.MAX_VALUE, min(answer[i - 1][j], answer[i][j - 1]) + 1).
    in the second sweep, we visit each entry in reverse order and answer[i][j] = min(answer[i][j], min(answer[i + 1][j], answer[i][j + 1]) + 1).*/

    public List<List<Integer>> updateMatrix3(List<List<Integer>> matrix) {
        List<List<Integer>> answer = new LinkedList();
        if(matrix.size() == 0) return answer;
        int[][] array = new int[matrix.size()][matrix.get(0).size()];
        int i = 0, j = 0;
        for(List<Integer> list : matrix) {
            for(Integer x : list) {
                if(x == 0) {
                    array[i][j] = 0;
                }
                else {
                    int left = Integer.MAX_VALUE - 1, top = Integer.MAX_VALUE - 1;
                    if(i - 1 >= 0) top = array[i - 1][j];
                    if(j - 1 >= 0) left = array[i][j - 1];
                    array[i][j] = Math.min(Integer.MAX_VALUE - 1, Math.min(top, left) + 1);
                }
                j++;
            }
            j = 0;
            i++;
        }
        for(int k = array.length - 1; k >= 0; k--) {
            for(int m = array[0].length - 1; m >= 0; m--) {
                if(array[k][m] != 0 && array[k][m] != 1) {
                    int down = Integer.MAX_VALUE - 1, right = Integer.MAX_VALUE - 1;
                    if(k + 1 < array.length) down = array[k + 1][m];
                    if(m + 1 < array[0].length) right = array[k][m + 1];
                    array[k][m] = Math.min(array[k][m], Math.min(down, right) + 1);
                }
            }
        }
        for(int[] l : array) {
            List<Integer> tmp = new LinkedList();
            for(int n : l) {
                tmp.add(n);
            }
            answer.add(tmp);
        }
        return answer;
    }
///////////////////////////////////////////////////////////////////////////////////////////////
//Java DFS solution beat %95

/*    Using DFS method.

    Assigned a large value to all the positions with value 1 and don't have 0 neighbors
    Start dfs search from positions whose value is 1*/
    public class Solution4 {
        public int[][] updateMatrix(int[][] matrix) {
            if(matrix.length==0) return matrix;

            for(int i = 0; i<matrix.length; i++)
                for(int j = 0; j<matrix[0].length; j++)
                    if(matrix[i][j]==1&&!hasNeiberZero(i, j,matrix))
                        matrix[i][j] = matrix.length+matrix[0].length+1;

            for(int i = 0; i<matrix.length; i++)
                for(int j = 0; j<matrix[0].length; j++)
                    if(matrix[i][j]==1)
                        dfs(matrix, i, j, -1);

            return matrix;
        }
        private void dfs(int[][] matrix, int x, int y, int val){
            if(x<0||y<0||y>=matrix[0].length||x>=matrix.length||matrix[x][y]<=val)
                return;

            if(val>0) matrix[x][y] = val;

            dfs(matrix, x+1, y, matrix[x][y]+1);
            dfs(matrix, x-1, y, matrix[x][y]+1);
            dfs(matrix, x, y+1, matrix[x][y]+1);
            dfs(matrix, x, y-1, matrix[x][y]+1);

        }
        private boolean hasNeiberZero(int x, int y, int[][] matrix){
            if(x>0&&matrix[x-1][y]==0) return true;
            if(x<matrix.length-1&&matrix[x+1][y]==0) return true;
            if(y>0&&matrix[x][y-1]==0) return true;
            if(y<matrix[0].length-1&&matrix[x][y+1]==0) return true;

            return false;
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////
//Easy Java DP solution
public class Solution {
    public int[][] updateMatrix5(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0) continue;
                int up = i-1 >=0?
                    (matrix[i-1][j]==Integer.MAX_VALUE?Integer.MAX_VALUE:matrix[i-1][j]+1):Integer.MAX_VALUE;
                int left = j-1>=0?
                    (matrix[i][j-1]==Integer.MAX_VALUE?Integer.MAX_VALUE:matrix[i][j-1]+1):Integer.MAX_VALUE;
                matrix[i][j] = Math.min(up,left);
            }
        }


        for(int i = matrix.length-1; i >= 0; i--){
            for(int j = matrix[0].length-1; j>=0; j--){
                if(matrix[i][j] == 0) continue;
                int down = i+1<matrix.length? matrix[i+1][j]+1 : Integer.MAX_VALUE;
                int right = j+1<matrix[0].length?matrix[i][j+1]+1:Integer.MAX_VALUE;
                matrix[i][j] = Math.min(down,Math.min(right,matrix[i][j]));
            }
        }

        return matrix;
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////



}
/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1:
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2:
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
 */