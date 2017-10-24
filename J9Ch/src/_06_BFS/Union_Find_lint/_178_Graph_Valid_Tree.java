package _06_BFS.Union_Find_lint;

import org.junit.Test;

import java.util.*;

/** 178. Graph Valid Tree
 * Medium

 * Created by tianhuizhu on 6/27/17.
 */
public class _178_Graph_Valid_Tree {

    // version 1: BFS
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }

        if (edges.length != n - 1) {
            return false;
        }

        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);

        // bfs
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();

        queue.offer(0);
        hash.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (hash.contains(neighbor)) {
                    continue;
                }
                hash.add(neighbor);
                queue.offer(neighbor);
            }
        }

        return (hash.size() == n);
    }

    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }

/*
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 */
    @Test
    public void test01(){
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        validTree(5,edges);
        System.out.println(validTree(5,edges));
    }
    @Test
    public void test02(){
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1,4}};
        validTree(5,edges);
        System.out.println(validTree(5,edges));
    }



    // version 2: Union Find
    public class Solution2 {
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
                if (uf.compressed_find(edges[i][0]) == uf.compressed_find(edges[i][1])) {
                    return false;
                }
                uf.union(edges[i][0], edges[i][1]);
            }
            return true;
        }
    }
}