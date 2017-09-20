package J_4_Breadth_First_Search.Optional_6;

import org.junit.Test;

import java.util.*;
/** 531 Six Degrees
 * Created by tianhuizhu on 6/28/17.
 */
public class _531_Six_Degrees {


     // Definition for Undirected graph.
      class UndirectedGraphNode {
          int label;
          List<UndirectedGraphNode> neighbors;
          UndirectedGraphNode(int x) {
              label = x;
              neighbors = new ArrayList<UndirectedGraphNode>();
          }
      }


    /**
     * @param graph a list of Undirected graph node
     * @param s, t two Undirected graph nodes
     * @return an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph,
                          UndirectedGraphNode s,
                          UndirectedGraphNode t) {
        // Write your code here
        if (s == t)
            return 0;

        Map<UndirectedGraphNode, Integer> visited = new HashMap<UndirectedGraphNode, Integer>();
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();

        queue.offer(s);
        visited.put(s, 0);
        while (!queue.isEmpty()) {
            UndirectedGraphNode node = queue.poll();
            int step = visited.get(node);
            for (int i = 0; i < node.neighbors.size(); i++) {
                if (visited.containsKey(node.neighbors.get(i))) {
                    continue;
                }
                visited.put(node.neighbors.get(i), step + 1);
                queue.offer(node.neighbors.get(i));
                if (node.neighbors.get(i) == t) {
                    return step + 1;
                }
            }
        }

        return -1;
    }

    /*
1------2-----4
 \          /
  \        /
   \--3--/
{1,2,3#2,1,4#3,1,4#4,2,3} and s = 1, t = 4 return 2

Gien a graph:

1      2-----4
             /
           /
          3
{1#2,4#3,4#4,2,3} and s = 1, t = 4 return -1
     */

    @Test
    public void test01(){
        ArrayList<UndirectedGraphNode> graph = new ArrayList<>();
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);
        graph.add(node1);
        graph.add(node2);
        graph.add(node3);
        graph.add(node4);
        node1.neighbors.add(node2); node1.neighbors.add(node3);
        node2.neighbors.add(node1); node2.neighbors.add(node4);
        node4.neighbors.add(node2); node2.neighbors.add(node3);
        node3.neighbors.add(node1); node2.neighbors.add(node4);

        System.out.println(sixDegrees(graph, node1, node4));
        System.out.println(sixDegrees(graph, node1, node2));

    }

}
