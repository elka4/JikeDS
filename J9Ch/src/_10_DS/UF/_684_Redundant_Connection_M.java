package _10_DS.UF;
import java.util.*;

//  684. Redundant Connection
//  https://leetcode.com/problems/redundant-connection/description/
//  Tree, Union Find, Graph
public class _684_Redundant_Connection_M {

    //https://leetcode.com/problems/redundant-connection/solution/
    //Approach #1: DFS [Accepted]
    //For each edge (u, v), traverse the graph with a depth-first search to see if we can connect u to v. If we can, then it must be the duplicate edge.
    class Solution1 {
        Set<Integer> seen = new HashSet();
        int MAX_EDGE_VAL = 1000;

        public int[] findRedundantConnection(int[][] edges) {
            ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
            for (int i = 0; i <= MAX_EDGE_VAL; i++) {
                graph[i] = new ArrayList();
            }

            for (int[] edge: edges) {
                seen.clear();
                if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                        dfs(graph, edge[0], edge[1])) {
                    return edge;
                }
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            throw new AssertionError();
        }
        public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
            if (!seen.contains(source)) {
                seen.add(source);
                if (source == target) return true;
                for (int nei: graph[source]) {
                    if (dfs(graph, nei, target)) return true;
                }
            }
            return false;
        }
    }


///////////////////////////////////////////////////////////////////////////////////////
    //Approach #2: Union-Find [Accepted]
    /*
    If we are familiar with a Disjoint Set Union (DSU) data structure, we can use this in a straightforward manner to solve the problem: we simply find the first edge occurring in the graph that is already connected. The rest of this explanation will focus on the details of implementing DSU.

A DSU data structure can be used to maintain knowledge of the connected components of a graph, and query for them quickly. In particular, we would like to support two operations:

dsu.find(node x), which outputs a unique id so that two nodes have the same id if and only if they are in the same connected component, and:

dsu.union(node x, node y), which draws an edge (x, y) in the graph, connecting the components with id find(x) and find(y) together.

To achieve this, we keep track of parent, which remembers the id of a smaller node in the same connected component. If the node is it's own parent, we call this the leader of that connected component.



We use two techniques to improve the run-time complexity: path compression, and union-by-rank.

Path compression involves changing the x = parent[x] in the find function to parent[x] = find(parent[x]). Basically, as we compute the correct leader for x, we should remember our calculation.

Union-by-rank involves distributing the workload of find across leaders evenly. Whenever we dsu.union(x, y), we have two leaders xr, yr and we have to choose whether we want parent[x] = yr or parent[y] = xr. We choose the leader that has a higher following to pick up a new follower.
Specifically, the meaning of rank is that there are less than 2 ^ rank[x] followers of x. This strategy can be shown to give us better bounds for how long the recursive loop in dsu.find could run for.
     */
    class Solution2 {
        int MAX_EDGE_VAL = 1000;

        public int[] findRedundantConnection(int[][] edges) {
            DSU dsu = new DSU(MAX_EDGE_VAL + 1);
            for (int[] edge: edges) {
                if (!dsu.union(edge[0], edge[1])) return edge;
            }
            throw new AssertionError();
        }
    }

    class DSU {
        int[] parent;
        int[] rank;

        public DSU(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
            rank = new int[size];
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) {
                return false;
            } else if (rank[xr] < rank[yr]) {
                parent[xr] = yr;
            } else if (rank[xr] > rank[yr]) {
                parent[yr] = xr;
            } else {
                parent[yr] = xr;
                rank[xr]++;
            }
            return true;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
//3
    //10 line Java solution, Union Find.
    class Solution3 {
        public int[] findRedundantConnection(int[][] edges) {
            int[] parent = new int[2001];
            for (int i = 0; i < parent.length; i++) parent[i] = i;

            for (int[] edge: edges){
                int f = edge[0], t = edge[1];
                if (find(parent, f) == find(parent, t)) return edge;
                else parent[find(parent, f)] = find(parent, t);
            }

            return new int[2];
        }

        private int find(int[] parent, int f) {
            if (f != parent[f]) {
                parent[f] = find(parent, parent[f]);
            }
            return parent[f];
        }
    }
///////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////
// 9Ch
class Jiuzhang {
    public int[] findRedundantConnection(int[][] edges) {
        int N = edges.length;
        int[] parent = new int[N+1];
        for (int i=0; i<=N; i++) {
            parent[i] = i;
        }
        int l, r, father_l, father_r;
        for (int[] now_edge:edges) {
            l = now_edge[0];
            r = now_edge[1];
            father_l = find_father(parent, l);
            father_r = find_father(parent, r);
            if (father_l == father_r) {
                return now_edge;
            }
            else {
                parent[father_l] = father_r;
            }
        }
        return new int[2];
    }

    int find_father(int[] parent, int now) {
        if (now != parent[now]) {
            parent[now] = find_father(parent, parent[now]);
        }
        return parent[now];
    }

}
///////////////////////////////////////////////////////////////////////////////////////
}
/*
In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

Update (2017-09-26):
We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.


 */

