package _10_DS.UF;
import java.util.*;

//  200. Number of Islands
//  https://leetcode.com/problems/number-of-islands/
//  http://www.lintcode.com/zh-cn/problem/number-of-islands/
//  DFS, Breadth-first Search, Union Find
//  7: 7
public class _200_Number_of_Islands_M {

//1
//Java Union Find Solution
    class Solution1{
    class UF {

        public int count = 0;
        public int[] id = null;

        public UF(int m, int n, char[][] grid) {
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] == '1') count++;
                }
            }
            id = new int[m * n];
            for(int i = 0; i < m * n; i++) {
                id[i] = i;
            }
        }

        public int find(int p) {
            while(p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }

        public boolean isConnected(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot != qRoot) return false;
            else return true;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot == qRoot) return;
            id[pRoot] = qRoot;
            count--;
        }
    }

    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        UF uf = new UF(m , n, grid);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '0') continue;
                int p = i * n + j;
                int q;
                if(i > 0 && grid[i - 1][j] == '1') {
                    q = p - n;
                    uf.union(p, q);
                }
                if(i < m - 1 && grid[i + 1][j] == '1') {
                    q = p + n;
                    uf.union(p, q);
                }
                if(j > 0 && grid[i][j - 1] == '1') {
                    q = p - 1;
                    uf.union(p, q);
                }
                if(j < n - 1 && grid[i][j + 1] == '1') {
                    q = p + 1;
                    uf.union(p, q);
                }
            }
        }
        return uf.count;
    }



}
///////////////////////////////////////////////////////////////////////////////////////
//2
public class Solution2 {
    public int numIslands(char[][] g) {
        if (g.length < 1 || g[0].length < 1) return 0;
        int n = g.length, m = g[0].length, island = 0;
        UnionFindSet uf = new UnionFindSet(n, m);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (g[i][j] == '1') {
                    uf.find(i, j);
                    if (i > 0 && g[i - 1][j] == '1') uf.merge(i - 1, j, i, j);
                    if (j > 0 && g[i][j - 1] == '1') uf.merge(i, j - 1, i, j);
                }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (g[i][j] == '1' && uf.isSetHead(i, j)) island++;

        return island;
    }

    private class UnionFindSet { // 2d
        int n, m;
        int[] size, p;

        public UnionFindSet(int nn, int mm) {
            n = nn; m = mm;
            size = new int[n * m]; p = new int[n * m];
            Arrays.fill(p, -1);
        }

        private int id(int i, int j) {
            return i * m + j;
        }

        private int find(int i, int j) {
            return find(id(i, j));
        }

        private int find(int x) {
            if (p[x] == -1) {
                size[x] = 1;
                p[x] = x;
                return x;
            }
            p[x]  = (p[x] == x) ? x : find(p[x]);
            return p[x];
        }

        private void merge(int i1, int j1, int i2, int j2) {
            int s1 = find(i1, j1), s2 = find(i2, j2);
            if (s1 == s2) return;
            if (size[s1] > size[s2]) {
                p[s2] = s1; size[s1] += size[s2];
            } else {
                p[s1] = s2; size[s2] += size[s1];
            }
        }

        private boolean isSetHead(int i, int j) {
            return id(i, j) == find(i, j);
        }
    }
}
///////////////////////////////////////////////////////////////////////////////////////
//3
//    Thanks for sharing. I followed your logic and implement a version by using a more standard union-find class.

    public class Solution3 {
        public int numIslands(char[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
            int row = grid.length;
            int col = grid[0].length;

            UnionFind island = new UnionFind(row, col, grid);

            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    if(grid[i][j] == '1'){
                        int p = i * col + j;
                        //right
                        if(j < col - 1 && grid[i][j + 1] == '1'){
                            int q = i * col + j + 1;
                            if(!island.find(p, q)){
                                island.union(p, q);
                            }
                        }
                        //down
                        if(i < row - 1 && grid[i + 1][j] == '1'){
                            int q = (i + 1) * col + j;
                            if(!island.find(p, q)){
                                island.union(p, q);
                            }
                        }
                    }
                }
            }
            return island.size();
        }

        //-------------------------------------------------------------------------------
        class UnionFind{
            private int[] id, size;
            private int count;

            public UnionFind(int row, int col, char[][] grid){
                id = new int[row * col];
                size = new int[row * col];

                for(int i = 0; i < row; i++){
                    for(int j = 0; j < col; j++){
                        if(grid[i][j] == '1') this.count++;
                    }
                }

                for(int i = 0; i < row * col; i++){
                    id[i] = i;
                    size[i] = 1;
                }
            }

            public int size(){return this.count;}

            private int root(int i){
                while(i != id[i]){
                    id[i] = id[id[i]];
                    i = id[i];
                }
                return i;
            }

            public boolean find(int p, int q){
                return root(p) == root(q);
            }

            public void union(int p, int q){
                int i = root(p);
                int j = root(q);

                if(size[i] < size[j]){
                    id[i] = j;
                    size[j] += size[i];
                }
                else{
                    id[j] = i;
                    size[i] -= size[j];
                }
                count --;
            }
        }
        //-------------------------------------------------------------------------------
    }


