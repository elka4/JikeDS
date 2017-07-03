package ch9S.chapter2_DataStructure1;

import java.util.LinkedList;
import java.util.Queue;

public class _06Surrounded_Regions_2 {
private static Queue<Integer> queue = null;
private static char[][] board;
private static int rows = 0;
private static int cols = 0;

public void solve(char[][] board) {
    // Note: The Solution object is instantiated only once and is reused by each test case.
    if (board.length == 0 || board[0].length == 0) return;
    queue = new LinkedList<Integer>();
    board = board;
    rows = board.length;
    cols = board[0].length;

    for (int i = 0; i < rows; i++) { // **important**
        enqueue(i, 0);
        enqueue(i, cols - 1);
    }

    for (int j = 1; j < cols - 1; j++) { // **important**
        enqueue(0, j);
        enqueue(rows - 1, j);
    }

    while (!queue.isEmpty()) {
        int cur = queue.poll();
        int x = cur / cols,
            y = cur % cols;

        if (board[x][y] == 'O') {
            board[x][y] = 'D';
        }

        enqueue(x - 1, y);
        enqueue(x + 1, y);
        enqueue(x, y - 1);
        enqueue(x, y + 1);
    }

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (board[i][j] == 'D') board[i][j] = 'O';
            else if (board[i][j] == 'O') board[i][j] = 'X';
        }
    }

    queue = null;
    board = null;
    rows = 0;
    cols = 0;
}

public static void enqueue(int x, int y) {
    if (x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] == 'O'){  
        queue.offer(x * cols + y);
    }
}
}

/*Given a 2D board containing 'X' and 'O', 
 * capture all regions surrounded by 'X'.
 

A region is captured by flipping all 'O''s
 into 'X''s in that surrounded region.

Have you met this question in a real interview? Yes
Example
X X X X
X O O X
X X O X
X O X X
After capture all regions surrounded by 'X', the board should be:

X X X X
X X X X
X X X X
X O X X
Tags 
Breadth First Search Union Find
Related Problems 
Hard Number of Islands II 15 %
Easy Number of Islands 22 %*/