package _10_DS.UF;
import org.junit.Test;

import java.util.*;

//  685. Redundant Connection II
//  https://leetcode.com/problems/redundant-connection-ii/description/
//  Tree, Depth-first Search, Union Find, Graph
//  4:
public class _685_Redundant_Connection_II_H {

/*
C++/Java, Union Find with explanation, O(n)
This problem is very similar to "Redundant Connection". But the description on the parent/child relationships is much better clarified.

There are two cases for the tree structure to be invalid.
1) A node having two parents;
   including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
2) A circle exists
If we can remove exactly 1 edge to achieve the tree structure, a single node can have at most two parents. So my solution works in two steps.

1) Check whether there is a node having two parents.
    If so, store them as candidates A and B, and set the second edge invalid.
2) Perform normal union find.
    If the tree is now valid
           simply return candidate B
    else if candidates not existing
           we find a circle, return current edge;
    else
           remove candidate A instead of B.

 */
    class Solution1 {
        public int[] findRedundantDirectedConnection(int[][] edges) {
            int[] can1 = {-1, -1};
            int[] can2 = {-1, -1};
            int[] parent = new int[edges.length + 1];
            for (int i = 0; i < edges.length; i++) {
                if (parent[edges[i][1]] == 0) {
                    parent[edges[i][1]] = edges[i][0];
                } else {
                    can2 = new int[] {edges[i][0], edges[i][1]};
                    can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                    edges[i][1] = 0;
                }
            }
            for (int i = 0; i < edges.length; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < edges.length; i++) {
                if (edges[i][1] == 0) {
                    continue;
                }
                int child = edges[i][1], father = edges[i][0];
                if (root(parent, father) == child) {
                    if (can1[0] == -1) {
                        return edges[i];
                    }
                    return can1;
                }
                parent[child] = father;
            }
            return can2;
        }

        int root(int[] parent, int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
    }

    @Test
    public void test01(){
        Solution1 sol1 = new Solution1();
        int[][] arr = new int[][]{{2,1}, {3,1}, {4,2}, {1,4}};
        int[] result = sol1.findRedundantDirectedConnection(arr);
        for (int i :result
                ) {
            System.out.print(i + " ");
        }
    }
//-------------------------------------------------------------------------////
//2
    //one pass disjoint set solution with explain

    //  https://discuss.leetcode.com/topic/106007/one-pass-disjoint-set-solution-with-explain
    class Solution2{
        public int[] findRedundantDirectedConnection(int[][] edges) {
            int n = edges.length;
            int[] parent = new int[n+1], ds = new int[n+1];
            Arrays.fill(parent, -1);
            int first = -1, second = -1, last = -1;
            for(int i = 0; i < n; i++) {
                int p = edges[i][0], c = edges[i][1];
                if (parent[c] != -1) {
                    first = parent[c];
                    second = i;
                    continue;
                }
                parent[c] = i;

                int p1 = find(ds, p);
                if (p1 == c) last = i;
                else ds[c] = p1;
            }

            if (last == -1) return edges[second]; // no cycle found by removing second
            if (second == -1) return edges[last]; // no edge removed
            return edges[first];
        }

        private int find(int[] ds, int i) {
            return ds[i] == 0 ? i : (ds[i] = find(ds, ds[i]));
        }
    }


    @Test
    public void test02(){
        Solution2 sol2 = new Solution2();
        int[][] arr = new int[][]{{2,1}, {3,1}, {4,2}, {1,4}};
        int[] result = sol2.findRedundantDirectedConnection(arr);
        for (int i :result
                ) {
            System.out.print(i + " ");
        }
    }//2 1
//-------------------------------------------------------------------------////
//3
//JAVA Solution by checking union-find

    class Solution3 {
        public int[] findRedundantDirectedConnection(int[][] edges) {
            int len = edges.length;
            int[] arr = new int [len + 1];
            for(int i = 1; i <= len; i++) arr[i] = i;
            int[] res= new int[2];
            for(int[] e : edges){
                // case1 : 2 edges point to same point, no cycle in the graph.
                if(arr[e[1]] != e[1]){
                    res = e;
                    continue;
                }
                int i = find(arr, e[0]), j = find(arr, e[1]);
                // case 2: There is a cycle in the graph.
                if(i == j ) {
                    if(res[0] == 0) {
                        res = e;
                        continue;
                    }
                    return new int []{arr[res[1]], res[1]};
                }
                arr[e[1]] = e[0];
            }
            int root = 0;
            // Check union-find again to find if we delete the correct edge
            for(int i = 1; i <= len; i++){
                int j = find(arr, i);
                if(root == 0) root = j;
                else if(j != root ) return new int []{arr[res[1]], res[1]};
            }
            return res;
        }

        public int find(int[] arr, int val){
            while(val != arr[val]){
                val = arr[val];
            }
            return val;
        }
    }

