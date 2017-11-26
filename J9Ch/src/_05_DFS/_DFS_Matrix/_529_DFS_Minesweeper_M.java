package _05_DFS._DFS_Matrix;
import java.util.*;

//  529. Minesweeper
//  https://leetcode.com/problems/minesweeper/description/
public class _529_DFS_Minesweeper_M {
    /*
    This is a typical Search problem, either by using DFS or BFS. Search rules:

    If click on a mine ('M'), mark it as 'X', stop further search.
    If click on an empty cell ('E'), depends on how many surrounding mine:
    2.1 Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
    2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.
     */
    //    DFS solution.

    public class Solution {
        public char[][] updateBoard(char[][] board, int[] click) {
            int m = board.length, n = board[0].length;
            int row = click[0], col = click[1];

            if (board[row][col] == 'M') { // Mine
                board[row][col] = 'X';
            } else { // Empty
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
                    board[row][col] = (char) (count + '0');
                } else { // Continue DFS to adjacent cells.
                    board[row][col] = 'B';
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i == 0 && j == 0) continue;
                            int r = row + i, c = col + j;
                            if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                            if (board[r][c] == 'E') updateBoard(board, new int[]{r, c});
                        }
                    }
                }
            }

            return board;
        }
    }

//----------------------------------------------------------------------------
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
                } else { // Empty
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
                        board[row][col] = (char) (count + '0');
                    } else { // Continue BFS to adjacent cells.
                        board[row][col] = 'B';
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (i == 0 && j == 0) continue;
                                int r = row + i, c = col + j;
                                if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                                if (board[r][c] == 'E') {
                                    queue.add(new int[]{r, c});
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

//----------------------------------------------------------------------------
    //    Another BFS -
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        if (m == 0) return board;
        int n = board[0].length;

        int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        int X = 0, Y = 0, newX = 0, newY = 0, cnt = 0;
        Queue<int[]> q1 = new LinkedList<>();
        Queue<int[]> q2 = new LinkedList<>();

        q1.offer(click);

        while (!q1.isEmpty()) {
            X = q1.peek()[0];
            Y = q1.poll()[1];
            switch (board[X][Y]) {
                case 'M':
                    board[X][Y] = 'X';
                    return board;
                case 'E':
                    cnt = 0;
                    q2.clear();
                    for (int i = 0; i < 8; i++) {
                        newX = X + dir[i][0];
                        newY = Y + dir[i][1];
                        if (0 <= newX && newX < m && 0 <= newY && newY < n) {
                            if (board[newX][newY] == 'M') cnt++;
                            if (board[newX][newY] == 'E') q2.offer(new int[]{newX, newY});
                        }
                    }
                    if (cnt == 0) {
                        board[X][Y] = 'B';
                        q1.addAll(q2);
                    } else board[X][Y] = Character.forDigit(cnt, 10);
                    break;
            }
        }
        return board;
    }

//----------------------------------------------------------------------------
    //Straight forward Java solution
    public class Solution4 {
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
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E') return;

            int num = getNumsOfBombs(board, x, y);

            if (num == 0) {
                board[x][y] = 'B';
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    dfs(board, nx, ny);
                }
            } else {
                board[x][y] = (char) ('0' + num);
            }

        }

        private int getNumsOfBombs(char[][] board, int x, int y) {
            int num = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nx = x + i, ny = y + j;
                    if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) continue;
                    if (board[nx][ny] == 'M' || board[nx][ny] == 'X') {
                        num++;
                    }
                }
            }
            return num;
        }
    }

//----------------------------------------------------------------------------
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

        private boolean isMine(char[][] board, int y, int x) {
            return isValid(board, y, x) && board[y][x] == 'M';
        }

        private boolean isEmpty(char[][] board, int y, int x) {
            return isValid(board, y, x) && board[y][x] == 'E';
        }

        private void mark(int[][] info, char[][] board, int y, int x) {
            for (int dy : new int[]{-1, 0, 1})
                for (int dx : new int[]{-1, 0, 1}) {
                    int newY = y + dy, newX = x + dx;
                    if (isValid(board, newY, newX))
                        info[newY][newX]++;
                }
        }

        private boolean isValid(char[][] board, int y, int x) {
            return y >= 0 && y < board.length && x >= 0 && x < board[y].length;
        }
    }


//----------------------------------------------------------------------------

/*    Java Solution, DFS + BFS
    This is a typical Search problem, either by using DFS or BFS. Search rules:

    If click on a mine ('M'), mark it as 'X', stop further search.
    If click on an empty cell ('E'), depends on how many surrounding mine:
            2.1 Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.
    DFS solution.*/

    public class Solution06 {
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

    public class Solution07 {
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

//----------------------------------------------------------------------------

}
/*

 */