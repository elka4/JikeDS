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
//////////////////////////////////////////////////////////////////////////////////////
    //jiuzhang

    // version 1: BFS
    class Coordinate {
        int x, y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Jiuzhang1 {
        /**
         * @param grid a boolean 2D matrix
         * @return an integer
         */
        public int numIslands(boolean[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int n = grid.length;
            int m = grid[0].length;
            int islands = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j]) {
                        markByBFS(grid, i, j);
                        islands++;
                    }
                }
            }

            return islands;
        }

        private void markByBFS(boolean[][] grid, int x, int y) {
            // magic numbers!
            int[] directionX = {0, 1, -1, 0};
            int[] directionY = {1, 0, 0, -1};

            Queue<Coordinate> queue = new LinkedList<>();

            queue.offer(new Coordinate(x, y));
            grid[x][y] = false;

            while (!queue.isEmpty()) {
                Coordinate coor = queue.poll();
                for (int i = 0; i < 4; i++) {
                    Coordinate adj = new Coordinate(
                            coor.x + directionX[i],
                            coor.y + directionY[i]
                    );
                    if (!inBound(adj, grid)) {
                        continue;
                    }
                    if (grid[adj.x][adj.y]) {
                        grid[adj.x][adj.y] = false;
                        queue.offer(adj);
                    }
                }
            }
        }

        private boolean inBound(Coordinate coor, boolean[][] grid) {
            int n = grid.length;
            int m = grid[0].length;

            return coor.x >= 0 && coor.x < n && coor.y >= 0 && coor.y < m;
        }
    }

    // version 2: Union Find
    class UnionFind {

        private int[] father = null;
        private int count;

        private int find(int x) {
            if (father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]);
        }

        public UnionFind(int n) {
            // initialize your data structure here.
            father = new int[n];
            for (int i = 0; i < n; ++i) {
                father[i] = i;
            }
        }

        public void connect(int a, int b) {
            // Write your code here
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                father[root_a] = root_b;
                count --;
            }
        }

        public int query() {
            // Write your code here
            return count;
        }

        public void set_count(int total) {
            count = total;
        }
    }

    public class Jiuzhang2 {
        /**
         * @param grid a boolean 2D matrix
         * @return an integer
         */
        public int numIslands(boolean[][] grid) {
            int count = 0;
            int n = grid.length;
            if (n == 0)
                return 0;
            int m = grid[0].length;
            if (m == 0)
                return 0;
            UnionFind union_find = new UnionFind(n * m);

            int total = 0;
            for(int i = 0;i < grid.length; ++i)
                for(int j = 0;j < grid[0].length; ++j)
                    if (grid[i][j])
                        total ++;

            union_find.set_count(total);
            for(int i = 0;i < grid.length; ++i)
                for(int j = 0;j < grid[0].length; ++j)
                    if (grid[i][j]) {
                        if (i > 0 && grid[i - 1][j]) {
                            union_find.connect(i * m + j, (i - 1) * m + j);
                        }
                        if (i <  n - 1 && grid[i + 1][j]) {
                            union_find.connect(i * m + j, (i + 1) * m + j);
                        }
                        if (j > 0 && grid[i][j - 1]) {
                            union_find.connect(i * m + j, i * m + j - 1);
                        }
                        if (j < m - 1 && grid[i][j + 1]) {
                            union_find.connect(i * m + j, i * m + j + 1);
                        }
                    }
            return union_find.query();
        }
    }

    // version 3: DFS (not recommended)
    public class Jiuzhang3 {
        /**
         * @param grid a boolean 2D matrix
         * @return an integer
         */
        private int m, n;
        public void dfs(boolean[][] grid, int i, int j) {
            if (i < 0 || i >= m || j < 0 || j >= n) return;

            if (grid[i][j]) {
                grid[i][j] = false;
                dfs(grid, i - 1, j);
                dfs(grid, i + 1, j);
                dfs(grid, i, j - 1);
                dfs(grid, i, j + 1);
            }
        }

        public int numIslands(boolean[][] grid) {
            // Write your code here
            m = grid.length;
            if (m == 0) return 0;
            n = grid[0].length;
            if (n == 0) return 0;

            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!grid[i][j]) continue;
                    ans++;
                    dfs(grid, i, j);
                }
            }
            return ans;
        }
    }




//////////////////////////////////////////////////////////////////////////////////////






}
/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3


 */


