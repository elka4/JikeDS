package _10_DS.UF;
import java.util.*;


//  130. Surrounded Regions
//  https://leetcode.com/problems/surrounded-regions/description/
//  http://www.lintcode.com/zh-cn/problem/surrounded-regions/
//  Breadth-first Search, Union Find
public class _130_Surrounded_Regions_M {



///////////////////////////////////////////////////////////////////////////////////////
    //Solve it using Union Find
    public class Solution1 {

    int[] unionSet; // union find set
    boolean[] hasEdgeO; // whether an union has an 'O' which is on the edge of the matrix

    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;

        // init, every char itself is an union
        int height = board.length, width = board[0].length;
        unionSet = new int[height * width];
        hasEdgeO = new boolean[unionSet.length];
        for(int i = 0;i<unionSet.length; i++) unionSet[i] = i;
        for(int i = 0;i<hasEdgeO.length; i++){
            int x = i / width, y = i % width;
            hasEdgeO[i] = (board[x][y] == 'O' && (x==0 || x==height-1 || y==0 || y==width-1));
        }

        // iterate the matrix, for each char, union it + its upper char + its right char if they equals to each other
        for(int i = 0;i<unionSet.length; i++){
            int x = i / width, y = i % width, up = x - 1, right = y + 1;
            if(up >= 0 && board[x][y] == board[up][y]) union(i,i-width);
            if(right < width && board[x][y] == board[x][right]) union(i,i+1);
        }

        // for each char in the matrix, if it is an 'O' and its union doesn't has an 'edge O', the whole union should be setted as 'X'
        for(int i = 0;i<unionSet.length; i++){
            int x = i / width, y = i % width;
            if(board[x][y] == 'O' && !hasEdgeO[findSet(i)])
                board[x][y] = 'X';
        }
    }

    private void union(int x,int y){
        int rootX = findSet(x);
        int rootY = findSet(y);
        // if there is an union has an 'edge O',the union after merge should be marked too
        boolean hasEdgeO = this.hasEdgeO[rootX] || this.hasEdgeO[rootY];
        unionSet[rootX] = rootY;
        this.hasEdgeO[rootY] = hasEdgeO;
    }

