package _06_BFS._BFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _529_BFS_Minesweeper_M {
/*
    Java Solution, DFS + BFS
    This is a typical Search problem, either by using DFS or BFS. Search rules:

    If click on a mine ('M'), mark it as 'X', stop further search.
    If click on an empty cell ('E'), depends on how many surrounding mine:
            2.1 Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.
    DFS solution.
*/

    public class Solution {
        public char[][] updateBoard(char[][] board, int[] click) {
            int m = board.length, n = board[0].length;
            int row = click[0], col = click[1];

            if (board[row][col] == 'M') { // Mine
                board[row][col] = 'X';
            }
            else { // Empty
                // Get number of mines first.
                int count = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = row + i, c = col + j;
                        if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                        if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                    }
                }

                if (count > 0) { // If it is not a 'B', stop further DFS.
                    board[row][col] = (char)(count + '0');
                }
                else { // Continue DFS to adjacent cells.
                    board[row][col] = 'B';
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i == 0 && j == 0) continue;
                            int r = row + i, c = col + j;
                            if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                            if (board[r][c] == 'E') updateBoard(board, new int[] {r, c});
                        }
                    }
                }
            }

            return board;
        }
    }
//    BFS solution. As you can see the basic logic is almost the same as DFS. Only added a queue to facilitate BFS.

    public class Solution2 {
        public char[][] updateBoard(char[][] board, int[] click) {
            int m = board.length, n = board[0].length;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(click);

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int row = cell[0], col = cell[1];

                if (board[row][col] == 'M') { // Mine
                    board[row][col] = 'X';
                }
                else { // Empty
                    // Get number of mines first.
                    int count = 0;
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i == 0 && j == 0) continue;
                            int r = row + i, c = col + j;
                            if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                            if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                        }
                    }

                    if (count > 0) { // If it is not a 'B', stop further BFS.
                        board[row][col] = (char)(count + '0');
                    }
                    else { // Continue BFS to adjacent cells.
                        board[row][col] = 'B';
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (i == 0 && j == 0) continue;
                                int r = row + i, c = col + j;
                                if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                                if (board[r][c] == 'E') {
                                    queue.add(new int[] {r, c});
                                    board[r][c] = 'B'; // Avoid to be added again.
                                }
                            }
                        }
                    }
                }
            }

            return board;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////

//    Straight forward Java solution
    public class Solution3 {
        public char[][] updateBoard(char[][] board, int[] click) {
            int x = click[0], y = click[1];
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
                return board;
            }

            dfs(board, x, y);
            return board;
        }

        int[] dx = {-1, 0, 1, -1, 1, 0, 1, -1};
        int[] dy = {-1, 1, 1, 0, -1, -1, 0, 1};
        private void dfs(char[][] board, int x, int y) {
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E')  return;

            int num = getNumsOfBombs(board, x, y);

            if (num == 0) {
                board[x][y] = 'B';
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    dfs(board, nx, ny);
                }
            } else {
                board[x][y] = (char)('0' + num);
            }

        }

        private int getNumsOfBombs(char[][] board, int x, int y) {
            int num = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nx = x + i, ny = y + j;
                    if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length)    continue;
                    if (board[nx][ny] == 'M' || board[nx][ny] == 'X') {
                        num++;
                    }
                }
            }
            return num;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////
    //Java naive solution with explanation
    /*
    Make sure you know the rule of updating the board.

When clicking on the unrevealed mine, just update the current cell to 'X'.
When clicking on the cell with mines nearby (8 neighbors including diagonals), just update the count of mines in the neighborhood.
When clicking on the cell with 0 mine nearby, all the 8 neighbors also need to be checked and updated. And since once a cell is visited, its character will be changed for sure, the board will record the visited cells itself so no worry about revisiting.
ps: I know the format is somewhat stupid lol.
     */
    class Solution4{
        public char[][] updateBoard(char[][] board, int[] click) {
            int x = click[0];
            int y = click[1];
            if(board[x][y] == 'M') board[x][y] = 'X';
            else if(countmines(board,x,y)>0) board[x][y] = (char)(countmines(board,x,y) + '0');
            else update(board,x,y);
            return board;
        }
        private void update(char[][] board, int i, int j){
            if(i<0||i>=board.length||j<0||j>=board[0].length) return;
            if(board[i][j]=='E'){
                if(countmines(board,i,j)==0) {
                    board[i][j] = 'B';
                    update(board,i,j-1);
                    update(board,i-1,j);
                    update(board,i,j+1);
                    update(board,i+1,j);
                    update(board,i-1,j-1);
                    update(board,i+1,j+1);
                    update(board,i+1,j-1);
                    update(board,i-1,j+1);
                }
                else{
                    board[i][j] = (char)(countmines(board,i,j) + '0');
                }
            }
        }

        private int countmines(char[][] board,int i, int j){ // just count mines in the neighborhood.
            int count = 0;
            if(i-1>=0&&board[i-1][j]=='M')count++;
            if(i+1<board.length&&board[i+1][j]=='M')count++;
            if(j-1>=0&&board[i][j-1]=='M')count++;
            if(j+1<board[0].length&&board[i][j+1]=='M')count++;
            if(i-1>=0&&j-1>=0&&board[i-1][j-1]=='M')count++;
            if(i+1<board.length&&j+1<board[0].length&&board[i+1][j+1]=='M') count++;
            if(i-1>=0&&j+1<board[0].length&&board[i-1][j+1]=='M') count++;
            if(i+1<board.length&&j-1>=0&&board[i+1][j-1]=='M') count++;
            return count;
        }
    }
