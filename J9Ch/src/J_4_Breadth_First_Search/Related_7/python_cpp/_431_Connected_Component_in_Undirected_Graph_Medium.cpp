/**431 Connected Component in Undirected Graph */


/**
 * Definition for Undirected graph.
 * struct UndirectedGraphNode {
 *     int label;
 *     vector<UndirectedGraphNode *> neighbors;
 *     UndirectedGraphNode(int x) : label(x) {};
 * };
 */
map<int, bool> v;
class Solution {
public:
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    void dfs(UndirectedGraphNode* x, vector<int> &tmp) {
        v[x->label] = true;
        tmp.push_back(x->label);
        int n = x->neighbors.size();
        for (int i = 0; i < n; ++i)
        if (v.find(x->neighbors[i]->label) == v.end()) {
            dfs(x->neighbors[i], tmp);
        }
    }

    vector<vector<int>> connectedSet(vector<UndirectedGraphNode*>& nodes) {
        // Write your code here
        vector<vector<int>> result;
        int n = nodes.size();
        for (int i = 0; i < n; ++i)
        if (v.find(nodes[i]->label) == v.end()) {
            vector<int> tmp;
            dfs(nodes[i], tmp);
            sort(tmp.begin(), tmp.end());
            result.push_back(tmp);
        }
        return result;
    }
};

