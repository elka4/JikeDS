package J_4_Breadth_First_Search.all;

import org.junit.Test;

import java.util.*;

/** 137. Clone Graph
 * Medium

 * Created by tianhuizhu on 6/27/17.
 */
public class _137_Clone_Graph {
    class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x; neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }


// version 1: 3 steps
    /**
     * Definition for undirected graph.
     * class UndirectedGraphNode {
     *     int label;
     *     ArrayList<UndirectedGraphNode> neighbors;
     *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     * };
     */

    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }

        // use bfs algorithm to traverse the graph and get all nodes.
        ArrayList<UndirectedGraphNode> nodes = getNodes(node);

        // copy nodes, store the old->new mapping information in a hash map
        HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
        for (UndirectedGraphNode n : nodes) {
            mapping.put(n, new UndirectedGraphNode(n.label));
        }

        // copy neighbors(edges)
        for (UndirectedGraphNode n : nodes) {
            UndirectedGraphNode newNode = mapping.get(n);
            for (UndirectedGraphNode neighbor : n.neighbors) {
                UndirectedGraphNode newNeighbor = mapping.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }

        return mapping.get(node);
    }

    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashSet<UndirectedGraphNode> set = new HashSet<>();

        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            for (UndirectedGraphNode neighbor : head.neighbors) {
                if(!set.contains(neighbor)){
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return new ArrayList<UndirectedGraphNode>(set);
    }

    public  void print(UndirectedGraphNode node) {
        if (null == node) return;
        List<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
        int index = 0;
        if (null != node)nodes.add(node);
        while (index != nodes.size()) {
            UndirectedGraphNode n = nodes.get(index);
            System.out.print(n.label + "(");
            for (UndirectedGraphNode neighbor : n.neighbors) {
                if (!(nodes.contains(neighbor))) {
                    nodes.add(neighbor);
                }
                System.out.print(neighbor.label + ",");
            }
            System.out.println(")");
            index = index + 1;
        }
    }
/*
  1
  / \
 /   \
0 --- 2
     / \
     \_/
 */
    @Test
    public void test01(){
       UndirectedGraphNode node0 = new UndirectedGraphNode(0);
       UndirectedGraphNode node1 = new UndirectedGraphNode(1);
       UndirectedGraphNode node2 = new UndirectedGraphNode(2);


        node0.neighbors.add(node1);
        node0.neighbors.add(node2);
        node1.neighbors.add(node0);
        node1.neighbors.add(node2);
        node2.neighbors.add(node0);
        node2.neighbors.add(node1);
        node2.neighbors.add(node2);


        print(node0);
        UndirectedGraphNode r = cloneGraph(node0);
        print(r);
    }


// version 2: two steps

    /**
     * Definition for undirected graph.
     * class UndirectedGraphNode {
     *     int label;
     *     ArrayList<UndirectedGraphNode> neighbors;
     *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     * };
     */
    public class Solution2 {
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) {
                return null;
            }

            ArrayList<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
            HashMap<UndirectedGraphNode, UndirectedGraphNode> map
                    = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

            // clone nodes
            nodes.add(node);
            map.put(node, new UndirectedGraphNode(node.label));

            int start = 0;
            while (start < nodes.size()) {
                UndirectedGraphNode head = nodes.get(start++);
                for (int i = 0; i < head.neighbors.size(); i++) {
                    UndirectedGraphNode neighbor = head.neighbors.get(i);
                    if (!map.containsKey(neighbor)) {
                        map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                        nodes.add(neighbor);
                    }
                }
            }

            // clone neighbors
            for (int i = 0; i < nodes.size(); i++) {
                UndirectedGraphNode newNode = map.get(nodes.get(i));
                for (int j = 0; j < nodes.get(i).neighbors.size(); j++) {
                    newNode.neighbors.add(map.get(nodes.get(i).neighbors.get(j)));
                }
            }

            return map.get(node);
        }
    }

// version 3: Non-Recursion DFS
    /**
     * Definition for undirected graph.
     * class UndirectedGraphNode {
     *     int label;
     *     ArrayList<UndirectedGraphNode> neighbors;
     *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     * };
     */
    class StackElement {
        public UndirectedGraphNode node;
        public int neighborIndex;
        public StackElement(UndirectedGraphNode node, int neighborIndex) {
            this.node = node;
            this.neighborIndex = neighborIndex;
        }
    }

    public class Solution3 {
        /**
         * @param node: A undirected graph node
         * @return: A undirected graph node
         */
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) {
                return node;
            }

            // use dfs algorithm to traverse the graph and get all nodes.
            ArrayList<UndirectedGraphNode> nodes = getNodes(node);

            // copy nodes, store the old->new mapping information in a hash map
            HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
            for (UndirectedGraphNode n : nodes) {
                mapping.put(n, new UndirectedGraphNode(n.label));
            }

            // copy neighbors(edges)
            for (UndirectedGraphNode n : nodes) {
                UndirectedGraphNode newNode = mapping.get(n);
                for (UndirectedGraphNode neighbor : n.neighbors) {
                    UndirectedGraphNode newNeighbor = mapping.get(neighbor);
                    newNode.neighbors.add(newNeighbor);
                }
            }

            return mapping.get(node);
        }

        private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
            Stack<StackElement> stack = new Stack<StackElement>();
            HashSet<UndirectedGraphNode> set = new HashSet<>();
            stack.push(new StackElement(node, -1));
            set.add(node);

            while (!stack.isEmpty()) {
                StackElement current = stack.peek();
                current.neighborIndex++;
                // there is no more neighbor to traverse for the current node
                if (current.neighborIndex == current.node.neighbors.size()) {
                    stack.pop();
                    continue;
                }

                UndirectedGraphNode neighbor = current.node.neighbors.get(
                        current.neighborIndex
                );
                // check if we visited this neighbor before
                if (set.contains(neighbor)) {
                    continue;
                }

                stack.push(new StackElement(neighbor, -1));
                set.add(neighbor);
            }

            return new ArrayList<UndirectedGraphNode>(set);
        }
    }

}