///////////////////////////////////////////////////////////////////////////////////////
//4
//Nice solution. I have a similar one using Union Find from the idea of "Number of Island II" with some improvements:
//
//    We don't need to check for four directions (cells), if we scan from left-top to right-bottom, we only need to check for top and left cells.
//    Optimize the UF by ranking and path compression.
    class Solution4 {
        int[][] dir = {{0,-1},{-1,0}};//only check for top and left cells

        public int numIslands(char[][] grid) {
            int m = grid.length;
            if(m==0) return 0;
            int n = grid[0].length;
            int[] ids = new int[m*n];
            Arrays.fill(ids,-1);
            int[] sz = new int[m*n];

            int count=0;
            for(int i=0;i<m;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(grid[i][j]=='1')
                    {
                        int id=n*i+j;
                        //if(ids[id]!=-1) continue;
                        count++;
                        ids[id]=id;
                        sz[id]=1;

                        for(int[] d:dir)
                        {
                            int x=i+d[0];
                            int y=j+d[1];
                            if(x<0||y<0||x>=m||y>=n||grid[x][y]!='1') continue;

                            int idnew = n*x+y;
                            int root = find(ids,idnew);
                            if(root!=id)
                            {
                                count--;
                                id=union(id,root,ids,sz);
                            }
                        }
                    }
                }
            }
            return count;
        }

        //quick find with path compression
        public int find(int[] ids, int id) {
            while(ids[id]!=id)
            {
                ids[id]=ids[ids[id]];
                id = ids[id];
            }
            return id;
        }

        //weighted union
        public int union(int id, int root, int[] ids, int[] sz) {
            if(sz[id]<sz[root])
            {
                ids[id]=root;
                sz[root]+=sz[id];
                return root;
            }
            else
            {
                ids[root]=id;
                sz[id]+=sz[root];
                return id;
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
//5
//refactored and becomes more readable

    class Solution5 {
        int[][] dir = {{0, -1}, {-1, 0}};//only check for top and left cells
        public int numIslands(char[][] grid) {//assume valid grid
            int rows = grid.length;
            if (rows == 0) return 0;
            int cols = grid[0].length;
            int[] roots = new int[rows * cols];
            Arrays.fill(roots, -1);
            int[] size = new int[rows * cols];

            int count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        int id = cols * i + j;//id for [i, j] block
                        if (roots[id] != -1) continue;
                        count++;
                        roots[id] = id;
                        size[id] = 1;

                        for (int[] d : dir) {
                            int neighborX = i + d[0];
                            int neighborY = j + d[1];
                            if (neighborX < 0 || neighborY < 0 || neighborX >= rows || neighborY >= cols
                                    || grid[neighborX][neighborY] != '1') {
                                continue;
                            }
                            int neighborId = cols * neighborX + neighborY;
                            int root = find(roots, neighborId);
                            if (root != id) {
                                count--;//
                                id = union(id, root, roots, size);
                            }
                        }
                    }
                }
            }
            return count;
        }

        public int find(int[] roots, int i) {//quick find with path compression
            while (roots[i] != i) {
                roots[i] = roots[roots[i]];
                i = roots[i];
            }
            return i;
        }

        public int union(int root1, int root2, int[] roots, int[] size) {//weighted union
            if (size[root1] < size[root2]) {
                roots[root1] = root2;
                size[root2] += size[root1];
                size[root1] = size[root2];//optional
                return root2;
            } else {//size[root1] >= size[root2]
                roots[root2] = root1;
                size[root1] += size[root2];
                size[root2] = size[root1];//optional
                return root1;
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
    //6
    //1D Union Find Java solution, easily generalized to other problems
    /*
    For any problem I work on, I will try to generalize some reusable template out for future use. We have limited time during interview and too much to worry about, so having some code template to use is very handy. For this problem, although it is easier and probably suggested to use BFS, but Union find also comes handy and can be easily extended to solve Island 2 and Surrounded regions.

I separate all the union find logic in a separate class and use 1d version to make the code clear. I also use a 2d array for the 4 direction visit. int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};


     */

    class Solutino6{
        int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0)  {
                return 0;
            }
            UnionFind uf = new UnionFind(grid);
            int rows = grid.length;
            int cols = grid[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        for (int[] d : distance) {
                            int x = i + d[0];
                            int y = j + d[1];
                            if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '1') {
                                int id1 = i*cols+j;
                                int id2 = x*cols+y;
                                uf.union(id1, id2);
                            }
                        }
                    }
                }
            }
            return uf.count;
        }

        //Union Find:
        class UnionFind {
            int[] father;
            int m, n;
            int count = 0;
            UnionFind(char[][] grid) {
                m = grid.length;
                n = grid[0].length;
                father = new int[m*n];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (grid[i][j] == '1') {
                            int id = i * n + j;
                            father[id] = id;
                            count++;
                        }
                    }
                }
            }
            public void union(int node1, int node2) {
                int find1 = find(node1);
                int find2 = find(node2);
                if(find1 != find2) {
                    father[find1] = find2;
                    count--;
                }
            }
            public int find (int node) {
                if (father[node] == node) {
                    return node;
                }
                father[node] = find(father[node]);
                return father[node];
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
    //7
//Jiuzhang

    // version 2: Union Find

    //8
    public class Jiuzhang {

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
//------------------------------------------------------------------------------
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

        //mine
        public int numIslands2(char[][] grid) {
            int m = grid.length;
            if (m == 0) return 0;
            int n = grid[0].length;
            if (n == 0) return 0;

            int total = 0;
            for(int i = 0; i < m; ++i)
                for(int j = 0; j < n; ++j)
                    if (grid[i][j] == '1')
                        total ++;

            UnionFind union_find = new UnionFind(m * n);
            union_find.set_count(total);

            int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};

            for(int i = 0;i < m; ++i){
                for(int j = 0;j < n; ++j) {
                    if (grid[i][j] == '0') continue;

                    for (int[] d : distance) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            int id1 = i*n+j; //理解并记住这个是n
                            int id2 = x*n+y;
                            union_find.connect(id1, id2);
                        }
                    }
                }
            }

            return union_find.query();
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
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

