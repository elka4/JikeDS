package _10_DS.UF;
import java.util.*;

//  261. Graph Valid Tree
//  https://leetcode.com/problems/graph-valid-tree/
//  http://www.lintcode.com/zh-cn/problem/graph-valid-tree/
//  Depth-first Search, Breadth-first Search, Union Find, Graph
//  3
public class _261_Graph_Valid_Tree_M {
    //1
    //AC Java Union-Find solution
    public class Solution1 {
        public boolean validTree(int n, int[][] edges) {
            // initialize n isolated islands
            int[] nums = new int[n];
            Arrays.fill(nums, -1);

            // perform union find
            for (int i = 0; i < edges.length; i++) {
                int x = find(nums, edges[i][0]);
                int y = find(nums, edges[i][1]);

                // if two vertices happen to be in the same set
                // then there's a cycle
                if (x == y) return false;

                // union
                nums[x] = y;
            }

            return edges.length == n - 1;
        }

        int find(int nums[], int i) {
            if (nums[i] == -1) return i;
            return find(nums, nums[i]);
        }
    }

//-------------------------------------------------------------------------////
//2
    //https://discuss.leetcode.com/topic/35515/share-my-25-line-dfs-20-line-bfs-and-clean-union-find-java-solutions
    //Union-Find with path compression and merge by rank
    public class Solution2 {

        class UnionFind {

            int[] parent;
            int[] rank;
            int count;

            UnionFind(int n) {
                parent = new int[n];
                rank = new int[n];
                count = n;  // number of components
                for (int i=0; i<n; ++i) { parent[i] = i; }  // initially, each node's parent is itself.
            }

            int find(int x) {
                if (x != parent[x]) {
                    parent[x] = find(parent[x]);  // find root with path compression
                }
                return parent[x];
            }

            boolean union(int x, int y) {
                int X = find(x), Y = find(y);
                if (X == Y) { return false; }
                if (rank[X] > rank[Y]) { parent[Y] = X; }  // tree Y is lower
                else if (rank[X] < rank[Y]) { parent[X] = Y; }  // tree X is lower
                else {  // same height
                    parent[Y] = X;
                    ++rank[X];
                }
                --count;
                return true;
            }
        }

        public boolean validTree(int n, int[][] edges) {
            UnionFind uf = new UnionFind(n);
            for (int[] edge: edges) {
                int x = edge[0], y = edge[1];
                if (!uf.union(x, y)) { return false; }  // loop detected
            }
            return uf.count == 1;
        }
    }
//-------------------------------------------------------------------------////

//-------------------------------------------------------------------------////

//-------------------------------------------------------------------------////
    //3
// 9Ch
// version 2: Union Find
public class Jiuzhang {
    class UnionFind{
        HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
        UnionFind(int n){
            for(int i = 0 ; i < n; i++) {
                father.put(i, i);
            }
        }
        int compressed_find(int x){
            int parent =  father.get(x);
            while(parent!=father.get(parent)) {
                parent = father.get(parent);
            }
            int temp = -1;
            int fa = father.get(x);
            while(fa!=father.get(fa)) {
                temp = father.get(fa);
                father.put(fa, parent) ;
                fa = temp;
            }
            return parent;

        }

        void union(int x, int y){
            int fa_x = compressed_find(x);
            int fa_y = compressed_find(y);
            if(fa_x != fa_y)
                father.put(fa_x, fa_y);
        }
    }
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        // tree should have n nodes with n-1 edges
        if (n - 1 != edges.length) {
            return false;
        }

        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < edges.length; i++) {
            //一个边的两个顶点都连接同一个顶点
            if (uf.compressed_find(edges[i][0]) == uf.compressed_find(edges[i][1])) {
                return false;
            }
            uf.union(edges[i][0], edges[i][1]);
        }
        return true;
    }
}
//-------------------------------------------------------------------------////
}
/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


 */

//给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 写一个函数去判断这张｀无向｀图是否是一棵树

