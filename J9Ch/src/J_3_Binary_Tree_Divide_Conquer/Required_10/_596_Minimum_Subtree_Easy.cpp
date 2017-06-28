/** 596. Minimum Subtree
 * Easy*/


class Solution {
public:
    /**
     * @param root the root of binary tree
     * @return the minimun weight node
     */
    TreeNode* findSubtree(TreeNode* root) {
        // Write your code here
        helper(root);
        return result;
    }

    int helper(TreeNode* root) {
        if (root == NULL) {
            return 0;
        }
        int left_weight = helper(root->left);
        int right_weight = helper(root->right);

        if (left_weight + right_weight + root->val <= minumun_weight) {
            minumun_weight = left_weight + right_weight + root->val;
            result = root;
        }
        return left_weight + right_weight + root->val;
    }

private:
    TreeNode* result = nullptr;
    int minumun_weight = INT_MAX;
};