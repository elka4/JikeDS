package _05_DFS._DFS_Other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _542_DFS_01_Matrix_M {

/*
General idea is BFS. Some small tricks:

At beginning, set cell value to Integer.MAX_VALUE if it is not 0.
If newly calculated distance >= current distance, then we don't need to explore that cell again.

 */
public class Solution {
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


//    General idea is BFS. Some small tricks:
//
//    At beginning, set cell value to Integer.MAX_VALUE if it is not 0.
//    If newly calculated distance >= current distance, then we don't need to explore that cell again.
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



    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
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



    public List<List<Integer>> updateMatrix2(List<List<Integer>> matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        int m = matrix.size(), n = matrix.get(0).size();
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                res[i][j] = (matrix.get(i).get(j) == 0) ? 0 : m+n;
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++){
                int left =(j-1 >= 0) ? res[i][j - 1]: res[i][j];
                int top = (i-1 >= 0) ? res[i - 1][j]: res[i][j];
                res[i][j] = Math.min(res[i][j], Math.min(top, left) + 1);
            }
        for(int i = m-1; i >=0 ; i--)
            for(int j = n-1; j >= 0; j--){
                int right = (j+1 < n) ? res[i][j+1]: res[i][j];
                int down = (i+1 < m) ? res[i+1][j]: res[i][j];
                res[i][j] = Math.min(res[i][j], Math.min(down, right) + 1);
            }
        for(int i = 0; i < m; i++){
            List<Integer> cur = new ArrayList<>();
            for(int j = 0; j < n; j++)
                cur.add(res[i][j]);
            ans.add(cur);
        }

        return ans;
    }


//    We can do it in-place without extra space.

    public class Solution3 {
        public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
            if (matrix ==null || matrix.size() <= 0 || matrix.get(0).size() <= 0) return matrix;
            for (int i=0; i<matrix.size(); i++) {
                for (int j=0; j<matrix.get(i).size(); j++) {
                    if (matrix.get(i).get(j) > 0) {
                        matrix.get(i).set(j, 10000);
                    }
                }
            }
            for (int i=0; i<matrix.size(); i++) {
                for (int j=0; j<matrix.get(i).size(); j++) {
                    int cur = matrix.get(i).get(j);
                    int min = i-1 >=0 ? Math.min( cur, matrix.get(i-1).get(j)) : cur;
                    min = j-1 >=0 ? Math.min( min, matrix.get(i).get(j-1)) : min;
                    matrix.get(i).set(j, Math.min(cur,min+1));
                }
            }
            for (int i=matrix.size()-1; i>=0; i--) {
                for (int j=matrix.get(i).size()-1; j>=0; j--) {
                    int cur = matrix.get(i).get(j);
                    int min = i+1 < matrix.size() ? Math.min( cur, matrix.get(i+1).get(j)) : cur;
                    min = j+1 < matrix.get(i).size() ? Math.min( min, matrix.get(i).get(j+1)) : min;
                    matrix.get(i).set(j, Math.min(cur,min+1));
                }
            }
            return matrix;
        }
    }

//----------------------------------------------------------------------------




//----------------------------------------------------------------------------






}
/*

 */