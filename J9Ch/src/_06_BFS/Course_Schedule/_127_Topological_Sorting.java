package _06_BFS.Course_Schedule;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**127 Topological Sorting
 * Created by tianhuizhu on 6/28/17.
 */
public class _127_Topological_Sorting {

      class DirectedGraphNode {
          int label;
          ArrayList<DirectedGraphNode> neighbors;
          DirectedGraphNode(int x) {
              label = x; neighbors = new ArrayList<DirectedGraphNode>();
          }
          public String toString(){
              return "DirectedGraphNode " + label;
          }
      }


    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        HashMap<DirectedGraphNode, Integer> map = new HashMap();

        //计算入度(除了入度为0的)
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1);
                }
            }
        }

        Queue<DirectedGraphNode> q = new LinkedList<DirectedGraphNode>();

        System.out.println(map);
        //q和result加入入度为0的node
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                q.offer(node);
                result.add(node);
            }
        }

        System.out.println("result " + result);

        while (!q.isEmpty()) {
            DirectedGraphNode node = q.poll();
            for (DirectedGraphNode n : node.neighbors) {
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0) {
                    result.add(n);
                    q.offer(n);
                }
            }
        }
        return result;
    }

    @Test
    public void test01(){
        ArrayList<DirectedGraphNode> graph = new ArrayList<>();
        DirectedGraphNode node0 = new DirectedGraphNode(0);
        DirectedGraphNode node1 = new DirectedGraphNode(1);
        DirectedGraphNode node2 = new DirectedGraphNode(2);
        DirectedGraphNode node3 = new DirectedGraphNode(3);
        DirectedGraphNode node4 = new DirectedGraphNode(4);
        DirectedGraphNode node5 = new DirectedGraphNode(5);

        graph.add(node0);
        graph.add(node1);
        graph.add(node2);
        graph.add(node3);
        graph.add(node4);
        graph.add(node5);

        node0.neighbors.add(node1);node0.neighbors.add(node2);node0.neighbors.add(node3);
        node1.neighbors.add(node4);
        node2.neighbors.add(node4);node2.neighbors.add(node5);
        node3.neighbors.add(node4);node2.neighbors.add(node5);


        ArrayList<DirectedGraphNode> result = topSort(graph);
        for (DirectedGraphNode node: result ) {
            System.out.println(node.label);
        }


    }

}
