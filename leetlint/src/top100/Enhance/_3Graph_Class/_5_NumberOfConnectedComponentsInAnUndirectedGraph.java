package top100.Enhance._3Graph_Class;

import java.util.*;

public class _5_NumberOfConnectedComponentsInAnUndirectedGraph {
 public int countComponent_UnionFind(int n, int[][] edges) {
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        for (int[] edge : edges) {              
            int par = findRoot(root, edge[0]);
            int child = findRoot(root, edge[1]);
            root[child] = par;
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (root[i] == i)
                count++;
        }
        return count;
    }
    private int findRoot(int[] root, int cur) {
        while (cur != root[cur]) {
            root[cur] = root[root[cur]];
            cur = root[cur];
        }
        return cur;
    }
    public int countComponents_DFS(int n, int[][] edges) {
    int[] tag = new int[n];
    for (int i = 0; i < n; i++) {
        tag[i] = i;
    }

    for (int[] edge : edges) {
        int start = edge[0];
        int end = edge[1];
        while (tag[start] != start) {
            start = tag[start] = tag[tag[start]];
        }

        while (tag[end] != end) {
            end = tag[end] = tag[tag[end]];
        }

        tag[start] = end;
        if (start != end)
            n--;
    }
    return n;
  }

  public int countComponents_BFS(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return n;
        }
        if (edges[0] == null || edges[0].length != 2) {
            return 0;
        }
        List<List<Integer>> neis = new ArrayList<List<Integer>>();
        boolean[] isVisited = new boolean[n];
        constructGraph(edges, neis, n);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                bfsHelper(i, neis, isVisited);
                count++;
            }
        }
        return count;
  }

    private void constructGraph(int[][] edges, List<List<Integer>> neis, int n) {
        //1. Create List
        for (int i = 0; i < n; i++) {
            neis.add(new ArrayList<Integer>());
        }
        //2. Add neighbors
        for (int[] edge : edges) {
            System.out.println(edge[0] + " " + edge[1]);
            neis.get(edge[0]).add(edge[1]);
            neis.get(edge[1]).add(edge[0]);
        }
    }

    private void dfsHelper(int cur, List<List<Integer>> neis,
                                          boolean[] isVisited) {
        isVisited[cur] = true;
        List<Integer> nei = neis.get(cur);
        for (Integer i : nei) {
            if (!isVisited[i]) {
                dfsHelper(i, neis, isVisited);
            }
        }
    }
    private void bfsHelper(int enter,
                            List<List<Integer>> neis, boolean[] isVisited) {
        isVisited[enter] = true;
        Deque<Integer> queue = new LinkedList<Integer>();
        queue.offerLast(enter);
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            for (Integer i: neis.get(cur)) {
                if (!isVisited[i]) {
                    queue.offerLast(i);
                    isVisited[i] = true;;
                }
            }
        }
    }
}

