/** 137. Clone Graph
 * Medium*/


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
    UndirectedGraphNode *clone(UndirectedGraphNode *node, map<int,UndirectedGraphNode*> &table)
    {
        if(node==NULL)
            return NULL;

        if(table.find(node->label)!=table.end())
            return table[node->label];

        UndirectedGraphNode *newnode=new UndirectedGraphNode(node->label);
        table[newnode->label]=newnode;

        for(int i=0;i<node->neighbors.size();i++)
        {
            UndirectedGraphNode *neighbor=clone(node->neighbors[i],table);
            newnode->neighbors.push_back(neighbor);
        }

        return newnode;
    }
    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    UndirectedGraphNode *cloneGraph(UndirectedGraphNode *node) {
        map<int,UndirectedGraphNode*> visitTable;
        return clone(node,visitTable);
    }
};