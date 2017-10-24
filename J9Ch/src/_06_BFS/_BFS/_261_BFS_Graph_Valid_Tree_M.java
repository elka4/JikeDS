package _06_BFS._BFS;
import java.util.*;import lib.*;
import org.junit.Test;
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
}
