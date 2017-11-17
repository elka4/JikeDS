package _10_DS.UF;
import java.util.*;

//  323. Number of Connected Components in an Undirected Graph
//  https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
//  Depth-first Search Breadth-first Search Union Find Graph
public class _323_Number_of_Connected_Components_in_an_Undirected_Graph_M {

/*

    Easiest 2ms Java Solution
    This is 1D version of Number of Islands II. For more explanations, check out this 2D Solution.

            n points = n islands = n trees = n roots.
    With each edge added, check which island is e[0] or e[1] belonging to.
    If e[0] and e[1] are in same islands, do nothing.
            Otherwise, union two islands, and reduce islands count by 1.
    Bonus: path compression can reduce time by 50%.
    Hope it helps!
*/
    class Solution1{
        public int countComponents(int n, int[][] edges) {
            int[] roots = new int[n];
            for(int i = 0; i < n; i++) roots[i] = i;

            for(int[] e : edges) {
                int root1 = find(roots, e[0]);
                int root2 = find(roots, e[1]);
                if(root1 != root2) {
                    roots[root1] = root2;  // union
                    n--;
                }
            }
            return n;
        }

        public int find(int[] roots, int id) {
            while(roots[id] != id) {
                roots[id] = roots[roots[id]];  // optional: path compression
                id = roots[id];
            }
            return id;
        }

    }
///////////////////////////////////////////////////////////////////////////////////////
    //AC JAVA code, Union Find
class Solution2{
    private int[] father;
    public int countComponents(int n, int[][] edges) {

        Set<Integer> set = new HashSet<Integer>();
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            union(edges[i][0], edges[i][1]);
        }

        for (int i = 0; i < n; i++){
            set.add(find(i));
        }
        return set.size();
    }

    int find(int node) {
        if (father[node] == node) {
            return node;
        }
        father[node] = find(father[node]);
        return father[node];
    }

    void union(int node1, int node2) {
        father[find(node1)] = find(node2);
    }
}
///////////////////////////////////////////////////////////////////////////////////////
//3

    //Java Union find & DFS & BFS Code (very clean)
    //https://discuss.leetcode.com/topic/40685/java-union-find-dfs-bfs-code-very-clean
    public class Solution3 {

        public int countComponents(int n, int[][] edges) {
            if (n <= 1) {
                return n;
            }
            int[] roots = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = i;
            }
            for (int[] edge : edges) {
                int x = find(roots, edge[0]);
                int y = find(roots, edge[1]);
                if (x != y) {
                    roots[x] = y;
                    n--;
                }
            }

            return n;
        }

        public int find(int[] roots, int id) {
            int x = id;
            while (roots[id] != id) {
                id = roots[id];
            }
            while (roots[x] != id) {
                int fa = roots[x];
                roots[x] = id;
                x = fa;
            }

            return id;
        }
    }

///////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////
}
/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

