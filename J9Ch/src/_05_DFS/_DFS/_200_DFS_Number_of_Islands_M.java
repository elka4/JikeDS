package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _200_DFS_Number_of_Islands_M {
    public class NumberofIslands {
        int[] dx = {-1,0,0,1};
        int[] dy = {0,1,-1,0};
        public int numIslands(char[][] grid) {
            if(grid==null || grid.length==0) return 0;
            int islands = 0;
            for(int i=0;i<grid.length;i++) {
                for(int j=0;j<grid[i].length;j++) {
                    if(grid[i][j]=='1') {
                        explore(grid,i,j);
                        islands++;
                    }
                }
            }
            return islands;
        }
        public void explore(char[][] grid, int i, int j) {
            grid[i][j]='x';
            for(int d=0;d<dx.length;d++) {
                if(i+dy[d]<grid.length && i+dy[d]>=0 && j+dx[d]<grid[0].length && j+dx[d]>=0 && grid[i+dy[d]][j+dx[d]]=='1') {
                    explore(grid,i+dy[d],j+dx[d]);
                }
            }
        }
    }
/*
    The algorithm works as follow:

    Scan each cell in the grid.
    If the cell value is '1' explore that island.
    Mark the explored island cells with 'x'.
    Once finished exploring that island, increment islands counter.
    The arrays dx[], dy[] store the possible moves from the current cell. Two land cells ['1'] are considered from the same island if they are horizontally or vertically adjacent (possible moves (-1,0),(0,1),(0,-1),(1,0)). Two '1' diagonally adjacent are not considered from the same island.
*/
    public class Solution2 {

        private int n;
        private int m;

        public int numIslands(char[][] grid) {
            int count = 0;
            n = grid.length;
            if (n == 0) return 0;
            m = grid[0].length;
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++)
                    if (grid[i][j] == '1') {
                        DFSMarking(grid, i, j);
                        ++count;
                    }
            }
            return count;
        }

        private void DFSMarking(char[][] grid, int i, int j) {
            if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
            grid[i][j] = '0';
            DFSMarking(grid, i + 1, j);
            DFSMarking(grid, i - 1, j);
            DFSMarking(grid, i, j + 1);
            DFSMarking(grid, i, j - 1);
        }
    }

//    Java Solution 1

    public class Solution3 {
        char[][] g;
        public int numIslands(char[][] grid) {
            int islands = 0;
            g = grid;
            for (int i=0; i<g.length; i++)
                for (int j=0; j<g[i].length; j++)
                    islands += sink(i, j);
            return islands;
        }
        int sink(int i, int j) {
            if (i < 0 || i == g.length || j < 0 || j == g[i].length || g[i][j] == '0')
                return 0;
            g[i][j] = '0';
            sink(i+1, j); sink(i-1, j); sink(i, j+1); sink(i, j-1);
            return 1;
        }
    }
//    Java Solution 2

    public class Solution4 {
        public int numIslands(char[][] grid) {
            int islands = 0;
            for (int i=0; i<grid.length; i++)
                for (int j=0; j<grid[i].length; j++)
                    islands += sink(grid, i, j);
            return islands;
        }
        int sink(char[][] grid, int i, int j) {
            if (i < 0 || i == grid.length || j < 0 || j == grid[i].length || grid[i][j] == '0')
                return 0;
            grid[i][j] = '0';
            for (int k=0; k<4; k++)
                sink(grid, i+d[k], j+d[k+1]);
            return 1;
        }
        int[] d = {0, 1, 0, -1, 0};
    }
}
