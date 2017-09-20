package J_9_Graph;

import java.util.ArrayList;
import java.util.HashMap;

//version 2: two steps
public class _01Clone_Graph_2steps {
public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) {
        return null;
    }

    ArrayList<UndirectedGraphNode> nodes = new ArrayList<>();
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map
    = new HashMap<>();

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
