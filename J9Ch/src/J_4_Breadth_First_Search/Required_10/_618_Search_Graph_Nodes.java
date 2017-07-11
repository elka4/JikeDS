package J_4_Breadth_First_Search.Required_10;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
/** 618. Search Graph Nodes
 * Medium

 * Created by tianhuizhu on 6/27/17.
 */
public class _618_Search_Graph_Nodes {
    class UndirectedGraphNode {
      int label;
      ArrayList<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) {
          label = x; neighbors = new ArrayList<UndirectedGraphNode>();
      }
  }

    /**
     * @param graph a list of Undirected graph node
     * @param values a hash mapping, <UndirectedGraphNode, (int)value>
     * @param node an Undirected graph node
     * @param target an integer
     * @return the a node
     */
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {
        // Write your code here
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        Set<UndirectedGraphNode> hash = new HashSet<UndirectedGraphNode>();

        queue.offer(node);
        hash.add(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            if (values.get(head) == target) {
                return head;
            }
            for (UndirectedGraphNode nei : head.neighbors) {
                if (!hash.contains(nei)){
                    queue.offer(nei);
                    hash.add(nei);
                }
            }
        }
        return null;
    }
/*
2------3  5
 \     |  |
  \    |  |
   \   |  |
    \  |  |
      1 --4
Give a node 1, target is 50

there a hash named values which is [3,4,10,50,50], represent:
Value of node 1 is 3
Value of node 2 is 4
Value of node 3 is 10
Value of node 4 is 50
Value of node 5 is 50

Return node 4
 */
    @Test
    public void test01(){
        ArrayList<UndirectedGraphNode> graph = new ArrayList<>();
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);
        UndirectedGraphNode node5 = new UndirectedGraphNode(5);

        node1.neighbors.add(node2);node1.neighbors.add(node3);node1.neighbors.add(node4);
        node2.neighbors.add(node1);node2.neighbors.add(node3);
        node3.neighbors.add(node2);node3.neighbors.add(node1);
        node4.neighbors.add(node1);node4.neighbors.add(node5);
        node5.neighbors.add(node4);

        Map<UndirectedGraphNode, Integer> values = new HashMap<>();
        values.put(node1, 3);
        values.put(node2, 4);
        values.put(node3, 10);
        values.put(node4, 50);
        values.put(node5, 50);

        UndirectedGraphNode node ;
        int target = 50;
        UndirectedGraphNode result = searchNode(graph, values,  node1, 50);
        System.out.println(result.label);

    }

}
