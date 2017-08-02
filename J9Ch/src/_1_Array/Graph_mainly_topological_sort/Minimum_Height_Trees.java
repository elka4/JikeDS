package _1_Array.Graph_mainly_topological_sort;
import java.util.*;
/*
LeetCode – Minimum Height Trees (Java)

For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]
 */
public class Minimum_Height_Trees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<Integer>();
        if(n==0){
            return result;
        }
        if(n==1){
            result.add(0);
            return result;
        }

        ArrayList<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>();
        for(int i=0; i<n; i++){
            graph.add(new HashSet<Integer>());
        }

        for(int[] edge: edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        LinkedList<Integer> leaves = new LinkedList<Integer>();
        for(int i=0; i<n; i++){
            if(graph.get(i).size()==1){
                leaves.offer(i);
            }
        }

        if(leaves.size()==0){
            return result;
        }

        while(n>2){
            n = n-leaves.size();

            LinkedList<Integer> newLeaves = new LinkedList<Integer>();

            for(int l: leaves){
                int neighbor = graph.get(l).iterator().next();
                graph.get(neighbor).remove(l);
                if(graph.get(neighbor).size()==1){
                    newLeaves.add(neighbor);
                }
            }

            leaves = newLeaves;
        }

        return leaves;
    }
}
