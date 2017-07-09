/**
 246
 Binary Tree Path Sum II
 */


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
    vector<vector<int>> binaryTreePathSum2(TreeNode *root, int target) {
        // Write your code here
        vector<vector<int>> results;
        vector<int> buffer;
        if (root == NULL)
            return results;
        findSum(root, target, buffer, 0, results);
        return results;
    }

    void findSum(TreeNode* head, int sum, vector<int> &buffer, int level,
                 vector<vector<int>> &results) {
        if (head == NULL) return;
        int tmp = sum;
        buffer.push_back(head->val);
        for (int i = level;i >= 0; i--) {
            tmp -= buffer[i];
            if (tmp == 0) {
                vector<int> temp;
                for (int j = i; j <= level; ++j)
                    temp.push_back(buffer[j]);
                results.push_back(temp);
            }
        }
        findSum(head->left, sum, buffer, level + 1, results);
        findSum(head->right, sum, buffer, level + 1, results);
        buffer.pop_back();
    }
};