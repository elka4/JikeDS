

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
     * @param root the root of binary tree.
     * @return an integer
     */
    int ans;
    int dfs(TreeNode* node, int len) {
        if (ans < len)
            ans = len;
        if (node->left)
            dfs(node->left, len + node->left->val);
        if (node->right)
            dfs(node->right, len + node->right->val);
    }
    int maxPathSum2(TreeNode *root) {
        // Write your code here
        if (!root) return 0;
        ans = root->val;
        dfs(root, root->val);
        return ans;
    }
};