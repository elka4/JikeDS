

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
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    vector<TreeNode *> stk;
    TreeNode* maxTree(vector<int> A) {
        // write your code here
        int len = A.size();
        for (int i = 0; i < len; ++i) {
            TreeNode *tmp = new TreeNode(A[i]);
            while (stk.size() > 0 && A[i] > stk[stk.size()-1]->val) {
                tmp->left = stk[stk.size()-1];
                stk.pop_back();
            }
            if (stk.size() > 0)
                stk[stk.size()-1]->right = tmp;
            stk.push_back(tmp);
        }
        return stk[0];
    }
};