package S_2_DS_I.all;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** 477 Surrounded Regions


 * Created by tianhuizhu on 6/28/17.
 */
public class _477_Surrounded_Regions {

    //version 1:
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
