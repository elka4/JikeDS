package J_4_BFS.all;

import org.junit.Test;

import java.util.*;

/**431 Connected Component in Undirected Graph

 * Created by tianhuizhu on 6/28/17.
 */
public class _431_Connected_Component_in_Undirected_Graph {
   // Definition for Undirected graph.
   class UndirectedGraphNode {
      int label;
      ArrayList<UndirectedGraphNode> neighbors;
     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  }

    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here

        int m = nodes.size();
        Map<UndirectedGraphNode, Boolean> visited = new HashMap<>();

        for (UndirectedGraphNode node : nodes){
            visited.put(node, false);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (UndirectedGraphNode node : nodes){
            if (visited.get(node) == false){
                bfs(node, visited, result);
            }
        }

        return result;
    }

    public void bfs(UndirectedGraphNode node, Map<UndirectedGraphNode, Boolean> visited,
                    List<List<Integer>> result){
        List<Integer>row = new ArrayList<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        visited.put(node, true);
        queue.offer(node);
        while (!queue.isEmpty()){
            UndirectedGraphNode u = queue.poll();
            row.add(u.label);
            for (UndirectedGraphNode v : u.neighbors){
                if (visited.get(v) == false){
                    visited.put(v, true);
                    queue.offer(v);
                }
            }
        }
        Collections.sort(row);
        result.add(row);

    }

/*
A------B  C
 \     |  | 
  \    |  |
   \   |  |
    \  |  |
      D   E
Return {A,B,D}, {C,E}. Since there are two connected component which is {A,B,D}, {C,E}
 */

    @Test
    public void test01(){
        ArrayList<UndirectedGraphNode> graph = new ArrayList<>();
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);
        UndirectedGraphNode node5 = new UndirectedGraphNode(5);
        graph.add(node1);
        graph.add(node2);
        graph.add(node3);
        graph.add(node4);
        graph.add(node5);

        node1.neighbors.add(node2); node1.neighbors.add(node4);
        node2.neighbors.add(node1); node2.neighbors.add(node4);
        node3.neighbors.add(node5);
        node4.neighbors.add(node1); node4.neighbors.add(node2);
        node5.neighbors.add(node3);

        System.out.println(connectedSet(graph));

    }

    //------------------------我是分割线--------------------------------------
// 并查集方法
    public class Solution2 {
        class UnionFind {
            HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
            UnionFind(HashSet<Integer> hashSet)
            {
                for (Integer now : hashSet) {
                    father.put(now, now);
                }
            }
            int find(int x)
            {
                int parent = father.get(x);
                while (parent != father.get(parent)) {
                    parent = father.get(parent);
                }
                return parent;
            }
            int compressed_find(int x)
            {
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

            void union(int x, int y)
            {
                int fa_x = find(x);
                int fa_y = find(y);
                if (fa_x != fa_y)
                    father.put(fa_x, fa_y);
            }
        }

        List<List<Integer> > print(HashSet<Integer> hashSet, UnionFind uf, int n) {
            List<List<Integer> > ans = new ArrayList<List<Integer> >();
            HashMap<Integer, List<Integer> > hashMap = new HashMap<Integer, List<Integer> >();
            for (int i : hashSet) {
                int fa = uf.find(i);
                if (!hashMap.containsKey(fa)) {
                    hashMap.put(fa, new ArrayList<Integer>());
                }
                List<Integer> now = hashMap.get(fa);
                now.add(i);
                hashMap.put(fa, now);
            }
            for (List<Integer> now : hashMap.values()) {
                Collections.sort(now);
                ans.add(now);
            }
            return ans;
        }

        public
        List<List<Integer> > connectedSet(ArrayList<UndirectedGraphNode> nodes)
        {
            // Write your code here

            HashSet<Integer> hashSet = new HashSet<Integer>();
            for (UndirectedGraphNode now : nodes) {
                hashSet.add(now.label);
                for (UndirectedGraphNode neighbour : now.neighbors) {
                    hashSet.add(neighbour.label);
                }
            }
            UnionFind uf = new UnionFind(hashSet);

            for (UndirectedGraphNode now : nodes) {

                for (UndirectedGraphNode neighbour : now.neighbors) {
                    int fnow = uf.find(now.label);
                    int fneighbour = uf.find(neighbour.label);
                    if (fnow != fneighbour) {
                        uf.union(now.label, neighbour.label);
                    }
                }
            }

            return print(hashSet, uf, nodes.size());
        }
    }

}
