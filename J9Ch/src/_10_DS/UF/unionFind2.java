package _10_DS.UF;

public class unionFind2 {
    int[] parents;

    public unionFind2() {

    }

    unionFind2(int totalNodes) {
        parents = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            parents[i] = i;
        }
    }

    void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parents[root2] = root1;
        }
    }

    boolean union2(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parents[root2] = root1;
            return false;
        }
        return true;
    }

    int find(int node) {
        while (parents[node] != node) {
            parents[node] = parents[parents[node]];
            node = parents[node];
        }

        return node;
    }

    //average O(1)
    int compressed_find(int node) {
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

    boolean isConnected(int node1, int node2) {
        return find(node1) == find(node2);
    }
}
