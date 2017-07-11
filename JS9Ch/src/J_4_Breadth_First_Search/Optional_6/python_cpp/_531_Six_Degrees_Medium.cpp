/** 531 Six Degrees */


/**
 * Definition for undirected graph.
 * struct UndirectedGraphNode {
 *     int label;
 *     vector<UndirectedGraphNode *> neighbors;
 *     UndirectedGraphNode(int x) : label(x) {};
 * };
 */
class Solution {
public:
    /**
     * @param graph a list of Undirected graph node
     * @param s, t two Undirected graph nodes
     * @return an integer
     */
    int sixDegrees(vector<UndirectedGraphNode*> graph,
                   UndirectedGraphNode* s,
                   UndirectedGraphNode* t) {
        // Write your code here
        map<UndirectedGraphNode*, int> visited;
        queue<UndirectedGraphNode*> q;
        q.push(s);
        visited[s] = 0;

        while (!q.empty()) {
            UndirectedGraphNode* x = q.front();
            q.pop();
            if (x == t)
                break;

            for (UndirectedGraphNode* y : x->neighbors)
                if (visited.find(y) == visited.end()) {
                    visited[y] = visited[x] + 1;
                    q.push(y);
                }
        }

        if (visited.find(t) == visited.end())
            return -1;
        else
            return visited[t];
    }
};