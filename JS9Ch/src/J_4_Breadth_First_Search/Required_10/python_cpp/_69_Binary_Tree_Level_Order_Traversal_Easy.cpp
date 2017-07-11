/** 69. Binary Tree Level Order Traversal
 * Easy*/


class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
public:
    vector<vector<int>> levelOrder(TreeNode *root) {
        vector<vector<int>> result;
        if (root == NULL) {
            return result;
        }

        queue<TreeNode *> Q;
        Q.push(root);
        while (!Q.empty()) {
            int size = Q.size();
            vector<int> level;

            for (int i = 0; i < size; i++) {
                TreeNode *head = Q.front(); Q.pop();
                level.push_back(head->val);
                if (head->left != NULL) {
                    Q.push(head->left);
                }
                if (head->right != NULL) {
                    Q.push(head->right);
                }
            }

            result.push_back(level);
        }

        return result;
    }
};