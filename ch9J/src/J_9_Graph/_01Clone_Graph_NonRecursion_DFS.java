package chapter9_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

//version 3: Non-Recursion DFS


public class _01Clone_Graph_NonRecursion_DFS {
    class StackElement {
        public UndirectedGraphNode node;
        public int neighborIndex;
        public StackElement(UndirectedGraphNode node, int neighborIndex) {
            this.node = node;
            this.neighborIndex = neighborIndex;
        }
    }
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
/*Clone an undirected graph. Each node in the graph contains 
 * a label and a list of its neighbors.

How we serialize an undirected graph:

Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator 
for node label and each neighbor of the node.

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains 
three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), 
thus forming a self-cycle.
Visually, the graph looks like the following:

   1
  / \
 /   \
0 --- 2
     / \
     \_/
Have you met this question in a real interview? Yes
Example
return a deep copied graph.

Tags 
Breadth First Search Facebook
Related Problems 
Medium Six Degrees 30 %
Easy Clone Binary Tree 52 %
Medium Route Between Two Nodes in Graph 36 %*/
