package _06_BFS._BFS_Matrix;

import java.util.LinkedList;
import java.util.Queue;

public class _200_BFS_Number_of_Islands_M {

//    DFS:

    public int numIslands(char[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfsFill(grid,i,j);
                    count++;
                }
            }
        return count;
    }
    private void dfsFill(char[][] grid,int i, int j){
        if(i>=0 && j>=0 && i<grid.length && j<grid[0].length&&grid[i][j]=='1'){
            grid[i][j]='0';
            dfsFill(grid, i + 1, j);
            dfsFill(grid, i - 1, j);
            dfsFill(grid, i, j + 1);
            dfsFill(grid, i, j - 1);
        }
    }
//    BFS:

    public int numIslands2(char[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    bfsFill(grid,i,j);
                    count++;
                }
            }
        return count;
    }
    private void bfsFill(char[][] grid,int x, int y){
        grid[x][y]='0';
        int n = grid.length;
        int m = grid[0].length;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int code = x*m+y;
        queue.offer(code);
        while(!queue.isEmpty())
        {
            code = queue.poll();
            int i = code/m;
            int j = code%m;
            if(i>0 && grid[i-1][j]=='1')    //search upward and mark adjacent '1's as '0'.
            {
                queue.offer((i-1)*m+j);
                grid[i-1][j]='0';
            }
            if(i<n-1 && grid[i+1][j]=='1')  //down
            {
                queue.offer((i+1)*m+j);
                grid[i+1][j]='0';
            }
            if(j>0 && grid[i][j-1]=='1')  //left
            {
                queue.offer(i*m+j-1);
                grid[i][j-1]='0';
            }
            if(j<m-1 && grid[i][j+1]=='1')  //right
            {
                queue.offer(i*m+j+1);
                grid[i][j+1]='0';
            }
        }
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
                    Coordinate adj = new Coordinate(coor.x + directionX[i],
                                                    coor.y + directionY[i]);
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
    /*
    相当于找dfs的入口，每次进入就把连在一起的都重设为false。
    能进入多少次，就是有多少个岛屿
    不知道为什么不推荐，不过
     */
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
/////////////////////////////////////////////////////////////////////////////
}
/*
Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Find the number of islands.

Have you met this question in a real interview? Yes
Example
Given graph:

[
  [1, 1, 0, 0, 0],
  [0, 1, 0, 0, 1],
  [0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0],
  [0, 0, 0, 0, 1]
]
return 3.
 */