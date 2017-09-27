package j_9_Graph;

import java.util.*;

public class _01Clone_Graph_2016 {
public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
	if (node == null) {
		return null;
	}
	
	ArrayList<UndirectedGraphNode> nodes = findAllNodes(node);
	
	//mapping old node to new node
	HashMap<UndirectedGraphNode, UndirectedGraphNode> map =	copyNodes(nodes);
	
	//connect all edges
	connectNeighbors(nodes, map);
	
	return map.get(node);
}

private ArrayList<UndirectedGraphNode> findAllNodes(UndirectedGraphNode node) {
	//BFS
	//always use these two data structures in graph BFS
	Queue<UndirectedGraphNode> queue = new LinkedList<>();
	//use this inQueue to avoid loop
	//all visited nodes are added to inQueue
	HashSet<UndirectedGraphNode> inQueue = new HashSet<>();
	
	ArrayList<UndirectedGraphNode> nodes = new ArrayList<>();
	
	queue.offer(node);
	nodes.add(node);
	inQueue.add(node);
	
	while (!queue.isEmpty()) {
		UndirectedGraphNode head = queue.poll();
		for (UndirectedGraphNode neighbor : head.neighbors) {
			//version 1
			if (!inQueue.contains(neighbor)) {
				queue.offer(neighbor);
				nodes.add(neighbor);
				inQueue.add(neighbor);
			}
			
			//version 2, better logic
			if (inQueue.contains(neighbor)) {
				continue;
			}
			queue.offer(neighbor);
			nodes.add(neighbor);
			inQueue.add(neighbor);
		}
	}
	return nodes;
}

private HashMap<UndirectedGraphNode, UndirectedGraphNode> copyNodes(
		ArrayList<UndirectedGraphNode> nodes
		){
	HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new
			HashMap<UndirectedGraphNode, UndirectedGraphNode>();
	for (UndirectedGraphNode node : nodes) {
		map.put(node, new UndirectedGraphNode(node.label));
	}
	return map;
}

private void connectNeighbors(ArrayList<UndirectedGraphNode> nodes, 
				HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
	//foreach all the node
	for (UndirectedGraphNode node : nodes) {
		//foreach all the neighbor of the node
		for (UndirectedGraphNode neighbor : node.neighbors) {
			UndirectedGraphNode newNode = map.get(node);
			UndirectedGraphNode newNeighbor = map.get(neighbor);
			newNode.neighbors.add(newNeighbor);
		}
	}
}
	
}
