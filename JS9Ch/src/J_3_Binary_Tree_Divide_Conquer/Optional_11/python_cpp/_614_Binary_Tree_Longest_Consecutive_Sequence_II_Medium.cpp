/**
 * 614
 * Binary Tree Longest Consecutive Sequence II
 */


/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left, *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class ResultType {
public:
    int max_length, max_down, max_up;
    ResultType(int len, int down, int up) {
        max_length = len;
        max_down = down;
        max_up = up;
    }
};

class Solution {
public:
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    int longestConsecutive2(TreeNode* root) {
        // Write your code here
        return helper(root).max_length;
    }

    ResultType helper(TreeNode* root) {
        if (root == NULL) {
            return ResultType(0, 0, 0);
        }
        ResultType left = helper(root->left);
        ResultType right = helper(root->right);

        int down = 0, up = 0;
        if (root->left && root->left->val + 1 == root->val)
            down = max(down, left.max_down + 1);

        if (root->left && root->left->val - 1 == root->val)
            up = max(up, left.max_up + 1);

        if (root->right && root->right->val + 1 == root->val)
            down = max(down, right.max_down + 1);

        if (root->right && root->right->val - 1 == root->val)
            up = max(up, right.max_up + 1);

        int len = down + 1 + up;
        len = max(len, max(left.max_length, right.max_length));

        return ResultType(len, down, up);
    }
};