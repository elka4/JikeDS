package top100.HighFreq._3Graph;

import java.util.LinkedList;

public class _29Friends {
	class Node{
		LinkedList<Node> neighbors;
		boolean visited;
		Node() {
			visited = false;
			neighbors = new LinkedList<Node>();
		}
	}
	class Travel{
		void DFS(Node node) {
			if(node.visited) return;
			node.visited = true;
			node.neighbors.forEach((neighbor) -> DFS(neighbor)) ;
		}
	}
}