//    Similar approach, but with precalculated mines (could be useful in case of multiple clicks, as in a real game) and the neighbors are generated via 2 loops:

    public class Solution5 {
        public char[][] updateBoard(char[][] board, int[] click) {
            int y = click[0], x = click[1];
            if (isMine(board, y, x)) board[y][x] = 'X';
            else reveal(board, getInfo(board), y, x);
            return board;
        }
        private int[][] getInfo(char[][] board) {
            int[][] info = new int[board.length][board[0].length];
            for (int y = 0; y < info.length; y++)
                for (int x = 0; x < info[y].length; x++)
                    if (isMine(board, y, x))
                        mark(info, board, y, x);
            return info;
        }
        private void reveal(char[][] board, int[][] info, int y, int x) {
            if (isEmpty(board, y, x)) {
                if (info[y][x] == 0) {
                    board[y][x] = 'B';
                    for (int dy : new int[]{-1, 0, 1})
                        for (int dx : new int[]{-1, 0, 1})
                            reveal(board, info, y + dy, x + dx);
                } else board[y][x] = (char) ('0' + info[y][x]);
            }
        }
        private boolean isMine(char[][] board, int y, int x)  { return isValid(board, y, x) && board[y][x] == 'M'; }
        private boolean isEmpty(char[][] board, int y, int x) { return isValid(board, y, x) && board[y][x] == 'E'; }
        private void mark(int[][] info, char[][] board, int y, int x) {
            for (int dy : new int[]{-1, 0, 1})
                for (int dx : new int[]{-1, 0, 1}) {
                    int newY = y + dy, newX = x + dx;
                    if (isValid(board, newY, newX))
                        info[newY][newX]++;
                }
        }
        private boolean isValid(char[][] board, int y, int x) {
            return y >= 0 && y < board.length && x >= 0 && x < board[y].length; }
    }


//////////////////////////////////////////////////////////////////////////////////////////

//Easy BFS Java code
public class Solution6 {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        Queue<int[]> q = new LinkedList<>();

        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        } else {
            board[click[0]][click[1]] = 'B';
        }

        int[] dx = {1, 1, 0, -1, -1, -1,  0, 1};
        int[] dy = {0, 1, 1,  1,  0, -1, -1, -1};

        q.offer(click);

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            int count = 0;

            for(int k = 0; k < 8; k++) {
                int i = cur[0] + dx[k], j = cur[1] + dy[k];
                if(i < 0 || i >= m || j < 0 || j >= n) continue;
                if(board[i][j] == 'M') {
                    count++;
                }
            }

            if(count != 0) {
                board[cur[0]][cur[1]] = (char)('0' + count);
            } else {
                for(int k = 0; k < 8; k++) {
                    int i = cur[0] + dx[k], j = cur[1] + dy[k];

                    if(i < 0 || i >= m || j < 0 || j >= n) continue;
                    if(board[i][j] == 'E') {
                        board[i][j] = 'B';
                        q.offer(new int[] {i, j});
                    }
                }
            }
        }

        return board;
    }
}


//////////////////////////////////////////////////////////////////////////////////////////

//Java DFS Solution, easy and straightforward

class Solution7{
    public char[][] updateBoard(char[][] board, int[] click) {
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        update(board, click[0], click[1], dx, dy);
        return board;
    }

    private void update(char[][] board, int i, int j, int[] dx, int[] dy) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'B') return;

        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return;
        }

        int count = countAdjacentMines(board, i, j, dx, dy);
        if (count != 0) {
            board[i][j] = (char) (count + '0');
            return;
        }

        board[i][j] = 'B';
        for (int k = 0; k < 8; k++) {
            update(board, i + dx[k], j + dy[k], dx, dy);
        }
    }

    private int countAdjacentMines(char[][] board, int i, int j, int[] dx, int[] dy) {
        int count = 0;
        for (int k = 0; k < 8; k++) {
            int row = i + dx[k], col = j + dy[k];
            if (row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == 'M') count++;
        }
        return count;
    }
}


//////////////////////////////////////////////////////////////////////////////////////////




}

/*
Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.

 */

/*
Input:

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
 */

/*
Input:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
 */