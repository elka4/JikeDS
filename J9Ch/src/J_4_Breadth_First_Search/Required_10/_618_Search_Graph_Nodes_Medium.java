package J_4_Breadth_First_Search.Required_10;
import java.util.*;
/** 618. Search Graph Nodes
 * Medium

 * Created by tianhuizhu on 6/27/17.
 */
public class _618_Search_Graph_Nodes_Medium {
    class UndirectedGraphNode {
      int label;
      ArrayList<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) {
          label = x; neighbors = new ArrayList<UndirectedGraphNode>();
      }
  }
    public class Solution {
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
    }

}