    @Test
    public void test03(){
        Solution3 sol3 = new Solution3();
        int[][] arr = new int[][]{{2,1}, {3,1}, {4,2}, {1,4}};
        int[] result = sol3.findRedundantDirectedConnection(arr);
        for (int i :result
                ) {
            System.out.print(i + " ");
        }
    }//2 1

//-------------------------------------------------------------------------////
    //4
/*    Concise JAVA solution, 4ms

-1
    SaoBiaoZi
    Reputation:  1
    Edge(i,j) should be removed if

    j is a child of another node ( j != root[j] )
    i and j form a ring ( root[i] == root[j] )
    If both of that two edges exists, the result is the unique edge which makes up the ring and has duplicate parent nodes.*/
    class Solution4{
        public int[] findRedundantDirectedConnection(int[][] edges) {
            int[] ancestor = new int[edges.length + 1];
            int[][] res = new int[2][2];
            for(int[]node : edges) {
                if(node[1] != getAncestor(ancestor, node[1]))
                    res[0] = node;
                else if(getAncestor(ancestor, node[0]) == getAncestor(ancestor, node[1]))
                    res[1] = node;
                else
                    ancestor[node[1]] = ancestor[node[0]];

                if(res[0][0] != 0 && res[1][0] != 0)
                    return find(edges, ancestor, res[0], res[1]);
            }
            return res[0][0] == 0 ? res[1] : res[0];
        }

        public int getAncestor(int[] ancestor, int node) {
            if(node != ancestor[node])
                ancestor[node] = ancestor[node] == 0 ? node : getAncestor(ancestor, ancestor[node]);
            return ancestor[node];
        }

        public int[] find(int[][] edges, int[] ancestor, int[] removed0, int[] removed1) {
            for(int[] res : edges)
                if(res[1] == removed0[1] && getAncestor(ancestor, res[1])  == getAncestor(ancestor, removed1[1]))
                    return res;
            return new int[2];
        }
    }

    @Test
    public void test04(){
        Solution4 sol5 = new Solution4();
        int[][] arr = new int[][]{{2,1}, {3,1}, {4,2}, {1,4}};
        int[] result = sol5.findRedundantDirectedConnection(arr);
        for (int i :result
                ) {
            System.out.print(i + " ");
        }
    }//2 1
//-------------------------------------------------------------------------////
//5
    // this is copied from 3
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;
        int[] arr = new int [len + 1];
        for(int i = 1; i <= len; i++) arr[i] = i;
        int[] res= new int[2];
        for(int[] e : edges){
            // case1 : 2 edges point to same point, no cycle in the graph.
            if(arr[e[1]] != e[1]){
                res = e;
                continue;
            }
            int i = find(arr, e[0]), j = find(arr, e[1]);
            // case 2: There is a cycle in the graph.
            if(i == j ) {
                if(res[0] == 0) {
                    res = e;
                    continue;
                }
                return new int []{arr[res[1]], res[1]};
            }
            arr[e[1]] = e[0];
        }
        int root = 0;
        // Check union-find again to find if we delete the correct edge
        for(int i = 1; i <= len; i++){
            int j = find(arr, i);
            if(root == 0) root = j;
            else if(j != root ) return new int []{arr[res[1]], res[1]};
        }
        return res;
    }

    public int find(int[] arr, int val){
        while(val != arr[val]){
            val = arr[val];
        }
        return val;
    }


    // NOT AC
    class Solution5{
        public int[] findRedundantDirectedConnection(int[][] edges) {
            int n = edges.length;
            int[] res= new int[2];
            UnionFind uf = new UnionFind(n+1);

            for(int[] e : edges){
                // case1 : 2 edges point to same point, no cycle in the graph.
                if(uf.find(e[1]) != e[1]){
                    res = e;
                    continue;
                }
                int i = uf.find(e[0]), j = uf.find(e[1]);
                // case 2: There is a cycle in the graph.
                if(i == j ) {
                    if(res[0] == 0) {
                        res = e;
                        continue;
                    }
                    return new int []{uf.find(res[1]), res[1]};
                }
                uf.union(e[1], e[0]);
            }
            int root = 0;
            // Check union-find again to find if we delete the correct edge
            for(int i = 1; i <= n; i++){
                int j = uf.find(i);
                if(root == 0) root = j;
                else if(j != root ) return new int []{uf.find(res[1]), res[1]};
            }
            return res;
        }
    }


    @Test
    public void test05(){
        Solution5 sol5 = new Solution5();
        int[][] arr = new int[][]{{2,1}, {3,1}, {4,2}, {1,4}};
        int[] result = sol5.findRedundantDirectedConnection(arr);
        for (int i :result
                ) {
            System.out.print(i + " ");
        }
    }//1 4
    //expected 21

//-------------------------------------------------------------------------////
}
/*
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */

