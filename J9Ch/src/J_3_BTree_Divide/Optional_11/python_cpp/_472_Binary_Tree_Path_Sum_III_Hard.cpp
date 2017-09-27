/**
 472
 Binary Tree Path Sum III */


/**
 * Definition of ParentTreeNode:
 * class ParentTreeNode {
 * public:
 *     int val;
 *     ParentTreeNode *parent, *left, *right;
 * }
 */
class Solution {
public:
    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    vector<vector<int>> binaryTreePathSum3(ParentTreeNode *root, int target) {
        // Write your code here
        vector<vector<int>> results;
        dfs(root, target, results);
        return results;
    }

    void dfs(ParentTreeNode *root, int target, vector<vector<int>> &results) {
        if (root == NULL)
            return;

        vector<int> buffer;
        findSum(root, NULL, target, buffer, results);

        dfs(root->left, target, results);
        dfs(root->right, target, results);
    }

    void findSum(ParentTreeNode *root, ParentTreeNode *father, int target,
                 vector<int> &buffer, vector<vector<int>> &results) {
        buffer.push_back(root->val);
        target -= root->val;

        if (target == 0) {
            results.push_back(buffer);
        }

        if (root->parent != NULL && root->parent != father)
            findSum(root->parent, root, target, buffer, results);

        if (root->left != NULL && root->left  != father)
            findSum(root->left, root, target, buffer, results);

        if (root->right != NULL && root->right != father)
            findSum(root->right, root, target, buffer, results);

        buffer.pop_back();
    }
};