    private int findSet(int x){
        if(unionSet[x] == x) return x;
        unionSet[x] = findSet(unionSet[x]);
        return unionSet[x];
    }
}
///////////////////////////////////////////////////////////////////////////////////////
//just another version in java:

    public class Solution2 {

        int[] unionSet; // union find set
        boolean[] hasEdgeO; // whether an union has an 'O' which is on the edge of the matrix

        public void solve(char[][] board) {
            if(board.length == 0 || board[0].length == 0) return;

            // init, every char itself is an union
            int height = board.length, width = board[0].length;
            unionSet = new int[height * width];
            hasEdgeO = new boolean[unionSet.length];
            for(int i = 0;i<unionSet.length; i++) unionSet[i] = i;
            for(int i = 0;i<hasEdgeO.length; i++){
                int x = i / width, y = i % width;
                hasEdgeO[i] = (board[x][y] == 'O' && (x==0 || x==height-1 || y==0 || y==width-1));
            }

            // iterate the matrix, for each char, union it + its upper char + its right char if they equals to each other
            for(int i = 0;i<unionSet.length; i++){
                int x = i / width, y = i % width, up = x - 1, right = y + 1;
                if(up >= 0 && board[x][y] == board[up][y]) union(i,i-width);
                if(right < width && board[x][y] == board[x][right]) union(i,i+1);
            }

            // for each char in the matrix, if it is an 'O' and its union doesn't has an 'edge O', the whole union should be setted as 'X'
            for(int i = 0;i<unionSet.length; i++){
                int x = i / width, y = i % width;
                if(board[x][y] == 'O' && !hasEdgeO[findSet(i)])
                    board[x][y] = 'X';
            }
        }

        private void union(int x,int y){
            int rootX = findSet(x);
            int rootY = findSet(y);
            // if there is an union has an 'edge O',the union after merge should be marked too
            boolean hasEdgeO = this.hasEdgeO[rootX] || this.hasEdgeO[rootY];
            unionSet[rootX] = rootY;
            this.hasEdgeO[rootY] = hasEdgeO;
        }

        private int findSet(int x){
            if(unionSet[x] == x) return x;
            unionSet[x] = findSet(unionSet[x]);
            return unionSet[x];
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
//Nice thought. I came up with this Java union-find with path compression and weighted union. Currently its run time is 17 ms. Can this be further improved? Thank you.

    public class Solution3 {

        private int[] ids;
        // Weight (size) of each union set
        private int[] sizes;
        // The id of union set for 'O's on edge
        private int OOnEdge;
        int m;
        int n;

        public void solve(char[][] board) {
            if((m = board.length) == 0 || (n = board[0].length) == 0) return;

            ids = new int[m * n];
            sizes = new int[m * n];
            Arrays.fill(sizes, 1);
            OOnEdge = -1;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'X') {
                        continue;
                    }
                    int index = i * n + j;
                    ids[index] = index;
                    // Nodes on edges
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                        if (OOnEdge == -1) {
                            // Set OOnEdge if it has not been set yet
                            OOnEdge = index;
                        } else {
                            // If OOnEdge is already set, unite it with index
                            unite(OOnEdge, index);
                        }
                    }
                    // Unite node with its upper neighbor
                    if (i > 0 && board[i - 1][j] == 'O') {
                        unite(index, index - n);
                    }
                    // Unite node with its left neighbor
                    if (j > 0 && board[i][j - 1] == 'O') {
                        unite(index, index - 1);
                    }
                }
            }

            // Find
            for (int i = 1; i < m - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    if (board[i][j] == 'X') {
                        continue;
                    }
                    int index = i * n + j;
                    if (OOnEdge == -1 || !find(index, OOnEdge)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void unite(int a, int b){
            int i = findRoot(a);
            int j = findRoot(b);

            // Weighted quick union
            if (sizes[i] < sizes[j]) {
                ids[i] = j;
                sizes[j] += sizes[i];
            } else {
                ids[j] = i;
                sizes[i] += sizes[j];
            }
        }

        private boolean find(int a, int b){
            return findRoot(a) == findRoot(b);
        }

        private int findRoot(int i) {
            while (i != ids[i]) {
                // Path compression
                ids[i] = ids[ids[i]];
                i = ids[i];
            }

            return i;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
    //4
//Cleaner Java code

    public class Solution4 {
        int rows, cols;

        public void solve(char[][] board) {
            if(board == null || board.length == 0) return;

            rows = board.length;
            cols = board[0].length;

            // last one is dummy, all outer O are connected to this dummy
            UnionFind uf = new UnionFind(rows * cols + 1);
            int dummyNode = rows * cols;

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    if(board[i][j] == 'O') {
                        if(i == 0 || i == rows-1 || j == 0 || j == cols-1) {
                            uf.union(node(i,j), dummyNode);
                        }
                        else {
                            if(i > 0 && board[i-1][j] == 'O')  uf.union(node(i,j), node(i-1,j));
                            if(i < rows-1 && board[i+1][j] == 'O')  uf.union(node(i,j), node(i+1,j));
                            if(j > 0 && board[i][j-1] == 'O')  uf.union(node(i,j), node(i, j-1));
                            if(j < cols-1 && board[i][j+1] == 'O')  uf.union(node(i,j), node(i, j+1));
                        }
                    }
                }
            }

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    if(uf.isConnected(node(i,j), dummyNode)) {
                        board[i][j] = 'O';
                    }
                    else {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        int node(int i, int j) {
            return i * cols + j;
        }
    }

    class UnionFind {
        int [] parents;
        public UnionFind(int totalNodes) {
            parents = new int[totalNodes];
            for(int i = 0; i < totalNodes; i++) {
                parents[i] = i;
            }
        }

        void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);
            if(root1 != root2) {
                parents[root2] = root1;
            }
        }

        int find(int node) {
            while(parents[node] != node) {
                parents[node] = parents[parents[node]];
                node = parents[node];
            }

            return node;
        }

        boolean isConnected(int node1, int node2) {
            return find(node1) == find(node2);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
// 9Ch
//  http://www.jiuzhang.com/solution/surrounded-regions/
///////////////////////////////////////////////////////////////////////////////////////
}
/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

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

 */
