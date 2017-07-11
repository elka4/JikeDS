package S_2_Data_Structure_I.all;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** 477 Surrounded Regions


 * Created by tianhuizhu on 6/28/17.
 */
public class _477_Surrounded_Regions {

    //version 1:
    public class Solution1 {
         final int[] directionX = {+1, -1, 0, 0};
         final int[] directionY = {0, 0, +1, -1};

        static final char FREE = 'F';
        static final char TRAVELED = 'T';

        public void solve(char[][] board) {
            if (board.length == 0) {
                return;
            }

            int row = board.length;
            int col = board[0].length;

            for (int i = 0; i < row; i++) {
                bfs(board, i, 0);
                bfs(board, i, col - 1);
            }

            for (int j = 1; j < col - 1; j++) {
                bfs(board, 0, j);
                bfs(board, row - 1, j);
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    switch(board[i][j]) {
                        case 'O':
                            board[i][j] = 'X';
                            break;
                        case 'F':
                            board[i][j] = 'O';
                    }
                }
            }
        }

        public void bfs(char[][] board, int i, int j) {
            if (board[i][j] != 'O') {
                return;
            }

            Queue<Node> queue = new LinkedList<Node>();
            queue.offer(new Node(i, j));

            while (!queue.isEmpty()) {
                Node crt = queue.poll();
                board[crt.x][crt.y] = FREE;

                for (Node node : expand(board, crt)) {
                    queue.offer(node);
                }
            }
        }

        private List<Node> expand(char[][] board, Node node) {
            List<Node> expansion = new ArrayList<Node>();

            for (int i = 0; i < directionX.length; i++) {
                int x = node.x + directionX[i];
                int y = node.y + directionY[i];

                // check validity
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
                    board[x][y] = TRAVELED;
                    expansion.add(new Node(x, y));
                }
            }

            return expansion;
        }

         class Node {
            int x;
            int y;

            Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }


    //version 2:

    public class Solution2 {
        private  Queue<Integer> queue = null;
        private  int rows = 0;
        private  int cols = 0;

        public void surroundedRegions(char[][] board) {
            if (board.length == 0 || board[0].length == 0) return;
            queue = new LinkedList<Integer>();
            rows = board.length;
            cols = board[0].length;

            for (int i = 0; i < rows; i++) { // **important**
                enqueue(i, 0, board);
                enqueue(i, cols - 1, board);
            }

            for (int j = 1; j < cols - 1; j++) { // **important**
                enqueue(0, j, board);
                enqueue(rows - 1, j, board);
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int x = cur / cols,
                        y = cur % cols;

                if (board[x][y] == 'O') {
                    board[x][y] = 'D';
                }

                enqueue(x - 1, y, board);
                enqueue(x + 1, y, board);
                enqueue(x, y - 1, board);
                enqueue(x, y + 1, board);
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

        public void enqueue(int x, int y, char[][] board) {
            if (x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] == 'O'){
                queue.offer(x * cols + y);
            }
        }
    }
}
