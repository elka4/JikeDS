package _10_DS.UF;

import java.util.HashMap;

public class UnionFind {
    UnionFind(){}
    HashMap<Integer, Integer> father = new HashMap<Integer,Integer>();
    public UnionFind(int totalNodes) {
        for(int i = 0; i < totalNodes; i++) {
            father.put(i, i);
        }
    }
    int find(int x) {
        int parent = x;
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        return parent;
    }

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

    boolean isConnected(int node1, int node2) {

        return find(node1) == find(node2);
    }
}
