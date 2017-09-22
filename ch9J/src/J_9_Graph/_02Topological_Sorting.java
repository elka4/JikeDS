package J_9_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class _02Topological_Sorting {
/**
 * @param graph: A list of Directed graph node
 * @return: Any topological order for the given graph.
 */    
public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
    // write your code here
    ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
    HashMap<DirectedGraphNode, Integer> indegree = new HashMap<>();

    //遍历图，统计每个node的入度
    for (DirectedGraphNode node : graph) {
        for (DirectedGraphNode neighbor : node.neighbors) {
            if (indegree.containsKey(neighbor)) {
                indegree.put(neighbor, indegree.get(neighbor) + 1);
            } else {
                indegree.put(neighbor, 1);
            }
        }
    }
    Queue<DirectedGraphNode> q = new LinkedList<DirectedGraphNode>();
    //将入度为0的node加进q，同时加入result
    for (DirectedGraphNode node : graph) {
        if (!indegree.containsKey(node)) {
            q.offer(node);
            result.add(node);
        }
    }
    //
    while (!q.isEmpty()) {
        DirectedGraphNode node = q.poll();
        for (DirectedGraphNode n : node.neighbors) {
            indegree.put(n, indegree.get(n) - 1);
            if (indegree.get(n) == 0) {
                result.add(n);
                q.offer(n);
            }
        }
    }
    return result;
}
}

/*Given an directed graph, a topological order of the graph nodes
 *  is defined as follow:
 

For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

 Notice

You can assume that there is at least one topological order in the graph.

Have you met this question in a real interview? Yes
Clarification
Learn more about representation of graphs

Example
For graph as follow:



The topological order can be:

[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]
...
Challenge 
Can you do it in both BFS and DFS?

Tags 
LintCode Copyright Geeks for Geeks Depth First Search Breadth First Search*/