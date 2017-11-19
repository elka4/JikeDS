package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _261_DFS_Graph_Valid_Tree_M {

//AC Java Union-Find solution
public class Solution {
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

//AC Java Graph DFS solution with adjacency list
public class Solution2 {
    public boolean validTree(int n, int[][] edges) {
        // initialize adjacency list
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);

        // initialize vertices
        for (int i = 0; i < n; i++)
            adjList.add(i, new ArrayList<Integer>());

        // add edges
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        // make sure there's no cycle
        if (hasCycle(adjList, 0, visited, -1))
            return false;

        // make sure all vertices are connected
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }

    // check if an undirected graph has cycle started from vertex u
    boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
        visited[u] = true;

        for (int i = 0; i < adjList.get(u).size(); i++) {
            int v = adjList.get(u).get(i);

            if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, visited, u)))
                return true;
        }

        return false;
    }
}


//-------------------------------------------------------------------------///
// version 1: BFS
public class Jiuzhang1 {
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
}


    // version 2: Union Find
    public class Jiuzhang2 {
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



//-------------------------------------------------------------------------///






}
/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


 */
