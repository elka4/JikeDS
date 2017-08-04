package _2_Matrix.bfs_dfs;
import java.util.*;
/*
LeetCode – Surrounded Regions (Java)

Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'. A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Analysis

This problem is similar to Number of Islands. In this problem,
only the cells on the boarders can not be surrounded.
So we can first merge those O's on the boarders like in Number of Islands
and replace O's with '#', and then scan the board and replace all O's left (if any).
 */

public class Surrounded_Regions {

    //1. Depth-first Search

    public void solve(char[][] board) {
        if(board == null || board.length==0)
            return;

        int m = board.length;
        int n = board[0].length;

        //merge O's on left & right boarder
        for(int i=0;i<m;i++){
            if(board[i][0] == 'O'){
                merge(board, i, 0);
            }

            if(board[i][n-1] == 'O'){
                merge(board, i,n-1);
            }
        }

        //merge O's on top & bottom boarder
        for(int j=0; j<n; j++){
            if(board[0][j] == 'O'){
                merge(board, 0,j);
            }

            if(board[m-1][j] == 'O'){
                merge(board, m-1,j);
            }
        }

        //process the board
        for(int i=0;i<m;i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void merge(char[][] board, int i, int j){
        if(i<0 || i>=board.length || j<0 || j>=board[0].length)
            return;

        if(board[i][j] != 'O')
            return;

        board[i][j] = '#';

        merge(board, i-1, j);
        merge(board, i+1, j);
        merge(board, i, j-1);
        merge(board, i, j+1);
    }


/*    This solution causes java.lang.StackOverflowError, because for a large board,
too many method calls are pushed to the stack and causes the overflow.





2. Breath-first Search

    We can also use a queue to do breath-first search for this problem.*/

    public void solve2(char[][] board) {
        if(board==null || board.length==0 || board[0].length==0)
            return;

        int m=board.length;
        int n=board[0].length;


        for(int j=0; j<n; j++){
            if(board[0][j]=='O'){
                bfs(board, 0, j);
            }
        }

        for(int j=0; j<n; j++){
            if(board[m-1][j]=='O'){
                bfs(board, m-1, j);
            }
        }

        for(int i=0; i<m; i++){
            if(board[i][0]=='O'){
                bfs(board, i, 0);
            }
        }

        for(int i=0; i<m; i++){
            if(board[i][n-1]=='O'){
                bfs(board, i, n-1);
            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }
                if(board[i][j]=='1'){
                    board[i][j]='O';
                }
            }
        }
    }

    public void bfs(char[][] board, int o, int p){
        int m=board.length;
        int n=board[0].length;

        int index = o*n+p;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.offer(index);
        board[o][p]='1';

        while(!queue.isEmpty()){
            int top = queue.poll();
            int i=top/n;
            int j=top%n;

            if(i-1>=0 && board[i-1][j]=='O'){
                board[i-1][j]='1';
                queue.offer((i-1)*n+j);
            }
            if(i+1<m && board[i+1][j]=='O'){
                board[i+1][j]='1';
                queue.offer((i+1)*n+j);
            }
            if(j-1>=0 && board[i][j-1]=='O'){
                board[i][j-1]='1';
                queue.offer(i*n+j-1);
            }
            if(j+1<n && board[i][j+1]=='O'){
                board[i][j+1]='1';
                queue.offer(i*n+j+1);
            }
        }
    }

/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////
}
