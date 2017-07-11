/**480. Binary Tree Paths
 * Easy*/


/**
 * Definition of TreeNode:
 * class TreeNode {
 * public:
 *     int val;
 *     TreeNode *left, *right;
 *     TreeNode(int val) {
 *         this->val = val;
 *         this->left = this->right = NULL;
 *     }
 * }
 */
class Solution {
public:
    /**
     * @param root the root of the binary tree
     * @return all root-to-leaf paths
     */
    vector<string> binaryTreePaths(TreeNode* root) {
        // Write your code here
        vector<string> path;
        if(root == NULL)
            return path;
        vector<vector<int> > pathv;
        unordered_map<TreeNode*, bool> visited;
        stack<TreeNode*> stk;
        stk.push(root);
        visited[root] = true;
        if(root->left == NULL && root->right == NULL)
            save(pathv, stk);
        while(!stk.empty())
        {
            TreeNode* top = stk.top();
            if(top->left && visited[top->left] == false)
            {
                stk.push(top->left);
                visited[top->left] = true;
                if(top->left->left == NULL && top->left->right == NULL)
                    save(pathv, stk);
                continue;
            }
            if(top->right && visited[top->right] == false)
            {
                stk.push(top->right);
                visited[top->right] = true;
                if(top->right->left == NULL && top->right->right == NULL)
                    save(pathv, stk);
                continue;
            }
            stk.pop();
        }
        return convert(pathv);
    }

    void save(vector<vector<int> >& pathv, stack<TreeNode*> stk)
    {
        vector<int> cur;
        while(!stk.empty())
        {
            TreeNode* top = stk.top();
            cur.push_back(top->val);
            stk.pop();
        }
        reverse(cur.begin(), cur.end());
        pathv.push_back(cur);
    }

    vector<string> convert(vector<vector<int> >& pathv)
    {
        vector<string> path;
        for(int i = 0; i < pathv.size(); i ++)
        {
            string cur;
            cur += to_string(pathv[i][0]);
            for(int j = 1; j < pathv[i].size(); j ++)
            {
                cur += "->";
                cur += to_string(pathv[i][j]);
            }
            path.push_back(cur);
        }
        return path;
    }
};