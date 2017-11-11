package _06_BFS._BFS_Graph;
import java.util.*;

public class _133_BFS_Clone_Graph_M {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    public class Solution {
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) return null;

            UndirectedGraphNode newNode = new UndirectedGraphNode(node.label); //new node for return
            HashMap<Integer, UndirectedGraphNode> map = new HashMap(); //store visited nodes

            map.put(newNode.label, newNode); //add first node to HashMap

            LinkedList<UndirectedGraphNode> queue = new LinkedList(); //to store **original** nodes need to be visited
            queue.add(node); //add first **original** node to queue

            while (!queue.isEmpty()) { //if more nodes need to be visited
                UndirectedGraphNode n = queue.pop(); //search first node in the queue
                for (UndirectedGraphNode neighbor : n.neighbors) {
                    if (!map.containsKey(neighbor.label)) { //add to map and queue if this node hasn't been searched before
                        map.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                        queue.add(neighbor);
                    }
                    map.get(n.label).neighbors.add(map.get(neighbor.label)); //add neighbor to new created nodes
                }
            }

            return newNode;
        }
    }


    public class Solution2 {
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) return null;

            Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap(); // origin node : copied node
            UndirectedGraphNode myNode = new UndirectedGraphNode(node.label); // copy

            map.put(node, myNode);

            Queue<UndirectedGraphNode> q = new ArrayDeque(); // origin nodes
            q.add(node);

            // bfs traverse graph
            while (!q.isEmpty()) {
                UndirectedGraphNode cur = q.poll();
                // all neighbors of current origin node
                for (UndirectedGraphNode neighbor : cur.neighbors) {
                    // if the origin node is not visited
                    if (!map.containsKey(neighbor)) {
                        map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                        // to avoid loop, we just add the unvisited node to queue
                        q.offer(neighbor);
                    }
                    // add neighbors to the copied node
                    // copied node: map.get(cur) -> copied node of cur
                    // neighbors: map.get(neighbor) -> copied node of neighbor
                    map.get(cur).neighbors.add(map.get(neighbor));
                }
            }
            return myNode;
        }
    }

}
