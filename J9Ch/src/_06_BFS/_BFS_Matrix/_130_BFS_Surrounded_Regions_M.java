package _06_BFS._BFS_Matrix;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class _130_BFS_Surrounded_Regions_M {
    /*
    The idea is to first find all 'O's on the edge, and do BFS from these 'O's. Keep adding 'O's into the queue in the BFS, and mark it as '+'. Since these 'O's are found by doing BFS from the 'O's on the edge, it means they are connected to the edge 'O's. so they are the 'O's that will remain as 'O' in the result.

After BFS, there are some 'O's can not be reached, they are the 'O's that need to be turned as 'X'.
     */
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
    public class Solution {
        public void solve(char[][] board) {
            if (board.length == 0) return;

            int rowN = board.length;
            int colN = board[0].length;
            Queue<Point> queue = new LinkedList<Point>();

            //get all 'O's on the edge first
            for (int r = 0; r< rowN; r++){
                if (board[r][0] == 'O') {
                    board[r][0] = '+';
                    queue.add(new Point(r, 0));
                }
                if (board[r][colN-1] == 'O') {
                    board[r][colN-1] = '+';
                    queue.add(new Point(r, colN-1));
                }
            }

            for (int c = 0; c< colN; c++){
                if (board[0][c] == 'O') {
                    board[0][c] = '+';
                    queue.add(new Point(0, c));
                }
                if (board[rowN-1][c] == 'O') {
                    board[rowN-1][c] = '+';
                    queue.add(new Point(rowN-1, c));
                }
            }


            //BFS for the 'O's, and mark it as '+'
            while (!queue.isEmpty()){
                Point p = queue.poll();
                int row = p.x;
                int col = p.y;
                if (row - 1 >= 0 && board[row-1][col] == 'O') {board[row-1][col] = '+'; queue.add(new Point(row-1, col));}
                if (row + 1 < rowN && board[row+1][col] == 'O') {board[row+1][col] = '+'; queue.add(new Point(row+1, col));}
                if (col - 1 >= 0 && board[row][col - 1] == 'O') {board[row][col-1] = '+'; queue.add(new Point(row, col-1));}
                if (col + 1 < colN && board[row][col + 1] == 'O') {board[row][col+1] = '+'; queue.add(new Point(row, col+1));}
            }


            //turn all '+' to 'O', and 'O' to 'X'
            for (int i = 0; i<rowN; i++){
                for (int j=0; j<colN; j++){
                    if (board[i][j] == 'O') board[i][j] = 'X';
                    if (board[i][j] == '+') board[i][j] = 'O';
                }
            }


        }
    }
//----------------------------------------------------------------------------

    /*
    dfs, bfs, union-find都可以做, 基本思路是
    从在四个边的各个'O'开始搜索, 连在一起的'O'就是不能被包围的, 其余的点都应该设为'X'.

    如果选择bfs, 可以把所有边上的所有'O'一起入队, 同时进行bfs
*/
    public class Solution2 {
        public void solve(char[][] board) {
            if (board == null || board.length == 0 || board[0].length == 0) return;
            int height = board.length, width = board[0].length;
            Deque<int[]> queue = new ArrayDeque<>();  // int[2] as [row, col]
            for (int i = 0; i < width; ++i) {
                if (board[0][i] == 'O') {
                    queue.addLast(new int[] {0, i});
                    board[0][i] = 'V';  // mark as visited
                }
                if (board[height - 1][i] == 'O') {
                    queue.addLast(new int[] {height - 1, i});
                    board[height - 1][i] = 'V';
                }
            }
            for (int i = 1; i < height - 1; ++i) {
                if (board[i][0] == 'O') {
                    queue.addLast(new int[] {i, 0});
                    board[i][0] = 'V';
                }
                if (board[i][width - 1] == 'O') {
                    queue.addLast(new int[] {i, width - 1});
                    board[i][width - 1] = 'V';
                }
            }
            while (!queue.isEmpty()) {
                int[] cur = queue.removeFirst();
                if (cur[0] - 1 >= 0 && board[cur[0] - 1][cur[1]] == 'O') {  // up
                    queue.addLast(new int[] {cur[0] - 1, cur[1]});
                    board[cur[0] - 1][cur[1]] = 'V';
                }
                if (cur[0] + 1 < height && board[cur[0] + 1][cur[1]] == 'O') {  // down
                    queue.addLast(new int[] {cur[0] + 1, cur[1]});
                    board[cur[0] + 1][cur[1]] = 'V';
                }
                if (cur[1] - 1 >= 0 && board[cur[0]][cur[1] - 1] == 'O') {  // left
                    queue.addLast(new int[] {cur[0], cur[1] - 1});
                    board[cur[0]][cur[1] - 1] = 'V';
                }
                if (cur[1] + 1 < width && board[cur[0]][cur[1] + 1] == 'O') {  // right
                    queue.addLast(new int[] {cur[0], cur[1] + 1});
                    board[cur[0]][cur[1] + 1] = 'V';
                }
            }
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    if (board[i][j] == 'O') board[i][j] = 'X';
                    else if (board[i][j] == 'V') board[i][j] = 'O';
                }
            }
        }
    }




}
