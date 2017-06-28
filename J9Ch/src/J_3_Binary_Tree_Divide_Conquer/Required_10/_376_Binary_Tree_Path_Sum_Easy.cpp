/** 376. Binary Tree Path Sum
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
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    vector<vector<int>> binaryTreePathSum(TreeNode *root, int target) {
        // Write your code here
        vector<vector<int>> result;
        vector<int> path;
        dfs(root, path, result, 0, target);
        return result;
    }

    void dfs(TreeNode *root, vector<int> &path, vector<vector<int>> &result, int len, int target) {
        if (root == NULL)
            return;
        path.push_back(root->val);
        len += root->val;
        if (root->left == NULL && root->right == NULL && len == target)
            result.push_back(path);

        dfs(root->left, path, result, len, target);

        dfs(root->right, path, result, len, target);

        len -= root->val;
        path.pop_back();
    }
};