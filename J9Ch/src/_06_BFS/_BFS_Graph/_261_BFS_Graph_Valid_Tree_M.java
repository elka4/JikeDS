package _06_BFS._BFS_Graph;
import java.util.*;

public class _261_BFS_Graph_Valid_Tree_M {

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


//    DFS
    public class Solution1 {
        public boolean validTree(int n, int[][] edges) {
            int[] visited = new int[n];
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i=0; i<n; ++i) { adjList.add(new ArrayList<Integer>()); }
            for (int[] edge: edges) {
                adjList.get(edge[0]).add(edge[1]);
                adjList.get(edge[1]).add(edge[0]);
            }
            if (hasCycle(-1, 0, visited, adjList)) { return false; }  // has cycle
            for (int v: visited) { if (v == 0) { return false; } }  // not 1 single connected component
            return true;
        }

        private boolean hasCycle(int pred, int vertex, int[] visited, List<List<Integer>> adjList) {
            visited[vertex] = 1;  // current vertex is being visited
            for (Integer succ: adjList.get(vertex)) {  // successors of current vertex
                if (succ != pred) {  // exclude current vertex's predecessor
                    if (visited[succ] == 1) { return true; }  // back edge/loop detected!
                    else if (visited[succ] == 0) {
                        if (hasCycle(vertex, succ, visited, adjList)) { return true; }
                    }
                }
            }
            visited[vertex] = 2;
            return false;
        }
    }
//    BFS
    public class Solution2 {
        public boolean validTree(int n, int[][] edges) {
            int[] visited = new int[n];
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i=0; i<n; ++i) { adjList.add(new ArrayList<Integer>()); }
            for (int[] edge: edges) {
                adjList.get(edge[0]).add(edge[1]);
                adjList.get(edge[1]).add(edge[0]);
            }
            Deque<Integer> q = new ArrayDeque<>();
            q.addLast(0); visited[0] = 1;  // vertex 0 is in the queue, being visited
            while (!q.isEmpty()) {
                Integer cur = q.removeFirst();
                for (Integer succ: adjList.get(cur)) {
                    if (visited[succ] == 1) { return false; }  // loop detected
                    if (visited[succ] == 0) { q.addLast(succ); visited[succ] = 1; }
                }
                visited[cur] = 2;  // visit completed
            }
            for (int v: visited) { if (v == 0) { return false; } }  // # of connected components is not 1
            return true;
        }
    }

//    Union-Find with path compression and merge by rank
    public class Solution3 {

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

//------------------------------------------------------------------------------//////
    // 9Ch
    // version 1: BFS
    public class Jiuzhang {
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
//------------------------------------------------------------------------------//////
}
/*
给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 写一个函数去判断这张｀无向｀图是否是一棵树

 注意事项

你可以假设我们不会给出重复的边在边的列表当中. 无向边　[0, 1] 和 [1, 0]　是同一条边， 因此他们不会同时出现在我们给你的边的列表当中。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出n = 5 并且 edges = [[0, 1], [0, 2], [0, 3], [1, 4]], 返回 true.

给出n = 5 并且 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], 返回 false.
 */
