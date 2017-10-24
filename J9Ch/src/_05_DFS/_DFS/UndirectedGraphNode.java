package _05_DFS._DFS;

import J_4_BFS.Optional_6._531_Six_Degrees;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
