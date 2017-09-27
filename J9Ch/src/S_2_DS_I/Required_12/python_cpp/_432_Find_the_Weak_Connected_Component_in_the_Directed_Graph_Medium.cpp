

/**
 * Definition for Directed graph.
 * struct DirectedGraphNode {
 *     int label;
 *     vector<DirectedGraphNode *> neighbors;
 *     DirectedGraphNode(int x) : label(x) {};
 * };
 */
map<int, int> f, g;
class Solution {
public:
    /**
     * @param nodes a array of directed graph node
     * @return a connected set of a directed graph
     */
    int find(int x) {
        if (x == f[x])
            return x;

        return f[x] = find(f[x]);
    }

    void merge(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y)
            f[x] = y;
    }

    vector<vector<int>> connectedSet2(vector<DirectedGraphNode*>& nodes) {
        // Write your code here
        vector<vector<int>> result;
        int n = nodes.size();
        for (int i = 0; i < n; ++i) f[nodes[i]->label] = nodes[i]->label;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < nodes[i]->neighbors.size(); ++j) {
                DirectedGraphNode* tmp = nodes[i]->neighbors[j];
                merge(nodes[i]->label, tmp->label);
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i)
        {
            int x = find(nodes[i]->label);
            if (g.find(x) == g.end())
                g[x] = ++cnt ;

            if (result.size() < cnt) {
                vector<int> tmp;
                result.push_back(tmp);
            }
            result[g[x]-1].push_back(nodes[i]->label);
        }
        return result;
    }
};