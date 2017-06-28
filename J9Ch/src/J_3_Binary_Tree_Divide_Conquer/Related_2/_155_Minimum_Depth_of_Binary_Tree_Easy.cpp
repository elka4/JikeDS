/**
155
Minimum Depth of Binary Tree*/


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
     * @param root: The root of binary tree.
     * @return: An integer
     */
    int ans;
    int solve_dp(TreeNode *root) {
        if(root == NULL)
            return 0;

        if (root->left == NULL && root->right == NULL)
            return 1;

        int lf = 0x7fffffff, rt = 0x7fffffff;
        if(root->left)
            lf = solve_dp(root->left);

        if(root->right)
            rt = solve_dp(root->right);

        return min(lf, rt) + 1;
    }
    int minDepth(TreeNode *root) {
        // write your code here
        if (!root) return 0;
        return solve_dp(root);
    }
};