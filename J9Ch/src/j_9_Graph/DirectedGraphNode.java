package j_9_Graph;

import java.util.ArrayList;

public class DirectedGraphNode {
	int label;
	 ArrayList<DirectedGraphNode> neighbors;
	 DirectedGraphNode(int x) { 
		 label = x; 
		 neighbors = new ArrayList<DirectedGraphNode>(); 
		 }
}