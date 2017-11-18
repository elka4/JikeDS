package _10_DS.UF;

import java.util.HashMap;

public class UnionFind {

    HashMap<Integer, Integer> father = new HashMap<Integer,Integer>();

    UnionFind(){}
    public UnionFind(int totalNodes) {
        for(int i = 0; i < totalNodes; i++) {
            father.put(i, i);
        }
    }



    int[] parents;

    //UnionFind2() { }

    /*UnionFind2(int totalNodes) {
        parents = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            parents[i] = i;
        }
    }*/

//------------------------------------------------------------------------
    int find(int x) {
        int parent = x;
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        return parent;
    }

    int find2(int node) {
        while (parents[node] != node) {
            parents[node] = parents[parents[node]];
            node = parents[node];
        }
        return node;
    }

//------------------------------------------------------------------------

    void union(int x, int y){
        int fa_x = find(x);
        int fa_y = find(y);
        if (fa_x != fa_y) {
            father.put(fa_x, fa_y);
        }
    }

    boolean union2(int x, int y){
        int fa_x = find(x);
        int fa_y = find(y);
        if (fa_x != fa_y) {
            father.put(fa_x, fa_y);
            return false;
        }
        return true;
    }



    void union_(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parents[root2] = root1;
        }
    }

    boolean union2_(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parents[root2] = root1;
            return false;
        }
        return true;
    }


//------------------------------------------------------------------------

    //average O(1)
    int compressed_find(int x){
        int parent = father.get(x);
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        int next;
        while (x != father.get(x)) {
            next = father.get(x);
            father.put(x, parent);
            x = next;
        }
        return parent;
    }

    //average O(1)
    int compressed_find_(int node) {
        int parent = parents[node];
        while (parent != parents[parent]) {
            parent = parents[parent];
        }
        int next;
        while (node != parents[parent]) {
            next = parents[parent];
            parents[node] = parent;
            node = next;
        }
        return parent;
    }

//------------------------------------------------------------------------

    boolean isConnected(int node1, int node2) {

        return find(node1) == find(node2);
    }

    boolean isConnected_(int node1, int node2) {
        return find(node1) == find(node2);
    }
//------------------------------------------------------------------------

}
