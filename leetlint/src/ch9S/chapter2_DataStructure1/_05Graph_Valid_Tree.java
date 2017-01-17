package ch9S.chapter2_DataStructure1;

import java.util.*;

public class _05Graph_Valid_Tree {
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

/*Given n nodes labeled from 0 to n - 1 and a list of undirected edges 
 * (each edge is a pair of nodes), write a function to check whether 
 * these edges make up a valid tree.
 

 Notice

You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and 
thus will not appear together in edges.

Have you met this question in a real interview? Yes
Example
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Tags 
Depth First Search Facebook Zenefits Union Find Breadth First Search Google
Related Problems 
Medium Find the Connected Component in the Undirected Graph 21 %*/